package cli;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class TestUi implements Ui {
    private final StringWriter outBuffer = new StringWriter();
    private final StringWriter errBuffer = new StringWriter();
    private final PrintWriter out = new PrintWriter(outBuffer, true);
    private final PrintWriter err = new PrintWriter(errBuffer, true);

    public PrintWriter out() {
        return out;
    }

    public PrintWriter err() {
        return err;
    }

    public String outText() {
        return outBuffer.toString();
    }

    public String errText() {
        return errBuffer.toString();
    }

    public void info(String msg) {
        out.println(msg);
    }

    public void success(String msg) {
        out.println(msg);
    }

    public void warn(String msg) {
        err.println(msg);
    }

    public void error(String msg) {
        err.println(msg);
    }
}
