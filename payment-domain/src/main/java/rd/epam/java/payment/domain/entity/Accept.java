package rd.epam.java.payment.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_public_id", referencedColumnName = "payment_private_id")
    private Payment paymentPrivateId;

    @Column(name = "client_callback_url")
    private String clientCallbackUrl;

    @Column(name = "client_payment_url")
    private String clientPaymentUrl;

    @Column(name = "key")
    private String key;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<AcceptStatus> acceptStatuses = new ArrayList<>();
}
