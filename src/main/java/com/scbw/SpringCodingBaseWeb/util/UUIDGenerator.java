package com.scbw.SpringCodingBaseWeb.util;

import java.util.UUID;

public class UUIDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
