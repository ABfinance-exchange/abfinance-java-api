package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAssetRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.asset.request.AssetDataRequest;
import com.abfinance.api.client.domain.asset.request.AssetCancelWithdrawRequest;
import com.abfinance.api.client.domain.asset.request.SetAssetDepositAccountRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

public class ABFinanceApiAssetRestClientImpl implements ABFinanceApiAssetRestClient {
    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiAssetRestClientImpl(ABFinanceApiService apiService) {
        this.apiService = apiService;
    }

    public ABFinanceApiAssetRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // Asset Endpoints
    @Override
    public Object getAssetCoinInfo(AssetDataRequest request) {
        return executeSync(apiService.getAssetCoinInfo(request.getCoin()));
    }

    @Override
    public Object setAssetDepositAccount(AssetDataRequest request) {
        SetAssetDepositAccountRequest setAssetDepositAccountRequest = converter.mapToSetDepositAccountRequest(request);
        return executeSync(apiService.setAssetDepositAccount(setAssetDepositAccountRequest));
    }

    @Override
    public Object getAssetMasterDepositAddress(AssetDataRequest masterDepositRequest) {
        return executeSync(apiService.getAssetMasterDepositAddress(
                masterDepositRequest.getCoin(),
                masterDepositRequest.getChainType()
        ));
    }

    @Override
    public Object getAssetInternalDepositRecords(AssetDataRequest assetDepositRecordsRequest) {
        return executeSync(apiService.getAssetInternalDepositRecords(
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor())
        );
    }

    @Override
    public Object getAssetDepositRecords(AssetDataRequest assetDepositRecordsRequest) {
        return executeSync(apiService.getAssetDepositRecords(
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor())
        );
    }

    @Override
    public Object getAssetSubMemberDepositAddress(AssetDataRequest subDepositRequest) {
        return executeSync(apiService.getAssetSubMemberDepositAddress(
                subDepositRequest.getCoin(),
                subDepositRequest.getChainType(),
                subDepositRequest.getSubMemberId()
        ));
    }

    @Override
    public Object getAssetSubMembersDepositRecords(AssetDataRequest assetDepositRecordsRequest) {
        return executeSync(apiService.getAssetSubMembersDepositRecords(
                assetDepositRecordsRequest.getSubMemberId(),
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor())
        );
    }

    @Override
    public Object createAssetInternalTransfer(AssetDataRequest assetInternalTransferRequest) {
        var request = converter.mapToAssetInternalTransferRequest(assetInternalTransferRequest);
        return executeSync(apiService.createAssetInternalTransfer(request));
    }

    @Override
    public Object getAssetSingleCoinBalance(AssetDataRequest singleCoinBalanceRequest) {
        return executeSync(converter.getSingleCoinBalance(apiService, singleCoinBalanceRequest));
    }

    @Override
    public Object getAssetAllCoinsBalance(AssetDataRequest allCoinsBalanceRequest) {
        return executeSync(apiService.getAssetAllCoinsBalance(
                allCoinsBalanceRequest.getAccountType() == null ? null : allCoinsBalanceRequest.getAccountType().getAccountTypeValue(),
                allCoinsBalanceRequest.getMemberId(),
                allCoinsBalanceRequest.getCoin(),
                allCoinsBalanceRequest.getWithBonus() == null ? null : String.valueOf(allCoinsBalanceRequest.getWithBonus().getValue()))
        );
    }

    @Override
    public Object getAssetInternalTransferRecords(AssetDataRequest internalTransferRequest) {
        return executeSync(apiService.getAssetInternalTransferRecords(
                internalTransferRequest.getTransferId(),
                internalTransferRequest.getCoin(),
                internalTransferRequest.getTransferStatus() == null ? null : internalTransferRequest.getTransferStatus().getStatus(),
                internalTransferRequest.getStartTime(),
                internalTransferRequest.getEndTime(),
                internalTransferRequest.getLimit(),
                internalTransferRequest.getCursor())
        );
    }

    @Override
    public Object getAssetTransferSubUidList() {
        return executeSync(apiService.getAssetTransferSubUidList());
    }

    @Override
    public Object getAssetTransferableCoins(AssetDataRequest request) {
        return executeSync(apiService.getAssetTransferableCoins(
                request.getFromAccountType() == null ? null : request.getFromAccountType().getAccountTypeValue(),
                request.getToAccountType() == null ? null : request.getToAccountType().getAccountTypeValue()));
    }

    @Override
    public Object getAssetUniversalTransferRecords(AssetDataRequest universalTransferRequest) {
        return executeSync(apiService.getAssetUniversalTransferRecords(
                universalTransferRequest.getTransferId(),
                universalTransferRequest.getCoin(),
                universalTransferRequest.getTransferStatus() == null ? null : universalTransferRequest.getTransferStatus().getStatus(),
                universalTransferRequest.getStartTime(),
                universalTransferRequest.getEndTime(),
                universalTransferRequest.getLimit(),
                universalTransferRequest.getCursor())
        );
    }

    @Override
    public Object createAssetUniversalTransfer(AssetDataRequest assetUniversalTransferRequest) {
        var request = converter.mapToAssetUniversalTransferRequest(assetUniversalTransferRequest);
        return executeSync(apiService.createAssetUniversalTransfer(request));
    }

    @Override
    public Object cancelAssetWithdraw(AssetDataRequest request) {
        AssetCancelWithdrawRequest assetCancelWithdrawRequest = converter.mapToAssetCancelWithdrawRequest(request);
        return executeSync(apiService.cancelAssetWithdraw(assetCancelWithdrawRequest));
    }

    @Override
    public Object createAssetWithdraw(AssetDataRequest assetWithdrawRequest) {
        var request = converter.mapToAssetWithdrawRequest(assetWithdrawRequest);
        return executeSync(apiService.createAssetWithdraw(request));
    }

    @Override
    public Object getAssetWithdrawAddress(AssetDataRequest request) {
        return executeSync(apiService.getAssetWithdrawAddress(
                request.getCoin(),
                request.getChain(),
                request.getLimit(),
                request.getCursor()
        ));
    }

    @Override
    public Object getAssetWithdrawalRecords(AssetDataRequest assetWithdrawRecordsRequest) {
        return executeSync(apiService.getAssetWithdrawalRecords(
                assetWithdrawRecordsRequest.getWithdrawID(),
                assetWithdrawRecordsRequest.getCoin(),
                assetWithdrawRecordsRequest.getWithdrawType() == null ? null : assetWithdrawRecordsRequest.getWithdrawType().getValue(),
                assetWithdrawRecordsRequest.getStartTime(),
                assetWithdrawRecordsRequest.getEndTime(),
                assetWithdrawRecordsRequest.getLimit(),
                assetWithdrawRecordsRequest.getCursor()
        ));
    }

    @Override
    public Object getAssetWithdrawalAmount(AssetDataRequest request) {
        return executeSync(apiService.getAssetWithdrawalAmount(request.getCoin()));
    }

    @Override
    public Object getVaspList() {
        return executeSync(apiService.getVaspList());
    }
}
