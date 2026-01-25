package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.domain.account.request.BatchSetCollateralCoinRequest;

public interface ABFinanceApiAsyncAccountRestClient {
    // Account endpoints
    void getWalletBalance(AccountDataRequest walletBalanceRequest, ABFinanceApiCallback<Object> callback);
    void getAccountBorrowHistory(AccountDataRequest borrowHistoryRequest, ABFinanceApiCallback<Object> callback);
    void setAccountCollateralCoin(AccountDataRequest setCollateralCoinRequest, ABFinanceApiCallback<Object> callback);
    void batchSetAccountCollateralCoin(BatchSetCollateralCoinRequest batchSetCollateralCoinRequest, ABFinanceApiCallback<Object> callback);
    void getAccountCollateralInfo(AccountDataRequest request, ABFinanceApiCallback<Object> callback);
    void getAccountFreeRate(AccountDataRequest getFeeRateRequest, ABFinanceApiCallback<Object> callback);
    void getAccountInfo(ABFinanceApiCallback<Object> callback);
    void getTransactionLog(AccountDataRequest getTransactionLogRequest, ABFinanceApiCallback<Object> callback);
    void getAccountMMPState(AccountDataRequest request, ABFinanceApiCallback<Object> callback);
    void getAccountSMPGroup(ABFinanceApiCallback<Object> callback);
}
