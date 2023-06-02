package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Account {

    int accountNumber;
    String accountPassword;
    int accountBalance;
    Timestamp accountCreatedAt;
}
