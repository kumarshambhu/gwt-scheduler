package gwtscheduler.client.mockClasses;

import gwtscheduler.client.framework.mvp.Display;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationHandler;

import org.goda.time.DateTime;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

import com.google.gwt.user.client.ui.Widget;

/**
 * Utility class for lasso tests.
 * @author malp
 */
public class MockDateTimeAwarePresenter implements ComplexGrid, CalendarPresenter {

  final int rows, cols;

  static Interval Interval;

  static {
    DateTime dt1 = new DateTime(2009, 01, 01, 01, 01, 01, 01);
    DateTime dt2 = new DateTime(2009, 01, 31, 01, 01, 01, 01);
    Interval = new Interval(dt1, dt2);
  }

  /**
   * Creates a new mock lasso subject, with a 100px x 100px grid.
   * @param rows the number of rows
   * @param cols the number of cols
   */
  public MockDateTimeAwarePresenter(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public int getColNum() {
    return cols;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public int getWidth() {
    return 100;
  }

  @Override
  public int getHeight() {
    return 100;
  }

  @Override
  public int getEffectiveHeight() {
    return 100;
  }

  @Override
  public int getEffectiveWidth() {
    return 100;
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end);
    //ranges are closed on start and open on end
    //[start, end[
    // so a correction is needed
    MutableDateTime last = to.toMutableDateTime();
    last.addDays(1);
    Interval i = new Interval(from, last.toInstant());
    return i;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = Interval.toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();

    time.addDays(distance);
    return time.toInstant();
  }

  @Override
  public boolean isWithinDateRange(Interval interval) {
    return false;
  }

  @Override
  public CalendarNavigationHandler getCalendarNavigationHandler() {
    return null;
  }

  @Override
  public Interval getCurrentInterval() {
    return null;
  }

  @Override
  public Interval getNextInterval(ReadableDateTime navDate) {
    return null;
  }

  @Override
  public Interval getPreviousInterval(ReadableDateTime navDate) {
    return null;
  }

  @Override
  public void forceLayout() {
  }

  @Override
  public String getTabLabel() {
    return null;
  }

  @Override
  public Widget getWidgetDisplay() {
    return null;
  }

  @Override
  public void bind() {
  }

  @Override
  public Display getDisplay() {
    return null;
  }

  @Override
  public void unbind() {
  }

}