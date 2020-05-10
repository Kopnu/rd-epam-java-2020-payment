package rd.epam.java.payment.repository;

import rd.epam.java.payment.domain.entity.Accept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * AcceptRepository for operating with pm_accepts table in database
 *
 * @author Aleksey_Lukyanov
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class AcceptRepository {

    private final EntityManager entityManager;

    private static final String FIND_BY_ID_LIST = "Select a from pm_accepts a Where a.accept_id=:ids";

    public void save(Accept accept) {
        try {
            log.debug("save() - save accept {}", accept);
            entityManager.getTransaction().begin();
            entityManager.persist(accept);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("save() - error during saving: ", e);
        }
    }

    /**
     * Finds accept by its id in database
     *
     * @param acceptId - accept id
     * @return accept or empty object
     */
    public Optional<Accept> findById(UUID acceptId) {
        try {
            log.debug("findById() - find accept by id = {}", acceptId);
            Accept accept = entityManager.find(Accept.class, acceptId);
            return Optional.ofNullable(accept);
        } catch (Exception e) {
            log.error("findById - error during searching by acceptId: ", e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of accepts by ids in database
     *
     * @param uuids - id list
     * @return list of accepts or empty list
     */
    public List<Accept> findByIdList(List<UUID> uuids) {
        try {
            log.debug("findByIdList() - find list of accepts by id = {}", uuids);
            TypedQuery<Accept> query = entityManager.createQuery(FIND_BY_ID_LIST, Accept.class);
            return query.setParameter("ids", uuids).getResultList();
        } catch (Exception e) {
            log.error("findByIdList() - error during searching by id list: ", e);
        }
        return Collections.emptyList();
    }

    /**
     * Updates single accept in database
     *
     * @param accept - accept to update
     * @return updated accept or empty object
     */
    public Optional<Accept> update(Accept accept) {
        try {
            log.debug("update() - update accept {}", accept);
            entityManager.getTransaction().begin();
            entityManager.merge(accept);
            entityManager.getTransaction().commit();
            return Optional.of(accept);
        } catch (Exception e) {
            log.error("update() - error during updating: ", e);
        }
        return Optional.empty();
    }

    /**
     * Deletes single accept in database
     *
     * @param id - id of accept to delete
     */
    public void delete(UUID id) {
        try {
            log.debug("delete() - delete accept by id = {}", id);
            entityManager.getTransaction().begin();
            Accept accept = entityManager.find(Accept.class, id);
            entityManager.remove(accept);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("delete() - error during deleting: ", e);
        }
    }
}
