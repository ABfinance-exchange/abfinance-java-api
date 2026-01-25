package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.restApi.ABFinanceApiUserRestClient;
import com.abfinance.api.client.domain.user.UserDataRequest;
import com.abfinance.api.client.domain.user.request.UserSubMemberRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import static com.abfinance.api.client.constant.Helper.listToString;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

/**
 * Implementation of ABFinance's REST API using Retrofit with synchronous/blocking
 * method calls.
 */
public class ABFinanceApiUserRestClientImpl implements ABFinanceApiUserRestClient {
    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiUserRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // User endpoints
    @Override
    public Object getCurrentAPIKeyInfo() {
        return executeSync(apiService.getCurrentAPIKeyInfo());
    }

    @Override
    public Object getSubUIDList() {
        return executeSync(apiService.getSubUIDList());
    }

    @Override
    public Object getSubUIDListUnlimited(UserDataRequest subUserRequest) {
        return executeSync(apiService.getSubUIDListUnlimited(
                subUserRequest.getPageSize(),
                subUserRequest.getNextCursor()
        ));
    }

    @Override
    public Object getSubUIDListUnlimited() {
        return executeSync(apiService.getSubUIDListUnlimited());
    }

    @Override
    public Object getSubAccAllAPIKeyInfo(UserDataRequest subUserRequest) {
        return executeSync(apiService.getSubAccAllAPIKeyInfo(
                subUserRequest.getSubMemberId(),
                subUserRequest.getLimit(),
                subUserRequest.getCursor()
        ));
    }

    @Override
    public Object createSubMember(UserDataRequest request) {
        UserSubMemberRequest subUserRequest = converter.mapToCreateSubMemberRequest(request);
        return executeSync(apiService.createSubMember(subUserRequest));
    }

    @Override
    public Object createSubAPI(UserDataRequest request) {
        var createApiKeyRequest = converter.mapToCreateSubApiRequest(request);
        return executeSync(apiService.createSubAPI(createApiKeyRequest));
    }

    @Override
    public Object freezeSubMember(UserDataRequest request) {
        var freezeSubUIDRquest = converter.mapToFreezeSubUIDRequest(request);
        return executeSync(apiService.freezeSubMember(freezeSubUIDRquest));
    }

    @Override
    public Object getUIDWalletType(UserDataRequest request) {
        return executeSync(apiService.getUIDWalletType(request.getMemberIds() == null ? null : listToString(request.getMemberIds())));
    }

    @Override
    public Object modifyMasterApiKey(UserDataRequest userDataRequest) {
        var modifyMasterApiKeyRequest = converter.mapToModifyMasterApiKeyRequest(userDataRequest);
        return executeSync(apiService.modifyMasterApiKey(modifyMasterApiKeyRequest));
    }

    @Override
    public Object modifySubApiKey(UserDataRequest userDataRequest) {
        var modifySubApiKeyRequest = converter.mapToModifySubApiKeyRequest(userDataRequest);
        return executeSync(apiService.modifySubApiKey(modifySubApiKeyRequest));
    }

    @Override
    public Object deleteMasterApiKey() {
        return executeSync(apiService.deleteMasterApiKey());
    }

    @Override
    public Object deleteSubApiKey(UserDataRequest userDataRequest) {
        var deleteSubApiKeyRequest = converter.mapToDeleteSubApiKeyRequest(userDataRequest);
        return executeSync(apiService.deleteSubApiKey(deleteSubApiKeyRequest));
    }
}
