package com.finwefeanor.DiningReviewApi.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class DoubleSerializer extends JsonSerializer<Double> {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializerProvider)
            throws IOException {
        if (value != null) {
            gen.writeString(decimalFormat.format(value));
        }

    }
}
