package cli.commands;

import cli.Command;
import cli.CommandContext;
import org.jline.reader.Candidate;

import java.util.ArrayList;
import java.util.List;

public final class AddCommand implements Command {
    @Override
    public String name() {
        return "add";
    }

    @Override
    public String description() {
        return "Add integers: add 1 2 3";
    }

    @Override
    public Object execute(List<String> args, CommandContext ctx) {
        if (args.isEmpty())
            return "Usage: add <int> <int> ...";

        long sum = 0;
        for (String a : args)
            sum += Long.parseLong(a);
        ctx.ui().success("OK");
        return sum;
    }

    @Override
    public List<Candidate> completeArgs(List<String> argsSoFar, int argIndex, String buffer) {
        // Suggest a few handy numbers; filters based on prefix automatically by us.
        List<String> suggestions = List.of("0", "1", "2", "5", "10", "100");
        List<Candidate> out = new ArrayList<>();
        for (String s : suggestions) {
            if (buffer == null || buffer.isEmpty() || s.startsWith(buffer)) {
                out.add(new Candidate(s));
            }
        }
        return out;
    }
}
