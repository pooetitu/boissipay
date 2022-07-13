package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewContract {
    @JsonProperty("name")
    private String name;
    @JsonProperty("contactPerson")
    private ContactPerson contactPerson;

    public String name() {
        return name;
    }

    public NewContract setName(String name) {
        this.name = name;
        return this;
    }

    public ContactPerson contactPerson() {
        return contactPerson;
    }

    public NewContract setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }
}
