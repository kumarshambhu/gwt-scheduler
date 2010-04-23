package gwtscheduler.client.utils;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;

/**
 * Generic date factory. Used to calculate the correct interval for a given type
 * of calendar.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 * @see IntervalType
 */
public class GenericDateGenerator implements DateGenerator {

  /** interval start and end */
  private DateTime current;
  /** inner generator */
  private FixedDateGenerator generator;

  @Inject
  private AppConfiguration config;

  /**
   * Default constructor.
   */
  public GenericDateGenerator() {
  }

  public DateTime current() {
    return current;
  }

  public void init(IntervalType interval, ReadableDateTime start) {
    //TODO maybe use a flag|bitmask for resetting fields?
    MutableDateTime mtd = new MutableDateTime(start.getMillis());
    mtd.setMillisOfSecond(0);
    mtd.setSecondOfMinute(0);
    mtd.setMinuteOfHour(0);

    this.current = mtd.toDateTime();

    if (IntervalType.DAY.equals(interval)) {
      generator = new DayDateGenerator();
    } else if (IntervalType.WEEK.equals(interval)) {
      generator = new WeekDateGenerator();
    } else if (IntervalType.MONTH.equals(interval)) {
      generator = new MonthDateGenerator();
    } else {
      throw new IllegalArgumentException("Unknown interval type: " + interval.toString());
    }
    goTo(this.current.toDateTime());
  }

  public void goTo(DateTime start) {
    generator.goTo(start);
  }

  public DateGenerator next() {
    current = generator.next();
    return this;
  }

  public DateGenerator previous() {
    current = generator.previous();
    return this;
  }

  @Override
  public DateTime nextDate() {
    return generator.next();
  }

  @Override
  public DateTime previousDate() {
    return generator.previous();
  }

  public Interval interval() {
    return generator.interval();
  }

  /**
   * Utility interface that defines a fixed date generator.
   * @author malp
   */
  private interface FixedDateGenerator {
    /**
     * Advances the date.
     */
    DateTime next();

    /**
     * Goes backward.
     */
    DateTime previous();

    /**
     * Moves to the specified date.
     * @param start the date
     */
    void goTo(DateTime start);

    /**
     * Returns the current interval.
     * @return the current interval
     */
    Interval interval();
  }

  /**
   * Date generator for days.
   * @author malp
   */
  private class DayDateGenerator implements FixedDateGenerator {

    public Interval interval() {
      DateTime end = current.plusDays(1);
      return new Interval(current, end);
    }

    public void goTo(DateTime where) {
      current = where;
    }

    public DateTime next() {
      return current.plusDays(1);
    }

    public DateTime previous() {
      return current.plusDays(-1);
    }

  }
  /**
   * Date generator for weeks.
   * @author malp
   */
  private class WeekDateGenerator implements FixedDateGenerator {
    /** defines the number of days in a week */
    final int WeekSize;

    /**
     * Default constructor.
     */
    private WeekDateGenerator() {
      WeekSize = AppInjector.GIN.getInjector().getConfiguration().daysInWeek();
    }

    public void goTo(DateTime where) {
      current = where;
    }

    public Interval interval() {
      DateTime end = null;
      //adjust to day of week start
      while (current.getDayOfWeek() != config.startDayOfWeek()) {
        current = current.plusDays(-1);
      }
      end = current.plusDays(WeekSize);
      return new Interval(current, end);
    }

    public DateTime next() {
      return current.plusDays(WeekSize);
    }

    public DateTime previous() {
      return current.plusDays(-WeekSize);
    }

  }

  /**
   * Date generator for months.
   * @author malp
   */
  private class MonthDateGenerator implements FixedDateGenerator {

    public void goTo(DateTime where) {
      MutableDateTime mtd = new MutableDateTime(where.getMillis(), where.getChronology());
      mtd.setDayOfMonth(1);
      current = mtd.toDateTime();
    }

    public Interval interval() {
      DateTime end = null;
      //      MutableDateTime monthStart = current.toMutableDateTime();
      DateTime iterator = new DateTime(current.getMillis(), current.getChronology());
      //      monthStart.setDayOfMonth(1);
      end = current.toDateTime().plusMonths(1);

      //adjust start date so that the first week contains the start day 
      while (iterator.isAfter(current)) {//current always points to first day of month
        iterator = iterator.plusDays(-1);
      }
      while (iterator.getDayOfWeek() != config.startDayOfWeek()) {
        iterator = iterator.plusDays(-1);
      }
      //adjust end so that last day contains last day of month
      while (end.getDayOfWeek() != config.startDayOfWeek()) {
        end = end.plusDays(1);
      }
      end = end.plusDays(-1);//it ends right before the next start of week
      return new Interval(iterator, end);
    }

    public DateTime next() {
      return copyStartPointer(1);
    }

    public DateTime previous() {
      return copyStartPointer(-1);
    }

    /**
     * Utility method that advances a copy of the start date pointer a given
     * amount.
     * @param months the number of months to move. Can be negative
     * @return a copy of the start date pointer
     */
    private DateTime copyStartPointer(int months) {
      return current.plusMonths(months);
    }
  }

}
