package rd.epam.java.payment.domain.entity;

import lombok.Data;
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
import java.util.UUID;

@Data
@Entity
@Table(name = "pm_accepts")
public class Accept {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "accept_id")
    private UUID acceptId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_private_id")
    private UUID paymentPrivateId;

    private String clientCallbackUrl;

    private String clientPaymentUrl;

    private String key;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accept_status_id")
    private String acceptStatusId;
}
