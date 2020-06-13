package rd.epam.java.payment.dto;

import rd.epam.java.payment.domain.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Payment DTO.
 *
 * @author Dmitrii_Lopatin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentDto {

    private UUID acceptId;

    private Account senderAccount;

    private Account receiverAccount;

    private Double amount;

    private String key;
}
