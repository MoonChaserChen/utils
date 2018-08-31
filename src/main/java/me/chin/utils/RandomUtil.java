package me.chin.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class RandomUtil {

    public static String getShortRandomId(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getLongRandomUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
