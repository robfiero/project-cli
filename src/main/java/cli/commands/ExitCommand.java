package cli.commands;

import cli.Command;
import cli.CommandContext;

import java.util.List;

public final class ExitCommand implements Command {
    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "Exit the application";
    }

    @Override
    public Object execute(List<String> args, CommandContext ctx) {
        ctx.ui().success("Goodbye.");
        System.exit(0);
        return null;
    }
}
