package ru.otus.homework.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void whenDateStringIsEmptyThenNullIsReturned() {
        assertThat(DateUtils.parseDate(null)).isNull();

        assertThat(DateUtils.parseDate("")).isNull();
    }

    @Test
    public void whenIncorrectFormatThenNullIsReturned() {
        assertThat(DateUtils.parseDate("1")).isNull();
    }

    @Test
    public void whenDateStringIsCorrectThenCorrectDateIsReturned() {
        Date date = DateUtils.parseDate("2020-05-05");

        assertThat(date).isNotNull();
        assertThat(date)
                .hasYear(2020)
                .hasMonth(5)
                .hasDayOfMonth(5);
    }
}