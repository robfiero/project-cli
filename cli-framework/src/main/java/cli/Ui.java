package cli;

import java.io.PrintWriter;

public interface Ui {
    PrintWriter out();

    PrintWriter err();

    void info(String msg);

    void success(String msg);

    void warn(String msg);

    void error(String msg);
}
