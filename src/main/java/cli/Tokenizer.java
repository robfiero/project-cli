package cli;

import java.util.ArrayList;
import java.util.List;

public final class Tokenizer {
    private Tokenizer() {
    }

    public static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        if (input == null || input.isBlank())
            return tokens;

        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        char quoteChar = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (inQuotes) {
                if (c == quoteChar) {
                    inQuotes = false;
                } else if (c == '\\' && i + 1 < input.length()) {
                    char next = input.charAt(i + 1);
                    switch (next) {
                        case 'n' -> {
                            current.append('\n');
                            i++;
                        }
                        case 't' -> {
                            current.append('\t');
                            i++;
                        }
                        case '\\', '"', '\'' -> {
                            current.append(next);
                            i++;
                        }
                        default -> current.append(c);
                    }
                } else {
                    current.append(c);
                }
            } else {
                if (Character.isWhitespace(c)) {
                    if (current.length() > 0) {
                        tokens.add(current.toString());
                        current.setLength(0);
                    }
                } else if (c == '"' || c == '\'') {
                    inQuotes = true;
                    quoteChar = c;
                } else {
                    current.append(c);
                }
            }
        }

        if (current.length() > 0)
            tokens.add(current.toString());
        return tokens;
    }
}
