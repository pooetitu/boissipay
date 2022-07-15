package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record InvoiceSent(
    @JsonProperty String contractName,
    @JsonProperty String contractRef,
    @JsonProperty String contractId,
    @JsonProperty List<String> payments
) {
}
