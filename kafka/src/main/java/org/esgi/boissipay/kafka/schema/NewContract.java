package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public final record NewContract(
        @JsonProperty String contractId,
        @JsonProperty String contractRef,
        @JsonProperty String contractType,
        @JsonProperty LocalDate createdAt,
        @JsonProperty LocalDate activatedAt,
        @JsonProperty LocalDate expireAt,
        @JsonProperty Subscriber subscriber
) {
}
