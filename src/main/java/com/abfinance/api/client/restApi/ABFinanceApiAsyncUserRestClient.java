package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.user.UserDataRequest;

/**
 * ABFinance API facade, supporting asynchronous/non-blocking access ABFinance's REST API.
 */
public interface ABFinanceApiAsyncUserRestClient {
    // User Data
    void createSubMember(UserDataRequest subUserRequest, ABFinanceApiCallback<Object> callback);
    void createSubAPI(UserDataRequest createApiKeyRequest, ABFinanceApiCallback<Object> callback);
    void getSubUIDList(ABFinanceApiCallback<Object> callback);
    void freezeSubMember(UserDataRequest freezeSubUIDRequest, ABFinanceApiCallback<Object> callback);
    void getCurrentAPIKeyInfo(ABFinanceApiCallback<Object> callback);
    void getUIDWalletType(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback);
    void modifyMasterApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback);
    void modifySubApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback);
    void deleteMasterApiKey(ABFinanceApiCallback<Object> callback);
    void deleteSubApiKey(UserDataRequest userDataRequest, ABFinanceApiCallback<Object> callback);
    void getSubUIDListUnlimited(UserDataRequest subUserRequest, ABFinanceApiCallback<Object> callback);
    void getSubUIDListUnlimited(ABFinanceApiCallback<Object> callback);
    void getSubAccAllAPIKeyInfo(UserDataRequest subUserRequest, ABFinanceApiCallback<Object> callback);
}
