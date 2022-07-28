package moine.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name = "created_time", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime createdDate;


    @Column(name = "updated_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")

    private LocalDateTime updatedDate;

    @PrePersist
    private void beforeSaving() {
        this.createdDate = LocalDateTime.now();
        System.out.println("createdDate = " + createdDate);
//        createdBy = Thread.currentThread().getName(); //todo: put your logic here
    }

    @PreUpdate
    private void beforeUpdating() {
        this.updatedDate = LocalDateTime.now();
        System.out.println("updatedDate = " + updatedDate);
//        modifiedBy = Thread.currentThread().getName(); //todo: put your logic here
    }


}
