package gwtscheduler.client.modules.views;

import gwtscheduler.client.widgets.common.CalendarPresenter;

import java.util.List;

import org.goda.time.ReadableDateTime;

/**
 * Defines a calendar controllers registry.
 * @author malp
 */
public interface UIManager {

  /**
   * Gets the views.
   * @return the views
   */
  List<CalendarPresenter> getControllers();

  /**
   * Adds a new controller.
   * @param provider the controller
   */
  void addPresenter(CalendarPresenter controller);

  /**
   * Fires the navigation event that goes to a specific date.
   * @param date the date
   */
  void fireDateNavigation(ReadableDateTime date);

}
