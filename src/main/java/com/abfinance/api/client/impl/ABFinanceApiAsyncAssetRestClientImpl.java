package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAsyncAssetRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.asset.request.AssetDataRequest;
import com.abfinance.api.client.domain.asset.request.AssetCancelWithdrawRequest;
import com.abfinance.api.client.domain.asset.request.SetAssetDepositAccountRequest;
import com.abfinance.api.client.service.ABFinanceJsonConverter;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

public class ABFinanceApiAsyncAssetRestClientImpl implements ABFinanceApiAsyncAssetRestClient {

    private final ABFinanceApiService apiService;
    private final ABFinanceJsonConverter converter = new ABFinanceJsonConverter();

    public ABFinanceApiAsyncAssetRestClientImpl(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, apiKey, secret, baseUrl, debugMode, recvWindow, logOption, "");
    }

    // Asset endpoints
    @Override
    public void getAssetCoinInfo(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetCoinInfo(request.getCoin()).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void setAssetDepositAccount(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        SetAssetDepositAccountRequest setAssetDepositAccountRequest = converter.mapToSetDepositAccountRequest(request);
        apiService.setAssetDepositAccount(setAssetDepositAccountRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetMasterDepositAddress(AssetDataRequest masterDepositRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetMasterDepositAddress(
                masterDepositRequest.getCoin(),
                masterDepositRequest.getChainType()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetInternalDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetInternalDepositRecords(
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetDepositRecords(
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetSubMemberDepositAddress(AssetDataRequest subDepositRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetSubMemberDepositAddress(
                subDepositRequest.getCoin(),
                subDepositRequest.getChainType(),
                subDepositRequest.getSubMemberId()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetSubMembersDepositRecords(AssetDataRequest assetDepositRecordsRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetSubMembersDepositRecords(
                assetDepositRecordsRequest.getSubMemberId(),
                assetDepositRecordsRequest.getCoin(),
                assetDepositRecordsRequest.getStartTime(),
                assetDepositRecordsRequest.getEndTime(),
                assetDepositRecordsRequest.getLimit(),
                assetDepositRecordsRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetCoinExchangeRecords(AssetDataRequest coinExchangeRecordsRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetCoinExchangeRecords(
                coinExchangeRecordsRequest.getFromCoin(),
                coinExchangeRecordsRequest.getToCoin(),
                coinExchangeRecordsRequest.getLimit(),
                coinExchangeRecordsRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createAssetInternalTransfer(AssetDataRequest assetInternalTransferRequest, ABFinanceApiCallback<Object> callback) {
        var request = converter.mapToAssetInternalTransferRequest(assetInternalTransferRequest);
        apiService.createAssetInternalTransfer(request).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetSingleCoinBalance(AssetDataRequest singleCoinBalanceRequest, ABFinanceApiCallback<Object> callback) {
        converter.getSingleCoinBalance(apiService, singleCoinBalanceRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetAllCoinsBalance(AssetDataRequest allCoinsBalanceRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetAllCoinsBalance(
                allCoinsBalanceRequest.getAccountType() == null ? null : allCoinsBalanceRequest.getAccountType().getAccountTypeValue(),
                allCoinsBalanceRequest.getMemberId(),
                allCoinsBalanceRequest.getCoin(),
                allCoinsBalanceRequest.getWithBonus() == null ? null : String.valueOf(allCoinsBalanceRequest.getWithBonus().getValue())
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetInternalTransferRecords(AssetDataRequest internalTransferRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetInternalTransferRecords(
                internalTransferRequest.getTransferId(),
                internalTransferRequest.getCoin(),
                internalTransferRequest.getTransferStatus() == null ? null : internalTransferRequest.getTransferStatus().getStatus(),
                internalTransferRequest.getStartTime(),
                internalTransferRequest.getEndTime(),
                internalTransferRequest.getLimit(),
                internalTransferRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetTransferSubUidList(ABFinanceApiCallback<Object> callback) {
        apiService.getAssetTransferSubUidList().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetTransferableCoins(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetTransferableCoins(
                request.getFromAccountType() == null ? null : request.getFromAccountType().getAccountTypeValue(),
                request.getToAccountType() == null ? null : request.getToAccountType().getAccountTypeValue()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetUniversalTransferRecords(AssetDataRequest universalTransferRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetUniversalTransferRecords(
                universalTransferRequest.getTransferId(),
                universalTransferRequest.getCoin(),
                universalTransferRequest.getTransferStatus() == null ? null : universalTransferRequest.getTransferStatus().getStatus(),
                universalTransferRequest.getStartTime(),
                universalTransferRequest.getEndTime(),
                universalTransferRequest.getLimit(),
                universalTransferRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createAssetUniversalTransfer(AssetDataRequest assetUniversalTransferRequest, ABFinanceApiCallback<Object> callback) {
        var request = converter.mapToAssetUniversalTransferRequest(assetUniversalTransferRequest);
        apiService.createAssetUniversalTransfer(request).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelAssetWithdraw(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        AssetCancelWithdrawRequest assetCancelWithdrawRequest = converter.mapToAssetCancelWithdrawRequest(request);
        apiService.cancelAssetWithdraw(assetCancelWithdrawRequest).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void createAssetWithdraw(AssetDataRequest assetWithdrawRequest, ABFinanceApiCallback<Object> callback) {
        var request = converter.mapToAssetWithdrawRequest(assetWithdrawRequest);
        apiService.createAssetWithdraw(request).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetWithdrawAddress(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetWithdrawAddress(
                request.getCoin(),
                request.getChain(),
                request.getLimit(),
                request.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetWithdrawalRecords(AssetDataRequest assetWithdrawRecordsRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetWithdrawalRecords(
                assetWithdrawRecordsRequest.getWithdrawID(),
                assetWithdrawRecordsRequest.getCoin(),
                assetWithdrawRecordsRequest.getWithdrawType() == null ? null : assetWithdrawRecordsRequest.getWithdrawType().getValue(),
                assetWithdrawRecordsRequest.getStartTime(),
                assetWithdrawRecordsRequest.getEndTime(),
                assetWithdrawRecordsRequest.getLimit(),
                assetWithdrawRecordsRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAssetWithdrawalAmount(AssetDataRequest request, ABFinanceApiCallback<Object> callback) {
        apiService.getAssetWithdrawalAmount(request.getCoin()).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
