package com.lj.application.account.retrieve.get;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

import java.time.Instant;

public record AccountOutput(
        AccountID id,
        String name,
        String user,
        String category,
        String symbol,
        long balance,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static AccountOutput from(final Account anAccount) {
        return new AccountOutput(
            anAccount.getId(),
            anAccount.getName(),
            anAccount.getUser(),
            anAccount.getCategory(),
            anAccount.getSymbol(),
            anAccount.getBalance(),
            anAccount.isActive(),
            anAccount.getCreatedAt(),
            anAccount.getUpdatedAt(),
            anAccount.getDeletedAt()
        );
    }
}
