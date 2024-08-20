package com.devsu.apirest2.util.enums;

public enum EnumAccountType {
    SAVINGS,
    CHECKING;

    public static EnumAccountType fromString(String type) {
        for (EnumAccountType accountType : EnumAccountType.values()) {
            if (accountType.name().equalsIgnoreCase(type)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Invalid account type: " + type);
    }

}
