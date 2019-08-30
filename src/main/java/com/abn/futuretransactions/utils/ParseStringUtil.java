package com.abn.futuretransactions.utils;

/**
 * @author abhi
 */
public class ParseStringUtil {
	 /**
     * is string null or zero length
     *
     * @param s
     * @return true if string is null or zero length
     */
    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.length() == 0);
    }

    /**
     * if string is null, return an empty string, otherwise return the string
     *
     * @param s
     * @return if string is null, return an empty string, otherwise return the string
     */
    public static String formatNullToEmpty(String s) {
        return (s == null ? "" : s);
    }

    /**
     * create a comma separated string from all these strings
     *
     * @param showNulls
     * @param noSpaces
     * @param strings
     * @return
     */
    public static String concatWithCommas(boolean showNulls, boolean noSpaces, String... strings) {
        StringBuilder out = new StringBuilder();
        int position = 0;
        for (String str : strings) {

            if (position > 0 && (showNulls || !isNullOrEmpty(str))) {
                out.append(",");
                if (!noSpaces) {
                    out.append(" ");
                }
            }

            if (showNulls || !isNullOrEmpty(str)) {
                out.append(ParseStringUtil.formatNullToEmpty(str));
            }
            position++;
        }
        return out.toString();
    }
}
