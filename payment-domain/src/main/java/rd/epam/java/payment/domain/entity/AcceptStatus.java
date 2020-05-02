package rd.epam.java.payment.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Entity for table pm_accept_statuses
 *
 * @author Aleksey_Lukyanov
 */
@Data
@Entity
@Table(name = "pm_accept_statuses")
public class AcceptStatus {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    private String status;
}
