package ru.otus.homework.model;

import lombok.Value;

@Value
public class SaveCommentRequest {
    String text;
    String bookId;
}
