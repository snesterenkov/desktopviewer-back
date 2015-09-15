package com.eklib.desktopviewer.util;

import java.util.Formatter;

/**
 * Created by alex on 9/14/2015.
 */
public class ConvertBytesUtil {

    public static String byteArray2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String ret = formatter.toString();
        formatter.close();

        return ret;
    }

}
