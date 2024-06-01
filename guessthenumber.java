import java.util.Random;
import java.util.Scanner;

public class guessthenumber 
{
    public static void main(String[] args) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Generates a random number between 1 and 100
        int numberOfGuesses = 0;
        int guess;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberOfGuesses++;

            if (guess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number correctly in " + numberOfGuesses + " attempts.");
            } else if (guess < numberToGuess) {
                System.out.println("Your guess is too low. Try again.");
            } else {
                System.out.println("Your guess is too high. Try again.");
            }
        } while (guess != numberToGuess);

        scanner.close();
    }
}