package cli.commands;

import cli.Command;
import cli.CommandContext;
import org.jline.reader.Candidate;

import java.util.ArrayList;
import java.util.List;

public final class TestGreetCommand implements Command {
    @Override
    public String name() {
        return "greet";
    }

    @Override
    public String description() {
        return "Greet someone for tests";
    }

    @Override
    public Object execute(List<String> args, CommandContext ctx) {
        if (args.isEmpty())
            return "Usage: greet <name> [--yell]";

        boolean yell = args.contains("--yell");
        String name = args.get(0);

        String msg = "Hello, " + name + "!";
        if (yell)
            msg = msg.toUpperCase();

        ctx.ui().info(msg);
        return null;
    }

    @Override
    public List<Candidate> completeArgs(List<String> argsSoFar, int argIndex, String buffer) {
        List<Candidate> cands = new ArrayList<>();

        if (argIndex == 0) {
            for (String n : List.of("Rob", "Claire", "Runner", "Friend")) {
                if (buffer.isEmpty() || n.startsWith(buffer))
                    cands.add(new Candidate(n));
            }
            return cands;
        }

        for (String flag : List.of("--yell")) {
            if (buffer.isEmpty() || flag.startsWith(buffer))
                cands.add(new Candidate(flag));
        }
        return cands;
    }
}
