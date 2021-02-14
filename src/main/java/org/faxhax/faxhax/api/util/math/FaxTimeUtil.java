package org.faxhax.faxhax.api.util.math;

import java.util.Calendar;

public class FaxTimeUtil {

    public static int get_hour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int get_day() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int get_month() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int get_minuite() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int get_second() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public class FaxTimer {

        private long time;

        public FaxTimer() {
            this.time = -1L;
        }

        public boolean passed(final long ms) {
            return this.getTime(System.nanoTime() - this.time) >= ms;
        }

        public void reset() {
            this.time = System.nanoTime();
        }

        public long getTime(final long time) {
            return time / 1000000L;
        }

    }

}