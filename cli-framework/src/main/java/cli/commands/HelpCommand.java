package cli.commands;

import cli.Command;
import cli.CommandContext;

import java.util.Comparator;
import java.util.List;

public final class HelpCommand implements Command {
    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "List available commands";
    }

    @Override
    public Object execute(List<String> args, CommandContext ctx) {
        ctx.ui().info("Available commands:");
        ctx.registry().all().stream()
                .sorted(Comparator.comparing(Command::name))
                .forEach(c -> ctx.ui().out().printf("  %-10s %s%n", c.name(), c.description()));
        ctx.ui().out().flush();
        return null;
    }
}
