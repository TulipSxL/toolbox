package cn.sxl.toolbox.enums;


/**
 * @author SxL
 * @since 1.6.1
 * 2019-11-07 12:35
 */


public enum MonthEnum {
    /**
     * 月份
     */
    January(1, 31), February(2, 28), March(3, 31), April(4, 30),
    May(5, 31), June(6, 30), July(7, 31), August(8, 31),
    September(9, 30), October(10, 31), November(11, 30), December(12, 31);

    private int number;
    private int day;

    MonthEnum(int number, int day) {
        this.number = number;
        this.day = day;
    }

    public static int getDayOfMonth(int month) {
        for (MonthEnum monthEnum : values()) {
            if (monthEnum.getNumber() == month) {
                return monthEnum.getDay();
            }
        }

        return 0;
    }

    public int getNumber() {
        return number;
    }

    public int getDay() {
        return day;
    }
}
