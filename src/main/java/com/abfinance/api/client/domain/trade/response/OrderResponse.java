package com.abfinance.api.client.domain.trade.response;

import com.abfinance.api.client.constant.ABFinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonPropertyOrder()
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderResponse {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("orderLinkId")
    private String orderLinkId;
    @Override
    public String toString() {
        return  new ToStringBuilder(this, ABFinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("orderId", orderId)
                .append("orderLinkId", orderLinkId)
                .toString();
    }
}
