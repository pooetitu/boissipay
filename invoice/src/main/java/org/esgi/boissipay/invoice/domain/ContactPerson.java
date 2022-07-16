package org.esgi.boissipay.invoice.domain;

public class ContactPerson {
    private String ccuid;
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public ContactPerson() {
    }

    public ContactPerson(String ccuid, String gender, String firstName, String lastName, String email, String phone) {
        this.ccuid = ccuid;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String ccuid() {
        return ccuid;
    }

    public ContactPerson setCcuid(String ccuid) {
        this.ccuid = ccuid;
        return this;
    }

    public String gender() {
        return gender;
    }

    public ContactPerson setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String firstName() {
        return firstName;
    }

    public ContactPerson setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String lastName() {
        return lastName;
    }

    public ContactPerson setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String email() {
        return email;
    }

    public ContactPerson setEmail(String email) {
        this.email = email;
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
