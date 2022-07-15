package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Customer(
        @JsonProperty String customerId,
        @JsonProperty String customerRef
) {
}
