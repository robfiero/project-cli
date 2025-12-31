package cli;

public final class CommandContext {
    private final TerminalUi ui;
    private final CommandRegistry registry;

    public CommandContext(TerminalUi ui, CommandRegistry registry) {
        this.ui = ui;
        this.registry = registry;
    }

    public TerminalUi ui() {
        return ui;
    }

    public CommandRegistry registry() {
        return registry;
    }
}
