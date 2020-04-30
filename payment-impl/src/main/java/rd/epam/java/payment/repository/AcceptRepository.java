package rd.epam.java.payment.repository;

import rd.epam.java.payment.domain.entity.Accept;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * AcceptRepository.
 *
 * @author Aleksey_Lukyanov
 */
@Slf4j
public class AcceptRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("payment-unit").createEntityManager();

    public void save(Accept accept) {
        try {
            log.info("save() - save accept {}", accept);
            entityManager.getTransaction().begin();
            entityManager.persist(accept);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during saving: ", e);
        }
    }

    public Optional<Accept> findById(UUID acceptId) {
        try {
            log.info("findById() - find accept by id = {}", acceptId);
            Accept accept = entityManager.find(Accept.class, acceptId);
            return Optional.ofNullable(accept);
        } catch (Exception e) {
            log.warn("Error during searching by acceptId: ", e);
        }
        return Optional.empty();
    }

    public List<Accept> findByIdList(List<UUID> ids) {
        try {
            log.info("findByIdList() - find list of accepts by id = {}", ids);
            TypedQuery<Accept> query = entityManager.createQuery("Select a from pm_accepts a Where a.accept_id=:ids", Accept.class);
            return query.setParameter("ids", ids).getResultList();
        } catch (Exception e) {
            log.warn("Error during searching by id list: ", e);
        }
        return Collections.emptyList();
    }

    public Optional<Accept> update(Accept accept) {
        try {
            log.info("update() - update accept {}", accept);
            entityManager.getTransaction().begin();
            entityManager.merge(accept);
            entityManager.getTransaction().commit();
            return Optional.of(accept);
        } catch (Exception e) {
            log.warn("Error during updating: ", e);
        }
        return Optional.empty();
    }

    public void delete(Integer id) {
        try {
            log.info("delete() - delete accept by id = {}", id);
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Accept.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during deleting: ", e);
        }
    }
}
