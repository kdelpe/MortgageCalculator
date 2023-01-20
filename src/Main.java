public class Main {

    public static void main(String[] args) {
        int principal = (int) MortgageConsole.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) MortgageConsole.readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) MortgageConsole.readNumber("Period (Years): ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterest, years);

        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }
}
