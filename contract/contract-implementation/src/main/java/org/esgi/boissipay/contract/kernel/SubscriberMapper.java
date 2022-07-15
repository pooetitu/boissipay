package org.esgi.boissipay.contract.kernel;

import org.esgi.boissipay.contract.model.BusinessSubscriber;
import org.esgi.boissipay.kafka.schema.ContactPerson;
import org.esgi.boissipay.kafka.schema.Customer;
import org.esgi.boissipay.kafka.schema.Subscriber;

public final class SubscriberMapper {

    private SubscriberMapper() {
    }

    public static Subscriber toKafkaSubscriber(org.esgi.boissipay.contract.domain.Subscriber subscriber) {
        return new Subscriber(
                subscriber.subscriberRef(),
                subscriber.subscriberType(),
                new Customer(
                        subscriber.customer().customerId(),
                        subscriber.customer().customerRef()
                ),
                new ContactPerson(
                        subscriber.contactPerson().ccuid(),
                        subscriber.contactPerson().gender(),
                        subscriber.contactPerson().firstName(),
                        subscriber.contactPerson().lastName(),
                        subscriber.contactPerson().email(),
                        subscriber.contactPerson().phone()
                )
        );
    }

    public static org.esgi.boissipay.contract.domain.Subscriber toSubscriber(BusinessSubscriber businessSubscriber) {
        return new org.esgi.boissipay.contract.domain.Subscriber(
                businessSubscriber.getSubscriberRef(),
                businessSubscriber.getSubscriberType().getValue(),
                new org.esgi.boissipay.contract.domain.Customer(
                        businessSubscriber.getParty().getProfessionnal().getCustomerId(),
                        businessSubscriber.getParty().getProfessionnal().getCustomerRef()
                ),
                new org.esgi.boissipay.contract.domain.ContactPerson(
                        businessSubscriber.getContactPerson().getCcuid(),
                        businessSubscriber.getContactPerson().getGender().getValue(),
                        businessSubscriber.getContactPerson().getFirstName(),
                        businessSubscriber.getContactPerson().getLastName(),
                        businessSubscriber.getContactPerson().getMail(),
                        businessSubscriber.getContactPerson().getPhone()
                )
        );
    }
}
