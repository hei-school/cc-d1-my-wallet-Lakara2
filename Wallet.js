class Wallet {
    constructor() {
        this.euroConversionRate = 0.00022;
        this.dollarConversionRate = 0.00025;
        this.yenConversionRate = 0.028;
        this.balance = 0.0;
        this.transactionHistory = [];
    }

    setEuroConversionRate(rate) {
        this.euroConversionRate = rate;
    }

    setDollarConversionRate(rate) {
        this.dollarConversionRate = rate;
    }

    setYenConversionRate(rate) {
        this.yenConversionRate = rate;
    }

    getEuroConversionRate() {
        return this.euroConversionRate;
    }

    getDollarConversionRate() {
        return this.dollarConversionRate;
    }

    getYenConversionRate() {
        return this.yenConversionRate;
    }

    addTransaction(date, amount, description) {
        const transaction = { date, amount, description };
        this.transactionHistory.push(transaction);
        this.balance += amount;
    }

    getBalance() {
        return this.balance;
    }

    getTransactionHistory() {
        return this.transactionHistory;
    }
}

module.exports = Wallet;