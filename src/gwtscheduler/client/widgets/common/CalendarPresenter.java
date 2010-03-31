package gwtscheduler.client.widgets.common;

import org.goda.time.Instant;
import org.goda.time.Interval;

import gwtscheduler.client.widgets.common.navigation.EventNavigationListener;
import net.customware.gwt.presenter.client.Presenter;

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
   * Gets the navigation events listener.
   * @return the listener
   */
  EventNavigationListener getNavigationListener();

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

}
