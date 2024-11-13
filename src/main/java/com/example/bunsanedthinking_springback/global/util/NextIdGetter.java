package com.example.bunsanedthinking_springback.global.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NextIdGetter {
    public static int getNextId(Integer maxId, int serial) {
        if (maxId == null) {
            return Integer.parseInt(serial + "1");
        }
        String maxIdStr = maxId.toString();
        int serialLength = Integer.toString(serial).length();
        int nextId = Integer.parseInt(maxIdStr.substring(serialLength)) + 1;
        return Integer.parseInt(serial + "" + nextId);
    }
}
