package com.devsu.apirest2.dto.account;

import com.devsu.apirest2.util.enums.EnumAccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountPatchDTO {
    @Enumerated(EnumType.STRING)
    private EnumAccountType accountType;
    private Double initialBalance;
    private Boolean status;
    private Long customerId;
}
