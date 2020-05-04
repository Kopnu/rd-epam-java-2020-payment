package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.repository.ClientRepository;
import rd.epam.java.payment.service.ClientService;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@link ClientService} implementation.
 *
 * @author Sergei_Kornilov
 */
@Slf4j
public class ClientServiceImpl implements ClientService {
    public final ClientRepository clientRepository = new ClientRepository();

    @Override
    public Client createClient(Client client) {
        if(Objects.isNull(client)){
            log.error("createClient() - error client is null");
            return null;
        }
        log.debug("createClient() - create client {}", client);
        clientRepository.save(client);
        return clientRepository.findByName(client.getName()).get();
    }

    /**
     * Finds client in repository by id
     *
     * @param uuid id of client
     * @return client or null
     */
    @Override
    public Client find(UUID uuid) {
        if (Objects.isNull(uuid)) {
            log.error("find() - error uuid is null");
            return null;
        }
        log.debug("find() - find client by uuid : {}", uuid);
        return clientRepository.findById(uuid).get();
    }

    /**
     * Finds list of clients in repository
     *
     * @param ids list of id
     * @return list of clients or empty list
     */
    @Override
    public List<Client> findAll(List<UUID> ids) {
        if(Objects.isNull(ids)){
            log.error("findAll() - error ids is null");
            return Collections.emptyList();
        }
        log.debug("findAll() - find list of clients by id = {}", ids);
        return clientRepository.findByIdList(ids);
    }

    /**
     * Updates one client in repository
     *
     * @param client entity to update
     * @return client or null
     */
    @Override
    public Client update(Client client) {
        if(Objects.isNull(client)){
            log.error("update() - error client is null");
            return null;
        }
        log.debug("update() - update client : {}",client);
        return clientRepository.update(client).get();
    }

    /**
     * Delete one client without repository
     *
     * @param uuid id client
     * @return true or false
     */
    @Override
    public boolean delete(UUID uuid) {
        if(Objects.isNull(uuid)){
            log.error("delete() - error uuid is null");
            return false;
        }
        log.debug("delete() - delete client by id : {}",uuid);
        clientRepository.delete(uuid);
        return true;
    }
}
