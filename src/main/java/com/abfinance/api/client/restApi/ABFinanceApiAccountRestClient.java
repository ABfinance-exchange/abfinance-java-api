package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.account.request.AccountDataRequest;

public interface ABFinanceApiAccountRestClient {
    // Account endpoints
    Object getWalletBalance(AccountDataRequest walletBalanceRequest);
    Object getAccountFreeRate(AccountDataRequest getFeeRateRequest);
    Object getAccountInfo();
    Object getTransactionLog(AccountDataRequest getTransactionLogRequest);
    Object getAccountSMPGroup();
    Object getUserSettingConfig();
}
