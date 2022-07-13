package org.esgi.boissipay.kafka.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactPerson {
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("phone")
    private String phone;

    public String firstname() {
        return firstname;
    }

    public ContactPerson setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String lastname() {
        return lastname;
    }

    public ContactPerson setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String mail() {
        return mail;
    }

    public ContactPerson setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String phone() {
        return phone;
    }

    public ContactPerson setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
