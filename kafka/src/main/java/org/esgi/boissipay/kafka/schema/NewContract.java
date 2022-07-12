package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewContract {
    @JsonProperty("name")
    private String name;

    public String name() {
        return name;
    }

    public NewContract setName(String name) {
        this.name = name;
        return this;
    }
}
