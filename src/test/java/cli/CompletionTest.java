package cli;

import cli.commands.GreetCommand;
import org.jline.reader.Candidate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

class CompletionTest {

    @Test
    void greetCompletesFirstArgNames() {
        GreetCommand cmd = new GreetCommand();
        List<Candidate> cands = cmd.completeArgs(List.of(), 0, "R");

        assertTrue(
                cands.stream().anyMatch(c -> c.value().equals("Rob")),
                "Expected 'Rob' as a completion candidate");
    }

    @Test
    void greetCompletesFlags() {
        GreetCommand cmd = new GreetCommand();
        List<Candidate> cands = cmd.completeArgs(List.of("Rob"), 1, "--");

        assertTrue(
                cands.stream().anyMatch(c -> c.value().equals("--yell")),
                "Expected '--yell' as a completion candidate");
    }
}
