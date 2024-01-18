package org.todo.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test_db_2")
public class TodoItem {

    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

}
