package org.todo.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity(name = "todo")
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todoId")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoItem todoItem)) return false;
        return Objects.equals(getId(), todoItem.getId()) && Objects.equals(getUserId(), todoItem.getUserId()) && Objects.equals(getTitle(), todoItem.getTitle()) && Objects.equals(getDescription(), todoItem.getDescription()) && Objects.equals(getStartDate(), todoItem.getStartDate()) && Objects.equals(getEndDate(), todoItem.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getTitle(), getDescription(), getStartDate(), getEndDate());
    }
}
