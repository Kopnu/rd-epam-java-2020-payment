package rd.epam.java.payment.service;

import rd.epam.java.payment.domain.entity.Client;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with {@link Client}.
 *
 * @author Sergei_Kornilov
 */
public interface ClientService {

    /**
     * Create client.
     *
     * @param client entity
     * @return saved entity
     */
    Client createClient(Client client);

    /**
     * Find client by id.
     *
     * @param uuid id
     * @return client entity
     */
    Client find(UUID uuid);

    /**
     * Find all clients by ids.
     *
     * @param ids list of id
     * @return client list
     */
    List<Client> findAll(List<UUID> ids);

    /**
     * Update client.
     *
     * @param client entity to update
     * @return client entity
     */
    Client update(Client client);

    /**
     * Delete client by id.
     *
     * @param uuid id
     * @return true - if deleted
     */
    boolean delete(UUID uuid);
}
