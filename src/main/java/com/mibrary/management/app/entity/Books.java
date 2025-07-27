package com.mibrary.management.app.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private Integer quantity;
    private boolean isAvailable;

    public boolean getIsAvailable() {
        return isAvailable;
    }

    //    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private List<User> users;

}
