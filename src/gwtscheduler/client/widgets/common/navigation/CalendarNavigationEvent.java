package gwtscheduler.client.widgets.common.navigation;

import org.goda.time.ReadableDateTime;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Logical event for calendar navigation
 * @author mping
 */
public class CalendarNavigationEvent extends GwtEvent<CalendarNavigationHandler> {

  /** Event type. */
  private static final Type<CalendarNavigationHandler> TYPE = new Type<CalendarNavigationHandler>();
  /** the date we are navigating to */
  public final ReadableDateTime date;

  /**
   * Constructor.
   * @param date the date we are navigating to
   */
  public CalendarNavigationEvent(ReadableDateTime date) {
    this.date = date;
  }

  /**
   * Gets the event type associated with calendar nav events.
   * @return the handler type
   */
  public static Type<CalendarNavigationHandler> getType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarNavigationHandler handler) {
    handler.onCalendarNavigation(this);
  }

  @Override
  public Type<CalendarNavigationHandler> getAssociatedType() {
    return TYPE;
  }

}
