package com.lj.domain.post;

import com.lj.domain.AggregateRoot;
import com.lj.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Post extends AggregateRoot<PostID> implements Cloneable {

    private String description;
    private String category;
    private String subcategory;
    private RecurrenceType recurrence;
    private String account;
    private String user;
    private String note;
    private PostType postType;
    private boolean effective;
    private long amount;
    private Instant createdAt;
    private Instant updatedAt;

    public Post(final PostID aPostID,
                final String aDescription,
                final String aCategory,
                final String aSubcategory,
                final RecurrenceType aRecurrence,
                final String anAccount,
                final String anUser,
                final String aNote,
                final PostType aPostType,
                final boolean isEffective,
                final long anAmount,
                final Instant aCreationDate,
                final Instant anUpdateDate) {
        super(aPostID);
        this.description = aDescription;
        this.category = aCategory;
        this.subcategory = aSubcategory;
        this.recurrence = aRecurrence;
        this.account = anAccount;
        this.user = anUser;
        this.note = aNote;
        this.postType = aPostType;
        this.effective = isEffective;
        this.amount = anAmount;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");;
        this.updatedAt = Objects.requireNonNull(anUpdateDate, "'updateDate' should not be null");;

    }

    public static Post newPost(final String aDescription,
                               final String aCategory,
                               final String aSubcategory,
                               final RecurrenceType aRecurrence,
                               final String anAccount,
                               final String anUser,
                               final String aNote,
                               final PostType aPostType,
                               final boolean isEffective,
                               final long anAmount) {
        final var id = PostID.unique();
        final var now = Instant.now();
        return new Post(id,
                        aDescription,
                        aCategory,
                        aSubcategory,
                        aRecurrence,
                        anAccount,
                        anUser,
                        aNote,
                        aPostType,
                        isEffective,
                        anAmount,
                        now,
                        now);
    }

    public static Post with(final PostID anId,
                            final String aDescription,
                            final String aCategory,
                            final String aSubcategory,
                            final RecurrenceType aRecurrence,
                            final String anAccount,
                            final String anUser,
                            final String aNote,
                            final PostType aPostType,
                            final boolean isEffective,
                            final long anAmount,
                            final Instant aCreatedAt,
                            final Instant anUpdatedAt) {
        return new Post(anId,
                aDescription,
                aCategory,
                aSubcategory,
                aRecurrence,
                anAccount,
                anUser,
                aNote,
                aPostType,
                isEffective,
                anAmount,
                aCreatedAt,
                anUpdatedAt);
    }

    public Post effective() {
        this.effective = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Post update(final String aDescription,
                       final String aCategory,
                       final String aSubcategory,
                       final RecurrenceType aRecurrence,
                       final String anAccount,
                       final String aNote,
                       final boolean isEffective,
                       final long anAmount) {
        this.description = aDescription;
        this.category = aCategory;
        this.subcategory = aSubcategory;
        this.recurrence = aRecurrence;
        this.account = anAccount;
        this.note = aNote;
        this.effective = isEffective;
        this.amount = anAmount;
        this.updatedAt = Instant.now();

        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new PostValidator(this, aHandler).validate();
    }

    @Override
    public Post clone() {
        try {
            return (Post) super.clone();
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

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
