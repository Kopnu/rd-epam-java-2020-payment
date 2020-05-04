package rd.epam.java.payment.service.impl;

import rd.epam.java.payment.domain.entity.Client;
import rd.epam.java.payment.service.ClientService;

import java.util.List;
import java.util.UUID;

/**
 * {@link ClientService} implementation.
 *
 * @author Sergei_Kornilov
 */
public class ClientServiceImpl implements ClientService {
    @Override
    public Client createClient(Client client) {
        return null;
    }


    @Override
    public Client find(UUID uuid) {
        return null;
    }

    @Override
    public List<Client> findAll(List<UUID> ids) {
        return null;
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}
