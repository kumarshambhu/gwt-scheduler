package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.EventHandler;

/**
 * 
 * @author mping
 */
public interface CalendarNavigationHandler extends EventHandler {

  /**
   * Fired when navigation ocurred.
   * @param calendarNavigationEvent the event
   */
  void onCalendarNavigation(CalendarNavigationEvent calendarNavigationEvent);

}
