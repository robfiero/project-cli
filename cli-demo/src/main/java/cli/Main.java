package cli;

import cli.commands.AddCommand;
import cli.commands.EchoCommand;
import cli.commands.ExitCommand;
import cli.commands.GreetCommand;
import cli.commands.HelpCommand;

public final class Main {

    public static final String APP_NAME = "Default CLI Implementation";

    public static void main(String[] args) throws Exception {
        CommandRegistry registry = new CommandRegistry()
                .register(new HelpCommand())
                .register(new EchoCommand())
                .register(new AddCommand())
                .register(new GreetCommand())
                .register(new ExitCommand());

        new CliEngine(registry, Main.APP_NAME).run();
    }
}
