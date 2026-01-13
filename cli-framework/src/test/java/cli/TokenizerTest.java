package cli;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenizerTest {

    @Test
    void tokenizesSimpleWords() {
        assertEquals(List.of("echo", "hi", "there"), Tokenizer.tokenize("echo hi there"));
    }

    @Test
    void tokenizesQuotedArgs() {
        assertEquals(List.of("echo", "hello world"), Tokenizer.tokenize("echo \"hello world\""));
    }

    @Test
    void tokenizesSingleQuotes() {
        assertEquals(List.of("echo", "hello world"), Tokenizer.tokenize("echo 'hello world'"));
    }
}
