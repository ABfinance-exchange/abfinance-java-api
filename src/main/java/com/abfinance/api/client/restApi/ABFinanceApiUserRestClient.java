package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.user.UserDataRequest;

public interface ABFinanceApiUserRestClient {
    // User Data
    Object createSubMember(UserDataRequest subUserRequest);
    Object createSubAPI(UserDataRequest createApiKeyRequest);
    Object getSubUIDList();
    Object freezeSubMember(UserDataRequest freezeSubUIDRequest);
    Object getCurrentAPIKeyInfo();
    Object getUIDWalletType(UserDataRequest userDataRequest);
    Object modifyMasterApiKey(UserDataRequest userDataRequest);
    Object modifySubApiKey(UserDataRequest userDataRequest);
    Object deleteMasterApiKey();
    Object deleteSubApiKey(UserDataRequest userDataRequest);
    Object getSubUIDListUnlimited(UserDataRequest subUserRequest);
    Object getSubUIDListUnlimited();
    Object getSubAccAllAPIKeyInfo(UserDataRequest subUserRequest);
}
