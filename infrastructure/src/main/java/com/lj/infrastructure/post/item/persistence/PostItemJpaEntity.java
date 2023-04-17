package com.lj.infrastructure.post.item.persistence;

import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemID;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_item")
public class PostItemJpaEntity {
    @Id
    private String id;
    @Column(name = "post", nullable = false)
    private String post;
    @Column(name = "invoice")
    private String invoice;
    @Column(name = "number")
    private int number;
    @Column(name = "amount")
    private long amount;
    @Column(name = "effective")
    private boolean effective;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    public PostItemJpaEntity() {}

    public PostItemJpaEntity(String id,
                             String post,
                             int number,
                             long amount,
                             boolean effective,
                             LocalDateTime paymentDate,
                             LocalDate dueDate,
                             String invoice) {
        this.id = id;
        this.post = post;
        this.number = number;
        this.amount = amount;
        this.effective = effective;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.invoice = invoice;
    }

    public static PostItemJpaEntity from(final PostItem aPostItem) {
        return new PostItemJpaEntity(
                aPostItem.getId().getValue(),
                aPostItem.getPost(),
                aPostItem.getNumber(),
                aPostItem.getAmount(),
                aPostItem.isEffective(),
                aPostItem.getPaymentDate(),
                aPostItem.getDueDate(),
                aPostItem.getInvoice()

        );
    }

    public PostItem toAggregate() {
        return PostItem.with(
                PostItemID.from(id),
                post,
                number,
                amount,
                effective,
                paymentDate,
                dueDate,
                invoice
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
