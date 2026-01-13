package cli;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;

public final class CommandRegistry {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public CommandRegistry register(Command command) {
        Objects.requireNonNull(command, "command");
        commands.put(command.name(), command);
        return this;
    }

    public Optional<Command> find(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public Collection<Command> all() {
        return Collections.unmodifiableCollection(commands.values());
    }

    public List<String> names() {
        return new ArrayList<>(commands.keySet());
    }
}
