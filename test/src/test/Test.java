/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author sukantakumarghosh
 */
public class Test {

    /**
     * @param @return
     */
    public static boolean isPalindrome(int checkNumber) {
        boolean result = false;
        int val = 0;
        int n = checkNumber;
        int rmd;
        while (checkNumber > 0) {
            rmd = checkNumber % 10;
            val = val * 10 + rmd;
            checkNumber = checkNumber / 10;

        }

        if (n == val) {
            result = true;
        }
        return result;

    }

    public static boolean isTraingular(int checkNumber) {
        boolean result = false;
        int sum = 0;
        for (int i = 1; i < checkNumber; i++) {
            sum += i;
            if (sum == checkNumber) {
                result = true;
            }
        }
        return result;
    }

    public static int sumOfDivisors(int n) {
        int divisors = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                divisors += i;
            }
        }
        return divisors;
    }

    public static boolean isPerfectNumber(int n) {

        return sumOfDivisors(n) == n;

    }

    public static boolean hasAmicableNumber(int n) {
        return n == sumOfDivisors(sumOfDivisors(n));
    }

    public static int hasNotAmicableNumber(int n) {
        if (n != sumOfDivisors(sumOfDivisors(n))) {
            return -1;
        } else {
            return sumOfDivisors(n);
        }

    }

    public static void main(String[] args) {
        final int STARTVALUE = 200;
        final int ENDVALUE = 230;

        for (int i = STARTVALUE; i <= ENDVALUE; i++) {
            if (isPerfectNumber(i) == true) {
                System.out.printf("%d: is a perfect number\n", i);
            } else if ((hasAmicableNumber(i)) == true) {
                System.out.printf("%d: is a amicable number to: %d\n", i, sumOfDivisors(i));
            } else if ((isTraingular(i)) == true) {
                System.out.printf("%d: is a tringular number\n", i);
            } else if ((isPalindrome(i)) == true) {
                System.out.printf("%d: is a Palindrome number\n", i);
            }

        }
        //System.out.println("Input a Number: ");
        //Scanner scn = new Scanner(System.in);
        //int fristData = scn.nextInt();
        //Test obj = new Test();

    }

}
