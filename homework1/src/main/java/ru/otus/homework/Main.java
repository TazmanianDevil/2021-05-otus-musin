package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.QuestionsPrinter;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionsPrinter questionsPrinter = context.getBean(QuestionsPrinter.class);
        questionsPrinter.printQuestion();
    }

}
