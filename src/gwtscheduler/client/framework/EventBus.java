package gwtscheduler.client.framework;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Event Bus definition.
 * @author mping
 */
public interface EventBus {

  /**
   * Adds a handler to this event bus.
   * @param <H> the handler generic type
   * @param type the handler type
   * @param handler the nalder
   * @return the handler registration
   */
  <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler);

  /**
   * Fires an event onto this event bus.
   * @param event the event to fire
   */
  void fireEvent(GwtEvent<?> event);

  /**
   * @param <H>
   * @param type
   * @param index
   * @return
   */
  <H extends EventHandler> H getHandler(Type<H> type, int index);

  /**
   * Gets the handler count for a given handler type.
   * @param type the handler type
   * @return the handler count
   */
  int getHandlerCount(Type<?> type);

  /**
   * Indicates if a given event is handled.
   * @param e the event
   * @return <code>true</code> if the event is handled
   */
  boolean isEventHandled(Type<?> e);
}
