package gwtscheduler.client.framework;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * GWT application event bus.
 * @author mping
 */
@Singleton
public class DefaultEventBus extends HandlerManager implements EventBus {

  /**
   * Default constructor
   */
  @Inject
  public DefaultEventBus() {
    super(null);
  }

}