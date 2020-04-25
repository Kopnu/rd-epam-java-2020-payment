import lombok.extern.slf4j.Slf4j;

import rd.epam.java.payment.domain.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ClientRepository.
 *
 * @author Dmitrii_Lopatin
 */
@Slf4j
public class ClientRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("Client-payment-unit").createEntityManager();

    /**
     * Add client record into database
     *
     * @param client - record for saving
     */
    public void save(Client client) {
        try {
            log.info("Saving client.");
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during saving: ", e);
        }
    }

    /**
     * Finds client in database by his id
     *
     * @param id of client
     * @return found client or empty object
     */
    public Optional<Client> findById(Integer id) {
        try {
            log.info("Finding client with id {}", id);
            Client client = entityManager.find(Client.class, id);
            return client != null ? Optional.of(client) : Optional.empty();
        } catch (Exception e) {
            log.warn("Error during searching by id: ", e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of clients in database based on id list
     *
     * @param idList - list of id
     * @return list of clients or empty list
     */
    public List<Client> findByIdList(List<Integer> idList) {
        try {
            log.info("Finding clients with ids {}", idList);
            List<Client> clientList = new ArrayList<>();
            for (Integer id : idList) {
                clientList.add(entityManager.find(Client.class, id));
            }
            return clientList;
        } catch (Exception e) {
            log.warn("Error during searching by id list: ", e);
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
            log.info("Updating client.");
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();
            return Optional.of(client);
        } catch (Exception e) {
            log.warn("Error during updating: ", e);
        }
        return Optional.empty();
    }

    /**
     * Deletes one client record
     *
     * @param id of record for deleting
     */
    public void delete(Integer id) {
        try {
            log.info("Deleting client with id {}.", id);
            entityManager.getTransaction().begin();
            entityManager.remove(findById(id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during deleting: ", e);
        }
    }
}
