package rd.epam.java.payment.service;

import rd.epam.java.payment.domain.entity.Accept;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with {@link Accept}.
 *
 * @author Sergei_Kornilov
 */
public interface AcceptService {

    /**
     * Create accept.
     *
     * @param accept entity
     * @return saved entity
     */
    Accept createAccept(Accept accept);

    /**
     * Find accept by id.
     *
     * @param uuid id
     * @return accept entity
     */
    Accept find(UUID uuid);

    /**
     * Find all accepts by ids.
     *
     * @param ids list of id
     * @return accept list
     */
    List<Accept> findAll(List<UUID> ids);

    /**
     * Update accept.
     *
     * @param accept entity to update
     * @return accept entity
     */
    Accept update(Accept accept);

    /**
     * Delete accept by id.
     *
     * @param uuid id
     * @return true - if deleted
     */
    boolean delete(UUID uuid);

}
