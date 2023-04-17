package com.lj.infrastructure.account.persistence;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "account")
public class AccountJpaEntity {
    @Id
    private String id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "user_id", nullable = false)
    private String user;
    @Column(name = "category", nullable = false, length = 20)
    private String category;
    @Column(name = "symbol", nullable = false, length = 20)
    private String symbol;
    @Column(name = "balance")
    private long balance;
    @Column(name = "active")
    private boolean active;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    @Column(name = "deleted_at")
    private Instant deletedAt;

    public AccountJpaEntity() {}

    public AccountJpaEntity(final String id,
                            final String name,
                            final String user,
                            final String category,
                            final String symbol,
                            final long balance,
                            final boolean active,
                            final Instant createdAt,
                            final Instant updatedAt,
                            final Instant deletedAt) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.category = category;
        this.symbol = symbol;
        this.balance = balance;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static AccountJpaEntity from(final Account anAccount) {
        return new AccountJpaEntity(
                anAccount.getId().getValue(),
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

    public Account toAggregate() {
        return Account.with(
                AccountID.from(id),
                name,
                user,
                category,
                symbol,
                balance,
                active,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
