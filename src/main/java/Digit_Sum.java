import java.util.Scanner;

public class Digit_Sum {

    int sum = 0;

    public static void main(String[] args) {

        int n, n2, n3;

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number1 :");

        n = s.nextInt();

        System.out.print("Enter the number2 :");
        n2 = s.nextInt();

        System.out.print("Enter the number3 :");
        n3 = s.nextInt();


        Digit_Sum obj = new Digit_Sum();

        int a = obj.add(n);
        int b = obj.add(n2);
        int c = obj.add(n3);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("Sum:" + (a + b + c));

    }

    int add(int n) {

        sum = n % 10;

        if (n == 0) {

            return 0;

        } else {

            return sum + add(n / 10);

        }


    }

}