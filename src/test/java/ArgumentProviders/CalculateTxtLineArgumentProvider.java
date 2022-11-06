package ArgumentProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;


public class CalculateTxtLineArgumentProvider implements ArgumentsProvider {
    public static class ArgForTesting {
        public String line;
        public String expectedLine;

        public ArgForTesting(String line, String expectedLine) {
            this.line = line;
            this.expectedLine = expectedLine;
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new ArgForTesting("1 + 2", "3.0")),
                Arguments.of(new ArgForTesting("(1 + 2) + 3.0", "6.0")),
                Arguments.of(new ArgForTesting("((1 + 2) + 3.0) / 2 * 1", "3.0")),

                Arguments.of(new ArgForTesting("2 / 0", "Division by zero!")),
                Arguments.of(new ArgForTesting("(2 + 10000) / 0 * 12", "Division by zero!")),
                Arguments.of(new ArgForTesting("(2 / 0) Hi everybody!", "Division by zero! Hi everybody!")),

                Arguments.of(new ArgForTesting("123 hi 14", "123 hi 14")),
                Arguments.of(new ArgForTesting("100 + 100 hi 14-1", "200 hi 13")),
                Arguments.of(new ArgForTesting("sin 100 + 12", "sin 112"))
        );
    }
}
