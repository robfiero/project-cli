package cli;

/**
 * Provides the execution context for commands, containing references to the UI and command registry.
 * 
 * This class serves as a dependency holder that supplies commands with access to the user interface
 * and the command registry needed for command execution and interaction.
 * 
 * <p>Instances of this class are immutable and thread-safe.</p>
 */
public final class CommandContext {
    private final Ui ui;
    private final CommandRegistry registry;

    public CommandContext(Ui ui, CommandRegistry registry) {
        this.ui = ui;
        this.registry = registry;
    }

    public Ui ui() {
        return ui;
    }

    public CommandRegistry registry() {
        return registry;
    }
}
