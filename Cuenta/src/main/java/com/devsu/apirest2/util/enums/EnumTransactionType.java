package com.devsu.apirest2.util.enums;

public enum EnumTransactionType {
    CREDIT,
    DEBIT;

    public static EnumTransactionType fromString(String type) {
        for (EnumTransactionType transactionType : EnumTransactionType.values()) {
            if (transactionType.name().equalsIgnoreCase(type)) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid account type: " + type);
    }
}
