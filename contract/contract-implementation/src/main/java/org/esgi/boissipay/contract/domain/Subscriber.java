package org.esgi.boissipay.contract.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Subscriber(
        @JsonProperty String subscriberRef,
        @JsonProperty String subscriberType,
        @JsonProperty Customer customer,
        @JsonProperty ContactPerson contactPerson
) {
}
