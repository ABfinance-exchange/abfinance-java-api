package com.abfinance.api.client.websocket.httpclient;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import static com.abfinance.api.client.log.Slf4jLoggingInterceptor.HandleLoggingInterceptor;

@Getter
public final class WebSocketStreamHttpClientSingleton {
    private final boolean debugMode;
    private final String logOption;
    private final OkHttpClient okHttpClient;

    private WebSocketStreamHttpClientSingleton(boolean debugMode, String logOption) {
        this.debugMode = debugMode;
        this.logOption = logOption;
        this.okHttpClient = createOkHttpClient(debugMode, logOption);
    }

    public static WebSocketStreamHttpClientSingleton createInstance(boolean debugMode, String logOption) {
        return new WebSocketStreamHttpClientSingleton(debugMode, logOption);
    }

    private static OkHttpClient createOkHttpClient(boolean debugMode, String logOption) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (debugMode) {
            HandleLoggingInterceptor(clientBuilder, logOption);
        }
        return clientBuilder.build();
    }

    public WebSocket createWebSocket(String url, WebSocketListener listener) {
        Request request = new Request.Builder().url(url).build();
        return okHttpClient.newWebSocket(request, listener);
    }
}
