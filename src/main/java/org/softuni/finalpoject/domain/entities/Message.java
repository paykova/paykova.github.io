package org.softuni.finalpoject.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    private String title;
    private String content;
    private User author;

    public Message() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            nullable = false
    )
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
