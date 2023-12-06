import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        final int MAX_TRIES = 3;
        int tries = 0;
        boolean isAuthenticated = false;

        while (tries < MAX_TRIES) {
            System.out.println("Veuillez vous connecter :");
            System.out.print("Identifiant : ");
            String username = scanner.nextLine();

            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            if (userManager.authenticate(username, password)) {
                isAuthenticated = true;
                System.out.println("Connexion réussie !");
                break;
            } else {
                System.out.println("Identifiants incorrects. Veuillez réessayer.");
                tries++;
            }
        }

        if (isAuthenticated) {
            Wallet myWallet = new Wallet();
            System.out.println("Portefeuille initialisé avec succès !");
            System.out.println("Que souhaitez-vous faire ?");

            System.out.println("1. Ajouter une transaction");
            System.out.println("2. Afficher le solde");
            System.out.println("3. Consulter l'historique des transactions");
            System.out.println("4. Conversion des devises");
            System.out.println("5. Quitter");

            int choice = 0;
            boolean validChoice;
            while (choice != 5) {
                System.out.print("Veuillez choisir une option (1 - 5) : ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Veuillez entrer un nombre valide.");
                    scanner.next();
                }

                validChoice = (choice >= 1 && choice <= 5);
                if (!validChoice) {
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
                }

                choice = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne après nextInt()

                switch (choice) {
                    case 1:
                        LocalDateTime dateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String date = dateTime.format(formatter);

                        System.out.print("Entrez le montant de la transaction en Ariary : ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Entrez la description de la transaction : ");
                        String description = scanner.nextLine();

                        myWallet.addTransaction(date, amount, description);
                        System.out.println("Transaction ajoutée avec succès !");
                        break;
                    case 2:
                        System.out.println("Solde actuel : " + (myWallet.getBalance()) + " ariary");
                        break;
                    case 3:
                        myWallet.sortTransactionsByDate();

                        List<Transaction> transactions = myWallet.getTransactionHistory();
                        System.out.println("Historique des transactions :");
                        for (Transaction transaction : transactions) {
                            System.out.println("Date : " + transaction.date() +
                                    ", Montant en Ariary : " + transaction.amount() +
                                    ", Description : " + transaction.description());
                        }
                        break;
                    case 4:
                        System.out.print("Veuillez entrer la devise de conversion (Euro, Dollar, Yen) : ");
                        String currencyChoice = scanner.nextLine();

                        double balance = myWallet.getBalance();

                        if (CurrencyConverter.isValidCurrency(currencyChoice)) {
                            System.out.println("Solde actuel : " + CurrencyConverter.convertCurrency(balance, currencyChoice, myWallet) + " " + currencyChoice);
                        } else {
                            System.out.println("Devise non prise en charge.");
                        }
                        break;
                    case 5:
                        System.out.println("Au revoir !");
                        break;
                }
            }
        } else {
            System.out.println("Nombre maximal de tentatives dépassé. Le programme va se terminer.");
        }

        scanner.close();
    }
}