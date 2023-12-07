#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "transaction.h"
#include "currency_converter.h"

void initTransactionList(TransactionList *list)
{
    list->transactions = NULL;
    list->count = 0;
}

void getCurrentDateTime(DateTime *dt) {
    time_t now;
    struct tm *currentTime;
    time(&now);
    currentTime = localtime(&now);
    
    dt->day = currentTime->tm_mday;
    dt->month = currentTime->tm_mon + 1;
    dt->year = currentTime->tm_year + 1900;
    dt->hour = currentTime->tm_hour;
    dt->minute = currentTime->tm_min;
}

void sortTransactionsByDate(TransactionList *list) {
    int i, j;
    int n = list->count;
    Transaction temp;

    for (i = 1; i < n; i++) {
        temp = list->transactions[i];
        j = i - 1;

        while (j >= 0 && (list->transactions[j].timestamp.year > temp.timestamp.year ||
                          (list->transactions[j].timestamp.year == temp.timestamp.year &&
                           (list->transactions[j].timestamp.month > temp.timestamp.month ||
                            (list->transactions[j].timestamp.month == temp.timestamp.month &&
                             (list->transactions[j].timestamp.day > temp.timestamp.day ||
                              (list->transactions[j].timestamp.day == temp.timestamp.day &&
                               (list->transactions[j].timestamp.hour > temp.timestamp.hour ||
                                (list->transactions[j].timestamp.hour == temp.timestamp.hour &&
                                 list->transactions[j].timestamp.minute > temp.timestamp.minute))))))))) {
            list->transactions[j + 1] = list->transactions[j];
            j = j - 1;
        }

        list->transactions[j + 1] = temp;
    }
}

void showHistory(TransactionList *list) {
    sortTransactionsByDate(list);

    printf("\nHistorique des transactions :\n");
    for (int i = 0; i < list->count; i++) {
        printf("Date: %02d/%02d/%d %02d:%02d - Description: %s - Montant: %.2lf\n",
               list->transactions[i].timestamp.day, list->transactions[i].timestamp.month,
               list->transactions[i].timestamp.year, list->transactions[i].timestamp.hour,
               list->transactions[i].timestamp.minute, list->transactions[i].description,
               list->transactions[i].amount);
    }
}

void addTransaction(TransactionList *list) {
    Transaction newTransaction;
    
    
    printf("Entrez le montant en ariary : ");
    scanf("%lf", &newTransaction.amount);
    
    printf("Entrez la description de la transaction : ");
    scanf(" %[^\n]", newTransaction.description);
    
    DateTime dt;
    getCurrentDateTime(&dt);
    newTransaction.timestamp = dt;

    list->transactions = realloc(list->transactions, (list->count + 1) * sizeof(Transaction));

    if (list->transactions == NULL) {
        printf("Erreur d'allocation mémoire.\n");
        return;
    }

    list->transactions[list->count++] = newTransaction;

    printf("Transaction ajoutée avec succès.\n");
}

void showBalance(const TransactionList *list) {
    double totalBalance = 0.0;

    for (int i = 0; i < list->count; i++) {
        totalBalance += list->transactions[i].amount;
    }

    printf("Le solde actuel est : %.2lf Ariary\n", totalBalance);
}

void convertCurrency(const TransactionList *list) {
    double totalBalance = 0.0;

    for (int i = 0; i < list->count; i++) {
        totalBalance += list->transactions[i].amount;
    }

    double amountInAriary = totalBalance;
    int choice;
    double conversionRate;

    printf("Choisissez la devise de conversion :\n");
    printf("---------------------------------------\n");
    printf("1. Euro\n");
    printf("2. Dollar\n");
    printf("3. Yen\n");
    printf("---------------------------------------\n");
    printf("Votre choix : ");

    if (scanf("%d", &choice) != 1 || choice < 1 || choice > 3) {
        printf("Choix invalide\n");
        while (getchar() != '\n');
        return;
    }

    switch (choice) {
        case 1:
            conversionRate = 0.00023;
    printf("---------------------------------------\n");
            printf("Le montant en Euro est : %.2lf\n", convertToEuro(amountInAriary, conversionRate));
    printf("---------------------------------------\n");
            break;
        case 2:
            conversionRate = 0.00020;
    printf("---------------------------------------\n");
            printf("Le montant en Dollar est : %.2lf\n", convertToDollar(amountInAriary, conversionRate));
    printf("---------------------------------------\n");
            break;
        case 3:
            conversionRate = 0.024;
    printf("---------------------------------------\n");
            printf("Le montant en Yen est : %.2lf\n", convertToYen(amountInAriary, conversionRate));
    printf("---------------------------------------\n");
            break;
        default:
            printf("Choix invalide\n");
            break;
    }
}