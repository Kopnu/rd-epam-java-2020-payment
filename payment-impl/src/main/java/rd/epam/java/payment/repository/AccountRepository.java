package rd.epam.java.payment.repository;

import rd.epam.java.payment.domain.entity.Account;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * AccountRepository  for operating with pm_accounts table in database
 *
 * @author Mihail_Sevryugin
 */
@Slf4j
public class AccountRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("payment-unit").createEntityManager();

    /**
     * Add account record into database
     *
     * @param account - record for saving
     */
    public void save(Account account) {
        try {
            log.info("save() - save account {}", account);
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during saving: ", e);
        }
    }

    /**
     * Finds accounts in database by his id
     *
     * @param id of account
     * @return found account or empty object
     */
    public Optional<Account> findById(Integer id) {
        try {
            log.info("findById() - find account by id = {}", id);
            Account account = entityManager.find(Account.class, id);
            return Optional.ofNullable(account);
        } catch (Exception e) {
            log.warn("Error during searching by id: ", e);
        }
        return Optional.empty();
    }

    /**
     * Finds list of clients in database
     *
     * @param ids - list of id
     * @return list of accounts or empty list
     */
    public List<Account> findByIdList(List<Integer> ids) {
        try {
            log.info("findByList() - find list of accounts by id = {},ids");
            TypedQuery<Account> query = entityManager.createQuery("Select b from pm_accounts b Where b.account_id=:ids", Account.class);
            return query.setParameter("ids", ids).getResultList();
        } catch (Exception e) {
            log.warn("Error during searching by id list: ", e);
        }
        return Collections.emptyList();
    }

    /**
     * Updates one account record in database
     *
     * @param account for updating
     * @return updated account or empty object
     */
    public Optional<Account> update(Account account) {
        try {
            log.info("update() - update account {}", account);
            entityManager.getTransaction().begin();
            entityManager.merge(account);
            entityManager.getTransaction().commit();
            return Optional.of(account);
        } catch (Exception e) {
            log.warn("Error during updating: ", e);
        }
        return Optional.empty();
    }

    /**
     * Deletes one account record
     *
     * @param id of record for deleting
     */
    public void delete(Integer id) {
        try {
            log.info("delete() - delete account by id = {}", id);
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Account.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            log.warn("Error during deleting: ", e);
        }
    }
}