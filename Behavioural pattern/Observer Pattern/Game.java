import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Observer Interface
interface Observer {
    void update(String playerName, int newScore);
}

// Subject Interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String playerName, int score);
}

// Concrete Subject
class GameServer implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private Map<String, Integer> playerScores = new HashMap<>();
    private String playerName;
    private int score;

    public void setScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = playerScores.getOrDefault(playerName, 0) + score;

        if (this.score >= 50) {
            notifyObservers(playerName, 50);
            System.out.println("Player " + playerName + " has won with a score of " + this.score + "!");
            playerScores.put(playerName, 0); // Reset the score after winning
        } else {
            playerScores.put(playerName, this.score);
            notifyObservers(playerName, this.score);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String playerName, int score) {
        for (Observer observer : observers) {
            observer.update(playerName, score);
        }
    }
}

// Concrete Observer
class Player implements Observer {
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void update(String playerName, int newScore) {
        System.out.println("Player " + this.playerName + " received update: " + playerName + "'s new score is " + newScore);
    }
}

public class Game {
    public static void main(String[] args) {
        GameServer gameServer = new GameServer();

        // Create players and register them as observers
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");

        gameServer.addObserver(player1);
        gameServer.addObserver(player2);
        gameServer.addObserver(player3);

        Scanner scanner = new Scanner(System.in);

        // Get scores from user input
        while (true) {
            System.out.print("Enter player name: ");
            String playerName = scanner.nextLine();

            System.out.print("Enter new score: ");
            int score = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            gameServer.setScore(playerName, score);

            System.out.print("Do you want to continue? (yes/no): ");
            String continueInput = scanner.nextLine();
            if (continueInput.equalsIgnoreCase("no")) {
                break;
            }
        }

        scanner.close();
    }
}
