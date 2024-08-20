package com.devsu.apirest2.util;

public class ErrorMessages {
    public static final String CUSTOMER_NOT_FOUND= "Customer associate with the provided id doesn't exists";
    public static final String ACCOUNT_NUMBER_EXISTS = "An account is already present with the provided account number, cannot save!";
    public static final String ACCOUNT_NUMBER_NOT_FOUND = "Account associate with the account number provided doesn't exists";
    public static final String TRANSACTION_ID_NOT_FOUND = "Transaction associate with the provided transaction id doesn't exists";
    public static final String INSUFFICIENT_FUNDS = "No funds available for processing this request, current funds %s";
}
