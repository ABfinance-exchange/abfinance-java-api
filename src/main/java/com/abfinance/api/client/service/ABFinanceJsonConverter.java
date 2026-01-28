package com.abfinance.api.client.service;

import com.abfinance.api.client.constant.Helper;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.TradeOrderType;
import com.abfinance.api.client.domain.TriggerBy;
import com.abfinance.api.client.domain.asset.request.AssetDataRequest;
import com.abfinance.api.client.domain.asset.request.*;
import com.abfinance.api.client.domain.trade.*;
import com.abfinance.api.client.domain.trade.request.*;
import com.abfinance.api.client.exception.ABFinanceApiException;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.abfinance.api.client.constant.Helper.generateTransferID;

public class ABFinanceJsonConverter {
    private final ObjectMapper mapper = new ObjectMapper();

    // Private Methods
    private TradeOrderRequest getTradeOrderRequest(Map<String, Object> orderMap) {
        String category = orderMap.containsKey("category") ? orderMap.get("category").toString() : null;
        if (StringUtils.isEmpty(category)) throw new ABFinanceApiException("Please set category for your order");
        return getTradeOrderRequest(orderMap, category);
    }

    private TradeOrderRequest getTradeOrderRequest(Map<String, Object> orderMap, String category) {
        String orderTypeValue = (String) orderMap.get("orderType");
        TradeOrderType currentOrderType = TradeOrderType.valueOf(orderTypeValue.toUpperCase());
        TimeInForce defaultTimeInForce = (currentOrderType == TradeOrderType.MARKET) ? TimeInForce.IMMEDIATE_OR_CANCEL : TimeInForce.GOOD_TILL_CANCEL;

        return TradeOrderRequest.builder()
                .category(CategoryType.valueOf(category.toUpperCase()))
                .symbol((String) orderMap.get("symbol"))
                .side(Side.valueOf(orderMap.get("side").toString().toUpperCase()))
                .orderType(currentOrderType)
                .qty((String) orderMap.get("qty"))
                .price(orderMap.containsKey("price") ? orderMap.get("price").toString() : null)
                .orderId((String) orderMap.getOrDefault("orderId", null))
                .orderLinkId((String) orderMap.getOrDefault("orderLinkId", null))
                .triggerDirection((Integer) orderMap.getOrDefault("triggerDirection", null))
                .orderFilter(orderMap.containsKey("orderFilter") ? OrderFilter.valueOf(orderMap.get("orderFilter").toString().toUpperCase()) : null)
                .triggerPrice((String) orderMap.getOrDefault("triggerPrice", null))
                .triggerBy(orderMap.containsKey("triggerBy") ? TriggerBy.valueOf(orderMap.get("triggerBy").toString().toUpperCase()) : null)
                .orderIv((String) orderMap.getOrDefault("orderIv", null))
                .timeInForce(TimeInForce.valueOf(orderMap.getOrDefault("timeInForce", defaultTimeInForce).toString().toUpperCase()))
                .positionIdx(orderMap.containsKey("positionIdx") ? getOrderPositionIndexFromString(orderMap.get("positionIdx").toString()) : null)
                .takeProfit((String) orderMap.getOrDefault("takeProfit", null))
                .stopLoss((String) orderMap.getOrDefault("stopLoss", null))
                .tpTriggerBy(orderMap.containsKey("tpTriggerBy") ? TriggerBy.valueOf(orderMap.get("tpTriggerBy").toString().toUpperCase()) : TriggerBy.LAST_PRICE)
                .slTriggerBy(orderMap.containsKey("slTriggerBy") ? TriggerBy.valueOf(orderMap.get("slTriggerBy").toString().toUpperCase()) : TriggerBy.LAST_PRICE)
                .reduceOnly((Boolean) orderMap.getOrDefault("reduceOnly", false))
                .closeOnTrigger((Boolean) orderMap.getOrDefault("closeOnTrigger", false))
                .smpType(orderMap.containsKey("smpType") ? SmpType.valueOf(orderMap.get("smpType").toString().toUpperCase()) : null)
                .mmp((Boolean) orderMap.getOrDefault("mmp", null))
                .tpslMode((String) orderMap.getOrDefault("tpslMode", null))
                .tpLimitPrice((String) orderMap.getOrDefault("tpLimitPrice", null))
                .slLimitPrice((String) orderMap.getOrDefault("slLimitPrice", null))
                .tpOrderType(orderMap.containsKey("tpOrderType") ? TradeOrderType.valueOf(orderMap.get("tpOrderType").toString().toUpperCase()) : null)
                .slOrderType(orderMap.containsKey("slOrderType") ? TradeOrderType.valueOf(orderMap.get("slOrderType").toString().toUpperCase()) : null)
                .marketUnit((String) orderMap.getOrDefault("marketUnit", null))
                .build();
    }

    private PositionIdx getOrderPositionIndexFromString(String positionIdxStr) {
        if (positionIdxStr == null) return null;
        try {
            int index = Integer.parseInt(positionIdxStr);
            for (PositionIdx pi : PositionIdx.values()) {
                if (pi.getIndex() == index) return pi;
            }
        } catch (NumberFormatException e) {
            try {
                return PositionIdx.valueOf(positionIdxStr.toUpperCase());
            } catch (IllegalArgumentException ex) {
                return null;
            }
        }
        return null;
    }

    public PlaceOrderRequest mapToPlaceOrderRequest(TradeOrderRequest tradeOrderRequest) {
        return PlaceOrderRequest.builder()
                .category(tradeOrderRequest.getCategory() == null ? null : tradeOrderRequest.getCategory().getCategoryTypeId())
                .symbol(tradeOrderRequest.getSymbol())
                .isLeverage(tradeOrderRequest.getIsLeverage())
                .side(tradeOrderRequest.getSide() == null ? null : tradeOrderRequest.getSide().getTransactionSide())
                .orderType(tradeOrderRequest.getOrderType() == null ? null : tradeOrderRequest.getOrderType().getOType())
                .qty(tradeOrderRequest.getQty())
                .price(tradeOrderRequest.getPrice())
                .triggerDirection(tradeOrderRequest.getTriggerDirection())
                .orderFilter(tradeOrderRequest.getOrderFilter() == null ? null : tradeOrderRequest.getOrderFilter().getOrderFilterType())
                .triggerPrice(tradeOrderRequest.getTriggerPrice())
                .triggerBy(tradeOrderRequest.getTriggerBy() == null ? null : tradeOrderRequest.getTriggerBy().getTrigger())
                .orderIv(tradeOrderRequest.getOrderIv())
                .timeInForce(tradeOrderRequest.getTimeInForce() == null ? null : tradeOrderRequest.getTimeInForce().getDescription())
                .positionIdx(tradeOrderRequest.getPositionIdx() == null ? null : tradeOrderRequest.getPositionIdx().getIndex())
                .orderLinkId(tradeOrderRequest.getOrderLinkId())
                .takeProfit(tradeOrderRequest.getTakeProfit())
                .stopLoss(tradeOrderRequest.getStopLoss())
                .tpTriggerBy(tradeOrderRequest.getTpTriggerBy() == null ? null : TriggerBy.LAST_PRICE.getTrigger())
                .slTriggerBy(tradeOrderRequest.getSlTriggerBy() == null ? null : TriggerBy.LAST_PRICE.getTrigger())
                .reduceOnly(tradeOrderRequest.getReduceOnly())
                .closeOnTrigger(tradeOrderRequest.getCloseOnTrigger())
                .smpType(tradeOrderRequest.getSmpType() == null ? null : tradeOrderRequest.getSmpType().getDescription())
                .mmp(tradeOrderRequest.getMmp())
                .tpslMode(tradeOrderRequest.getTpslMode())
                .tpLimitPrice(tradeOrderRequest.getTpLimitPrice())
                .slLimitPrice(tradeOrderRequest.getSlLimitPrice())
                .tpOrderType(tradeOrderRequest.getTpOrderType() == null ? null : tradeOrderRequest.getTpOrderType().getOType())
                .slOrderType(tradeOrderRequest.getSlOrderType() == null ? null : tradeOrderRequest.getSlOrderType().getOType())
                .marketUnit(tradeOrderRequest.getMarketUnit())
                .build();
    }

    public SetDcpRequest convertMapToDcpRequest(TradeOrderRequest tradeOrderRequest) {
        return SetDcpRequest.builder().timeWindow(tradeOrderRequest.getTimeWindow()).build();
    }

    // Trade request converters - aliases for consistency
    public PlaceOrderRequest convertTradeToPlaceOrderRequest(TradeOrderRequest tradeOrderRequest) {
        return mapToPlaceOrderRequest(tradeOrderRequest);
    }

    public TradeOrderRequest convertMapToSingleOrderRequest(Map<String, Object> orderMap) {
        return getTradeOrderRequest(orderMap);
    }

    public TradeOrderRequest convertJsonToSingleOrderRequest(String json) throws IOException {
        Map<String, Object> orderMap = mapper.readValue(json, Map.class);
        return getTradeOrderRequest(orderMap);
    }

    public PlaceBatchOrderRequest convertToPlaceBatchOrderRequest(BatchOrderRequest batchOrderRequest) {
        List<PlaceOrderRequest> requests = new ArrayList<>();
        for (TradeOrderRequest order : batchOrderRequest.getRequest()) {
            requests.add(mapToPlaceOrderRequest(order));
        }
        return PlaceBatchOrderRequest.builder()
                .category(batchOrderRequest.getCategory())
                .request(requests)
                .build();
    }

    public BatchOrderRequest convertMapToBatchOrderRequest(Map<String, Object> payload) {
        String category = (String) payload.get("category");
        List<Map<String, Object>> orders = (List<Map<String, Object>>) payload.get("request");
        List<TradeOrderRequest> requests = new ArrayList<>();
        for (Map<String, Object> orderMap : orders) {
            requests.add(getTradeOrderRequest(orderMap, category));
        }
        return BatchOrderRequest.builder()
                .category(CategoryType.valueOf(category.toUpperCase()))
                .request(requests)
                .build();
    }

    public BatchOrderRequest jsonToBatchOrderRequest(String json) throws IOException {
        Map<String, Object> payload = mapper.readValue(json, Map.class);
        return convertMapToBatchOrderRequest(payload);
    }

    public AmendBatchOrderRequest convertToAmendBatchOrderRequest(BatchOrderRequest batchOrderRequest) {
        List<AmendOrderRequest> requests = new ArrayList<>();
        for (TradeOrderRequest order : batchOrderRequest.getRequest()) {
            requests.add(convertTradeToAmendOrderRequest(order));
        }
        return AmendBatchOrderRequest.builder()
                .category(batchOrderRequest.getCategory())
                .request(requests)
                .build();
    }

    public CancelBatchOrderRequest convertToCancelBatchOrderRequest(BatchOrderRequest batchOrderRequest) {
        List<CancelOrderRequest> requests = new ArrayList<>();
        for (TradeOrderRequest order : batchOrderRequest.getRequest()) {
            requests.add(convertTradeToCancelOrderRequest(order));
        }
        return CancelBatchOrderRequest.builder()
                .category(batchOrderRequest.getCategory())
                .request(requests)
                .build();
    }

    public CancelOrderRequest convertTradeToCancelOrderRequest(TradeOrderRequest order) {
        return CancelOrderRequest.builder()
                .category(order.getCategory() == null ? null : order.getCategory().getCategoryTypeId())
                .symbol(order.getSymbol())
                .orderId(order.getOrderId())
                .orderLinkId(order.getOrderLinkId())
                .orderFilter(order.getOrderFilter() == null ? null : order.getOrderFilter().getOrderFilterType())
                .build();
    }

    public CancelAllOrdersRequest convertTradeToCancelAllOrdersRequest(TradeOrderRequest order) {
        return CancelAllOrdersRequest.builder()
                .category(order.getCategory() == null ? null : order.getCategory().getCategoryTypeId())
                .symbol(order.getSymbol())
                .baseCoin(order.getBaseCoin())
                .settleCoin(order.getSettleCoin())
                .orderFilter(order.getOrderFilter() == null ? null : order.getOrderFilter().getOrderFilterType())
                .stopOrderType(order.getStopOrderType() == null ? null : order.getStopOrderType().getDescription())
                .build();
    }

    public AmendOrderRequest convertTradeToAmendOrderRequest(TradeOrderRequest order) {
        return AmendOrderRequest.builder()
                .category(order.getCategory() == null ? null : order.getCategory().getCategoryTypeId())
                .symbol(order.getSymbol())
                .orderId(order.getOrderId())
                .orderLinkId(order.getOrderLinkId())
                .orderIv(order.getOrderIv())
                .triggerPrice(order.getTriggerPrice())
                .qty(order.getQty())
                .price(order.getPrice())
                .tpslMode(order.getTpslMode())
                .takeProfit(order.getTakeProfit())
                .stopLoss(order.getStopLoss())
                .tpTriggerBy(order.getTpTriggerBy() == null ? null : order.getTpTriggerBy().getTrigger())
                .slTriggerBy(order.getSlTriggerBy() == null ? null : order.getSlTriggerBy().getTrigger())
                .triggerBy(order.getTriggerBy() == null ? null : order.getTriggerBy().getTrigger())
                .tpLimitPrice(order.getTpLimitPrice())
                .slLimitPrice(order.getSlLimitPrice())
                .build();
    }

    // Asset request
    public AssetInternalTransferRequest mapToAssetInternalTransferRequest(AssetDataRequest assetDataRequest) {
        return AssetInternalTransferRequest.builder()
                .transferId(assetDataRequest.getTransferId() == null ? generateTransferID() : assetDataRequest.getTransferId())
                .coin(assetDataRequest.getCoin())
                .amount(assetDataRequest.getAmount())
                .fromAccountType(assetDataRequest.getFromAccountType().getAccountTypeValue())
                .toAccountType(assetDataRequest.getToAccountType().getAccountTypeValue())
                .build();
    }

    public AssetUniversalTransferRequest mapToAssetUniversalTransferRequest(AssetDataRequest assetDataRequest) {
        return AssetUniversalTransferRequest.builder()
                .transferId(assetDataRequest.getTransferId() == null ? generateTransferID() : assetDataRequest.getTransferId())
                .coin(assetDataRequest.getCoin())
                .amount(assetDataRequest.getAmount())
                .fromMemberId(assetDataRequest.getFromMemberId())
                .toMemberId(assetDataRequest.getToMemberId())
                .fromAccountType(assetDataRequest.getFromAccountType().getAccountTypeValue())
                .toAccountType(assetDataRequest.getToAccountType().getAccountTypeValue())
                .build();
    }

    public SetAssetDepositAccountRequest mapToSetDepositAccountRequest(AssetDataRequest request) {
        return SetAssetDepositAccountRequest.builder().accountType(request.getAccountType() == null ? null : request.getAccountType().getAccountTypeValue()).build();
    }

    public AssetCancelWithdrawRequest mapToAssetCancelWithdrawRequest(AssetDataRequest request) {
        return AssetCancelWithdrawRequest.builder().withdrawId(request.getWithdrawID()).build();
    }

    public AssetWithdrawRequest mapToAssetWithdrawRequest(AssetDataRequest assetDataRequest) {
        Map<String, String> beneficiary = Collections.emptyMap();
        if (assetDataRequest.getWithdrawBeneficiaryMap() != null) {
            beneficiary = assetDataRequest.getWithdrawBeneficiaryMap().getBeneficiaryMap();
        } else if (assetDataRequest.getBeneficiaryMap() != null && !assetDataRequest.getBeneficiaryMap().isEmpty()) {
            beneficiary = assetDataRequest.getBeneficiaryMap();
        }
        return AssetWithdrawRequest.builder()
                .coin(assetDataRequest.getCoin())
                .chain(assetDataRequest.getChain())
                .address(assetDataRequest.getAddress())
                .tag(assetDataRequest.getTag())
                .amount(assetDataRequest.getAmount())
                .timestamp(assetDataRequest.getTimestamp() == null ? Helper.generateTimestamp() : assetDataRequest.getTimestamp())
                .forceChain(assetDataRequest.getForceChain())
                .accountType(assetDataRequest.getAccountType() != null ? assetDataRequest.getAccountType().name() : null)
                .feeType(assetDataRequest.getFeeType() == null ? null : assetDataRequest.getFeeType().getValue())
                .beneficiary(beneficiary)
                .build();
    }

    public Call<Object> getSingleCoinBalance(ABFinanceApiService apiService, AssetDataRequest singleCoinBalanceRequest) {
        return apiService.getAssetSingleCoinBalance(
                singleCoinBalanceRequest.getAccountType() == null ? null : singleCoinBalanceRequest.getAccountType().getAccountTypeValue(),
                singleCoinBalanceRequest.getToAccountType() == null ? null : singleCoinBalanceRequest.getToAccountType().getAccountTypeValue(),
                singleCoinBalanceRequest.getMemberId(),
                singleCoinBalanceRequest.getToMemberId() == null ? null : singleCoinBalanceRequest.getToMemberId().toString(),
                singleCoinBalanceRequest.getCoin(),
                singleCoinBalanceRequest.getWithBonus() == null ? null : singleCoinBalanceRequest.getWithBonus().getValue(),
                singleCoinBalanceRequest.getWithTransferSafeAmount() == null ? null : singleCoinBalanceRequest.getWithTransferSafeAmount().getValue(),
                singleCoinBalanceRequest.getWithLtvTransferSafeAmount() == null ? null : singleCoinBalanceRequest.getWithLtvTransferSafeAmount().getValue());
    }
}
