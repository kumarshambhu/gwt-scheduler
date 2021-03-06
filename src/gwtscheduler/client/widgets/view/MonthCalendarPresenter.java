package gwtscheduler.client.widgets.view;

import gwtscheduler.client.framework.EventBus;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationEvent;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Days;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.MutableDateTime;
import org.goda.time.Period;
import org.goda.time.PeriodType;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthCalendarPresenter extends AbstractCalendarPresenter<MonthDisplay> implements ComplexGrid {

  /** defines the number of days in a week */
  private final int WeekSize;

  @Inject
  @Month
  private MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  public MonthCalendarPresenter(AppConfiguration cfg, @Month MonthDisplay display, EventBus bus) {
    super(display, bus);
    WeekSize = cfg.daysInWeek();
    getDisplay().initLasso(new HorizontalLassoStrategy(), this);
  }

  public String getTabLabel() {
    return "Month";
  }

  @Override
  public void onCalendarNavigation(CalendarNavigationEvent calendarNavigationEvent) {
    ReadableDateTime date = calendarNavigationEvent.date;
    if (!date.equals(getDateGenerator().current())) {
      getDateGenerator().init(IntervalType.MONTH, date);
      getDisplay().clearAllAppointments();
    }
    ReadableInterval intv = getDateGenerator().interval();
    adjustVisibleRows(intv);
    decorator.decorate(intv, getDisplay().getDecorables());
  }

  @Override
  public int getColNum() {
    return WeekSize;
  }

  @Override
  public int getRowNum() {
    return getDisplay().getVisibleRows();
  }

  /**
   * Adjusts the number of visible rows, according to the weeks.
   * @param intv the interval that is to show
   */
  protected void adjustVisibleRows(ReadableInterval intv) {
    //must show the necessary rows only
    int weeks = (Days.daysIn(intv).getDays() + 1) / WeekSize;
    getDisplay().showRows(weeks);
  }

  @Override
  public Duration getDurationPerCells(int count) {
    return new Period(1000 * 60 * 60 * 24, PeriodType.days()).toStandardDuration();
  }

  /**
   * LassoSubject impl.
   */

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.addDays(distance);
    return time.toInstant();
  }

  @Override
  protected int[] getPositionForCellIndex(int index) {
    assert index >= 0 : "Index should not be negative";
    assert index < getRowLength() * getColLength() : "Index should be less than total number of cells";

    return new int[] {index / getRowLength(), index % getRowLength()};
  }

  @Override
  public int[] getAbsolutePositionForCell(int[] cellPos) {
    //we override this to take into account month cell labels
    //so we add a slight offset to the returned position
    int[] pos = super.getAbsolutePositionForCell(cellPos);
    return new int[] {pos[0], pos[1] + getDisplay().getTitleHeight(cellPos[0], cellPos[1])};
  }

  /**
   * Allows override of decorator.
   * @param decorator
   */
  public void setDecorator(MultipleElementsIntervalDecorator decorator) {
    this.decorator = decorator;
  }

}
