package com.lumi.aiot.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.UUID;

/**
 * @author eric
 */
public class UUIDUtils {

    static final int DEFAULT_RAMDOMLENGTH = 6;

    public UUIDUtils() {
    }

    public static String generateUUID() {
        return generateUUID(DEFAULT_RAMDOMLENGTH);
    }

    public static String generateUUID(int ramdomLength) {
        String random = RandomStringUtils.randomAlphanumeric(ramdomLength);
        return random + "-" + UUID.randomUUID().toString();
    }
}
