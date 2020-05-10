package rd.epam.java.payment.repository;

import rd.epam.java.payment.domain.entity.Client;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Client repository for working with client table in database
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
public class ClientRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("payment-unit").createEntityManager();
    private static String qlQueryID = "Select b from pm_clients b Where b.client_id=:ids";

    /**
     * Add client record into database
     *
     * @param client - record for saving
     */
    public void save(Client client) {
        try {
            log.debug("save() - save client {}", client);
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("save() - error during saving ",e);
        }
    }

    /**
     * Finds client in database by his id
     *
     * @param id of client
     * @return found client or empty object
     */
    public Optional<Client> findById(UUID id) {
        try {
            log.debug("findById() - find client by id = {}", id);
            Client client = entityManager.find(Client.class, id);
            return Optional.ofNullable(client);
        } catch (Exception e) {
            log.error("findByIdList() - error during searching by id ",e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of clients in database
     *
     * @param ids - list of id
     * @return list of clients or empty list
     */
    public List<Client> findByIdList(List<UUID> ids) {
        try {
            log.debug("findByIdList() - find list of clients by id = {}", ids);
            TypedQuery<Client> query = entityManager.createQuery(qlQueryID, Client.class);
            return query.setParameter("ids", ids).getResultList();
        } catch (Exception e) {
            log.error("findByIdList() - error during searching by id list ",e);
        }
        return Collections.emptyList();
    }

    /**
     * Updates one client record in database
     *
     * @param client for updating
     * @return updated client or empty object
     */
    public Optional<Client> update(Client client) {
        try {
            log.debug("update() - update client {}", client);
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();
            return Optional.of(client);
        } catch (Exception e) {
            log.error("update() - error during updating ",e);
        }
        return Optional.empty();
    }

    /**
     * Deletes one client record
     *
     * @param id of record for deleting
     */
    public void delete(UUID id) {
        try {
            log.debug("delete() - delete client by id = {}", id);
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Client.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.error("delete() - error during deleting ",e);
        }
    }
}
