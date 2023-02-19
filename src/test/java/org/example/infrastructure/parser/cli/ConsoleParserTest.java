package org.example.infrastructure.parser.cli;

import org.example.core.usecases.data.TaskActionType;
import org.example.core.usecases.data.TaskDTO;
import org.example.infrastructure.io.parser.cli.ConsoleParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConsoleParserTest {

    final ConsoleParser consoleParser = new ConsoleParser();
    @Test
    public void GivenActionThatExistShouldParseIt() {
        List<String> args = List.of("add");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADD,null, null, null, null, null, null);

        Assertions.assertEquals(consoleParser.parse(args), expected);
    }

    @Test
    public void GivenActionThatNotExistShouldThrow() {
        List<String> args = List.of("consult");

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> consoleParser.parse(args));


        Assertions.assertEquals("consult is not an existing action !", exception.getMessage());
    }

    @Test
    public void GivenTaskActionWhichNeedParentIdValueShouldParseItWithParentId() {
        List<String> args = List.of("add-sub", "519");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", null, null, null, null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedTaskIdValueShouldParseItWithParentId() {
        List<String> args = List.of("remove", "519");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.REMOVE, "519", null, null, null, null, null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueShouldParseIt() {
        List<String> args = List.of("add-sub", "519");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", null, null, null, null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithoutValueShouldThrows() {
        List<String> args = List.of("add-sub");
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> consoleParser.parse(args));


        Assertions.assertEquals("Missing value for action add-sub", exception.getMessage());
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndOneOptionWithValueShouldParseIt() {
        List<String> args = List.of("add-sub", "519", "-c", "content");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", null, "content", null, null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndTwoOptionWithValueShouldParseIt() {
        List<String> args = List.of("add-sub", "519", "-c", "content", "-d", "2021-01-01");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", "2021-01-01", "content", null, null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndThreeOptionWithValueShouldParseIt() {
        List<String> args = List.of("add-sub", "519", "-c", "content", "-d", "2021-01-01", "-s", "DONE");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", "2021-01-01", "content", "DONE", null);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndFourOptionWithValueShouldParseIt() {
        List<String> args = List.of("add-sub", "519", "-d", "2021-01-01", "-c", "content", "-s", "DONE", "-t", "tag");
        TaskDTO actual = consoleParser.parse(args);
        TaskDTO expected = new TaskDTO(TaskActionType.ADDSUBTASK, null, "519", "2021-01-01", "content", "DONE", "tag");

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndOneOptionWithoutValueShouldThrow() {
        List<String> args = List.of("add-sub", "519", "-c");
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> consoleParser.parse(args));


        Assertions.assertEquals("Missing value for option -c", exception.getMessage());
    }

    @Test
    public void GivenTaskActionAndOneOptionWithoutValueShouldThrow() {
        List<String> args = List.of("add", "-c");
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> consoleParser.parse(args));


        Assertions.assertEquals("Missing value for option -c", exception.getMessage());
    }

    @Test
    public void GivenTaskActionWhichNeedValueWithValueAndAnIncorrectOptionWithoutValueShouldThrow() {
        List<String> args = List.of("add-sub", "519", "-caz");
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> consoleParser.parse(args));


        Assertions.assertEquals("-caz is not an existing action !", exception.getMessage());
    }
}
