package org.faxhax.faxhax.api.util.text;

public class FaxEzMessageUtil {

    private static String message;

    public static void set_message(String message) {
        FaxEzMessageUtil.message = message;
    }

    public static String get_message() {
        return message;
    }

}
