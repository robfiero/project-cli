package cli;

import cli.completion.CommandCompleter;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.List;

public final class CliEngine {

    private final CommandRegistry registry;
    private final String appName;

    public CliEngine(CommandRegistry registry, String appName) {
        this.registry = registry;
        this.appName = appName;
    }

    public void run() throws Exception {
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        TerminalUi ui = new TerminalUi(terminal);

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .completer(new CommandCompleter(registry))
                .build();

        CommandContext ctx = new CommandContext(ui, registry);

        ui.info("Type 'help' for commands. TAB to complete commands and arguments.");

        while (true) {
            String line;
            try {
                line = reader.readLine(ui.prompt(appName).toString());

            } catch (UserInterruptException e) {
                // Ctrl-C
                ui.warn("^C");
                continue;
            } catch (EndOfFileException e) {
                // Ctrl-D
                ui.info("bye");
                break;
            }

            List<String> tokens = Tokenizer.tokenize(line);
            if (tokens.isEmpty())
                continue;

            String cmdName = tokens.get(0);
            List<String> args = tokens.subList(1, tokens.size());

            Command cmd = registry.find(cmdName).orElse(null);
            if (cmd == null) {
                ui.error("Unknown command: " + cmdName + " (type 'help')");
                continue;
            }

            try {
                Object result = cmd.execute(args, ctx);
                if (result != null) {
                    // default result formatting (neutral color)
                    ui.out().println(result);
                    ui.out().flush();
                }
            } catch (Exception ex) {
                ui.error("Error: " + ex.getMessage());
            }
        }
    }
}
