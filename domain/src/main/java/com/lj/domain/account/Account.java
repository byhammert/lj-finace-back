package com.lj.domain.account;

import com.lj.domain.AggregateRoot;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

import java.time.Instant;
import java.util.Objects;

public class Account extends AggregateRoot<AccountID> implements Cloneable {
    private String name;
    private String user;
    private String category;
    private String symbol;
    private long balance;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Account(final AccountID anAccountID,
                   final String aName,
                   final String user,
                   final String aCategory,
                   final String aSymbol,
                   final Long aBalance,
                   final boolean isActive,
                   final Instant aCreationDate,
                   final Instant anUpdateDate,
                   final Instant aDeleteDate) {
        super(anAccountID);
        this.name = aName;
        this.user = user;
        this.category = aCategory;
        this.symbol = aSymbol;
        this.balance = aBalance;
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");;
        this.updatedAt = Objects.requireNonNull(anUpdateDate, "'updateDate' should not be null");;
        this.deletedAt = aDeleteDate;
    }

    public static Account newAccount(final String aName,
                                     final String anUser,
                                     final String aCategory,
                                     final String aSymbol,
                                     final long aBalance,
                                     final boolean isActive){
        final var id = AccountID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Account(id, aName, anUser, aCategory, aSymbol, aBalance, isActive, now, now, deletedAt);
    }

    public static Account with(final AccountID anId,
                               final String aName,
                               final String anUser,
                               final String aCategory,
                               final String aSymbol,
                               final long aBalance,
                               final boolean isActive,
                               final Instant aCreatedAt,
                               final Instant anUpdatedAt,
                               final Instant aDeletedAt) {
        return new Account(anId,
                aName,
                anUser,
                aCategory,
                aSymbol,
                aBalance,
                isActive,
                aCreatedAt,
                anUpdatedAt,
                aDeletedAt);
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new AccountValidator(this, aHandler).validate();
    }

    public Account deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Account activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Account addAmount(final long amount) {
        this.balance = this.balance + amount;
        this.updatedAt = Instant.now();
        return this;
    }

    public Account update(final String aName,
                          final String aCategory,
                          final String aSymbol,
                          final long aBalance,
                          final boolean isActive) {
        this.name = aName;
        this.category = aCategory;
        this.symbol = aSymbol;
        this.balance = aBalance;
        this.updatedAt = Instant.now();

        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        return this;
    }

    @Override
    public Account clone() {
        try {
            return (Account) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
