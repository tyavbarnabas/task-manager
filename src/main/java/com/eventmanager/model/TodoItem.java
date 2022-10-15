package com.eventmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name ="Todo_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean complete;
    private boolean pending;
    private String createdDate;
    private String updatedDate;
    private String completedDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;



    public TodoItem(String description,String title) {
        this.description = description;
        this.title=title;
    }



    public boolean isComplete() {
        return complete;
    }

    public boolean isPending() {
        return pending;
    }

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, description='%s', complete='%s', pending = '%s',createdDate='%s', updatedDate='%s',completedDate,status = '%s'}",
                id,description,complete,pending,createdDate,updatedDate,completedDate,status);
    }
}
