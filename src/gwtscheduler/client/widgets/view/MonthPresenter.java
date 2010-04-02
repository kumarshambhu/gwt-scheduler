package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import gwtscheduler.common.calendar.IntervalType;
import net.customware.gwt.presenter.client.EventBus;

import org.goda.time.Days;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
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
public class MonthPresenter extends AbstractCalendarPresenter<MonthDisplay> implements ComplexGrid {

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
  public MonthPresenter(AppConfiguration cfg, @Month MonthDisplay display, EventBus bus) {
    super(display, bus);
    WeekSize = cfg.daysInWeek();
    getDisplay().initLasso(new HorizontalLassoStrategy(), this);
  }

  public String getTabLabel() {
    return "Month";
  }

  public Interval onNavigateNext() {
    Interval next = getFactory().next().interval();
    adjustVisibleRows(next);
    decorator.decorate(next, getDisplay().getDecorables());
    return next;
  }

  public Interval onNavigatePrevious() {
    Interval prev = getFactory().previous().interval();
    adjustVisibleRows(prev);
    decorator.decorate(prev, getDisplay().getDecorables());
    return prev;
  }

  public ReadableInterval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.MONTH, date);
    }
    ReadableInterval intv = getFactory().interval();
    adjustVisibleRows(intv);
    decorator.decorate(intv, getDisplay().getDecorables());
    return intv;
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
    assert index > 0 : "Index should be bigger than zero";
    assert index < getRowLength() * getColLength() : "Index should be less than total number of cells";

    return new int[] {index / getRowLength(), index % getRowLength()};
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

  /**
   * Allows override of decorator.
   * @param decorator
   */
  public void setDecorator(MultipleElementsIntervalDecorator decorator) {
    this.decorator = decorator;
  }

}
