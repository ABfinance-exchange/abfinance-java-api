package com.abfinance.api.client.restApi;

import com.abfinance.api.client.constant.ABFinanceApiConstants;
import com.abfinance.api.client.domain.account.request.*;
import com.abfinance.api.client.domain.apilimit.request.SetApiLimitRequest;
import com.abfinance.api.client.domain.asset.request.*;
import com.abfinance.api.client.domain.trade.OrderStatus;
import com.abfinance.api.client.domain.trade.request.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * ABFinance REST API URL mappings and endpoint security configuration.
 */
public interface ABFinanceApiService {

    // ==================== Market Data Endpoints ====================

    /**
     * Get Server Time
     * GET /v5/market/time
     */
    @GET("/v5/market/time")
    Call<Object> getServerTime();

    /**
     * Get Kline
     * GET /v5/market/kline
     */
    @GET("/v5/market/kline")
    Call<Object> getMarketLinesData(@Query("category") String category,
                                    @Query("symbol") String symbol,
                                    @Query("interval") String interval,
                                    @Query("start") Long start,
                                    @Query("end") Long end,
                                    @Query("limit") Integer limit);

    /**
     * Get Instruments Info
     * GET /v5/market/instruments-info
     */
    @GET("/v5/market/instruments-info")
    Call<Object> getInstrumentsInfo(@Query("category") String category,
                                    @Query("symbol") String symbol,
                                    @Query("status") String status,
                                    @Query("baseCoin") String baseCoin,
                                    @Query("limit") Integer limit,
                                    @Query("cursor") String cursor);

    /**
     * Get Orderbook
     * GET /v5/market/orderbook
     */
    @GET("/v5/market/orderbook")
    Call<Object> getMarketOrderBook(@Query("category") String category,
                                    @Query("symbol") String symbol,
                                    @Query("limit") Integer limit);

    /**
     * Get Tickers
     * GET /v5/market/tickers
     */
    @GET("/v5/market/tickers")
    Call<Object> getMarketTickers(@Query("category") String category,
                                  @Query("symbol") String symbol,
                                  @Query("baseCoin") String baseCoin,
                                  @Query("expDate") String expDate);

    /**
     * Get Public Recent Trading History
     * GET /v5/market/recent-trade
     */
    @GET("/v5/market/recent-trade")
    Call<Object> getRecentTradeData(@Query("category") String category,
                                    @Query("symbol") String symbol,
                                    @Query("baseCoin") String baseCoin,
                                    @Query("optionType") String optionType,
                                    @Query("limit") Integer limit);

    /**
     * Get Price Limit
     * GET /v5/market/price-limit
     */
    @GET("/v5/market/price-limit")
    Call<Object> getPriceLimit(@Query("category") String category,
                               @Query("symbol") String symbol);

    /**
     * Get RPI Orderbook
     * GET /v5/market/rpi_orderbook
     */
    @GET("/v5/market/rpi_orderbook")
    Call<Object> getRpiOrderbook(@Query("category") String category,
                                 @Query("symbol") String symbol,
                                 @Query("limit") Integer limit);

    // ==================== Order/Trade Endpoints ====================

    /**
     * Get Order History
     * GET /v5/order/history
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/order/history")
    Call<Object> getOrderHistory(@Query("category") String category,
                                 @Query("symbol") String symbol,
                                 @Query("baseCoin") String baseCoin,
                                 @Query("settleCoin") String settleCoin,
                                 @Query("orderId") String orderId,
                                 @Query("orderLinkId") String orderLinkId,
                                 @Query("orderFilter") String orderFilter,
                                 @Query("orderStatus") OrderStatus orderStatus,
                                 @Query("startTime") Long startTime,
                                 @Query("endTime") Long endTime,
                                 @Query("limit") Integer limit,
                                 @Query("cursor") String cursor);

    /**
     * Get Borrow Quota (Spot)
     * GET /v5/order/spot-borrow-check
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/order/spot-borrow-check")
    Call<Object> getBorrowQuota(@Query("category") String category,
                                @Query("symbol") String symbol,
                                @Query("side") String side);

    /**
     * Get Open Orders
     * GET /v5/order/realtime
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/order/realtime")
    Call<Object> getOpenOrders(@Query("category") String category,
                               @Query("symbol") String symbol,
                               @Query("baseCoin") String baseCoin,
                               @Query("settleCoin") String settleCoin,
                               @Query("orderId") String orderId,
                               @Query("orderLinkId") String orderLinkId,
                               @Query("openOnly") Integer openOnly,
                               @Query("orderFilter") String orderFilter,
                               @Query("limit") Integer limit,
                               @Query("cursor") String cursor);

    /**
     * Get Trade History
     * GET /v5/execution/list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/execution/list")
    Call<Object> getTradeHistory(@Query("category") String category,
                                 @Query("symbol") String symbol,
                                 @Query("orderId") String orderId,
                                 @Query("orderLinkId") String orderLinkId,
                                 @Query("baseCoin") String baseCoin,
                                 @Query("startTime") Long startTime,
                                 @Query("endTime") Long endTime,
                                 @Query("execType") String execType,
                                 @Query("limit") Integer limit,
                                 @Query("cursor") String cursor);

    /**
     * Set Disconnect Cancel All
     * POST /v5/order/disconnected-cancel-all
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/disconnected-cancel-all")
    Call<Object> setDisconnectCancelAllTime(@Body SetDcpRequest setDcpRequest);

    /**
     * Place Order
     * POST /v5/order/create
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/create")
    Call<Object> createOrder(@Body PlaceOrderRequest placeOrderRequest);

    /**
     * Batch Place Order
     * POST /v5/order/create-batch
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/create-batch")
    Call<Object> createBatchOrder(@Body PlaceBatchOrderRequest placeBatchOrderRequest);

    /**
     * Cancel Order
     * POST /v5/order/cancel
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/cancel")
    Call<Object> cancelOrder(@Body CancelOrderRequest cancelOrderRequest);

    /**
     * Batch Cancel Order
     * POST /v5/order/cancel-batch
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/cancel-batch")
    Call<Object> cancelBatchOrder(@Body CancelBatchOrderRequest cancelBatchOrderRequest);

    /**
     * Cancel All Orders
     * POST /v5/order/cancel-all
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/cancel-all")
    Call<Object> cancelAllOrder(@Body CancelAllOrdersRequest cancelAllOrdersRequest);

    /**
     * Amend Order
     * POST /v5/order/amend
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/amend")
    Call<Object> amendOrder(@Body AmendOrderRequest amendOrderRequest);

    /**
     * Batch Amend Order
     * POST /v5/order/amend-batch
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/order/amend-batch")
    Call<Object> amendBatchOrder(@Body AmendBatchOrderRequest batchOrderRequest);

    // ==================== Account Endpoints ====================

    /**
     * Get Wallet Balance
     * GET /v5/account/wallet-balance
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/wallet-balance")
    Call<Object> getWalletBalance(@Query("accountType") String accountType,
                                  @Query("coin") String coin);

    /**
     * Get Fee Rate
     * GET /v5/account/fee-rate
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/fee-rate")
    Call<Object> getAccountFreeRate(@Query("category") String category,
                                    @Query("symbol") String symbol,
                                    @Query("baseCoin") String baseCoin);

    /**
     * Get Account Info
     * GET /v5/account/info
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/info")
    Call<Object> getAccountInfo();

    /**
     * Get Instruments Info (Account)
     * GET /v5/account/instruments-info
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/instruments-info")
    Call<Object> getAccountInstrumentsInfo(@Query("category") String category,
                                           @Query("symbol") String symbol,
                                           @Query("status") String status,
                                           @Query("baseCoin") String baseCoin,
                                           @Query("limit") Integer limit,
                                           @Query("cursor") String cursor);

    /**
     * Query DCP Info
     * GET /v5/account/query-dcp-info
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/query-dcp-info")
    Call<Object> getAccountDcpInfo();

    /**
     * Set Limit Price Action
     * POST /v5/account/set-limit-px-action
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/account/set-limit-px-action")
    Call<Object> setAccountLimitPxAction(@Body Map<String, Object> request);

    /**
     * Get SMP Group ID
     * GET /v5/account/smp-group
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/smp-group")
    Call<Object> getAccountSMPGroupId();

    /**
     * Get Transaction Log
     * GET /v5/account/transaction-log
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/transaction-log")
    Call<Object> getUtaTransactionLog(@Query("accountType") String accountType,
                                      @Query("category") String category,
                                      @Query("currency") String currency,
                                      @Query("baseCoin") String baseCoin,
                                      @Query("type") String type,
                                      @Query("startTime") Long startTime,
                                      @Query("endTime") Long endTime,
                                      @Query("limit") Integer limit,
                                      @Query("cursor") String cursor);

    /**
     * Withdraw (Account)
     * POST /v5/account/withdrawal
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/account/withdrawal")
    Call<Object> accountWithdrawal(@Body Map<String, Object> request);

    // ==================== Asset Endpoints ====================

    /**
     * Get Coin Info
     * GET /v5/asset/coin/query-info
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/coin/query-info")
    Call<Object> getAssetCoinInfo(@Query("coin") String coin);

    /**
     * Set Deposit Account
     * POST /v5/asset/deposit/deposit-to-account
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/asset/deposit/deposit-to-account")
    Call<Object> setAssetDepositAccount(@Body SetAssetDepositAccountRequest setAssetDepositAccountRequest);

    /**
     * Get Master Deposit Address
     * GET /v5/asset/deposit/query-address
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/deposit/query-address")
    Call<Object> getAssetMasterDepositAddress(@Query("coin") String coin,
                                              @Query("chainType") String chainType);

    /**
     * Get Internal Deposit Records
     * GET /v5/asset/deposit/query-internal-record
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/deposit/query-internal-record")
    Call<Object> getAssetInternalDepositRecords(@Query("coin") String coin,
                                                @Query("startTime") Long startTime,
                                                @Query("endTime") Long endTime,
                                                @Query("limit") Integer limit,
                                                @Query("cursor") String cursor);

    /**
     * Get Deposit Records
     * GET /v5/asset/deposit/query-record
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/deposit/query-record")
    Call<Object> getAssetDepositRecords(@Query("coin") String coin,
                                        @Query("startTime") Long startTime,
                                        @Query("endTime") Long endTime,
                                        @Query("limit") Integer limit,
                                        @Query("cursor") String cursor);

    /**
     * Get Sub Deposit Address
     * GET /v5/asset/deposit/query-sub-member-address
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/deposit/query-sub-member-address")
    Call<Object> getAssetSubMemberDepositAddress(@Query("coin") String coin,
                                                 @Query("chainType") String chainType,
                                                 @Query("subMemberId") String subMemberId);

    /**
     * Get Sub Deposit Records
     * GET /v5/asset/deposit/query-sub-member-record
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/deposit/query-sub-member-record")
    Call<Object> getAssetSubMembersDepositRecords(@Query("subMemberId") String subMemberId,
                                                  @Query("coin") String coin,
                                                  @Query("startTime") Long startTime,
                                                  @Query("endTime") Long endTime,
                                                  @Query("limit") Integer limit,
                                                  @Query("cursor") String cursor);

    /**
     * Create Internal Transfer
     * POST /v5/asset/transfer/inter-transfer
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/asset/transfer/inter-transfer")
    Call<Object> createAssetInternalTransfer(@Body AssetInternalTransferRequest assetInternalTransferRequest);

    /**
     * Get Single Coin Balance
     * GET /v5/asset/transfer/query-account-coin-balance
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-account-coin-balance")
    Call<Object> getAssetSingleCoinBalance(@Query("accountType") String accountType,
                                           @Query("toAccountType") String toAccountType,
                                           @Query("memberId") String memberId,
                                           @Query("toMemberId") String toMemberId,
                                           @Query("coin") String coin,
                                           @Query("withBonus") Integer withBonus,
                                           @Query("withTransferSafeAmount") Integer withTransferSafeAmount,
                                           @Query("withLtvTransferSafeAmount") Integer withLtvTransferSafeAmount);

    /**
     * Get All Coins Balance
     * GET /v5/asset/transfer/query-account-coins-balance
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-account-coins-balance")
    Call<Object> getAssetAllCoinsBalance(@Query("accountType") String accountType,
                                         @Query("memberId") String memberId,
                                         @Query("coin") String coin,
                                         @Query("withBonus") String withBonus);

    /**
     * Get Internal Transfer Records
     * GET /v5/asset/transfer/query-inter-transfer-list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-inter-transfer-list")
    Call<Object> getAssetInternalTransferRecords(@Query("transferId") String transferId,
                                                 @Query("coin") String coin,
                                                 @Query("status") String status,
                                                 @Query("startTime") Long startTime,
                                                 @Query("endTime") Long endTime,
                                                 @Query("limit") Integer limit,
                                                 @Query("cursor") String cursor);

    /**
     * Get Sub UID
     * GET /v5/asset/transfer/query-sub-member-list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-sub-member-list")
    Call<Object> getAssetTransferSubUidList();

    /**
     * Get Transferable Coin
     * GET /v5/asset/transfer/query-transfer-coin-list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-transfer-coin-list")
    Call<Object> getAssetTransferableCoins(@Query("fromAccountType") String fromAccountType,
                                           @Query("toAccountType") String toAccountType);

    /**
     * Get Universal Transfer Records
     * GET /v5/asset/transfer/query-universal-transfer-list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/transfer/query-universal-transfer-list")
    Call<Object> getAssetUniversalTransferRecords(@Query("transferId") String transferId,
                                                  @Query("coin") String coin,
                                                  @Query("status") String status,
                                                  @Query("startTime") Long startTime,
                                                  @Query("endTime") Long endTime,
                                                  @Query("limit") Integer limit,
                                                  @Query("cursor") String cursor);

    /**
     * Create Universal Transfer
     * POST /v5/asset/transfer/universal-transfer
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/asset/transfer/universal-transfer")
    Call<Object> createAssetUniversalTransfer(@Body AssetUniversalTransferRequest assetUniversalTransferRequest);

    /**
     * Cancel Withdrawal
     * POST /v5/asset/withdraw/cancel
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/asset/withdraw/cancel")
    Call<Object> cancelAssetWithdraw(@Body AssetCancelWithdrawRequest assetCancelWithdrawRequest);

    /**
     * Withdraw
     * POST /v5/asset/withdraw/create
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/asset/withdraw/create")
    Call<Object> createAssetWithdraw(@Body AssetWithdrawRequest assetWithdrawRequest);

    /**
     * Get Withdraw Address
     * GET /v5/asset/withdraw/query-address
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/withdraw/query-address")
    Call<Object> getAssetWithdrawAddress(@Query("coin") String coin,
                                         @Query("chain") String chain,
                                         @Query("limit") Integer limit,
                                         @Query("cursor") String cursor);

    /**
     * Get Withdrawal Records
     * GET /v5/asset/withdraw/query-record
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/withdraw/query-record")
    Call<Object> getAssetWithdrawalRecords(@Query("withdrawID") String withdrawID,
                                           @Query("coin") String coin,
                                           @Query("withdrawType") Integer withdrawType,
                                           @Query("startTime") Long startTime,
                                           @Query("endTime") Long endTime,
                                           @Query("limit") Integer limit,
                                           @Query("cursor") String cursor);

    /**
     * Get Withdrawable Amount
     * GET /v5/asset/withdraw/withdrawable-amount
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/withdraw/withdrawable-amount")
    Call<Object> getAssetWithdrawalAmount(@Query("coin") String coin);

    // ==================== Earn Endpoints ====================

    /**
     * Get Earn Product List
     * GET /v5/earn/product
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/earn/product")
    Call<Object> getEarnProduct(@Query("category") String category,
                                @Query("coin") String coin,
                                @Query("productId") String productId,
                                @Query("limit") Integer limit,
                                @Query("cursor") String cursor);

    /**
     * Get Earn Position
     * GET /v5/earn/position
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/earn/position")
    Call<Object> getEarnPosition(@Query("category") String category,
                                 @Query("coin") String coin,
                                 @Query("productId") String productId,
                                 @Query("limit") Integer limit,
                                 @Query("cursor") String cursor);

    /**
     * Place Earn Order
     * POST /v5/earn/place-order
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/earn/place-order")
    Call<Object> placeEarnOrder(@Body Map<String, Object> request);

    /**
     * Get Earn Order
     * GET /v5/earn/order
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/earn/order")
    Call<Object> getEarnOrder(@Query("orderId") String orderId,
                              @Query("orderType") String orderType,
                              @Query("category") String category,
                              @Query("coin") String coin,
                              @Query("productId") String productId,
                              @Query("startTime") Long startTime,
                              @Query("endTime") Long endTime,
                              @Query("limit") Integer limit,
                              @Query("cursor") String cursor);

    // ==================== API Limit Endpoints ====================

    /**
     * Get API Limit
     * GET /v5/apilimit/query
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/apilimit/query")
    Call<Object> getApiLimit(@Query("quotaId") String quotaId);

    /**
     * Get All API Limit
     * GET /v5/apilimit/query-all
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/apilimit/query-all")
    Call<Object> getAllApiLimit();

    /**
     * Get API Limit Cap
     * GET /v5/apilimit/query-cap
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/apilimit/query-cap")
    Call<Object> getApiLimitCap();

    /**
     * Set API Limit
     * POST /v5/apilimit/set
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/v5/apilimit/set")
    Call<Object> setApiLimit(@Body SetApiLimitRequest request);

    // ==================== New Account Endpoints ====================

    /**
     * Get User Setting Config
     * GET /v5/account/user-setting-config
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/account/user-setting-config")
    Call<Object> getUserSettingConfig();

    // ==================== New Asset Endpoints ====================

    /**
     * Get VASP List
     * GET /v5/asset/withdraw/vasp/list
     */
    @Headers(ABFinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/v5/asset/withdraw/vasp/list")
    Call<Object> getVaspList();
}
