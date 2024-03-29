package shop.mtcoding.bankapp.model.history;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class History {
    private Integer id;
    private Long amount;
    private Long wBalance; // 출금계좌 잔액
    private Long dBalance; // 입금계좌 잔액
    private Integer wAccountId;
    private Integer dAccountId;
    private Timestamp createdAt;
}
