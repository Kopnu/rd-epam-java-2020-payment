package rd.epam.java.payment.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * AccountStatus.Entity for account
 *
 * @author Mihail_Sevryugin
 */
@Data
@Entity
@Table(name = "pm_accounts")
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_id")
    private UUID accountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client clientId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "inn")
    private String inn;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<AccountStatus> accountStatuses = new ArrayList<>();
}
