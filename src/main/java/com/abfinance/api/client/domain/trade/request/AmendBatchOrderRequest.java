package com.abfinance.api.client.domain.trade.request;

import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.service.CategoryTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AmendBatchOrderRequest {
    @JsonSerialize(using = CategoryTypeSerializer.class)
    private CategoryType category;
    private List<AmendOrderRequest> request;
}
