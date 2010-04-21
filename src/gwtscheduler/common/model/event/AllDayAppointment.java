package gwtscheduler.common.model.event;

import java.io.Serializable;

import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.EventType;

import org.goda.time.Interval;

/**
 * Defines an all-day event.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AllDayAppointment extends AbstractAppointment implements Serializable {

  /**
   * @param interval
   * @param type
   */
  public AllDayAppointment(Interval interval) {
    super(interval, EventType.ALLDAY);
  }

  @Override
  protected Interval filter(Interval interval) {
    return interval;
  }

}
