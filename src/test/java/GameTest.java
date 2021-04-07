import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    private static Stream<Arguments> legalInputProvider() {
        return Stream.of(
                Arguments.of("rock"),
                Arguments.of("paper"),
                Arguments.of("scissors")
        );
    }

    private static Stream<Arguments> illegalInputProvider() {
        return Stream.of(
                Arguments.of("rocks"),
                Arguments.of("aaa"),
                Arguments.of("asdgaggg"),
                Arguments.of("peko"),
                Arguments.of("papers")
        );
    }

    private static Stream<Arguments> winProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"rock", "scissors"}),
                Arguments.of((Object) new String[]{"scissors", "paper"}),
                Arguments.of((Object) new String[]{"paper", "rock"})
        );
    }

    private static Stream<Arguments> loseProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"scissors", "rock"}),
                Arguments.of((Object) new String[]{"paper", "scissors"}),
                Arguments.of((Object) new String[]{"rock", "paper"})
        );
    }

    private static Stream<Arguments> drawProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"scissors", "scissors"}),
                Arguments.of((Object) new String[]{"paper", "paper"}),
                Arguments.of((Object) new String[]{"rock", "rock"})
        );
    }

    @ParameterizedTest
    @MethodSource("legalInputProvider")
    public void Player1LegalInput(String input) {
        Game game = new Game();
        game.setPlyr1(input);
    }

    @ParameterizedTest
    @MethodSource("legalInputProvider")
    public void Player2LegalInput(String input) {
        Game game = new Game();
        game.setPlyr2(input);
    }

    @ParameterizedTest
    @MethodSource("illegalInputProvider")
    public void whenExceptionThrown_thenPlayer1IllegalInput(String input) {
        Game game = new Game();
        Exception ex = assertThrows(IllegalArgumentException.class, ()->game.setPlyr1(input));
    }

    @ParameterizedTest
    @MethodSource("illegalInputProvider")
    public void whenExceptionThrown_thenPlayer2IllegalInput(String input) {
        Game game = new Game();
        Exception ex = assertThrows(IllegalArgumentException.class, ()->game.setPlyr2(input));
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={arguments}")
    @MethodSource("winProvider")
    public void allP1Win_RunTest(String[] input) {
        Game game = new Game();
        game.setPlyr1(input[0]);
        game.setPlyr2(input[1]);
        game.process();
        assertEquals(State.P1WIN, game.getFinalState());
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={arguments}")
    @MethodSource("loseProvider")
    public void allP1Lose_RunTest(String[] input) {
        Game game = new Game();
        game.setPlyr1(input[0]);
        game.setPlyr2(input[1]);
        game.process();
        assertEquals(State.P2WIN, game.getFinalState());
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={arguments}")
    @MethodSource("drawProvider")
    public void allDraw_RunTest(String[] input) {
        Game game = new Game();
        game.setPlyr1(input[0]);
        game.setPlyr2(input[1]);
        game.process();
        assertEquals(State.DRAW, game.getFinalState());
    }
}
