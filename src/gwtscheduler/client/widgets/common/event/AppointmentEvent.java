package gwtscheduler.client.widgets.common.event;

import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.common.model.event.AbstractAppointment;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AppointmentEvent extends GwtEvent<AppointmentHandler> {

  //TODO change to an interface
  public final CalendarPresenter owner;
  public final AbstractAppointment appointment;

  /**
   * Default constructor for an appointment event
   * @param owner the owner
   * @param appointment the appointment
   */
  public AppointmentEvent(CalendarPresenter owner, AbstractAppointment appointment) {
    this.owner = owner;
    this.appointment = appointment;
  }

  /**
   * Event type for blur events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<AppointmentHandler> TYPE = new Type<AppointmentHandler>();

  /**
   * Gets the event type associated with resize events.
   * @return the handler type
   */
  public static Type<AppointmentHandler> getType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AppointmentHandler handler) {
    handler.onAddEvent(this);
  }

  @Override
  public Type<AppointmentHandler> getAssociatedType() {
    return TYPE;
  }
}
