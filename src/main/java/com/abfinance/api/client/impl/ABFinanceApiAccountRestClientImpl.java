package com.abfinance.api.client.impl;

import com.abfinance.api.client.domain.account.request.BatchSetCollateralCoinRequest;
import com.abfinance.api.client.restApi.ABFinanceApiAccountRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

public class ABFinanceApiAccountRestClientImpl implements ABFinanceApiAccountRestClient {
    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiAccountRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // Account endpoints
    @Override
    public Object getWalletBalance(AccountDataRequest walletBalanceRequest) {
        return executeSync(apiService.getWalletBalance(
                walletBalanceRequest.getAccountType() == null ? null : walletBalanceRequest.getAccountType().getAccountTypeValue(),
                walletBalanceRequest.getCoins()
        ));
    }

    @Override
    public Object getAccountBorrowHistory(AccountDataRequest borrowHistoryRequest) {
        return executeSync(apiService.getAccountBorrowHistory(
                borrowHistoryRequest.getCurrency(),
                borrowHistoryRequest.getStartTime(),
                borrowHistoryRequest.getEndTime(),
                borrowHistoryRequest.getLimit(),
                borrowHistoryRequest.getCursor()
        ));
    }

    @Override
    public Object setAccountCollateralCoin(AccountDataRequest setCollateralCoinRequest) {
        var request = converter.mapToSetCollateralCoinRequest(setCollateralCoinRequest);
        return executeSync(apiService.setAccountCollateralCoin(request));
    }

    @Override
    public Object batchSetAccountCollateralCoin(BatchSetCollateralCoinRequest batchSetCollateralCoinRequest) {
        return executeSync(apiService.batchSetAccountCollateralCoin(batchSetCollateralCoinRequest));
    }

    @Override
    public Object getAccountCollateralInfo(AccountDataRequest request) {
        return executeSync((apiService.getAccountCollateralInfo(
                request.getCurrency()
        )));
    }

    @Override
    public Object getAccountFreeRate(AccountDataRequest getFeeRateRequest) {
        return executeSync(apiService.getAccountFreeRate(
                getFeeRateRequest.getCategory().getCategoryTypeId(),
                getFeeRateRequest.getSymbol(),
                getFeeRateRequest.getBaseCoin()
        ));
    }

    @Override
    public Object getAccountInfo() {
        return executeSync(apiService.getAccountInfo());
    }

    @Override
    public Object getTransactionLog(AccountDataRequest getTransactionLogRequest) {
        return executeSync(apiService.getUtaTransactionLog(
                getTransactionLogRequest.getAccountType() == null ? null : getTransactionLogRequest.getAccountType().getAccountTypeValue(),
                getTransactionLogRequest.getCategory() == null ? null : getTransactionLogRequest.getCategory().getCategoryTypeId(),
                getTransactionLogRequest.getCurrency(),
                getTransactionLogRequest.getBaseCoin(),
                null,
                getTransactionLogRequest.getStartTime(),
                getTransactionLogRequest.getEndTime(),
                getTransactionLogRequest.getLimit(),
                getTransactionLogRequest.getCursor()
        ));
    }

    @Override
    public Object getAccountMMPState(AccountDataRequest request) {
        return executeSync(apiService.getAccountMMPState(request.getBaseCoin()));
    }

    @Override
    public Object getAccountSMPGroup() {
        return executeSync(apiService.getAccountSMPGroupId());
    }
}
