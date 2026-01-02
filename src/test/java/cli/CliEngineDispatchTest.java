package cli;

import cli.commands.AddCommand;
import cli.commands.EchoCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CliEngineDispatchTest {

    @Test
    void printsReturnValueFromCommand() {
        CommandRegistry registry = new CommandRegistry()
                .register(new AddCommand())
                .register(new EchoCommand());

        CliEngine engine = new CliEngine(registry, "test");

        TestUi ui = new TestUi();
        CommandContext ctx = new CommandContext(ui, registry);

        engine.executeLine("add 1 2 3", ctx);

        assertTrue(ui.outText().contains("6"), "Expected sum to be printed");
        assertEquals("", ui.errText(), "Expected no errors");
    }

    @Test
    void unknownCommandWritesError() {
        CommandRegistry registry = new CommandRegistry();
        CliEngine engine = new CliEngine(registry, "test");

        TestUi ui = new TestUi();
        CommandContext ctx = new CommandContext(ui, registry);

        engine.executeLine("nope", ctx);

        assertTrue(ui.errText().toLowerCase().contains("unknown command"));
    }
}
