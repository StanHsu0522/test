import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("=== Welcome to Rock-Paper-Scissors game ===\n" +
                "Enter Player1 choice (rock, paper, scissors):");
        game.setPlyr1(scanner.next());
        System.out.println("Enter Player2 choice (rock, paper, scissors):");
        game.setPlyr2(scanner.next());
        game.process();


        System.exit(0);
    }
}
