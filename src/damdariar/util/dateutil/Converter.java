package damdariar.util.dateutil;

import java.util.Date;

public class Converter {

    protected Date date;
    protected BEJalaliCalendar jalali;
    protected BEGregorianCalendar gregorian;

    public Converter() {
        date = new Date();
        jalali = new BEJalaliCalendar();
        gregorian = new BEGregorianCalendar();
    }

    public Converter(long time) {
        date = new Date(time);
        jalali = new BEJalaliCalendar();
        jalali.setTime(date);
        gregorian = new BEGregorianCalendar();
        gregorian.setTime(date);
    }

    public void setTime(long time) {
        date.setTime(time);
        computeCalendars();
    }

    public long setJalali(int year, int month, int date) {
        jalali.set(year, month - 1, date);
        computeGregorian();
        return this.date.getTime();
    }

    public long setJalali(int year, int month, int date, int hour, int minute) {
        jalali.set(year, month - 1, date, hour, minute);
        computeGregorian();
        return this.date.getTime();
    }
    
    public long setJalali(int year, int month, int date, int hour, int minute,int second) {
        jalali.set(year, month - 1, date, hour, minute,second);
        computeGregorian();
        return this.date.getTime();
    }

    public long setGregorian(int year, int month, int date) {
        gregorian.set(year, month - 1, date);
        computeJalali();
        return this.date.getTime();
    }

    public long setGregorian(int year, int month, int date, int hour, int minute) {
        gregorian.set(year, month - 1, date, hour, minute);
        computeJalali();
        return this.date.getTime();
    }

    public int getJYear() {
        return jalali.get(BECalendar.YEAR);
    }

    public int getJMonth() {
        return jalali.get(BECalendar.MONTH) + 1;
    }

    public int getJDate() {
        return jalali.get(BECalendar.DATE);
    }

    public boolean isJLeap() {
        return jalali.isLeapYear(jalali.get(BECalendar.YEAR));
    }

    public boolean isJLeap(int year) {
        return jalali.isLeapYear(year);
    }

    public int getGYear() {
        return gregorian.get(BECalendar.YEAR);
    }

    public int getGMonth() {
        return gregorian.get(BECalendar.MONTH) + 1;
    }

    public int getGDate() {
        return gregorian.get(BECalendar.DATE);
    }

    public boolean isGLeap() {
        return gregorian.isLeapYear(gregorian.get(BECalendar.YEAR));
    }

    public boolean isGLeap(int year) {
        return gregorian.isLeapYear(year);
    }

// one hour is added to the result hour, this is a bug!!!!!!!!!!!!!!!!!!!!!!!
    public int getHour() {
        return (jalali.get(BECalendar.HOUR) == 12) ? 0 : jalali.get(BECalendar.HOUR) + 1;
    }

    public int getMinute() {
        return jalali.get(BECalendar.MINUTE);
    }

    public int getSecond() {
        return jalali.get(BECalendar.SECOND);
    }

    public long getTime() {
        return date.getTime();
    }

    public int getJDay() {
        return jalali.get(BECalendar.DATE);
    }


    public String getJFormatedDate() {
        if (date.getTime() == 0) {
            return "";
        } else {
            return getJYear() + "/" + getJMonth() + "/" + getJDay();
        }
    }

    public String getJFormatedHoure() {
        if (date.getTime() == 0) {
            return "";
        } else {
            return getHour() + ":" + getMinute() + ":" + getSecond();
        }
    }


// Implementation

    protected void computeJalali() {
        date.setTime(gregorian.getTimeInMillis());
        jalali.setTime(date);
    }

    protected void computeGregorian() {
        date.setTime(jalali.getTimeInMillis());
        gregorian.setTime(date);
    }

    protected void computeCalendars() {
        jalali.setTime(date);
        gregorian.setTime(date);
    }

}
