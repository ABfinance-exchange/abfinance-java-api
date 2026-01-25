package com.abfinance.api.client.security;

import com.abfinance.api.client.constant.ABFinanceApiConstants;
import com.abfinance.api.client.constant.Helper;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.abfinance.api.client.security.HmacSHA256Signer.sign;

/**
 * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;

    private final String secret;

    private final Long recvWindow;

    private final String referer;

    public AuthenticationInterceptor(String apiKey, String secret, Long recvWindow, String referer) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.recvWindow = recvWindow;
        this.referer = referer;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        boolean isSignatureRequired = original.header(ABFinanceApiConstants.SIGN_TYPE_HEADER) != null;

        // Endpoint requires signing the payload
        String payload = "";
        if ("GET".equals(original.method())) {
            payload = original.url().encodedQuery(); // extract query params
            newRequestBuilder.get();
        }else if ("POST".equals(original.method()) && original.body() != null) {
            Buffer buffer = new Buffer();
            original.body().writeTo(buffer);
            payload = buffer.readString(StandardCharsets.UTF_8);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(payload, mediaType);
            newRequestBuilder.post(body);
        }

        if (isSignatureRequired) {
            long timestamp = Helper.generateTimestamp();
            String signature = sign(apiKey, secret, StringUtils.isEmpty(payload) ? ""  : payload, String.valueOf(timestamp), String.valueOf(recvWindow));
            newRequestBuilder.addHeader(ABFinanceApiConstants.API_KEY_HEADER, apiKey);
            newRequestBuilder.addHeader(ABFinanceApiConstants.SIGN_HEADER, signature);
            newRequestBuilder.addHeader(ABFinanceApiConstants.TIMESTAMP_HEADER, String.valueOf(timestamp));
            newRequestBuilder.addHeader(ABFinanceApiConstants.RECV_WINDOW_HEADER, String.valueOf(recvWindow));
            newRequestBuilder.addHeader(ABFinanceApiConstants.API_CONTENT_TYPE, ABFinanceApiConstants.DEFAULT_CONTENT_TYPE);
            newRequestBuilder.addHeader(ABFinanceApiConstants.USER_AGENT_HEADER, ABFinanceApiConstants.AGENT_NAME + "/" + ABFinanceApiConstants.VERSION);
            newRequestBuilder.addHeader(ABFinanceApiConstants.CONNECTION_HEADER, ABFinanceApiConstants.KEEP_ALIVE);
            if(StringUtils.isNotEmpty(referer))newRequestBuilder.addHeader(ABFinanceApiConstants.BROKER_HEADER, referer);
        }
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AuthenticationInterceptor that = (AuthenticationInterceptor) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, secret);
    }
}