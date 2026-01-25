package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.asset.request.AssetDataRequest;

public interface ABFinanceApiAsyncAssetRestClient {
    // Asset Endpoints
    void getAssetCoinInfo(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
    void setAssetDepositAccount(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
    void getAssetMasterDepositAddress(AssetDataRequest masterDepositRequest, ABFinanceApiCallback<Object> callback);
    void getAssetInternalDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback);
    void getAssetDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback);
    void getAssetSubMemberDepositAddress(AssetDataRequest subDepositRequest, ABFinanceApiCallback<Object> callback);
    void getAssetSubMembersDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback);
    void getAssetCoinExchangeRecords(AssetDataRequest assetDataRequest, ABFinanceApiCallback<Object> callback);
    void createAssetInternalTransfer(AssetDataRequest assetInternalTransferRequest, ABFinanceApiCallback<Object> callback);
    void getAssetSingleCoinBalance(AssetDataRequest singleCoinBalanceRequest, ABFinanceApiCallback<Object> callback);
    void getAssetAllCoinsBalance(AssetDataRequest allCoinsBalanceRequest, ABFinanceApiCallback<Object> callback);
    void getAssetInternalTransferRecords(AssetDataRequest internalTransferRequest, ABFinanceApiCallback<Object> callback);
    void getAssetTransferSubUidList(ABFinanceApiCallback<Object> callback);
    void getAssetTransferableCoins(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
    void getAssetUniversalTransferRecords(AssetDataRequest universalTransferRequest, ABFinanceApiCallback<Object> callback);
    void createAssetUniversalTransfer(AssetDataRequest assetUniversalTransferRequest, ABFinanceApiCallback<Object> callback);
    void cancelAssetWithdraw(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
    void createAssetWithdraw(AssetDataRequest assetWithdrawRequest, ABFinanceApiCallback<Object> callback);
    void getAssetWithdrawAddress(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
    void getAssetWithdrawalRecords(AssetDataRequest assetWithdrawRecordsRequest, ABFinanceApiCallback<Object> callback);
    void getAssetWithdrawalAmount(AssetDataRequest request, ABFinanceApiCallback<Object> callback);
}
