package org.esgi.boissipay.billing.models;

import java.time.ZonedDateTime;

public record Billing(ZonedDateTime instant, String contractName) {
}

