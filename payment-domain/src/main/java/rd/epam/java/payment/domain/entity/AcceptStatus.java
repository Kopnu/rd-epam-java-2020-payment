package rd.epam.java.payment.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * Entity for table pm_accept_statuses
 *
 * @author Aleksey_Lukyanov
 */
@Data
@Entity
@Table(name = "pm_accept_statuses")
public class AcceptStatus implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Accept id;

    private String status;

    public AcceptStatus(String acceptStatus) {
        this.status = acceptStatus;
    }
}
