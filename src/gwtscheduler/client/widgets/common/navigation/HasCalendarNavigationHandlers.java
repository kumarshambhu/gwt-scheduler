package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Defines a handler for calendar navigation events.
 * @author mping
 */
public interface HasCalendarNavigationHandlers {

  /**
   * Adds a {@link CalendarNavigationEvent} handler.
   * @param handler the handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addCalendarNavigationHandler(CalendarNavigationHandler handler);
}
