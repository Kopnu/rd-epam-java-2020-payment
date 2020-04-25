import lombok.extern.slf4j.Slf4j;

import rd.epam.java.payment.domain.entity.Payment;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * PaymentRepository.
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
public class PaymentRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("Payment-payment-unit").createEntityManager();

    /**
     * Add payment record into database
     *
     * @param payment - record for saving
     */
    public void save(Payment payment) {
        try {
            log.info("Saving payment.");
            entityManager.getTransaction().begin();
            entityManager.persist(payment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during saving: ", e);
        }
    }

    /**
     * Finds payment in database by his private id
     *
     * @param id of payment
     * @return found payment or empty object
     */
    public Optional<Payment> findById(Integer id) {
        try {
            log.info("Finding payment with id {}", id);
            Payment payment = entityManager.find(Payment.class, id);
            return payment != null ? Optional.of(payment) : Optional.empty();
        } catch (Exception e) {
            log.warn("Error during searching by id: ", e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of payments in database based on private id list
     *
     * @param idList - list of id
     * @return list of payments or empty list
     */
    public List<Payment> findByIdList(List<Integer> idList) {
        try {
            log.info("Finding payments with ids {}", idList);
            List<Payment> paymentList = new ArrayList<>();
            for (Integer id : idList) {
                paymentList.add(entityManager.find(Payment.class, id));
            }
            return paymentList;
        } catch (Exception e) {
            log.warn("Error during searching by id list: ", e);
        }
        return Collections.emptyList();
    }

    /**
     * Updates one payment record in database
     *
     * @param payment for updating
     * @return updated payment or empty object
     */
    public Optional<Payment> update(Payment payment) {
        try {
            log.info("Updating payment.");
            entityManager.getTransaction().begin();
            entityManager.merge(payment);
            entityManager.getTransaction().commit();
            return Optional.of(payment);
        } catch (Exception e) {
            log.warn("Error during updating: ", e);
        }
        return Optional.empty();
    }

    /**
     * Deletes one payment record from database
     *
     * @param id of record for deleting
     */
    public void delete(Integer id) {
        try {
            log.info("Deleting payment with id {}.", id);
            entityManager.getTransaction().begin();
            entityManager.remove(findById(id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during deleting: ", e);
        }
    }
}
