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

/**
 * CliEngine is the main execution engine for a command-line interface application.
 * 
 * It manages the interactive command loop, handles user input, processes commands,
 * and displays results using a terminal UI. The engine supports both interactive mode
 * (via {@link #run()}) and programmatic command execution (via {@link #executeLine(String, CommandContext)}).
 * 
 * <p>Features:</p>
 * <ul>
 *   <li>Interactive command-line interface with line editing and tab completion</li>
 *   <li>Command registry lookup and execution</li>
 *   <li>Terminal UI for formatted output and prompts</li>
 *   <li>Exception handling for user interrupts (Ctrl-C) and EOF (Ctrl-D)</li>
 *   <li>Tokenization and parsing of user input</li>
 *   <li>Result formatting and error reporting</li>
 * </ul>
 * 
 * @author
 * @version 1.0
 */
public final class CliEngine {

    private final CommandRegistry registry;
    private final String appName;

    public CliEngine(CommandRegistry registry, String appName) {
        this.registry = registry;
        this.appName = appName;
    }

    /**
     * Starts the CLI engine and enters an interactive command loop.
     * 
     * Initializes a terminal UI with command completion support and waits for user input.
     * Processes commands entered by the user, tokenizes them, and executes the corresponding
     * registered command with its arguments.
     * 
     * The method handles the following user interrupts:
     * - Ctrl-C (UserInterruptException): Displays a warning and continues the loop
     * - Ctrl-D (EndOfFileException): Gracefully exits the loop
     * 
     * Each command line is tokenized into a command name and argument list. If the command
     * is found in the registry, it is executed with the provided arguments and command context.
     * The result of command execution is printed to the output stream if non-null.
     * 
     * Any exceptions thrown during command execution are caught and displayed as error messages
     * to the user, allowing the CLI to remain responsive for further input.
     * 
     * @throws Exception if an error occurs during terminal initialization or line reading
     */
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

    /**
     * Executes a single line of command input.
     * 
     * Tokenizes the input line, retrieves the corresponding command from the registry,
     * and executes it with the provided arguments. If the command produces a result,
     * it is printed to the output. Any exceptions during execution are caught and
     * reported as errors to the user interface.
     * 
     * @param line the command line string to execute (may contain multiple tokens)
     * @param ctx the command context containing UI and state information
     */
    public void executeLine(String line, CommandContext ctx) {
        List<String> tokens = Tokenizer.tokenize(line);
        if (tokens.isEmpty())
            return;

        String cmdName = tokens.get(0);
        List<String> args = tokens.subList(1, tokens.size());

        Command cmd = registry.find(cmdName).orElse(null);
        if (cmd == null) {
            ctx.ui().error("Unknown command: " + cmdName + " (type 'help')");
            return;
        }

        try {
            Object result = cmd.execute(args, ctx);
            if (result != null) {
                ctx.ui().out().println(result);
                ctx.ui().out().flush();
            }
        } catch (Exception ex) {
            ctx.ui().error("Error: " + ex.getMessage());
        }
    }
}
