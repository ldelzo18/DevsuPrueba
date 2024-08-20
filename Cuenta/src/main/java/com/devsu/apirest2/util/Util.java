package com.devsu.apirest2.util;

import com.devsu.apirest2.util.enums.EnumAccountType;
import com.devsu.apirest2.util.enums.EnumTransactionType;

import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final Map<Class<? extends Enum<?>>, Enum<?>[]> ENUM_MAP = new HashMap<>();

    static {
        ENUM_MAP.put(EnumAccountType.class, EnumAccountType.values());
        ENUM_MAP.put(EnumTransactionType.class, EnumTransactionType.values());
    }

    public static Class<? extends Enum<?>> findMatchingEnum(String message) {
        return ENUM_MAP.keySet().stream().filter(
                key -> message.contains(key.getSimpleName())
                ).findFirst().orElse(null);
    }


}
