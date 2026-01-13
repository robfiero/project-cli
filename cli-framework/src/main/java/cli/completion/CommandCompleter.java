package cli.completion;

import cli.Command;
import cli.CommandRegistry;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.ArrayList;
import java.util.List;

public final class CommandCompleter implements Completer {

    private final CommandRegistry registry;

    public CommandCompleter(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        List<String> words = line.words();
        int wordIndex = line.wordIndex();
        String buffer = line.word() == null ? "" : line.word();

        // Completing command name (first token)
        if (wordIndex == 0) {
            for (String name : registry.names()) {
                if (buffer.isEmpty() || name.startsWith(buffer)) {
                    candidates.add(new Candidate(name));
                }
            }
            return;
        }

        // Completing args for a known command
        String cmdName = words.isEmpty() ? "" : words.get(0);
        Command cmd = registry.find(cmdName).orElse(null);
        if (cmd == null)
            return;

        // argsSoFar excludes command itself
        List<String> argsSoFar = new ArrayList<>();
        for (int i = 1; i < words.size(); i++) {
            argsSoFar.add(words.get(i));
        }

        int argIndex = wordIndex - 1; // because wordIndex 1 corresponds to arg 0
        candidates.addAll(cmd.completeArgs(argsSoFar, argIndex, buffer));
    }
}
