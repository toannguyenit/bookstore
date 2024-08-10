package vn.edu.likelion.app.bookstore.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(nullable = false, updatable = false)
     Date createTime = new Date();

    @Column(nullable = true, insertable = false)
     Date updateTime;

    @Column(nullable = false)
     int isDeleted;
}
