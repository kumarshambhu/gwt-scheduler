package gwtscheduler.client.widgets.common;

import gwtscheduler.client.framework.mvp.Presenter;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationHandler;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a calendar controller. Responsible for mediating the view and the
 * listener. For most cases, the implementing class will be the listener itself.
 * @author malp
 */
public interface CalendarPresenter extends Presenter {

  /**
   * Gets the label for the view.
   * @return the label
   */
  String getTabLabel();

  /**
   * Gets the navigation events handler.
   * @return the handler
   */
  CalendarNavigationHandler getCalendarNavigationHandler();

  /**
   * Gets the widget.
   * @return the widget
   */
  Widget getWidgetDisplay();

  /**
   * Forces the layout of the display.
   */
  void forceLayout();

  /**
   * Gets the correspondent time interval for a given cell range
   * @param start the starting cell
   * @param end the end cell
   * @return the time interval
   */
  Interval getIntervalForRange(int[] start, int[] end);

  /**
   * Gets the correspondent instant for a cell
   * @param start the starting cell
   * @param end the end cell
   */
  Instant getInstantForCell(int[] start);

  /**
   * Indicates if the supplied interval is within the current presenting date
   * range.
   * @param interval the interval
   * @return <code>true</code> if any part of the interval fits within the
   *         current presenting date range
   */
  boolean isWithinDateRange(Interval interval);

  /**
   * Gets the current interval.
   * @return the current interval
   */
  Interval getCurrentInterval();

  /**
   * Returns the previous instant for the supplied date, according to this
   * presenter's properties.
   * @param navDate the nav date
   * @return the previous date
   */
  Interval getPreviousInterval(ReadableDateTime navDate);

  /**
   * Returns the next date for the supplied date, according to this presenter's
   * properties.
   * @param navDate the nav date
   * @return the next date
   */
  Interval getNextInterval(ReadableDateTime navDate);

}
