import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GuessTheMovie {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("movies.txt");
        String movie = getMovie(file);
        char[] movieLetters = movie.toCharArray();
        ArrayList<Character> toGuess = new ArrayList<Character>();

        for (char element : movieLetters) {
            if (element == ' ') {
                toGuess.add(element);
            } else {
                toGuess.add('_');
            };
        }

        int attempts = 10;
        while (toGuess.contains('_')) {
            System.out.println("You are guessing: ");
            for (char element : toGuess) {
                System.out.print(element);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            System.out.println("Enter a letter:");

            String guess = scanner.nextLine();
            if (guess.length() > 1) {
                System.out.println("You must provide one letter. Do it again in a right way!");
                continue;
            };
            char letter = guess.charAt(0);
            for (int i = 0; i < toGuess.size(); i++) {
                if (movieLetters[i] == letter) toGuess.set(i, letter);
                continue;
            }

            attempts--;
            System.out.println("Attempts left: " + attempts);
            if (attempts == 0) {
                System.out.println("No attempts left... Next time you will be lucky");
                return;
            }
        }
        System.out.println("Congratulations, you won the game!");
    }

    public static int getRowCount(File file) throws FileNotFoundException {
        int rowCount = 0;
        Scanner sc = new Scanner(file);
        if (file.exists()) {
            while (sc.hasNextLine()) {
                sc.nextLine();
                rowCount++;
            }
        }
        return rowCount;
    }

    public static String getMovie(File file) throws FileNotFoundException {
        ArrayList<String> movies = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            movies.add(scanner.nextLine());
        }
        int numOfRows = getRowCount(file);
        int randomIndex = (int) (Math.random() * numOfRows) + 1;

        return movies.get(randomIndex - 1);
    }
}
