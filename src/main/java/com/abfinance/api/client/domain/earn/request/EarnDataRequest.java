package com.abfinance.api.client.domain.earn.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EarnDataRequest {
    private String category;
    private String coin;
    private String productId;
    private String orderId;
    private String orderType;
    private String accountType;
    private String quantity;
    private String serialNo;
    private Long startTime;
    private Long endTime;
    private Integer limit;
    private String cursor;
}
