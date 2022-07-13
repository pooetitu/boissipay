package org.esgi.boissipay.billing.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record Payment(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX[ VV]") ZonedDateTime instant,
        String contractName
    ) {
}

