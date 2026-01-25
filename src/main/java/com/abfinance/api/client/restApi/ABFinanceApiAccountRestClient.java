package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.domain.account.request.BatchSetCollateralCoinRequest;

public interface ABFinanceApiAccountRestClient {
    // Account endpoints
    Object getWalletBalance(AccountDataRequest walletBalanceRequest);
    Object getAccountBorrowHistory(AccountDataRequest borrowHistoryRequest);
    Object setAccountCollateralCoin(AccountDataRequest setCollateralCoinRequest);
    Object batchSetAccountCollateralCoin(BatchSetCollateralCoinRequest batchSetCollateralCoinRequest);
    Object getAccountCollateralInfo(AccountDataRequest request);
    Object getAccountFreeRate(AccountDataRequest getFeeRateRequest);
    Object getAccountInfo();
    Object getTransactionLog(AccountDataRequest getTransactionLogRequest);
    Object getAccountMMPState(AccountDataRequest request);
    Object getAccountSMPGroup();
}
