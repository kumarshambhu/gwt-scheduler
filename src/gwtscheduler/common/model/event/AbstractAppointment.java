package gwtscheduler.common.model.event;

import java.io.Serializable;

import org.goda.time.Interval;

/**
 * Super class for events.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class AbstractAppointment implements Serializable {

  /** the event's date/time range */
  protected Interval interval;
  /** the event type */
  protected EventType type;

  /**
   * Creates a new scheduled event.
   * @param interval the interval
   * @param type the event type
   */
  public AbstractAppointment(Interval interval, EventType type) {
    this.interval = filter(interval);
    this.type = type;
  }

  /**
   * Gets the current interval.
   * @return the interval
   */
  public Interval interval() {
    return interval;
  }

  /**
   * Filters dates, removing the unecessary parts.
   * @param date the date to filter
   */
  protected abstract Interval filter(Interval interval);

}
