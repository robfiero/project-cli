package cli.commands;

import cli.Command;
import cli.CommandContext;
import org.jline.reader.Candidate;

import java.util.List;

public final class TestEchoCommand implements Command {
    @Override
    public String name() {
        return "echo";
    }

    @Override
    public String description() {
        return "Echo arguments for tests";
    }

    @Override
    public Object execute(List<String> args, CommandContext ctx) {
        return String.join(" ", args);
    }

    @Override
    public List<Candidate> completeArgs(List<String> argsSoFar, int argIndex, String buffer) {
        return List.of();
    }
}
