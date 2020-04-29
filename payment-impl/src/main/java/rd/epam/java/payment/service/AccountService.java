package rd.epam.java.payment.service;

import rd.epam.java.payment.domain.entity.Account;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with {@link Account}.
 *
 * @author Sergei_Kornilov
 */
public interface AccountService {

    /**
     * Create account.
     *
     * @param account entity
     * @return saved entity
     */
    Account createAccount(Account account);

    /**
     * Find account by id.
     *
     * @param uuid id
     * @return account entity
     */
    Account find(UUID uuid);

    /**
     * Find all accounts by ids.
     *
     * @param ids list of id
     * @return account list
     */
    List<Account> findAll(List<UUID> ids);

    /**
     * Update account.
     *
     * @param account entity to update
     * @return account entity
     */
    Account update(Account account);

    /**
     * Delete account by id.
     *
     * @param uuid id
     * @return true - if deleted
     */
    boolean delete(UUID uuid);

}
