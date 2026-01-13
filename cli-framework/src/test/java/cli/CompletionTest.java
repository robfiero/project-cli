package cli;

import cli.commands.TestGreetCommand;
import org.jline.reader.Candidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

class CompletionTest {

    @Test
    void greetCompletesFirstArgNames() {
        TestGreetCommand cmd = new TestGreetCommand();
        List<Candidate> cands = cmd.completeArgs(List.of(), 0, "R");

        assertTrue(
                cands.stream().anyMatch(c -> c.value().equals("Rob")),
                "Expected 'Rob' as a completion candidate");
    }

    @Test
    void greetCompletesFlags() {
        TestGreetCommand cmd = new TestGreetCommand();
        List<Candidate> cands = cmd.completeArgs(List.of("Rob"), 1, "--");

        assertTrue(
                cands.stream().anyMatch(c -> c.value().equals("--yell")),
                "Expected '--yell' as a completion candidate");
    }
}
