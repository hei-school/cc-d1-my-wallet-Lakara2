public class CurrencyConverter {
    public static double convertCurrency(double amount, String targetCurrency, Wallet myWallet) {
        switch (targetCurrency.toLowerCase()) {
            case "euro":
                return myWallet.convertToEuro(amount);
            case "dollar":
                return myWallet.convertToDollar(amount);
            case "yen":
                return myWallet.convertToYen(amount);
            default:
                System.out.println("Devise non prise en charge.");
                return amount;
        }
    } 
}
