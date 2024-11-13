package com.example.bunsanedthinking_springback.global.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NextIdGetter {
    public static int getNextId(Integer maxId, int serialNumber) {
        if (maxId == null) {
            return Integer.parseInt(serialNumber + "1");
        }
        String maxIdStr = maxId.toString();
        int serialLength = String.valueOf(serialNumber).length();

        String remaining = maxIdStr.substring(serialLength);

        int nextIdSuffix = Integer.parseInt(remaining) + 1;
        return Integer.parseInt(serialNumber + "" + nextIdSuffix);
    }

}
