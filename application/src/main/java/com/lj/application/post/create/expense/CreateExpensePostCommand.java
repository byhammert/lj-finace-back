package com.lj.application.post.create.expense;

import com.lj.domain.post.RecurrenceType;

import java.time.LocalDate;

public record CreateExpensePostCommand(
        String description,
        String category,
        String subcategory,
        RecurrenceType recurrence,
        String account,
        String user,
        String note,
        boolean effective,
        long amount,
        int installment,
        LocalDate dueDate
) {

    public static CreateExpensePostCommand with(final String aDescription,
                                                final String aCategory,
                                                final String aSubcategory,
                                                final RecurrenceType aRecurrence,
                                                final String anAccount,
                                                final String anUser,
                                                final String aNote,
                                                final boolean isEffective,
                                                final long anAmount,
                                                final int anInstallment,
                                                final LocalDate aDueDate) {

        return new CreateExpensePostCommand(aDescription,
                aCategory,
                aSubcategory,
                aRecurrence,
                anAccount,
                anUser,
                aNote,
                isEffective,
                anAmount,
                anInstallment,
                aDueDate);

    }

}
