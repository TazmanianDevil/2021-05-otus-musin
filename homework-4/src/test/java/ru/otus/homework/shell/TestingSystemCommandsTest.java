package ru.otus.homework.shell;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.ParameterMissingResolutionException;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.TestingSystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TestingSystemCommandsTest {

    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGOUT = "logout";
    private static final String GREETING_PATTERN = "Welcome, %s";
    private static final String NAME_IS_EMPTY_ERROR = "Parameter '--name string' should be specified";
    private static final String SURNAME_IS_EMPTY_ERROR = "Parameter '--surname string' should be specified";
    private static final String COMMAND_PATTERN = "%s %s %s";
    private static final String CUSTOM_NAME = "Peter";
    private static final String CUSTOM_SURNAME = "Vasin";
    private static final String COMMAND_TEST = "test";

    @MockBean
    private TestingSystem testingSystem;

    @Autowired
    private Shell shell;

    @Test
    void whenLoginCommandWithoutNameThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> COMMAND_LOGIN);

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterMissingResolutionException.class);
        Exception e = (ParameterMissingResolutionException) result;
        assertThat(e).hasMessageContaining(NAME_IS_EMPTY_ERROR);
    }

    @Test
    void whenLoginCommandWithoutSurnameThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, CUSTOM_NAME, ""));

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterMissingResolutionException.class);
        Exception e = (ParameterMissingResolutionException) result;
        assertThat(e).hasMessageContaining(SURNAME_IS_EMPTY_ERROR);
    }

    @Test
    void whenCorrectNameAnsSurnameThenGreetingIsShown() {
        String result = (String) shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, CUSTOM_NAME, CUSTOM_SURNAME));

        assertThat(result).isEqualTo(String.format(GREETING_PATTERN, CUSTOM_NAME));
    }

    @Test
    void whenNameIsShorterThenTwoCharactersThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, "s", "sss"));

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterValidationException.class);
        ParameterValidationException e = (ParameterValidationException) result;
        assertThat(e.getConstraintViolations()).isNotEmpty()
                .extracting("message").contains("размер должен находиться в диапазоне от 2 до 20");
    }

    @Test
    void whenNameIsLongerThenTwentyCharactersThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, StringUtils.repeat("s", 30), "sss"));

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterValidationException.class);
        ParameterValidationException e = (ParameterValidationException) result;
        assertThat(e.getConstraintViolations()).isNotEmpty()
                .extracting("message").contains("размер должен находиться в диапазоне от 2 до 20");
    }

    @Test
    void whenSurnameIsShorterThenTwoCharactersThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, "sss", "s"));

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterValidationException.class);
        ParameterValidationException e = (ParameterValidationException) result;
        assertThat(e.getConstraintViolations()).isNotEmpty()
                .extracting("message").contains("размер должен находиться в диапазоне от 2 до 40");
    }

    @Test
    void whenSurnameIsLongerThenFortyCharactersThenExceptionIsReturned() {
        Object result = shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, "sss", StringUtils.repeat("s", 50)));

        assertNotNull(result);
        assertThat(result).isInstanceOf(ParameterValidationException.class);
        ParameterValidationException e = (ParameterValidationException) result;
        assertThat(e.getConstraintViolations()).isNotEmpty()
                .extracting("message").contains("размер должен находиться в диапазоне от 2 до 40");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenUserStartTestIsWithoutLoggingThenCommandNotCurrentlyAvailableIsReturned() {
        Object result = shell.evaluate(() -> COMMAND_TEST);

        assertThat(result).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenUserLogoutWithoutLoggingInLoggingThenCommandNotCurrentlyAvailableIsReturned() {
        Object result = shell.evaluate(() -> COMMAND_LOGOUT);

        assertThat(result).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenUserLoggedOutThenLogoutMessageIsShown() {
        shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, CUSTOM_NAME, CUSTOM_SURNAME));

        String result = (String) shell.evaluate(() -> COMMAND_LOGOUT);
        assertThat(result).isEqualTo("You have successfully logout");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenUserStartTestThenTestIsRunAndGoodbyeMessageIsReturned() {
        shell.evaluate(() -> String.format(COMMAND_PATTERN, COMMAND_LOGIN, CUSTOM_NAME, CUSTOM_SURNAME));

        String result = (String) shell.evaluate(() -> COMMAND_TEST);

        assertThat(result).isEqualTo("Goodbye!");
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(testingSystem, times(1)).test(userCaptor.capture());
        assertThat(userCaptor.getValue()).isNotNull()
                .extracting("name", "surname")
                .contains(CUSTOM_NAME, CUSTOM_SURNAME);
    }

}