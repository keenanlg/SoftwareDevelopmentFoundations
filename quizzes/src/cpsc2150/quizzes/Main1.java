package cpsc2150.quizzes;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.print("Enter 100 integers: ");

        //Reading in numbers
        Scanner scnr = new Scanner(System.in);
        String userInput = scnr.nextLine();
        String[] sNumbers = userInput.split(" ");
        int[] numbers = new int[100];
        for (int i = 0; i < 100; i++) {
            numbers[i] = Integer.parseInt(sNumbers[i]);
        }

        //Sum
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        System.out.println('\n' + "The sum of all numbers is " + sum);

        //Average
        double average = (double)sum / (double)numbers.length;
        System.out.println("The average of all numbers is " + average);

        //Min
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println("The min of all numbers is " + min);

        //Max
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("The max of all numbers is " + max);
    }
}