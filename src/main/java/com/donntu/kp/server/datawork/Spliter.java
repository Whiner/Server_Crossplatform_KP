package com.donntu.kp.server.datawork;

import com.donntu.kp.server.logger.Log;

public class Spliter {
    private static int birdAttrCount = 11;
    private static int mammalAttrCount = 10;

    public static String[] split(String string) throws Exception {
        if (validateLine(string)) {
            return string.split(";");
        } else {
            throw new Exception("Ошибка в строке: " + string);
        }
    }

    public static boolean validateLine(String line) {
        String[] split = line.split(";");
        if (split.length != birdAttrCount && split.length != mammalAttrCount) {
            Log.getInstance().log("Ошибка на строке: " + line);
            return false;
        } else {
            return true;
        }

    }

    public void setBirdAttrCount(int birdAttrCount) {
        Spliter.birdAttrCount = birdAttrCount;
    }

    public void setMammalAttrCount(int mammalAttrCount) {
        Spliter.mammalAttrCount = mammalAttrCount;
    }
}
