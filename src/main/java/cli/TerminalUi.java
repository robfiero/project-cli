package cli;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import java.io.PrintWriter;

public final class TerminalUi {
    private final Terminal terminal;
    private final PrintWriter out;
    private final PrintWriter err;

    public TerminalUi(Terminal terminal) {
        this.terminal = terminal;
        this.out = terminal.writer();
        this.err = terminal.writer();
    }

    public PrintWriter out() {
        return out;
    }

    public PrintWriter err() {
        return err;
    }

    public void info(String msg) {
        out.println(color(msg, AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN)));
        out.flush();
    }

    public void success(String msg) {
        out.println(color(msg, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)));
        out.flush();
    }

    public void warn(String msg) {
        err.println(color(msg, AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)));
        err.flush();
    }

    public void error(String msg) {
        err.println(color(msg, AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)));
        err.flush();
    }

    public String prompt(String appName) {
        // Example prompt: [demo] >
        return new AttributedStringBuilder()
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE).bold())
                .append("[").append(appName).append("]")
                // this was default color, changed to black.
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK).bold())
                .append(" ")
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.MAGENTA).bold())
                .append("> ")
                .toAnsi(terminal);
    }

    private String color(String s, AttributedStyle style) {
        return new AttributedString(s, style).toAnsi(terminal);
    }
}
