#include "currency_converter.h"

double convertToEuro(double amountInAriary, double euroConversionRate) {
    return amountInAriary * euroConversionRate;
}

double convertToDollar(double amountInAriary, double dollarConversionRate) {
    return amountInAriary * dollarConversionRate;
}

double convertToYen(double amountInAriary, double yenConversionRate) {
    return amountInAriary * yenConversionRate;
}