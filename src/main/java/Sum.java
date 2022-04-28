import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        System.out.println("Sonni kiriting: ");
        int number = new Scanner(System.in).nextInt();
        int num1 = number % 10;
        int num2 = (number / 10) % 10;
        System.out.println("num2 = " + num2);
        System.out.println("num3 = " + num1);
    }
}
