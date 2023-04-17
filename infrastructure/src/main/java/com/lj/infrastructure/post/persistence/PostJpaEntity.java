package com.lj.infrastructure.post.persistence;

import com.lj.domain.post.Post;
import com.lj.domain.post.PostID;
import com.lj.domain.post.PostType;
import com.lj.domain.post.RecurrenceType;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post")
public class PostJpaEntity {
    @Id
    private String id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "post_category", nullable = false)
    private String category;
    @Column(name = "post_subcategory", nullable = false)
    private String subcategory;
    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence", nullable = false)
    private RecurrenceType recurrence;
    @Column(name = "account", nullable = false)
    private String account;
    @Column(name = "user_id", nullable = false)
    private String user;
    @Column(name = "note")
    private String note;
    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", nullable = false)
    private PostType postType;
    @Column(name = "amount")
    private long amount;
    @Column(name = "effective")
    private boolean effective;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public PostJpaEntity() {}

    public PostJpaEntity(String id,
                         String description,
                         String category,
                         String subcategory,
                         RecurrenceType recurrence,
                         String account,
                         String user,
                         String note,
                         PostType postType,
                         long amount,
                         boolean effective,
                         Instant createdAt,
                         Instant updatedAt) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.subcategory = subcategory;
        this.recurrence = recurrence;
        this.account = account;
        this.user = user;
        this.note = note;
        this.postType = postType;
        this.amount = amount;
        this.effective = effective;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostJpaEntity from(final Post aPost) {
        return new PostJpaEntity(
                aPost.getId().getValue(),
                aPost.getDescription(),
                aPost.getCategory(),
                aPost.getSubcategory(),
                aPost.getRecurrence(),
                aPost.getAccount(),
                aPost.getUser(),
                aPost.getNote(),
                aPost.getPostType(),
                aPost.getAmount(),
                aPost.isEffective(),
                aPost.getCreatedAt(),
                aPost.getUpdatedAt()

        );
    }

    public Post toAggregate() {
        return Post.with(
                PostID.from(id),
                description,
                category,
                subcategory,
                recurrence,
                account,
                user,
                note,
                postType,
                effective,
                amount,
                createdAt,
                updatedAt
        );
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public RecurrenceType getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(RecurrenceType recurrence) {
        this.recurrence = recurrence;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
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
}
