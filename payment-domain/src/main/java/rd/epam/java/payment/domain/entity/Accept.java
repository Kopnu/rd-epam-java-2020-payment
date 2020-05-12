package rd.epam.java.payment.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Entity for table pm_accepts
 */
@Data
@Entity
@Table(name = "pm_accepts")
@Accessors(chain = true)
public class Accept {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "accept_id")
    private UUID acceptId;

    @OneToOne
    @JoinColumn(name = "payment_private_id")
    private Payment paymentPrivateId;

    private String clientCallbackUrl;

    private String clientPaymentUrl;

    private String key;

    @OneToOne
    @JoinColumn(name = "accept_status_id", referencedColumnName = "id")
    private AcceptStatus acceptStatus;
}
