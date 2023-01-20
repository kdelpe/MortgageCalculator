import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) throws Exception {
        int principal = (int) readNumber("Principal: ", 1000, 1000000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);    
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("\t\t\tMORTGAGE CALCULATOR");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Principal amount: " + NumberFormat.getCurrencyInstance().format(principal));
        System.out.println("Annual interest rate: " + annualInterest + " %");
        System.out.println("Term (years): " + years);
        System.out.println("Monthly Payments: " + mortgageFormatted);

        System.out.println();
        System.out.println("\t\t\tOUTSTANDING BALANCE");
        System.out.println("----------------------------------------------------------------------------");
        for (short month = 1; month <= (years * MONTHS_IN_YEAR); month++){
            double balance = calculateBalance(principal, annualInterest, years, month);    
            System.out.println("Month " + month + ": " + NumberFormat.getCurrencyInstance().format(balance));
        }        
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min || value <= max)
                break;                     
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principal, double annualInterest, byte years, short numberOfPaymentsMade) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEAR;
        double numberOfPayments = years * MONTHS_IN_YEAR;

        double balance = principal * ((Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));

        return balance;
    }

    public static double calculateMortgage(int principal, double annualInterest, byte years) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEAR;
        double numberOfPayments = years * MONTHS_IN_YEAR;

        double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments) 
        / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));

        return mortgage;
    }
}
