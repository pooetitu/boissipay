package org.esgi.boissipay.contract.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Customer(
        @JsonProperty String customerId,
        @JsonProperty String customerRef
) {
}
