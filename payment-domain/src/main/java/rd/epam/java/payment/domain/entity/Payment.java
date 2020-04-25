package rd.epam.java.payment.domain.entity;

import lombok.Data;

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
public class Payment {
    @Id
    @GeneratedValue
    @Column(name="payment_private_id")
    private UUID paymentPrivateId;

    @Column(name="payment_public_id")
    private UUID paymentPublicId;

    @Column(name="client_id")
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="client_id")
    private Client clientId;

    @Column(name="receiver_account_id")
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="receiver_account_id")
    private UUID receiverAccountId;

    @Column(name="sender_account_id")
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="sender_account_id")
    private UUID senderAccountId;

    @Column(name="amount")
    private Double amount;

    @Column(name="key")
    private String key;
}
