package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContactPerson(
        @JsonProperty String ccuid,
        @JsonProperty String gender,
        @JsonProperty String firstName,
        @JsonProperty String lastName,
        @JsonProperty String email,
        @JsonProperty String phone
) {
}
