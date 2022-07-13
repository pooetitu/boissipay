package org.esgi.boissipay.billing.exposition;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public record CreateBillingRequest(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX[ VV]") ZonedDateTime instant,
        String contractName
) {

}
