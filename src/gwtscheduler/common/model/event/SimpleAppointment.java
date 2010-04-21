package gwtscheduler.common.model.event;

import java.io.Serializable;

import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.EventType;

import org.goda.time.Interval;

/**
 * Defines a simple event, ie, an event that does not last more than a day.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class SimpleAppointment extends AbstractAppointment implements Serializable {

  /**
   * @param start
   * @param end
   */
  public SimpleAppointment(Interval interval) {
    super(interval, EventType.SIMPLE);
  }

  @Override
  protected Interval filter(Interval interval) {
    return interval;
  }

}
