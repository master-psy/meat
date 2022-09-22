package com.common.util;

import org.apache.commons.lang.StringUtils;

public class ParseUtil {
    public static Integer stringToInteger(String val) {
        if (StringUtils.isEmpty(val))
            return null;
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double stringToDouble(String val) {
        if (StringUtils.isEmpty(val))
            return null;
        try {
            return Double.valueOf(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static Float stringToFloat(String val) {
        if (StringUtils.isEmpty(val))
            return null;
        try {
            return Float.valueOf(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static Float parseFloat(Object val) {
        if (val == null)
            return null;

        return stringToFloat(String.valueOf(val));
    }

    public static String parseString(Object val) {
        if (val == null)
            return null;

        return String.valueOf(val);
    }

    public static Integer parseInt(Object val) {
        if (val == null)
            return null;

        return stringToInteger(String.valueOf(val));
    }

}
