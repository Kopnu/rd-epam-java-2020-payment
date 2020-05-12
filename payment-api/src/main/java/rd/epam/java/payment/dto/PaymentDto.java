package rd.epam.java.payment.dto;

import java.util.UUID;

/**
 * Payment DTO.
 *
 * @author Dmitrii_Lopatin
 */
public class PaymentDto {
    private UUID senderAccountId;
    private UUID receiverAccountId;
    private Double amount;
    private String key;
    private UUID acceptId;
}
