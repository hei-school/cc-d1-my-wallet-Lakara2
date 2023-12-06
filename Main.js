const Transaction = require('./Transaction');
const User = require('./User');
const UserManager = require('./UserManager');
const Wallet = require('./Wallet');
const readline = require('readline');

const userManager = new UserManager();
const myWallet = new Wallet();

const MAX_TRIES = 3;
let tries = 0;
let isAuthenticated = false;

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log("Veuillez vous connecter :");

function authenticateUser() {
    rl.question("Identifiant : ", function (username) {
        rl.question("Mot de passe : ", function (password) {
            if (userManager.authenticate(username, password)) {
                isAuthenticated = true;
                console.log("Connexion réussie !");
                showOptions();
            } else {
                console.log("Identifiants incorrects. Veuillez réessayer.");
                tries++;
                if (tries < MAX_TRIES) {
                    authenticateUser();
                } else {
                    console.log("Nombre maximal de tentatives dépassé. Le programme va se terminer.");
                    rl.close();
                }
            }
        });
    });
}

authenticateUser();

function showOptions() {
    console.log("Que souhaitez-vous faire ?");
    console.log("1. Ajouter une transaction");
    console.log("2. Afficher le solde");
    console.log("3. Consulter l'historique des transactions");
    console.log("4. Conversion des devises");
    console.log("5. Quitter");

    rl.question("Votre choix : ", function (choice) {
        switch (choice) {
            case "1":
                const dateTime = new Date().toLocaleString();
                rl.question("Entrez le montant de la transaction : ", function (amount) {
                    rl.question("Entrez la description de la transaction : ", function (description) {
                        myWallet.addTransaction(dateTime, parseFloat(amount), description);
                        showOptions();
                    });
                });
                break;
            case "2":
                console.log("Solde actuel : " + myWallet.getBalance());
                showOptions();
                break;
            case "3":
                console.log("Historique des transactions :");
                let transactions = myWallet.getTransactionHistory();
                transactions.forEach(transaction => {
                    console.log("Date : " + transaction.date +
                        ", Montant : " + transaction.amount +
                        ", Description : " + transaction.description);
                });
                showOptions();
                break;
                case "4":
                    const currencies = ["euro", "dollar", "yen"];
                    rl.question("Veuillez entrer la devise de conversion (Euro, Dollar, Yen) : ", function (currencyChoice) {
                        currencyChoice = currencyChoice.toLowerCase();
                        if (currencies.includes(currencyChoice)) {
                            let balance = myWallet.getBalance();
                            let convertedAmount;

                            switch (currencyChoice) {
                                case "euro":
                                    convertedAmount = balance * myWallet.getEuroConversionRate();
                                    console.log("Solde actuel : " + convertedAmount + " Euro");
                                    break;
                                case "dollar":
                                    convertedAmount = balance * myWallet.getDollarConversionRate();
                                    console.log("Solde actuel : " + convertedAmount + " Dollar");
                                    break;
                                case "yen":
                                    convertedAmount = balance * myWallet.getYenConversionRate();
                                    console.log("Solde actuel : " + convertedAmount + " Yen");
                                    break;
                                default:
                                    console.log("Devise non prise en charge.");
                                    break;
                            }
                        } else {
                            console.log("Devise non prise en charge.");
                        }
                        showOptions();
                    });
                    break;

            case "5":
                console.log("Au revoir !");
                rl.close();
                break;
            default:
                console.log("Option non valide. Veuillez choisir une option valide.");
                showOptions();
                break;
        }
    });
}

showOptions();