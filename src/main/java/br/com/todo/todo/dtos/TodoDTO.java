package br.com.todo.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private UUID id;
    private String name;
    private String description;
    private int priority;
    private boolean status;
    private Date createdAt;

}
