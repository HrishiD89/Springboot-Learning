package com.app.bookfinder.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("Borrow")
public class Borrow {
    @Id
    private String id;

    @Indexed
    private String bookId;

    @Indexed
    private String memberId;

    private Instant borrowedAt;
    private Instant dueAt;
    private Instant returnedAt;

    public Borrow(){}

    public Borrow(String id, String bookId, String memberId, Instant borrowAt, Instant dueAt, Instant returnAt) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowedAt = borrowAt;
        this.dueAt = dueAt;
        this.returnedAt = returnAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Instant getBorrowAt() {
        return borrowedAt;
    }

    public void setBorrowAt(Instant borrowAt) {
        this.borrowedAt = borrowAt;
    }

    public Instant getDueAt() {
        return dueAt;
    }

    public void setDueAt(Instant dueAt) {
        this.dueAt = dueAt;
    }

    public Instant getReturnAt() {
        return returnedAt;
    }

    public void setReturnAt(Instant returnAt) {
        this.returnedAt = returnAt;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", borrowedAt=" + borrowedAt +
                ", dueAt=" + dueAt +
                ", returnedAt=" + returnedAt +
                '}';
    }
}

