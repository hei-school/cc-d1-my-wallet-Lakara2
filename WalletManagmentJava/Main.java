import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Création d'un nouveau portefeuille...");
        Wallet myWallet = new Wallet();
        System.out.println("Portefeuille initialisé avec succès !");
        System.out.println("Que souhaitez-vous faire ?");

        System.out.println("1. Ajouter une transaction");
        System.out.println("2. Afficher le solde");
        System.out.println("3. Consulter l'historique des transactions");
        System.out.println("4. Quitter");

        int choice = 0;
        boolean validChoice;
        do {
            System.out.print("Veuillez choisir une option (1 - 4) : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            validChoice = (choice >= 1 && choice <= 4);
            if (!validChoice) {
                System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }

        switch (choice) {
            case 1:
                    scanner.nextLine();
                    System.out.print("Entrez la date de la transaction (YYYY-MM-DD) : ");
                    String date = scanner.nextLine();
                    System.out.print("Entrez le montant de la transaction en Ariary : ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Entrez la description de la transaction : ");
                    String description = scanner.nextLine();

                    myWallet.addTransaction(date, amount, description);
                    System.out.println("Transaction ajoutée avec succès !");
                    break;
            case 2:
                System.out.print("Veuillez entrer la devise de conversion (Euro, Dollar, Yen) : ");
                String currencyChoice = scanner.nextLine();

                double balance = myWallet.getBalance();
                System.out.println("Solde actuel : " + CurrencyConverter.convertCurrency(balance, currencyChoice, myWallet));
                break;
            case 3:
                    List<Transaction> transactions = myWallet.getTransactionHistory();
                    System.out.println("Historique des transactions :");
                    for (Transaction transaction : transactions) {
                        System.out.println("Date : " + transaction.getDate() +
                                ", Montant en Ariary : " + transaction.getAmount() +
                                ", Description : " + transaction.getDescription());
                        System.out.println("Montant en Euro : " + myWallet.convertToEuro(transaction.getAmount()));
                        System.out.println("Montant en Dollar : " + myWallet.convertToDollar(transaction.getAmount()));
                        System.out.println("Montant en Yen : " + myWallet.convertToYen(transaction.getAmount()));
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    break;
            }
        } while (!validChoice);
        scanner.close();
    }
}