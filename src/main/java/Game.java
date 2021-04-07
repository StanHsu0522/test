import java.util.PriorityQueue;

enum State {
    DRAW, P1WIN, P2WIN, ERROR
}

public class Game {

    private String plyr1;
    private String plyr2;
    private State finalState;

    Game(){
        finalState = State.ERROR;
    }

    public void setPlyr1(String input) {
        validateInput(input);
        plyr1 = input;
    }

    public void setPlyr2(String input) {
        validateInput(input);
        plyr2 = input;
    }

    public State getFinalState() {
        return finalState;
    }

    public void process() {
        if (plyr1.equals("rock")) {
            if (plyr2.equals("rock")) {
                finalState = State.DRAW;
            }
            else if (plyr2.equals("paper")) {
                finalState = State.P2WIN;
            }
            else {
                finalState = State.P1WIN;
            }
        }
        else if (plyr1.equals("paper")) {
            if (plyr2.equals("rock")) {
                finalState = State.P1WIN;
            }
            else if (plyr2.equals("paper")) {
                finalState = State.DRAW;
            }
            else {
                finalState = State.P2WIN;
            }
        }
        else {
            if (plyr2.equals("rock")) {
                finalState = State.P2WIN;
            }
            else if (plyr2.equals("paper")) {
                finalState = State.P1WIN;
            }
            else {
                finalState = State.DRAW;
            }
        }
        printResult();
    }

    private void printResult() {
        switch (finalState) {
            case DRAW:
                System.out.println("Draw!!");
                break;
            case P1WIN:
                System.out.println("Player 1 win!");
                break;
            case P2WIN:
                System.out.println("Player 2 win!");
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    private void validateInput(String input)
            throws IllegalArgumentException {
        if (!(input.equals("rock") || input.equals("paper") || input.equals("scissors"))) {
            throw new IllegalArgumentException("Bad Choice!");
        }
    }
}
