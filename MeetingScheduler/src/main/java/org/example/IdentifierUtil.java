package org.example;

import java.util.UUID;

public class IdentifierUtil {
    public static Long generate(){
        return Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }
}
