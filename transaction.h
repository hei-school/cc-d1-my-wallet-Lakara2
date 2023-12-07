#ifndef TRANSACTIONS_H
#define TRANSACTIONS_H

#define MAX_DESCRIPTION_LENGTH 100

typedef struct {
    int day;
    int month;
    int year;
    int hour;
    int minute;
} DateTime;

typedef struct {
    DateTime timestamp;
    char description[MAX_DESCRIPTION_LENGTH];
    double amount;
} Transaction;

typedef struct {
    Transaction *transactions;
    int count;
} TransactionList;

void initTransactionList(TransactionList *list);
void addTransaction(TransactionList* list);
void showBalance(const TransactionList* list);
void showHistory(TransactionList* list);
void convertCurrency(const TransactionList* list);

#endif