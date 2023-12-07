#include <stdio.h>
#include <string.h>
#include "transaction.h"
#include "currency_converter.h"

#define USERNAME "bryan"
#define PASSWORD "lakara"

int login() {
    char username[20];
    char password[20];

    printf("Entrez votre nom d'utilisateur : ");
    scanf("%s", username);
    printf("Entrez votre mot de passe : ");
    scanf("%s", password);

    if (strcmp(username, USERNAME) == 0 && strcmp(password, PASSWORD) == 0) {
        printf("Connexion réussie !\n");
        return 1;
    } else {
        printf("Nom d'utilisateur ou mot de passe incorrect.\n");
        return 0;
    }
}

int main() {
    int loggedIn = 0;
    
    while (!loggedIn) {
        if (!login()) {
            printf("Connexion échouée. Fin du programme.\n");
            return 1;
        }
        loggedIn = 1;

        TransactionList transactions;
        initTransactionList(&transactions);

        int choix = 0;
        while (loggedIn) {
            printf("\nMenu:\n");
            printf("1. Ajout de transaction\n");
            printf("2. Consultation du solde\n");
            printf("3. Consultation de l'historique\n");
            printf("4. Conversion de devise\n");
            printf("5. Quitter\n");
            printf("Choix : ");
            scanf("%d", &choix);

            switch (choix) {
                case 1:
                addTransaction(&transactions);
                    break;
                case 2:
                    showBalance(&transactions);
                    break;
                case 3:
                    showHistory(&transactions);
                    break;
                case 4:
                    convertCurrency(&transactions);
                    break;
                case 5:
                    printf("Au revoir !\n");
                    return 0;
                default:
                    printf("Choix invalide\n");
                    break;
            }
        }
    }
    return 0;
}