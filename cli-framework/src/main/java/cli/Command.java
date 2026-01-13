package cli;

import org.jline.reader.Candidate;

import java.util.List;

public interface Command {
    String name();

    String description();

    /**
     * Execute the command.
     *
     * @param args arguments passed to the command (excluding the command name)
     * @param ctx  command context (UI, registry, etc.)
     * @return the result of executing the command; may be a message, a value (e.g.
     *         sum),
     *         or null if there's no meaningful return value
     * @throws Exception if an error occurs during execution
     */
    Object execute(List<String> args, CommandContext ctx) throws Exception;

    /**
     * Provide completion candidates for this command's args.
     * 
     * @param argsSoFar args already present (excluding command name)
     * @param argIndex  index of arg being completed (0-based)
     * @param buffer    token currently being completed (may be empty)
     * @return a list of Candidate objects representing possible completions
     *         for theurrent argument, or an empty list if none are available
     */
    default List<Candidate> completeArgs(List<String> argsSoFar, int argIndex, String buffer) {
        return List.of();
    }
}
