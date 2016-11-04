/*
 * @(#)BEJalaliCalendar.java	1.00 01/06/04
 * based on BEGregorianCalendar.java	1.53 00/01/19
 *
 * Copyright 1996-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */

/*
 * (C) Copyright Taligent, Inc. 1996-1998 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996-1998 - All Rights Reserved
 *
 *   The original version of this source code and documentation is copyrighted
 * and owned by Taligent, Inc., a wholly-owned subsidiary of IBM. These
 * materials are provided under terms of a License Agreement between Taligent
 * and Sun. This technology is protected by multiple US and International
 * patents. This notice and attribution to Taligent may not be removed.
 *   Taligent is a registered trademark of Taligent, Inc.
 *
 */

package damdariar.util.dateutil;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <code>JalaliCalendar</code> is a concrete subclass of {@link Calendar} and provides the standard calendar used in
 * Iran.
 * <p/>
 * <p/>
 * The Jalali calendar has 2 eras, BH (Before Hegira) and AH (After Hegira).
 * <p/>
 * <p/>
 * <code>JalaliCalendar</code> implements <em>proleptic</em> Jalali calendar. That is, dates are computed by
 * extrapolating the current rules indefinitely far backward and forward in time. As a result,
 * <code>JalaliCalendar</code> may be used for all years to generate meaningful and consistent results.
 * <p/>
 * <p>Values calculated for the <code>WEEK_OF_YEAR</code> field range from 1 to 53. Week 1 for a year is the earliest
 * seven day period starting on <code>getFirstDayOfWeek()</code> that contains at least
 * <code>getMinimalDaysInFirstWeek()</code> days from that year. It thus depends on the values of
 * <code>getMinimalDaysInFirstWeek()</code>, <code>getFirstDayOfWeek()</code>, and the day of the week of Farvardin 1.
 * Weeks between week 1 of one year and week 1 of the following year are numbered sequentially from 2 to 52 or 53 (as
 * needed).
 * <p/>
 * <p>For example, Farvardin 1, 1380 was a Wednesday. If <code>getFirstDayOfWeek()</code> is <code>SATURDAY</code> and
 * <code>getMinimalDaysInFirstWeek()</code> is 4, then week 1 of 1380 starts on Farvardin 4, 1380, and ends on Farvardin
 * 10, 1380; the first three days of 1380 then are part of week 53 of 1379. If, however,
 * <code>getFirstDayOfWeek()</code> is <code>SUNDAY</code>, then week 1 of 1380 starts on Esfand 28, 1379 (year 1379 is
 * Leap), and ends on Farvardin 4, 1380.
 * <p/>
 * <p>Values calculated for the <code>WEEK_OF_MONTH</code> field range from 0 or 1 to 4 or 5.  Week 1 of a month (the
 * days with <code>WEEK_OF_MONTH = 1</code>) is the earliest set of at least <code>getMinimalDaysInFirstWeek()</code>
 * contiguous days in that month, ending on the day before <code>getFirstDayOfWeek()</code>. Unlike week 1 of a year,
 * week 1 of a month may be shorter than 7 days, need not start on <code>getFirstDayOfWeek()</code>, and will not
 * include days of the previous month. Days of a month before week 1 have a <code>WEEK_OF_MONTH</code> of 0.
 * <p/>
 * <p>For example, if <code>getFirstDayOfWeek()</code> is <code>SATURDAY</code> and
 * <code>getMinimalDaysInFirstWeek()</code> is 4, then the first week of Farvardin 1380 is Saturday, Farvardin 3 through
 * Friday, Farvardin 9. These days have a <code>WEEK_OF_MONTH</code> of 1.  Wednesday, Farvardin 1 through Friday,
 * Farvardin 3 have a <code>WEEK_OF_MONTH</code> of 0. If <code>getMinimalDaysInFirstWeek()</code> is changed to 3, then
 * Farvardin 1 through Farvardin 3 have a <code>WEEK_OF_MONTH</code> of 1.
 * <p/>
 * <p/>
 * <strong>Example:</strong>
 * <blockquote>
 * <pre>
 * // get the supported ids for GMT+03:30 (Tehran Standard Time)
 * String[] ids = TimeZone.getAvailableIDs((3 * 60 + 30) * 60 * 1000);
 * // if no ids were returned, something is wrong. get out.
 * if (ids.length == 0)
 *     System.exit(0);
 * <p/>
 *  // begin output
 * System.out.println("Current Time");
 * <p/>
 * // create a Tehran Standard Time time zone
 * SimpleTimeZone pdt = new SimpleTimeZone((3 * 60 + 30) * 60 * 1000, ids[0]);
 * <p/>
 * // set up rules for daylight savings time
 * pdt.setStartRule(JalaliCalendar.FARVARDIN, 2, 0);
 * pdt.setEndRule(JalaliCalendar.SHAHRIVAR, 31, 0);
 * <p/>
 * // create a JalaliCalendar with the Tehran Daylight time zone
 * // and the current date and time
 * Calendar calendar = new JalaliCalendar(pdt);
 * Date trialTime = new Date();
 * calendar.setTime(trialTime);
 * <p/>
 * // print out a bunch of interesting things
 * System.out.println("ERA: " + calendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + calendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *                    + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
 * System.out.println("DST_OFFSET: "
 *                    + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
 * <p/>
 * System.out.println("Current Time, with hour reset to 3");
 * calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
 * calendar.set(Calendar.HOUR, 3);
 * System.out.println("ERA: " + calendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + calendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *        + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000))); // in hours
 * System.out.println("DST_OFFSET: "
 *        + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000))); // in hours
 * </pre>
 * </blockquote>
 *
 * @author David Goldsmith, Mark Davis, Chen-Lieh Huang, Alan Liu, Behdad Esfahbod
 * @version 1.00
 * @see class wraps a {@link JalaliCalendar} but extends {@link java.util.Calendar}
 * @see Calendar
 * @see JalaliCalendar
 * @see TimeZone
 * @since JDK1.1
 */
public class JalaliCalendar extends Calendar {
//////////////////
// Class Variables
//////////////////
    // --BE

    /**
     * Value of the <code>ERA</code> field indicating the period before the common era (Before Hagira).
     * The sequence of years at the transition from <code>BH</code> to <code>AH</code> is
     * ..., 2 BH, 1 BH, 1 AH, 2 AH,...
     *
     * @see Calendar#ERA
     */
    public static final int BH = 0;

    /**
     * Value of the <code>ERA</code> field indicating the common era (After Hagira).
     * The sequence of years at the transition from <code>BH</code> to <code>AH</code> is
     * ..., 2 BH, 1 BH, 1 AH, 2 AH,...
     *
     * @see Calendar#ERA
     */
    public static final int AH = 1;

    /**
     * Value of the <code>MONTH</code> field indicating the first month of the year.
     */
    public final static int FARVARDIN = JANUARY;
    /**
     * Value of the <code>MONTH</code> field indicating the second month of the year.
     */
    public final static int ORDIBEHESHT = FEBRUARY;
    /**
     * Value of the <code>MONTH</code> field indicating the third month of the year.
     */
    public final static int KHORDAD = MARCH;
    /**
     * Value of the <code>MONTH</code> field indicating the fourth month of the year.
     */
    public final static int TIR = APRIL;
    /**
     * Value of the <code>MONTH</code> field indicating the fifth month of the year.
     */
    public final static int MORDAD = MAY;
    /**
     * Value of the <code>MONTH</code> field indicating the sixth month of the year.
     */
    public final static int SHAHRIVAR = JUNE;
    /**
     * Value of the <code>MONTH</code> field indicating the seventh month of the year.
     */
    public final static int MEHR = JULY;
    /**
     * Value of the <code>MONTH</code> field indicating the eighth month of the year.
     */
    public final static int ABAN = AUGUST;
    /**
     * Value of the <code>MONTH</code> field indicating the ninth month of the year.
     */
    public final static int AZAR = SEPTEMBER;
    /**
     * Value of the <code>MONTH</code> field indicating the tenth month of the year.
     */
    public final static int DEY = OCTOBER;
    /**
     * Value of the <code>MONTH</code> field indicating the eleventh month of the year.
     */
    public final static int BAHMAN = NOVEMBER;
    /**
     * Value of the <code>MONTH</code> field indicating the twelfth month of the year.
     */
    public final static int ESFAND = DECEMBER;

    /**
     * Persian month names.
     */
    private static final String[] JALALI_MONTHS_NAMES = {
        "\u0641\u0631\u0648\u0631\u062F\u064A\u0646",
        "\u0627\u0631\u062f\u06cc\u0628\u0647\u0634\u062a",
        "\u062E\u0631\u062F\u0627\u062F",
        "\u062A\u064A\u0631",
        "\u0645\u0631\u062F\u0627\u062F",
        "\u0634\u0647\u0631\u064A\u0648\u0631",
        "\u0645\u0647\u0631",
        "\u0622\u0628\u0627\u0646",
        "\u0622\u0630\u0631",
        "\u062f\u06cc",
        "\u0628\u0647\u0645\u0646",
        "\u0627\u0633\u0641\u0646\u062F"
    };

    /**
     * Persian week day names.
     */
    private static final String[] JALALI_WEEK_DAY_NAMES = {
        "\u0634\u0646\u0628\u0647",
        "\u064A\u0643\u0634\u0646\u0628\u0647",
        "\u062F\u0648\u0634\u0646\u0628\u0647",
        "\u0633\u0647\u200C\u0634\u0646\u0628\u0647",
        "\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647",
        "\u067E\u0646\u062C\u0634\u0646\u0628\u0647",
        "\u062C\u0645\u0639\u0647"
    };



/////////////////////
// Instance Variables
/////////////////////
    /**
     * An instance of JalaliCalendar to delegate method calls.
     */
    private BEJalaliCalendar delegatee;

///////////////
// Constructors
///////////////
    /**
     * Constructs a default JalaliCalendar using the current time in the default time zone with the default locale.
     */
    public JalaliCalendar() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Constructs a JalaliCalendar based on the current time in the given time zone with the default locale.
     *
     * @param zone the given time zone.
     */
    public JalaliCalendar(TimeZone zone) {
        this(zone, Locale.getDefault());
    }

    /**
     * Constructs a JalaliCalendar based on the current time in the default time zone with the given locale.
     *
     * @param aLocale the given locale.
     */
    public JalaliCalendar(Locale aLocale) {
        this(TimeZone.getDefault(), aLocale);
    }

    /**
     * Constructs a JalaliCalendar based on the current time in the given time zone with the given locale.
     *
     * @param zone    the given time zone.
     * @param aLocale the given locale.
     */
    public JalaliCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        delegatee = new BEJalaliCalendar(zone, aLocale);
        copyDelegateeProperties();
    }

    /**
     * Constructs a JalaliCalendar with the given date set in the default time zone with the default locale.
     *
     * @param year  the value used to set the YEAR time field in the calendar.
     * @param month the value used to set the MONTH time field in the calendar.
     *              Month value is 0-based. e.g., 0 for Farvardin.
     * @param date  the value used to set the DATE time field in the calendar.
     */
    public JalaliCalendar(int year, int month, int date) {
        super(TimeZone.getDefault(), Locale.getDefault());
        delegatee = new BEJalaliCalendar(year, month, date);
        copyDelegateeProperties();
    }

    /**
     * Constructs a JalaliCalendar with the given date and time set for the default time zone with the default locale.
     *
     * @param year   the value used to set the YEAR time field in the calendar.
     * @param month  the value used to set the MONTH time field in the calendar.
     *               Month value is 0-based. e.g., 0 for Farvardin.
     * @param date   the value used to set the DATE time field in the calendar.
     * @param hour   the value used to set the HOUR_OF_DAY time field in the calendar.
     * @param minute the value used to set the MINUTE time field in the calendar.
     */
    public JalaliCalendar(int year, int month, int date, int hour, int minute) {
        super(TimeZone.getDefault(), Locale.getDefault());
        delegatee = new BEJalaliCalendar(year, month, date, hour, minute);
        copyDelegateeProperties();
    }

    /**
     * Constructs a JalaliCalendar with the given date and time set for the default time zone with the default locale.
     *
     * @param year   the value used to set the YEAR time field in the calendar.
     * @param month  the value used to set the MONTH time field in the calendar.
     *               Month value is 0-based. e.g., 0 for Farvardin.
     * @param date   the value used to set the DATE time field in the calendar.
     * @param hour   the value used to set the HOUR_OF_DAY time field in the calendar.
     * @param minute the value used to set the MINUTE time field in the calendar.
     * @param second the value used to set the SECOND time field in the calendar.
     */
    public JalaliCalendar(int year, int month, int date, int hour,
                          int minute, int second) {
        super(TimeZone.getDefault(), Locale.getDefault());
        delegatee = new BEJalaliCalendar(year, month, date, hour, minute, second);
        copyDelegateeProperties();
    }

/////////////////
// Delegating Methods
/////////////////

    /**
     * Returns name of Jalali Month which is indicated by <code>month</code>.
     */
    public static String getJalaliMonthName(int month) {
        return JALALI_MONTHS_NAMES[month];
    }

    public String getJalaliMonthName() {
        return getJalaliMonthName(get(MONTH) % 12);
    }

    /**
     * Returns name of Jalali Week Day which is indicated by <code>weekDay</code>.
     */
    public static String getJalaliWeekDayName(int weekDay) {
        return JALALI_WEEK_DAY_NAMES[weekDay];
    }

    public String getJalaliWeekDayName() {
        return getJalaliWeekDayName(get(DAY_OF_WEEK) % 7);
    }

    /**
     * Determines if the given year is a leap year. Returns true if the given year is a leap year.
     *
     * @param year the given year.
     * @return true if the given year is a leap year; false otherwise.
     */
    public static boolean isLeapYear(int year) {
        int mod = (year + 11) % 33;
        return mod % 4 == 0 && mod != 32;
    }

    /**
     * Compares this JalaliCalendar to an object reference.
     *
     * @param obj the object reference with which to compare
     * @return true if this object is equal to <code>obj</code>; false otherwise
     */
    public boolean equals(Object obj) {
        return obj instanceof JalaliCalendar &&
                delegatee.equals(((JalaliCalendar) obj).delegatee);
    }

    /**
     * Overrides Calendar
     * Date Arithmetic function.
     * Adds the specified (signed) amount of time to the given time field, based on the calendar's rules.
     *
     * @param field  the time field.
     * @param amount the amount of date or time to be added to the field.
     * @throws IllegalArgumentException if an unknown field is given.
     * @see Calendar#add(int, int)
     */
    public void add(int field, int amount) {
        delegatee.add(field, amount);
        copyDelegateeProperties();
    }


    /**
     * Overrides Calendar
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param field the time field.
     * @param up    Indicates if rolling up or rolling down the field value.
     * @throws IllegalArgumentException if an unknown field value is given.
     * @see Calendar#roll(int, boolean)
     */
    public void roll(int field, boolean up) {
        delegatee.roll(field, up);
        copyDelegateeProperties();
    }

    /**
     * Roll a field by a signed amount.
     *
     * @see Calendar#roll(int, int)
     * @since 1.2
     */
    public void roll(int field, int amount) {
        delegatee.roll(field, amount);
        copyDelegateeProperties();
    }

    /**
     * Returns minimum value for the given field.
     * e.g. for Jalali DAY_OF_MONTH, 1
     *
     * @see Calendar#getMinimum(int)
     */
    public int getMinimum(int field) {
        return delegatee.getMinimum(field);
    }

    /**
     * Returns maximum value for the given field.
     * e.g. for Jalali DAY_OF_MONTH, 31
     *
     * @see Calendar#getMaximum(int)
     */
    public int getMaximum(int field) {
        return delegatee.getMaximum(field);
    }

    /**
     * Returns highest minimum value for the given field if varies.
     * Otherwise same as getMinimum(). For Jalali, no difference.
     *
     * @see Calendar#getGreatestMinimum(int)
     */
    public int getGreatestMinimum(int field) {
        return delegatee.getGreatestMinimum(field);
    }

    /**
     * Returns lowest maximum value for the given field if varies.
     * Otherwise same as getMaximum(). For Jalali DAY_OF_MONTH, 29
     *
     * @see Calendar#getLeastMaximum(int)
     */
    public int getLeastMaximum(int field) {
        return delegatee.getLeastMaximum(field);
    }

    /**
     * Return the minimum value that this field could have, given the current date.
     * For the Jalali calendar, this is the same as getMinimum() and getGreatestMinimum().
     *
     * @see Calendar#getActualMinimum(int)
     * @since 1.2
     */
    public int getActualMinimum(int field) {
        return delegatee.getActualMinimum(field);
    }

    /**
     * Return the maximum value that this field could have, given the current date.
     * For example, with the date "Esf 3, 1379" and the DAY_OF_MONTH field, the actual maximum would be 30; for
     * "Esf 3, 1380" it s 29. Similarly for a Hebrew calendar, for some years the actual maximum for MONTH is 12, and
     * for others 13.
     *
     * @see Calendar#getActualMaximum(int)
     * @since 1.2
     */
    public int getActualMaximum(int field) {
        return delegatee.getActualMaximum(field);
    }

//////////////////////
// Proposed public API
//////////////////////

    /**
     * Return true if the current time for this Calendar is in Daylignt Savings Time.
     * <p/>
     * Note -- MAKE THIS PUBLIC AT THE NEXT API CHANGE. POSSIBLY DEPRECATE AND REMOVE TimeZone.inDaylightTime().
     */
    public boolean inDaylightTime() {
        return delegatee.inDaylightTime();
    }

    /**
     * Return the year that corresponds to the <code>WEEK_OF_YEAR</code> field.
     * This may be one year before or after the calendar year stored in the <code>YEAR</code> field. For example,
     * Farvardin 1, 1381 is considered Thursday of week 53 of 1380 (if minimal days in first week is 3 or more, and the
     * first day of the week is Saturday). Given these same settings, the ISO year of Farvardin 1, 1381 is 1380.
     * <p/>
     * Warning: This method will complete all fields.
     *
     * @return the year corresponding to the <code>WEEK_OF_YEAR</code> field, which
     *         may be one year before or after the <code>YEAR</code> field.
     * @see #WEEK_OF_YEAR
     */
    public int getISOYear() {
        return getISOYear();
    }

/////////////////////////////
// Time => Fields computation
/////////////////////////////

    /**
     * Overrides Calendar
     * Converts UTC as milliseconds to time field values.
     * The time is <em>not</em> recomputed first; to recompute the time, then the fields, call the
     * <code>complete</code> method.
     *
     * @see Calendar#complete
     * @see Calendar#computeFields()
     */
    protected void computeFields() {
        delegatee.computeFields();
        copyDelegateeProperties();
    }

/////////////////////////////
// Fields => Time computation
/////////////////////////////
    // --BE
    /**
     * Overrides Calendar
     * Converts time field values to UTC as milliseconds.
     *
     * @throws IllegalArgumentException if any fields are invalid.
     * @see Calendar#computeTime()
     */
    protected void computeTime() {
        delegatee.computeTime();
        copyDelegateeProperties();
    }

/////////////////////////////
// java.util.Calendar methods
/////////////////////////////

    public long getTimeInMillis() {
        return delegatee.getTimeInMillis();
    }

    public void setTimeInMillis(long millis) {
        delegatee.setTimeInMillis(millis);
        copyDelegateeProperties();
    }

    public int get(int field) {
        return delegatee.get(field);
    }

    public void set(int field, int value) {
        delegatee.set(field, value);
        copyDelegateeProperties();
    }

    public void complete() {
        delegatee.complete();
        copyDelegateeProperties();
    }

    public int hashCode() {
        return delegatee.hashCode();
    }

    public void setTimeZone(TimeZone value) {
        delegatee.setTimeZone(value);
        copyDelegateeProperties();
    }

    public TimeZone getTimeZone() {
        return delegatee.getTimeZone();
    }

    public void setLenient(boolean lenient) {
        delegatee.setLenient(lenient);
        copyDelegateeProperties();
    }

    public boolean isLenient() {
        return delegatee.isLenient();
    }

    public void setFirstDayOfWeek(int value) {
        delegatee.setFirstDayOfWeek(value);
        copyDelegateeProperties();
    }

    public int getFirstDayOfWeek() {
        return delegatee.getFirstDayOfWeek();
    }

    public void setMinimalDaysInFirstWeek(int value) {
        delegatee.setMinimalDaysInFirstWeek(value);
        copyDelegateeProperties();
    }

    public int getMinimalDaysInFirstWeek() {
        return delegatee.getMinimalDaysInFirstWeek();
    }

    public Object clone() {
        copyDelegateeProperties();
        JalaliCalendar other = (JalaliCalendar) super.clone();
        BEJalaliCalendar otherDelegatee = (BEJalaliCalendar) delegatee.clone();
        other.delegatee = otherDelegatee;
        return other;
    }

    public String toString() {
        return delegatee.toString();
    }

    private final void copyDelegateeProperties() {
        this.areFieldsSet = delegatee.areFieldsSet;
        this.fields = delegatee.fields;
        this.isSet = delegatee.isSet;
        this.isTimeSet = delegatee.isTimeSet;
        this.time = delegatee.time;
    }
}
