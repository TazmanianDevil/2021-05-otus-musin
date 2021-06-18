package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.TestingSystem;

import javax.validation.constraints.Size;

@ShellComponent
@RequiredArgsConstructor
public class TestingSystemCommands {

    private final TestingSystem testingSystem;

    private User user;

    @ShellMethod(value = "Login testing system command using name and surname.", key = {"l", "li", "login"})
    public String login(@ShellOption @Size(min = 2, max = 20) String name,
                        @ShellOption @Size(min = 2, max = 40) String surname) {
        this.user = new User(name, surname);
        return String.format("Welcome, %s", name);
    }

    @ShellMethod(value = "Logout testing system.", key = {"lo", "logout"})
    @ShellMethodAvailability(value = "isUserLoggedIn")
    public String logout() {
        this.user = null;
        return "You have successfully logout";
    }

    @ShellMethod(value = "Start testing.", key = {"t", "test"})
    @ShellMethodAvailability("isUserLoggedIn")
    public String test() {
        testingSystem.test(user);
        return "Goodbye!";
    }

    private Availability isUserLoggedIn() {
        return user == null || StringUtils.isEmpty(user.getName()) ?
                Availability.unavailable("You must be logged in") :
                Availability.available();
    }

}
