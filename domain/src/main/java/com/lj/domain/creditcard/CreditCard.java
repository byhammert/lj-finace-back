package com.lj.domain.creditcard;

import com.lj.domain.AggregateRoot;
import com.lj.domain.validation.ValidationHandler;

public class CreditCard extends AggregateRoot<CreditCardID> implements Cloneable {

    private String description;
    private String account;
    private String user;
    private int closingDay;
    private int dueDay;
    private long limit;

    public CreditCard(final CreditCardID aCreditCardID,
                      final String aDescription,
                      final String anAccount,
                      final String anUser,
                      final int aClosingDay,
                      final int aDueDay,
                      final long aLimit) {
        super(aCreditCardID);
        this.description = aDescription;
        this.account = anAccount;
        this.user = anUser;
        this.closingDay = aClosingDay;
        this.dueDay = aDueDay;
        this.limit = aLimit;
    }

    public static CreditCard newCreditCard(final String aDescription,
                                           final String anAccount,
                                           final String anUser,
                                           final int aClosingDay,
                                           final int aDueDay,
                                           final long aLimit) {

        final var id = CreditCardID.unique();
        return new CreditCard(id,
                                aDescription,
                                anAccount,
                                anUser,
                                aClosingDay,
                                aDueDay,
                                aLimit);
    }

    public static CreditCard with(final CreditCardID anId,
                                  final String aDescription,
                                  final String anAccount,
                                  final String anUser,
                                  final int aClosingDay,
                                  final int aDueDay,
                                  final long aLimit) {

        return new CreditCard(anId,
                aDescription,
                anAccount,
                anUser,
                aClosingDay,
                aDueDay,
                aLimit);


    }

    public CreditCard updateLimit(long anAmount) {
        this.limit = limit + anAmount;
        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new CreditCardValidator(this, aHandler).validate();
    }

    @Override
    public CreditCard clone() {
        try {
            return (CreditCard) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
