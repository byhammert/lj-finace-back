package com.lj.application.post.create.creditcard;

import com.lj.domain.post.RecurrenceType;

import java.time.LocalDate;

public record CreateCreditCardPostCommand(
        String creditCardId,
        String description,
        String category,
        String subcategory,
        RecurrenceType recurrence,
        String account,
        String user,
        String note,
        long amount,
        int installment,
        LocalDate dueDate
) {

    public static CreateCreditCardPostCommand with(final String creditCardId,
                                                   final String aDescription,
                                                   final String aCategory,
                                                   final String aSubcategory,
                                                   final RecurrenceType aRecurrence,
                                                   final String anAccount,
                                                   final String anUser,
                                                   final String aNote,
                                                   final long anAmount,
                                                   final int anInstallment,
                                                   final LocalDate aDueDate) {

        return new CreateCreditCardPostCommand(creditCardId,
                aDescription,
                aCategory,
                aSubcategory,
                aRecurrence,
                anAccount,
                anUser,
                aNote,
                anAmount,
                anInstallment,
                aDueDate);

    }

}
