package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAsyncUserRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.user.UserDataRequest;
import com.abfinance.api.client.domain.user.request.UserSubMemberRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import static com.abfinance.api.client.constant.Helper.listToString;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

/**
 * Implementation of ABFinance's REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class ABFinanceApiAsyncUserRestClientImpl implements ABFinanceApiAsyncUserRestClient {

    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiAsyncUserRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // User endpoints
    @Override
    public void createSubMember(UserDataRequest request, ABFinanceApiCallback<Object> callback) {
        UserSubMemberRequest subUserRequest = converter.mapToCreateSubMemberRequest(request);
        apiService.createSubMember(subUserRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createSubAPI(UserDataRequest request, ABFinanceApiCallback<Object> callback) {
        var createApiKeyRequest = converter.mapToCreateSubApiRequest(request);
        apiService.createSubAPI(createApiKeyRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getSubUIDList(ABFinanceApiCallback<Object> callback) {
        apiService.getSubUIDList().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void freezeSubMember(UserDataRequest request, ABFinanceApiCallback<Object> callback) {
        var freezeSubUIDRquest = converter.mapToFreezeSubUIDRequest(request);
        apiService.freezeSubMember(freezeSubUIDRquest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getCurrentAPIKeyInfo(ABFinanceApiCallback<Object> callback) {
        apiService.getCurrentAPIKeyInfo().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getUIDWalletType(UserDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getUIDWalletType(request.getMemberIds() == null ? null : listToString(request.getMemberIds())).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void modifyMasterApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback) {
        var modifyMasterApiKeyRequest = converter.mapToModifyMasterApiKeyRequest(userDataRequest);
        apiService.modifyMasterApiKey(modifyMasterApiKeyRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void modifySubApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback) {
        var modifySubApiKeyRequest = converter.mapToModifySubApiKeyRequest(userDataRequest);
        apiService.modifySubApiKey(modifySubApiKeyRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void deleteMasterApiKey(ABFinanceApiCallback<Object> callback) {
        apiService.deleteMasterApiKey().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void deleteSubApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback) {
        var deleteSubApiKeyRequest = converter.mapToDeleteSubApiKeyRequest(userDataRequest);
        apiService.deleteSubApiKey(deleteSubApiKeyRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getSubUIDListUnlimited(UserDataRequest subUserRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getSubUIDListUnlimited(
                subUserRequest.getPageSize(),
                subUserRequest.getNextCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getSubUIDListUnlimited(ABFinanceApiCallback<Object> callback) {
        apiService.getSubUIDListUnlimited().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getSubAccAllAPIKeyInfo(UserDataRequest subUserRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getSubAccAllAPIKeyInfo(
                subUserRequest.getSubMemberId(),
                subUserRequest.getLimit(),
                subUserRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
