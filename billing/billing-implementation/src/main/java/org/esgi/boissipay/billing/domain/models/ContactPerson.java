package org.esgi.boissipay.billing.domain.models;

public record ContactPerson(
    String ccuid,
    String gender,
    String firstName,
    String lastName,
    String email,
    String phone
) {
}
