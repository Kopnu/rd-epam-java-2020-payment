package rd.epam.java.payment.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * AccountStatus.Entity for account status
 *
 * @author Mihail_Sevryugin
 */
@Data
@Entity
@Table(name = "pm_account_statuses")
public class AccountStatus {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String status;
}

