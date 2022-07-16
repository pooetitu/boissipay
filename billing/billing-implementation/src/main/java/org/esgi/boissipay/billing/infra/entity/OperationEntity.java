package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations")
public class OperationEntity {

    @Id
    private String id;

    private String contractId;

    private String personCcuid;
    private String personGender;
    private String personFirstName;
    private String personLastName;
    private String personEmail;
    private String personPhone;


    public OperationEntity() {
    }

    public OperationEntity(String id, String contractId, String personCcuid, String personGender,
                           String personFirstName, String personLastName, String personEmail, String personPhone) {
        this.id = id;
        this.contractId = contractId;
        this.personCcuid = personCcuid;
        this.personGender = personGender;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.personEmail = personEmail;
        this.personPhone = personPhone;
    }

    public String personCcuid() {
        return personCcuid;
    }

    public OperationEntity setPersonCcuid(String personCcuid) {
        this.personCcuid = personCcuid;
        return this;
    }

    public String personGender() {
        return personGender;
    }

    public OperationEntity setPersonGender(String personGender) {
        this.personGender = personGender;
        return this;
    }

    public String personFirstName() {
        return personFirstName;
    }

    public OperationEntity setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public String personLastName() {
        return personLastName;
    }

    public OperationEntity setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public String personEmail() {
        return personEmail;
    }

    public OperationEntity setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
        return this;
    }

    public String personPhone() {
        return personPhone;
    }

    public OperationEntity setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
        return this;
    }

    public String contractId() {
        return contractId;
    }

    public OperationEntity setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public String id() {
        return id;
    }

    public OperationEntity setId(String id) {
        this.id = id;
        return this;
    }
}
