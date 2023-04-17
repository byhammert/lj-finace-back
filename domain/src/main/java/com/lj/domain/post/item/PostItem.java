package com.lj.domain.post.item;

import com.lj.domain.AggregateRoot;
import com.lj.domain.post.Post;
import com.lj.domain.post.PostID;
import com.lj.domain.post.PostType;
import com.lj.domain.post.PostValidator;
import com.lj.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostItem extends AggregateRoot<PostItemID> implements Cloneable {

    private String post;

    private String invoice;
    private int number;
    private long amount;
    private boolean effective;
    private LocalDateTime paymentDate;
    private LocalDate dueDate;

    public PostItem(final PostItemID aPostItemID,
                    final String aPost,
                    final int aNumber,
                    final long anAmount,
                    final boolean isEffective,
                    final LocalDateTime aPaymentDate,
                    final LocalDate aDueDate,
                    final String anInvoice) {
        super(aPostItemID);
        this.post = aPost;
        this.number = aNumber;
        this.amount = anAmount;
        this.effective = isEffective;
        this.paymentDate = aPaymentDate;
        this.dueDate = aDueDate;
        this.invoice = anInvoice;
    }

    public static PostItem newPostItem(final String aPost,
                                       final int aNumber,
                                       final long anAmount,
                                       final boolean isEffective,
                                       final LocalDateTime aPaymentDate,
                                       final LocalDate aDueDate,
                                       final String anInvoice) {

        final var id = PostItemID.unique();

        LocalDateTime paymentDate = aPaymentDate;
        if (isEffective && aPaymentDate == null) {
            final var now = Instant.now();
            paymentDate = LocalDateTime.now();
        }

        return new PostItem(id,
                            aPost,
                            aNumber,
                            anAmount,
                            isEffective,
                            paymentDate,
                            aDueDate,
                            anInvoice

        );
    }

    public static PostItem with(final PostItemID anId,
                                final String aPost,
                                final int aNumber,
                                final long anAmount,
                                final boolean isEffective,
                                final LocalDateTime aPaymentDate,
                                final LocalDate aDueDate,
                                final String anInvoice) {
        return new PostItem(anId,
                            aPost,
                            aNumber,
                            anAmount,
                            isEffective,
                            aPaymentDate,
                            aDueDate,
                            anInvoice);
    }

    public PostItem update(final long anAmount,
                           final boolean isEffective,
                           final LocalDateTime aPaymentDate) {
        this.paymentDate = aPaymentDate;
        this.effective = isEffective;
        this.amount = anAmount;

        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new PostItemValidator(this, aHandler).validate();
    }

    @Override
    public PostItem clone() {
        try {
            return (PostItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
