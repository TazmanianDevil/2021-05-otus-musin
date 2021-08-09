package ru.otus.homework.model;

import lombok.Value;

@Value
public class UpdateCommentRequest {
    String bookId;
    String oldText;
    String newText;
}
