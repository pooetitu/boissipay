package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public record Invoice(
    @JsonProperty String invoiceRef,
    @JsonProperty String contractRef,
    @JsonProperty String contractId,
    @JsonProperty ContactPerson contactPerson,
    @JsonProperty ZonedDateTime instant,
    @JsonProperty List<ProcessPayment> payments
) {
}
