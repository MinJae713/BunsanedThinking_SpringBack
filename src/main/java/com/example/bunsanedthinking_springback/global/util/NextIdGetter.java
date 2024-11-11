package com.example.bunsanedthinking_springback.global.util;

public class NextIdGetter {
    public static int getNextId(int maxId, int serial) {
        String maxIdStr = maxId+"";
        int serialLength = (serial+"").length();
        int nextId = Integer.parseInt(maxIdStr.substring(serialLength));
        nextId++;
        return Integer.parseInt(serial+""+nextId);
    }
}
