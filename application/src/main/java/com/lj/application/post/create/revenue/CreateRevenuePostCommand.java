package com.lj.application.post.create.revenue;

import com.lj.domain.post.RecurrenceType;

import java.time.LocalDate;

public record CreateRevenuePostCommand(
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

    public static CreateRevenuePostCommand with(final String aDescription,
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

        return new CreateRevenuePostCommand(aDescription,
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
