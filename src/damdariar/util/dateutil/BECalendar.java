/*
 * @(#)Calendar.java	1.73 03/04/23
 *
 * Copyright 2003 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import sun.util.calendar.ZoneInfo;

/**
 * This is a copy of <code>java.util.Calendar</code> allowing package level access to its members.
 *
 * @author Mark Davis, David Goldsmith, Chen-Lieh Huang, Alan Liu
 * @version 1.73, 04/23/03
 * @see Date
 * @see BEGregorianCalendar
 * @see TimeZone
 * @see java.text.DateFormat
 * @see java.util.Calendar
 * @since JDK1.1
 */
abstract class BECalendar implements Serializable, Cloneable {

    // Data flow in Calendar
    // ---------------------

    // The current time is represented in two ways by Calendar: as UTC
    // milliseconds from the epoch start (1 January 1970 0:00 UTC), and as local
    // fields such as MONTH, HOUR, AM_PM, etc.  It is possible to compute the
    // millis from the fields, and vice versa.  The data needed to do this
    // conversion is encapsulated by a TimeZone object owned by the Calendar.
    // The data provided by the TimeZone object may also be overridden if the
    // user sets the ZONE_OFFSET and/or DST_OFFSET fields directly. The class
    // keeps track of what information was most recently set by the caller, and
    // uses that to compute any other information as needed.

    // If the user sets the fields using set(), the data flow is as follows.
    // This is implemented by the Calendar subclass's computeTime() method.
    // During this process, certain fields may be ignored.  The disambiguation
    // algorithm for resolving which fields to pay attention to is described
    // above.

    //   local fields (YEAR, MONTH, DATE, HOUR, MINUTE, etc.)
    //           |
    //           | Using Calendar-specific algorithm
    //           V
    //   local standard millis
    //           |
    //           | Using TimeZone or user-set ZONE_OFFSET / DST_OFFSET
    //           V
    //   UTC millis (in time data member)

    // If the user sets the UTC millis using setTime(), the data flow is as
    // follows.  This is implemented by the Calendar subclass's computeFields()
    // method.

    //   UTC millis (in time data member)
    //           |
    //           | Using TimeZone getOffset()
    //           V
    //   local standard millis
    //           |
    //           | Using Calendar-specific algorithm
    //           V
    //   local fields (YEAR, MONTH, DATE, HOUR, MINUTE, etc.)

    // In general, a round trip from fields, through local and UTC millis, and
    // back out to fields is made when necessary.  This is implemented by the
    // complete() method.  Resolving a partial set of fields into a UTC millis
    // value allows all remaining fields to be generated from that value.  If
    // the Calendar is lenient, the fields are also renormalized to standard
    // ranges when they are regenerated.

    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * era, e.g., AD or BC in the Julian calendar. This is a calendar-specific
     * value; see subclass documentation.
     *
     * @see BEGregorianCalendar#AD
     * @see BEGregorianCalendar#BC
     */
    public final static int ERA = 0;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * year. This is a calendar-specific value; see subclass documentation.
     */
    public final static int YEAR = 1;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * month. This is a calendar-specific value. The first month of the year is
     * <code>JANUARY</code> which is 0; the last depends on the number of months in a year.
     *
     * @see #JANUARY
     * @see #FEBRUARY
     * @see #MARCH
     * @see #APRIL
     * @see #MAY
     * @see #JUNE
     * @see #JULY
     * @see #AUGUST
     * @see #SEPTEMBER
     * @see #OCTOBER
     * @see #NOVEMBER
     * @see #DECEMBER
     * @see #UNDECIMBER
     */
    public final static int MONTH = 2;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current year.  The first week of the year, as
     * defined by <code>getFirstDayOfWeek()</code> and
     * <code>getMinimalDaysInFirstWeek()</code>, has value 1.  Subclasses define
     * the value of <code>WEEK_OF_YEAR</code> for days before the first week of
     * the year.
     *
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public final static int WEEK_OF_YEAR = 3;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * week number within the current month.  The first week of the month, as
     * defined by <code>getFirstDayOfWeek()</code> and
     * <code>getMinimalDaysInFirstWeek()</code>, has value 1.  Subclasses define
     * the value of <code>WEEK_OF_MONTH</code> for days before the first week of
     * the month.
     *
     * @see #getFirstDayOfWeek
     * @see #getMinimalDaysInFirstWeek
     */
    public final static int WEEK_OF_MONTH = 4;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DAY_OF_MONTH</code>.
     * The first day of the month has value 1.
     *
     * @see #DAY_OF_MONTH
     */
    public final static int DATE = 5;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * day of the month. This is a synonym for <code>DATE</code>.
     * The first day of the month has value 1.
     *
     * @see #DATE
     */
    public final static int DAY_OF_MONTH = 5;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * number within the current year.  The first day of the year has value 1.
     */
    public final static int DAY_OF_YEAR = 6;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the day
     * of the week.  This field takes values <code>SUNDAY</code>,
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     * <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>.
     *
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public final static int DAY_OF_WEEK = 7;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * ordinal number of the day of the week within the current month. Together
     * with the <code>DAY_OF_WEEK</code> field, this uniquely specifies a day
     * within a month.  Unlike <code>WEEK_OF_MONTH</code> and
     * <code>WEEK_OF_YEAR</code>, this field's value does <em>not</em> depend on
     * <code>getFirstDayOfWeek()</code> or
     * <code>getMinimalDaysInFirstWeek()</code>.  <code>DAY_OF_MONTH 1</code>
     * through <code>7</code> always correspond to <code>DAY_OF_WEEK_IN_MONTH
     * 1</code>; <code>8</code> through <code>14</code> correspond to
     * <code>DAY_OF_WEEK_IN_MONTH 2</code>, and so on.
     * <code>DAY_OF_WEEK_IN_MONTH 0</code> indicates the week before
     * <code>DAY_OF_WEEK_IN_MONTH 1</code>.  Negative values count back from the
     * end of the month, so the last Sunday of a month is specified as
     * <code>DAY_OF_WEEK = SUNDAY, DAY_OF_WEEK_IN_MONTH = -1</code>.  Because
     * negative values count backward they will usually be aligned differently
     * within the month than positive values.  For example, if a month has 31
     * days, <code>DAY_OF_WEEK_IN_MONTH -1</code> will overlap
     * <code>DAY_OF_WEEK_IN_MONTH 5</code> and the end of <code>4</code>.
     *
     * @see #DAY_OF_WEEK
     * @see #WEEK_OF_MONTH
     */
    public final static int DAY_OF_WEEK_IN_MONTH = 8;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating
     * whether the <code>HOUR</code> is before or after noon.
     * E.g., at 10:04:15.250 PM the <code>AM_PM</code> is <code>PM</code>.
     *
     * @see #AM
     * @see #PM
     * @see #HOUR
     */
    public final static int AM_PM = 9;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the morning or afternoon. <code>HOUR</code> is used for the 12-hour
     * clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR</code> is 10.
     *
     * @see #AM_PM
     * @see #HOUR_OF_DAY
     */
    public final static int HOUR = 10;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * hour of the day. <code>HOUR_OF_DAY</code> is used for the 24-hour clock.
     * E.g., at 10:04:15.250 PM the <code>HOUR_OF_DAY</code> is 22.
     *
     * @see #HOUR
     */
    public final static int HOUR_OF_DAY = 11;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * minute within the hour.
     * E.g., at 10:04:15.250 PM the <code>MINUTE</code> is 4.
     */
    public final static int MINUTE = 12;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * second within the minute.
     * E.g., at 10:04:15.250 PM the <code>SECOND</code> is 15.
     */
    public final static int SECOND = 13;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * millisecond within the second.
     * E.g., at 10:04:15.250 PM the <code>MILLISECOND</code> is 250.
     */
    public final static int MILLISECOND = 14;
    /**
     * Field number for <code>get</code> and <code>set</code>
     * indicating the raw offset from GMT in milliseconds.
     * <p/>
     * This field reflects the correct GMT offset value of the time
     * zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical GMT offset changes.
     */
    public final static int ZONE_OFFSET = 15;
    /**
     * Field number for <code>get</code> and <code>set</code> indicating the
     * daylight savings offset in milliseconds.
     * <p/>
     * This field reflects the correct daylight saving offset value of
     * the time zone of this <code>Calendar</code> if the
     * <code>TimeZone</code> implementation subclass supports
     * historical Daylight Saving Time schedule changes.
     */
    public final static int DST_OFFSET = 16;
    /**
     * The number of distinct fields recognized by <code>get</code> and <code>set</code>.
     * Field numbers range from <code>0..FIELD_COUNT-1</code>.
     */
    public final static int FIELD_COUNT = 17;

    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Sunday.
     */
    public final static int SUNDAY = 1;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Monday.
     */
    public final static int MONDAY = 2;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Tuesday.
     */
    public final static int TUESDAY = 3;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Wednesday.
     */
    public final static int WEDNESDAY = 4;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Thursday.
     */
    public final static int THURSDAY = 5;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Friday.
     */
    public final static int FRIDAY = 6;
    /**
     * Value of the <code>DAY_OF_WEEK</code> field indicating
     * Saturday.
     */
    public final static int SATURDAY = 7;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * first month of the year.
     */
    public final static int JANUARY = 0;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * second month of the year.
     */
    public final static int FEBRUARY = 1;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * third month of the year.
     */
    public final static int MARCH = 2;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * fourth month of the year.
     */
    public final static int APRIL = 3;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * fifth month of the year.
     */
    public final static int MAY = 4;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * sixth month of the year.
     */
    public final static int JUNE = 5;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * seventh month of the year.
     */
    public final static int JULY = 6;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * eighth month of the year.
     */
    public final static int AUGUST = 7;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * ninth month of the year.
     */
    public final static int SEPTEMBER = 8;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * tenth month of the year.
     */
    public final static int OCTOBER = 9;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * eleventh month of the year.
     */
    public final static int NOVEMBER = 10;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * twelfth month of the year.
     */
    public final static int DECEMBER = 11;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * thirteenth month of the year. Although <code>GregorianCalendar</code>
     * does not use this value, lunar calendars do.
     */
    public final static int UNDECIMBER = 12;

    /**
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from midnight to just before noon.
     */
    public final static int AM = 0;
    /**
     * Value of the <code>AM_PM</code> field indicating the
     * period of the day from noon to just before midnight.
     */
    public final static int PM = 1;

    // Internal notes:
    // Calendar contains two kinds of time representations: current "time" in
    // milliseconds, and a set of time "fields" representing the current time.
    // The two representations are usually in sync, but can get out of sync
    // as follows.
    // 1. Initially, no fields are set, and the time is invalid.
    // 2. If the time is set, all fields are computed and in sync.
    // 3. If a single field is set, the time is invalid.
    // Recomputation of the time and fields happens when the object needs
    // to return a result to the user, or use a result for a computation.

    /**
     * The field values for the currently set time for this calendar.
     * This is an array of <code>FIELD_COUNT</code> integers, with index values
     * <code>ERA</code> through <code>DST_OFFSET</code>.
     *
     * @serial
     */
    protected int fields[]; // NOTE: Make transient when possible

    /**
     * The flags which tell if a specified time field for the calendar is set.
     * A new object has no fields set.  After the first call to a method
     * which generates the fields, they all remain set after that.
     * This is an array of <code>FIELD_COUNT</code> booleans, with index values
     * <code>ERA</code> through <code>DST_OFFSET</code>.
     *
     * @serial
     */
    protected boolean isSet[]; // NOTE: Remove when possible

    /**
     * Pseudo-time-stamps which specify when each field was set. There
     * are two special values, UNSET and INTERNALLY_SET. Values from
     * MINIMUM_USER_SET to Integer.MAX_VALUE are legal user set values.
     */
    transient int stamp[];

    /**
     * The currently set time for this calendar, expressed in milliseconds after
     * January 1, 1970, 0:00:00 GMT.
     *
     * @serial
     * @see #isTimeSet
     */
    protected long time;

    /**
     * True if then the value of <code>time</code> is valid.
     * The time is made invalid by a change to an item of <code>field[]</code>.
     *
     * @serial
     * @see #time
     */
    protected boolean isTimeSet; // NOTE: Make transient when possible

    /**
     * True if <code>fields[]</code> are in sync with the currently set time.
     * If false, then the next attempt to get the value of a field will
     * force a recomputation of all fields from the current value of
     * <code>time</code>.
     *
     * @serial
     */
    protected boolean areFieldsSet; // NOTE: Make transient when possible

    /**
     * True if all fields have been set.
     *
     * @serial
     */
    transient boolean areAllFieldsSet;

    /**
     * True if this calendar allows out-of-range field values during computation
     * of <code>time</code> from <code>fields[]</code>.
     *
     * @serial
     * @see #setLenient
     */
    private boolean lenient = true;

    /**
     * The <code>TimeZone</code> used by this calendar. </code>Calendar</code>
     * uses the time zone data to translate between locale and GMT time.
     *
     * @serial
     */
    private TimeZone zone;

    /**
     * The first day of the week, with possible values <code>SUNDAY</code>,
     * <code>MONDAY</code>, etc.  This is a locale-dependent value.
     *
     * @serial
     */
    private int firstDayOfWeek;

    /**
     * The number of days required for the first week in a month or year,
     * with possible values from 1 to 7.  This is a locale-dependent value.
     *
     * @serial
     */
    private int minimalDaysInFirstWeek;

    /**
     * Cache to hold the firstDayOfWeek and minimalDaysInFirstWeek
     * of a Locale.
     */
    private static Hashtable cachedLocaleData = new Hashtable(3);

    // Special values of stamp[]
    static final int UNSET = 0;
    static final int INTERNALLY_SET = 1;
    static final int MINIMUM_USER_STAMP = 2;

    /**
     * The next available value for <code>stamp[]</code>, an internal array.
     * This actually should not be written out to the stream, and will probably
     * be removed from the stream in the near future.  In the meantime,
     * a value of <code>MINIMUM_USER_STAMP</code> should be used.
     *
     * @serial
     */
    private int nextStamp = MINIMUM_USER_STAMP;

    // the internal serial version which says which version was written
    // - 0 (default) for version up to JDK 1.1.5
    // - 1 for version from JDK 1.1.6, which writes a correct 'time' value
    //     as well as compatible values for other fields.  This is a
    //     transitional format.
    // - 2 (not implemented yet) a future version, in which fields[],
    //     areFieldsSet, and isTimeSet become transient, and isSet[] is
    //     removed. In JDK 1.1.6 we write a format compatible with version 2.
    static final int currentSerialVersion = 1;

    /**
     * The version of the serialized data on the stream.  Possible values:
     * <dl>
     * <dt><b>0</b> or not present on stream</dt>
     * <dd>
     * JDK 1.1.5 or earlier.
     * </dd>
     * <dt><b>1</b></dt>
     * <dd>
     * JDK 1.1.6 or later.  Writes a correct 'time' value
     * as well as compatible values for other fields.  This is a
     * transitional format.
     * </dd>
     * </dl>
     * When streaming out this class, the most recent format
     * and the highest allowable <code>serialVersionOnStream</code>
     * is written.
     *
     * @serial
     * @since JDK1.1.6
     */
    private int serialVersionOnStream = currentSerialVersion;

    // Proclaim serialization compatibility with JDK 1.1
    static final long serialVersionUID = -1807547505821590642L;

    /**
     * Constructs a Calendar with the default time zone
     * and locale.
     *
     * @see TimeZone#getDefault
     */
    protected BECalendar() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Constructs a calendar with the specified time zone and locale.
     *
     * @param zone    the time zone to use
     * @param aLocale the locale for the week data
     */
    protected BECalendar(TimeZone zone, Locale aLocale) {
        fields = new int[FIELD_COUNT];
        isSet = new boolean[FIELD_COUNT];
        stamp = new int[FIELD_COUNT];

        this.zone = zone;
        setWeekCountData(aLocale);
    }

    /**
     * Gets a calendar using the default time zone and locale. The
     * <code>Calendar</code> returned is based on the current time
     * in the default time zone with the default locale.
     *
     * @return a Calendar.
     */
    public static BECalendar getInstance() {
        return createCalendar(TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Gets a calendar using the specified time zone and default locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the given time zone with the default locale.
     *
     * @param zone the time zone to use
     * @return a Calendar.
     */
    public static BECalendar getInstance(TimeZone zone) {
        return createCalendar(zone, Locale.getDefault());
    }

    /**
     * Gets a calendar using the default time zone and specified locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the default time zone with the given locale.
     *
     * @param aLocale the locale for the week data
     * @return a Calendar.
     */
    public static BECalendar getInstance(Locale aLocale) {
        return createCalendar(TimeZone.getDefault(), aLocale);
    }

    /**
     * Gets a calendar with the specified time zone and locale.
     * The <code>Calendar</code> returned is based on the current time
     * in the given time zone with the given locale.
     *
     * @param zone    the time zone to use
     * @param aLocale the locale for the week data
     * @return a Calendar.
     */
    public static BECalendar getInstance(TimeZone zone,
                                         Locale aLocale) {
        return createCalendar(zone, aLocale);
    }

    private static BECalendar createCalendar(TimeZone zone,
                                             Locale aLocale) {
        // else create the default calendar
        return new BEGregorianCalendar(zone, aLocale);
    }

    /**
     * Gets the list of locales for which Calendars are installed.
     *
     * @return the list of locales for which Calendars are installed.
     */
    public static synchronized Locale[] getAvailableLocales() {
        return DateFormat.getAvailableLocales();
    }

    /**
     * Converts the current field values in <code>fields[]</code>
     * to the millisecond time value
     * <code>time</code>.
     */
    protected abstract void computeTime();

    /**
     * Converts
     * the current millisecond time value
     * <code>time</code>
     * to field values in <code>fields[]</code>.
     * This allows you to sync up the time field values with
     * a new time that is set for the calendar.  The time is <em>not</em>
     * recomputed first; to recompute the time, then the fields, call the
     * <code>complete</code> method.
     *
     * @see #complete
     */
    protected abstract void computeFields();

    /**
     * Gets this Calendar's current time.
     *
     * @return the current time.
     * @see #setTime
     * @see #getTimeInMillis
     */
    public final Date getTime() {
        return new Date(getTimeInMillis());
    }

    /**
     * Sets this Calendar's current time with the given Date.
     * <p/>
     * Note: Calling <code>setTime()</code> with
     * <code>Date(Long.MAX_VALUE)</code> or <code>Date(Long.MIN_VALUE)</code>
     * may yield incorrect field values from <code>get()</code>.
     *
     * @param date the given Date.
     * @see #getTime
     * @see #setTimeInMillis
     */
    public final void setTime(Date date) {
        setTimeInMillis(date.getTime());
    }

    /**
     * Gets this Calendar's current time as a long.
     *
     * @return the current time as UTC milliseconds from the epoch.
     * @see #getTime
     * @see #setTimeInMillis
     */
    public long getTimeInMillis() {
        if (!isTimeSet) updateTime();
        return time;
    }

    /**
     * Sets this Calendar's current time from the given long value.
     *
     * @param millis the new time in UTC milliseconds from the epoch.
     * @see #setTime
     * @see #getTimeInMillis
     */
    public void setTimeInMillis(long millis) {
        isTimeSet = true;
        time = millis;
        areFieldsSet = false;

        // commented by Amir Pashazadeh
//        if (!areFieldsSet) {
        computeFields();
        areFieldsSet = true;
        areAllFieldsSet = true;
//        }
    }

    /**
     * Gets the value for a given time field.
     *
     * @param field the given time field.
     * @return the value for the given time field.
     * @throws ArrayIndexOutOfBoundsException if specified field is out of range
     *                                        (<tt>field &lt; 0 || field &gt;= FIELD_COUNT</tt>).
     */
    public int get(int field) {
        complete();
        return internalGet(field);
        //return fields[field];
    }

    /**
     * Gets the value for a given time field. This is an internal
     * fast time field value getter for the subclasses.
     *
     * @param field the given time field.
     * @return the value for the given time field.
     */
    protected final int internalGet(int field) {
        return fields[field];
    }

    /**
     * Sets the value for the given time field.  This is an internal
     * fast setter for subclasses.  It does not affect the areFieldsSet, isTimeSet,
     * or areAllFieldsSet flags.
     */
    final void internalSet(int field, int value) {
        fields[field] = value;
    }

    /**
     * Clears the value of the given calendar field and resets the
     * field status flags only. The difference from clear(int) is that
     * this method doesn't reset isTimeSet.
     *
     * @param field the given calendar field.
     * @throws ArrayIndexOutOfBoundsException if specified field is out of range
     *                                        (<tt>field &lt; 0 || field &gt;= FIELD_COUNT</tt>).
     */
    final void internalClear(int field) {
        fields[field] = 0;
        stamp[field] = UNSET;
        areFieldsSet = false;
        areAllFieldsSet = false;
        isSet[field] = false;
    }

    /**
     * Sets the time field with the given value.
     *
     * @param field the given time field.
     * @param value the value to be set for the given time field.
     * @throws ArrayIndexOutOfBoundsException if specified field is out of range
     *                                        (<tt>field &lt; 0 || field &gt;= FIELD_COUNT</tt>).
     */
    public void set(int field, int value) {
        isTimeSet = false;
        internalSet(field, value);
        //fields[field] = value;
        stamp[field] = nextStamp++;
        if (nextStamp == Integer.MAX_VALUE) {
            adjustStamp();
        }
        areFieldsSet = false;
        isSet[field] = true; // Remove later
    }

    /**
     * Sets the values for the fields year, month, and date.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     *
     * @param year  the value used to set the YEAR time field.
     * @param month the value used to set the MONTH time field.
     *              Month value is 0-based. e.g., 0 for January.
     * @param date  the value used to set the DATE time field.
     */
    public final void set(int year, int month, int date) {
        set(YEAR, year);
        set(MONTH, month);
        set(DATE, date);
    }

    /**
     * Sets the values for the fields year, month, date, hour, and minute.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     *
     * @param year   the value used to set the YEAR time field.
     * @param month  the value used to set the MONTH time field.
     *               Month value is 0-based. e.g., 0 for January.
     * @param date   the value used to set the DATE time field.
     * @param hour   the value used to set the HOUR_OF_DAY time field.
     * @param minute the value used to set the MINUTE time field.
     */
    public final void set(int year, int month, int date, int hour, int minute) {
        set(YEAR, year);
        set(MONTH, month);
        set(DATE, date);
        set(HOUR_OF_DAY, hour);
        set(MINUTE, minute);
    }

    /**
     * Sets the values for the fields year, month, date, hour, minute, and second.
     * Previous values of other fields are retained.  If this is not desired,
     * call <code>clear</code> first.
     *
     * @param year   the value used to set the YEAR time field.
     * @param month  the value used to set the MONTH time field.
     *               Month value is 0-based. e.g., 0 for January.
     * @param date   the value used to set the DATE time field.
     * @param hour   the value used to set the HOUR_OF_DAY time field.
     * @param minute the value used to set the MINUTE time field.
     * @param second the value used to set the SECOND time field.
     */
    public final void set(int year, int month, int date, int hour, int minute,
                          int second) {
        set(YEAR, year);
        set(MONTH, month);
        set(DATE, date);
        set(HOUR_OF_DAY, hour);
        set(MINUTE, minute);
        set(SECOND, second);
    }

    /**
     * Clears the values of all the time fields.
     */
    public final void clear() {
        fields = new int[FIELD_COUNT];
        stamp = new int[FIELD_COUNT];
        areFieldsSet = false;
        areAllFieldsSet = false;
        isSet = new boolean[FIELD_COUNT]; // Remove later
        isTimeSet = false;
    }

    /**
     * Clears the value in the given time field.
     *
     * @param field the time field to be cleared.
     */
    public final void clear(int field) {
        fields[field] = 0;
        stamp[field] = UNSET;
        areFieldsSet = false;
        areAllFieldsSet = false;
        isSet[field] = false; // Remove later
        isTimeSet = false;
    }

    /**
     * Determines if the given time field has a value set.
     *
     * @return true if the given time field has a value set; false otherwise.
     */
    public final boolean isSet(int field) {
        return stamp[field] != UNSET;
        // return isSet[field];
    }

    /**
     * Fills in any unset fields in the time field list.
     */
    protected void complete() {
        if (!isTimeSet) updateTime();
        if (!areFieldsSet) {
            computeFields(); // fills in unset fields
            areFieldsSet = true;
            areAllFieldsSet = true;
        }
    }

    /**
     * Compares this calendar to the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and is a <code>Calendar</code> object that
     * represents the same calendar as this object.
     *
     * @param obj the object to compare with.
     * @return <code>true</code> if the objects are the same;
     *         <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BECalendar))
            return false;

        BECalendar that = (BECalendar) obj;

        return getTimeInMillis() == that.getTimeInMillis() &&
                lenient == that.lenient &&
                firstDayOfWeek == that.firstDayOfWeek &&
                minimalDaysInFirstWeek == that.minimalDaysInFirstWeek &&
                zone.equals(that.zone);
    }

    /**
     * Returns a hash code for this calendar.
     *
     * @return a hash code value for this object.
     * @since 1.2
     */
    public int hashCode() {
        /* Don't include the time because (a) we don't want the hash value to
         * move around just because a calendar is set to different times, and
         * (b) we don't want to trigger a time computation just to get a hash.
         * Note that it is not necessary for unequal objects to always have
         * unequal hashes, but equal objects must have equal hashes.  */
        return (lenient ? 1 : 0)
                | (firstDayOfWeek << 1)
                | (minimalDaysInFirstWeek << 4)
                | (zone.hashCode() << 7);
    }

    /**
     * Compares the time field records.
     * Equivalent to comparing result of conversion to UTC.
     *
     * @param when the Calendar to be compared with this Calendar.
     * @return true if the current time of this Calendar is before
     *         the time of Calendar when; false otherwise.
     */
    public boolean before(Object when) {
        return when instanceof BECalendar &&
                getTimeInMillis() < ((BECalendar) when).getTimeInMillis();
    }

    /**
     * Compares the time field records.
     * Equivalent to comparing result of conversion to UTC.
     *
     * @param when the Calendar to be compared with this Calendar.
     * @return true if the current time of this Calendar is after
     *         the time of Calendar when; false otherwise.
     */
    public boolean after(Object when) {
        return when instanceof BECalendar &&
                getTimeInMillis() > ((BECalendar) when).getTimeInMillis();
    }

    /**
     * Date Arithmetic function.
     * Adds the specified (signed) amount of time to the given time field,
     * based on the calendar's rules. For example, to subtract 5 days from
     * the current time of the calendar, you can achieve it by calling:
     * <p>add(Calendar.DATE, -5).
     *
     * @param field  the time field.
     * @param amount the amount of date or time to be added to the field.
     */
    abstract public void add(int field, int amount);

    /**
     * Time Field Rolling function.
     * Adds or subtracts (up/down) a single unit of time on the given time
     * field without changing larger fields. For example, to roll the current
     * date up by one day, you can achieve it by calling:
     * <p>roll(Calendar.DATE, true).
     * When rolling on the year or Calendar.YEAR field, it will roll the year
     * value in the range between 1 and the value returned by calling
     * getMaximum(Calendar.YEAR).
     * When rolling on the month or Calendar.MONTH field, other fields like
     * date might conflict and, need to be changed. For instance,
     * rolling the month on the date 01/31/96 will result in 02/29/96.
     * When rolling on the hour-in-day or Calendar.HOUR_OF_DAY field, it will
     * roll the hour value in the range between 0 and 23, which is zero-based.
     *
     * @param field the time field.
     * @param up    indicates if the value of the specified time field is to be
     *              rolled up or rolled down. Use true if rolling up, false otherwise.
     * @see BECalendar#add
     * @see BECalendar#set
     */
    abstract public void roll(int field, boolean up);

    /**
     * Time Field Rolling function.
     * Add to field a signed amount without changing larger fields.
     * A negative roll amount means to roll down.
     * [NOTE:  This default implementation on Calendar just repeatedly calls the
     * version of roll() that takes a boolean and rolls by one unit.  This may not
     * always do the right thing.  For example, if the DAY_OF_MONTH field is 31,
     * rolling through February will leave it set to 28.  The GregorianCalendar
     * version of this function takes care of this problem.  Other subclasses
     * should also provide overrides of this function that do the right thing.
     *
     * @param field  the time field.
     * @param amount the signed amount to add to <code>field</code>.
     * @see BECalendar#add
     * @see BECalendar#set
     * @since 1.2
     */
    public void roll(int field, int amount) {
        while (amount > 0) {
            roll(field, true);
            amount--;
        }
        while (amount < 0) {
            roll(field, false);
            amount++;
        }
    }

    /**
     * Sets the time zone with the given time zone value.
     *
     * @param value the given time zone.
     */
    public void setTimeZone(TimeZone value) {
        zone = value;
        /* Recompute the fields from the time using the new zone.  This also
         * works if isTimeSet is false (after a call to set()).  In that case
         * the time will be computed from the fields using the new zone, then
         * the fields will get recomputed from that.  Consider the sequence of
         * calls: cal.setTimeZone(EST); cal.set(HOUR, 1); cal.setTimeZone(PST).
         * Is cal set to 1 o'clock EST or 1 o'clock PST?  Answer: PST.  More
         * generally, a call to setTimeZone() affects calls to set() BEFORE AND
         * AFTER it up to the next call to complete().
         */
        areFieldsSet = false;
    }

    /**
     * Gets the time zone.
     *
     * @return the time zone object associated with this calendar.
     */
    public TimeZone getTimeZone() {
        return zone;
    }

    /**
     * Specify whether or not date/time interpretation is to be lenient.  With
     * lenient interpretation, a date such as "February 942, 1996" will be
     * treated as being equivalent to the 941st day after February 1, 1996.
     * With strict interpretation, such dates will cause an exception to be
     * thrown.
     *
     * @see java.text.DateFormat#setLenient
     */
    public void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    /**
     * Tell whether date/time interpretation is to be lenient.
     */
    public boolean isLenient() {
        return lenient;
    }

    /**
     * Sets what the first day of the week is; e.g., Sunday in US,
     * Monday in France.
     *
     * @param value the given first day of the week.
     */
    public void setFirstDayOfWeek(int value) {
        if (firstDayOfWeek == value) {
            return;
        }
        firstDayOfWeek = value;
        invalidateWeekFields();
    }

    /**
     * Gets what the first day of the week is; e.g., Sunday in US,
     * Monday in France.
     *
     * @return the first day of the week.
     */
    public int getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    /**
     * Sets what the minimal days required in the first week of the year are;
     * For example, if the first week is defined as one that contains the first
     * day of the first month of a year, call the method with value 1. If it
     * must be a full week, use value 7.
     *
     * @param value the given minimal days required in the first week
     *              of the year.
     */
    public void setMinimalDaysInFirstWeek(int value) {
        if (minimalDaysInFirstWeek == value) {
            return;
        }
        minimalDaysInFirstWeek = value;
        invalidateWeekFields();
    }

    /**
     * Gets what the minimal days required in the first week of the year are;
     * e.g., if the first week is defined as one that contains the first day
     * of the first month of a year, getMinimalDaysInFirstWeek returns 1. If
     * the minimal days required must be a full week, getMinimalDaysInFirstWeek
     * returns 7.
     *
     * @return the minimal days required in the first week of the year.
     */
    public int getMinimalDaysInFirstWeek() {
        return minimalDaysInFirstWeek;
    }

    /**
     * Gets the minimum value for the given time field.
     * e.g., for Gregorian DAY_OF_MONTH, 1.
     *
     * @param field the given time field.
     * @return the minimum value for the given time field.
     */
    abstract public int getMinimum(int field);

    /**
     * Gets the maximum value for the given time field.
     * e.g. for Gregorian DAY_OF_MONTH, 31.
     *
     * @param field the given time field.
     * @return the maximum value for the given time field.
     */
    abstract public int getMaximum(int field);

    /**
     * Gets the highest minimum value for the given field if varies.
     * Otherwise same as getMinimum(). For Gregorian, no difference.
     *
     * @param field the given time field.
     * @return the highest minimum value for the given time field.
     */
    abstract public int getGreatestMinimum(int field);

    /**
     * Gets the lowest maximum value for the given field if varies.
     * Otherwise same as getMaximum(). e.g., for Gregorian DAY_OF_MONTH, 28.
     *
     * @param field the given time field.
     * @return the lowest maximum value for the given time field.
     */
    abstract public int getLeastMaximum(int field);

    /**
     * Return the minimum value that this field could have, given the current date.
     * For the Gregorian calendar, this is the same as getMinimum() and getGreatestMinimum().
     * <p/>
     * The version of this function on Calendar uses an iterative algorithm to determine the
     * actual minimum value for the field.  There is almost always a more efficient way to
     * accomplish this (in most cases, you can simply return getMinimum()).  GregorianCalendar
     * overrides this function with a more efficient implementation.
     *
     * @param field the field to determine the minimum of
     * @return the minimum of the given field for the current date of this Calendar
     * @since 1.2
     */
    public int getActualMinimum(int field) {
        int fieldValue = getGreatestMinimum(field);
        int endValue = getMinimum(field);

        // if we know that the minimum value is always the same, just return it
        if (fieldValue == endValue) {
            return fieldValue;
        }

        // clone the calendar so we don't mess with the real one, and set it to
        // accept anything for the field values
        BECalendar work = (BECalendar) this.clone();
        work.setLenient(true);

        // now try each value from getLeastMaximum() to getMaximum() one by one until
        // we get a value that normalizes to another value.  The last value that
        // normalizes to itself is the actual minimum for the current date
        int result = fieldValue;

        do {
            work.set(field, fieldValue);
            if (work.get(field) != fieldValue) {
                break;
            } else {
                result = fieldValue;
                fieldValue--;
            }
        } while (fieldValue >= endValue);

        return result;
    }

    /**
     * Return the maximum value that this field could have, given the current date.
     * For example, with the date "Feb 3, 1997" and the DAY_OF_MONTH field, the actual
     * maximum would be 28; for "Feb 3, 1996" it s 29.  Similarly for a Hebrew calendar,
     * for some years the actual maximum for MONTH is 12, and for others 13.
     * <p/>
     * The version of this function on Calendar uses an iterative algorithm to determine the
     * actual maximum value for the field.  There is almost always a more efficient way to
     * accomplish this (in most cases, you can simply return getMaximum()).  GregorianCalendar
     * overrides this function with a more efficient implementation.
     *
     * @param field the field to determine the maximum of
     * @return the maximum of the given field for the current date of this Calendar
     * @since 1.2
     */
    public int getActualMaximum(int field) {
        int fieldValue = getLeastMaximum(field);
        int endValue = getMaximum(field);

        // if we know that the maximum value is always the same, just return it
        if (fieldValue == endValue) {
            return fieldValue;
        }

        // clone the calendar so we don't mess with the real one, and set it to
        // accept anything for the field values
        BECalendar work = (BECalendar) this.clone();
        work.setLenient(true);

        // if we're counting weeks, set the day of the week to Sunday.  We know the
        // last week of a month or year will contain the first day of the week.
        if (field == WEEK_OF_YEAR || field == WEEK_OF_MONTH)
            work.set(DAY_OF_WEEK, firstDayOfWeek);

        // now try each value from getLeastMaximum() to getMaximum() one by one until
        // we get a value that normalizes to another value.  The last value that
        // normalizes to itself is the actual maximum for the current date
        int result = fieldValue;

        do {
            work.set(field, fieldValue);
            if (work.get(field) != fieldValue) {
                break;
            } else {
                result = fieldValue;
                fieldValue++;
            }
        } while (fieldValue <= endValue);

        return result;
    }

    /**
     * Overrides Cloneable
     */
    public Object clone() {
        try {
            BECalendar other = (BECalendar) super.clone();

            other.fields = new int[FIELD_COUNT];
            other.isSet = new boolean[FIELD_COUNT];
            other.stamp = new int[FIELD_COUNT];
            System.arraycopy(this.fields, 0, other.fields, 0, FIELD_COUNT);
            System.arraycopy(this.isSet, 0, other.isSet, 0, FIELD_COUNT);
            System.arraycopy(this.stamp, 0, other.stamp, 0, FIELD_COUNT);

            other.zone = (TimeZone) zone.clone();
            return other;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    private static final String[] FIELD_NAME = {
        ",ERA=", ",YEAR=", ",MONTH=", ",WEEK_OF_YEAR=", ",WEEK_OF_MONTH=", ",DAY_OF_MONTH=",
        ",DAY_OF_YEAR=", ",DAY_OF_WEEK=", ",DAY_OF_WEEK_IN_MONTH=", ",AM_PM=", ",HOUR=",
        ",HOUR_OF_DAY=", ",MINUTE=", ",SECOND=", ",MILLISECOND=", ",ZONE_OFFSET=",
        ",DST_OFFSET="
    };

    /**
     * Return a string representation of this calendar. This method
     * is intended to be used only for debugging purposes, and the
     * format of the returned string may vary between implementations.
     * The returned string may be empty but may not be <code>null</code>.
     *
     * @return a string representation of this calendar.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName());
        buffer.append("[time=");
        buffer.append(isTimeSet ? String.valueOf(time) : "?");
        buffer.append(",areFieldsSet=");
        buffer.append(areFieldsSet);
        buffer.append(",areAllFieldsSet=");
        buffer.append(areAllFieldsSet);
        buffer.append(",lenient=");
        buffer.append(lenient);
        buffer.append(",zone=");
        buffer.append(zone);
        buffer.append(",firstDayOfWeek=");
        buffer.append(firstDayOfWeek);
        buffer.append(",minimalDaysInFirstWeek=");
        buffer.append(minimalDaysInFirstWeek);
        for (int i = 0; i < FIELD_COUNT; ++i) {
            buffer.append(FIELD_NAME[i]);
            buffer.append(isSet(i) ? String.valueOf(fields[i]) : "?");
        }
        buffer.append(']');
        return buffer.toString();
    }

    // =======================privates===============================

    /**
     * Both firstDayOfWeek and minimalDaysInFirstWeek are locale-dependent.
     * They are used to figure out the week count for a specific date for
     * a given locale. These must be set when a Calendar is constructed.
     *
     * @param desiredLocale the given locale.
     */
    private void setWeekCountData(Locale desiredLocale) {
        /* try to get the Locale data from the cache */
        /*int[] data = (int[]) cachedLocaleData.get(desiredLocale);
        if (data == null) {   cache miss 
            ResourceBundle resource = LocaleData.getLocaleElements(desiredLocale);
            String[] dateTimePatterns =
                    resource.getStringArray("DateTimeElements");
            data = new int[2];
            data[0] = Integer.parseInt(dateTimePatterns[0]);
            data[1] = Integer.parseInt(dateTimePatterns[1]);
             cache update 
            cachedLocaleData.put(desiredLocale, data);
        }
        firstDayOfWeek = data[0];
        minimalDaysInFirstWeek = data[1];*/
    }

    /**
     * Recompute the time and update the status fields isTimeSet
     * and areFieldsSet.  Callers should check isTimeSet and only
     * call this method if isTimeSet is false.
     */
    private void updateTime() {
        computeTime();
        // If we are lenient, we need to recompute the fields to normalize
        // the values.  Also, if we haven't set all the fields yet (i.e.,
        // in a newly-created object), we need to fill in the fields. [LIU]
        if (isLenient() || !areAllFieldsSet) areFieldsSet = false;
        isTimeSet = true;
    }

    /**
     * Adjusts the stamp[] values before nextStamp overflow. nextStamp
     * is set to the next stamp value upon the return.
     */
    private final void adjustStamp() {
        int max = MINIMUM_USER_STAMP;
        int newStamp = MINIMUM_USER_STAMP;

        for (; ;) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stamp.length; i++) {
                int v = stamp[i];
                if (v >= newStamp && min > v) {
                    min = v;
                }
                if (max < v) {
                    max = v;
                }
            }
            if (max != min && min == Integer.MAX_VALUE) {
                break;
            }
            for (int i = 0; i < stamp.length; i++) {
                if (stamp[i] == min) {
                    stamp[i] = newStamp;
                }
            }
            newStamp++;
            if (min == max) {
                break;
            }
        }
        nextStamp = newStamp;
    }

    /**
     * Invalidates the WEEK_OF_MONTH and WEEK_OF_YEAR fields if they
     * have been calculated internally.
     */
    private void invalidateWeekFields() {
        if (stamp[WEEK_OF_MONTH] == INTERNALLY_SET) {
            stamp[WEEK_OF_MONTH] = UNSET;
            isSet[WEEK_OF_MONTH] = false;
            areFieldsSet = false;
        }
        if (stamp[WEEK_OF_YEAR] == INTERNALLY_SET) {
            stamp[WEEK_OF_YEAR] = UNSET;
            isSet[WEEK_OF_YEAR] = false;
            areFieldsSet = false;
        }
    }

    /**
     * Save the state of this object to a stream (i.e., serialize it).
     * <p/>
     * Ideally, <code>Calendar</code> would only write out its state data and
     * the current time, and not write any field data out, such as
     * <code>fields[]</code>, <code>isTimeSet</code>, <code>areFieldsSet</code>,
     * and <code>isSet[]</code>.  <code>nextStamp</code> also should not be part
     * of the persistent state. Unfortunately, this didn't happen before JDK 1.1
     * shipped. To be compatible with JDK 1.1, we will always have to write out
     * the field values and state flags.  However, <code>nextStamp</code> can be
     * removed from the serialization stream; this will probably happen in the
     * near future.
     */
    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        // Try to compute the time correctly, for the future (stream
        // version 2) in which we don't write out fields[] or isSet[].
        if (!isTimeSet) {
            try {
                updateTime();
            } catch (IllegalArgumentException e) {
            }
        }

        // If this Calendar has a ZoneInfo, save it and set a
        // SimpleTimeZone equvalent (as a single DST schedule) for
        // backward compatibility.
        TimeZone savedZone = null;
        if (zone instanceof ZoneInfo) {
            SimpleTimeZone stz = ((ZoneInfo) zone).getLastRuleInstance();
            if (stz == null) {
                stz = new SimpleTimeZone(zone.getRawOffset(), zone.getID());
            }
            savedZone = zone;
            zone = stz;
        }

        // Write out the 1.1 FCS object.
        stream.defaultWriteObject();

        // Write out the ZoneInfo object
        // 4802409: we write out even if it is null, a temporary workaround
        // the real fix for bug 4844924 in corba-iiop
        stream.writeObject(savedZone);
        if (savedZone != null) {
            zone = savedZone;
        }
    }

    /**
     * Reconstitute this object from a stream (i.e., deserialize it).
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        stamp = new int[FIELD_COUNT];

        // Starting with version 2 (not implemented yet), we expect that
        // fields[], isSet[], isTimeSet, and areFieldsSet may not be
        // streamed out anymore.  We expect 'time' to be correct.
        if (serialVersionOnStream >= 2) {
            isTimeSet = true;
            if (fields == null) fields = new int[FIELD_COUNT];
            if (isSet == null) isSet = new boolean[FIELD_COUNT];
        } else if (serialVersionOnStream >= 0) {
            for (int i = 0; i < FIELD_COUNT; ++i)
                stamp[i] = isSet[i] ? INTERNALLY_SET : UNSET;
        }

        serialVersionOnStream = currentSerialVersion;

        // If there's a ZoneInfo object, use it for zone.
        try {
            ZoneInfo zi = (ZoneInfo) stream.readObject();
            if (zi != null) {
                zone = zi;
            }
        } catch (Exception e) {
        }

        // If the deserialized object has a SimpleTimeZone, try to
        // replace it with a ZoneInfo equivalent (as of 1.4) in order
        // to be compatible with the SimpleTimeZone-based
        // implementation as much as possible.
        TimeZone tz = getTimeZone();
        if (tz instanceof SimpleTimeZone) {
            String id = tz.getID();
            TimeZone zi = TimeZone.getTimeZone(id);
            if (zi != null && zi.hasSameRules(tz) && zi.getID().equals(id)) {
                setTimeZone(zi);
            }
        }
    }
}
