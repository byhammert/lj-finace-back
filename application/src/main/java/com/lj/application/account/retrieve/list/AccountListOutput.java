package com.lj.application.account.retrieve.list;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

import java.time.Instant;

public record AccountListOutput(
        AccountID id,
        String name,
        String category,
        String symbol,
        long balance,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static AccountListOutput from(final Account anAccount) {
        return new AccountListOutput(
            anAccount.getId(),
            anAccount.getName(),
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
