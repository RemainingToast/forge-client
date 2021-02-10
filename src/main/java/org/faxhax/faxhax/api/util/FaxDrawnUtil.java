package org.faxhax.faxhax.api.util;

import java.util.ArrayList;
import java.util.List;

public class FaxDrawnUtil {
    
    public static List<String> hidden_tags = new ArrayList<>();

    public static void add_remove_item(String s) {
        s = s.toLowerCase();
        if (hidden_tags.contains(s)) {
            FaxMessageUtil.send_client_message("Added " + s);
            hidden_tags.remove(s);
        } else {
            FaxMessageUtil.send_client_message("Removed " + s);
            hidden_tags.add(s);
        }

    }

}