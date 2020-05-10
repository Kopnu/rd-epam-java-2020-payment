package rd.epam.java.payment.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * AccountStatus.Entity for account status
 *
 * @author Mihail_Sevryugin
 */
@Data
@Entity
@Table(name = "pm_account_statuses")
public class AccountStatus implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Account id;

    @Column(name = "status")
    private String status;
}

