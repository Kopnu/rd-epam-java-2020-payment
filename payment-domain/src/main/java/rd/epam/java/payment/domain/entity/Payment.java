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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Entity for table payments
 */
@Data
@Entity
@Table(name = "pm_payments")
@Accessors(chain = true)
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "payment_private_id")
    private UUID paymentPrivateId;

    @Column(name = "payment_public_id")
    private UUID paymentPublicId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client clientId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "account_id")
    private Account receiverAccountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_account_id", referencedColumnName = "account_id")
    private Account senderAccountId;

    private Double amount;

    private String key;
}
