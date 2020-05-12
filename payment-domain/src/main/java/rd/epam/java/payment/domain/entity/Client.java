package rd.epam.java.payment.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entity for table pm_clients
 */
@Data
@Entity
@Table(name = "pm_clients")
@Accessors(chain = true)
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "client_id")
    private UUID id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
}
