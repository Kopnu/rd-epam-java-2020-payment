import lombok.extern.slf4j.Slf4j;

import rd.epam.java.payment.domain.entity.Payment;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Payment repository for working with payment table in database
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
public class PaymentRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("payment-unit").createEntityManager();

    /**
     * Add payment record into database
     *
     * @param payment - record for saving
     */
    public void save(Payment payment) {
        try {
            log.info("save() - save payment {}", payment);
            entityManager.getTransaction().begin();
            entityManager.persist(payment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during saving: ", e);
        }
    }

    /**
     * Finds payment in database by its id
     *
     * @param id of payment
     * @return found payment or empty object
     */
    public Optional<Payment> findById(Integer id) {
        try {
            log.info("findById() - find payment by id = {}", id);
            Payment payment = entityManager.find(Payment.class, id);
            return Optional.ofNullable(payment);
        } catch (Exception e) {
            log.warn("Error during searching by id: ", e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of payments in database
     *
     * @param ids - list of id
     * @return list of payments or empty list
     */
    public List<Payment> findByIdList(List<Integer> ids) {
        try {
            log.info("findByIdList() - find list of payments by id = {}", ids);
            TypedQuery<Payment> query = entityManager.createQuery("Select b from pm_payments b Where b.payment_private_id=:ids", Payment.class);
            return query.setParameter("ids", ids).getResultList();
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
            log.info("update() - update payment {}", payment);
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
     * Deletes one payment record
     *
     * @param id of record for deleting
     */
    public void delete(Integer id) {
        try {
            log.info("delete() - delete payment by id = {}", id);
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Payment.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during deleting: ", e);
        }
    }
}
