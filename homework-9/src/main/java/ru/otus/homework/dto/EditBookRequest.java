package ru.otus.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditBookRequest {
    private long id;
    private String title;
    private String author;
    private long genre;
}
