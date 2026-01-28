package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.asset.request.AssetDataRequest;

public interface ABFinanceApiAssetRestClient {
    // Asset Endpoints
    Object getAssetCoinInfo(AssetDataRequest request);
    Object setAssetDepositAccount(AssetDataRequest request);
    Object getAssetMasterDepositAddress(AssetDataRequest masterDepositRequest);
    Object getAssetInternalDepositRecords(AssetDataRequest assetDepositRecordsRequest);
    Object getAssetDepositRecords(AssetDataRequest assetDepositRecordsRequest);
    Object getAssetSubMemberDepositAddress(AssetDataRequest subDepositRequest);
    Object getAssetSubMembersDepositRecords(AssetDataRequest assetDepositRecordsRequest);
    Object createAssetInternalTransfer(AssetDataRequest assetInternalTransferRequest);
    Object getAssetSingleCoinBalance(AssetDataRequest singleCoinBalanceRequest);
    Object getAssetAllCoinsBalance(AssetDataRequest allCoinsBalanceRequest);
    Object getAssetInternalTransferRecords(AssetDataRequest internalTransferRequest);
    Object getAssetTransferSubUidList();
    Object getAssetTransferableCoins(AssetDataRequest request);
    Object getAssetUniversalTransferRecords(AssetDataRequest universalTransferRequest);
    Object createAssetUniversalTransfer(AssetDataRequest assetUniversalTransferRequest);
    Object cancelAssetWithdraw(AssetDataRequest request);
    Object createAssetWithdraw(AssetDataRequest assetWithdrawRequest);
    Object getAssetWithdrawAddress(AssetDataRequest request);
    Object getAssetWithdrawalRecords(AssetDataRequest assetWithdrawRecordsRequest);
    Object getAssetWithdrawalAmount(AssetDataRequest request);
    Object getVaspList();
}
