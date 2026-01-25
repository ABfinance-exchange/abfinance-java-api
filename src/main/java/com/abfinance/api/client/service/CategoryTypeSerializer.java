package com.abfinance.api.client.service;

import com.abfinance.api.client.domain.CategoryType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;

public class CategoryTypeSerializer extends JsonSerializer<CategoryType> {
    @Override
    public void serialize(CategoryType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getCategoryTypeId());
    }
}
