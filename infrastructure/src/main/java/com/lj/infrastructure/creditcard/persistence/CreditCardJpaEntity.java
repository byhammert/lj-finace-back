package com.lj.infrastructure.creditcard.persistence;

import com.lj.domain.creditcard.CreditCard;
import com.lj.domain.creditcard.CreditCardID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCardJpaEntity {
    @Id
    private String id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "account", nullable = false)
    private String account;
    @Column(name = "user_id", nullable = false)
    private String user;
    @Column(name = "closing_day", nullable = false)
    private int closingDay;
    @Column(name = "due_day", nullable = false)
    private int dueDay;
    @Column(name = "credit_limit", nullable = false)
    private long limit;

    public CreditCardJpaEntity() {
    }

    public CreditCardJpaEntity(final String anId,
                               final String aDescription,
                               final String anAccount,
                               final String anUser,
                               final int aClosingDay,
                               final int aDueDay,
                               final long aLimit) {
        this.id = anId;
        this.description = aDescription;
        this.account = anAccount;
        this.user = anUser;
        this.closingDay = aClosingDay;
        this.dueDay = aDueDay;
        this.limit = aLimit;
    }

    public  static CreditCardJpaEntity from(final CreditCard aCreditCard) {
        return new CreditCardJpaEntity(aCreditCard.getId().getValue(),
                                        aCreditCard.getDescription(),
                                        aCreditCard.getAccount(),
                                        aCreditCard.getUser(),
                                        aCreditCard.getClosingDay(),
                                        aCreditCard.getDueDay(),
                                        aCreditCard.getLimit());
    }

    public CreditCard toAggregate() {
        return CreditCard.with(CreditCardID.from(id),
                                description,
                                account,
                                user,
                                closingDay,
                                dueDay,
                                limit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getClosingDay() {
        return closingDay;
    }

    public void setClosingDay(int closingDay) {
        this.closingDay = closingDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
