package cn.sxl.toolbox.util;

import cn.sxl.toolbox.enums.MonthEnum;

import java.util.Calendar;

/**
 * @author SxL
 * @since 1.6.1
 * 2019-11-15 11:14
 */

public class DateUtil {
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getDate(Calendar calendar) {
        return calendar.get(Calendar.DATE);
    }

    public static int getDate() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    public static int getDay(int year, int month) {
        if (isLeap(year)) {
            return MonthEnum.getDayOfMonth(month) + 1;
        } else {
            return MonthEnum.getDayOfMonth(month);
        }
    }

    public static int getDay() {
        int year = getYear();
        int month = getMonth();

        if (isLeap(year)) {
            return MonthEnum.getDayOfMonth(month) + 1;
        } else {
            return MonthEnum.getDayOfMonth(month);
        }
    }

    public static boolean isLeap(int year) {
        return ((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0);
    }

    public static boolean isLeap() {
        int year = getYear();

        return ((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0);
    }

    public static int getRemainDays(int year, int month) {
        return getDay(year, month) - getDate() + 1;
    }

    public static int getRemainDays() {
        return getDay() - getDate() + 1;
    }

    public static String convertYearToString() {
        return String.valueOf(getYear());
    }

    public static String convertMonthToString() {
        return String.valueOf(MonthEnum.values()[getMonth() - 1]);
    }
}
