package rd.epam.java.payment.service;


import rd.epam.java.payment.domain.entity.Payment;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with {@link Payment}.
 *
 * @author Sergei_Kornilov
 */
public interface PaymentService {

    /**
     * Create payment.
     *
     * @param payment entity
     * @return saved entity
     */
    Payment createPayment(Payment payment);

    /**
     * Find payment by id.
     *
     * @param uuid id
     * @return payment entity
     */
    Payment find(UUID uuid);

    /**
     * Find all payments by ids.
     *
     * @param ids list of id
     * @return payment list
     */
    List<Payment> findAll(List<UUID> ids);

    /**
     * Update client.
     *
     * @param payment entity to update
     * @return payment entity
     */
    Payment update(Payment payment);

    /**
     * Delete client by id.
     *
     * @param uuid id
     * @return true - if deleted
     */
    boolean delete(UUID uuid);

}
