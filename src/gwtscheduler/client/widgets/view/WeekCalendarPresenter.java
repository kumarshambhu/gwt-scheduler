package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationEvent;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import gwtscheduler.common.calendar.IntervalType;
import net.customware.gwt.presenter.client.EventBus;

import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.Period;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * @author malp
 */
@Singleton
public class WeekCalendarPresenter extends AbstractCalendarPresenter<AbstractDaysView> {

  /** holds the number of rows within a day */
  private final int rows;

  @Inject
  @Week
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected WeekCalendarPresenter(AppConfiguration cfg, @Week AbstractDaysView view, EventBus bus) {
    super(view, bus);
    rows = cfg.rowsInDay();
    getDisplay().initLasso(new VerticalLassoStrategy(false), this);
  }

  public String getTabLabel() {
    return "Week";
  }

  @Override
  public int getColNum() {
    return 7;

  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public void onCalendarNavigation(CalendarNavigationEvent calendarNavigationEvent) {
    ReadableDateTime date = calendarNavigationEvent.date;
    if (!date.equals(getFactory().current())) {
      MutableDateTime copy = date.toMutableDateTime();
      AppConfiguration cfg = AppInjector.GIN.getInjector().getConfiguration();
      //adjust to first day of week
      int firstDay = cfg.startDayOfWeek();
      while (copy.getDayOfWeek() != firstDay) {
        copy.addDays(-1);
      }
      getFactory().init(IntervalType.WEEK, copy);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getDisplay().getDecorables());
  }

  public Instant getInstantForCell(int[] start) {
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    int minutesPerCell = (24 * 60) / getRowNum();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.addMinutes(minutesPerCell * distance);
    return time.toInstant();
  }

  @Override
  public Duration getDurationPerCells(int count) {
    int minutesPerCell = (24 * 60) / getRowNum();
    return new Period(0, minutesPerCell * count, 0, 0).toStandardDuration();
  }

  @Override
  protected int[] getPositionForCellIndex(int index) {
    assert index >= 0 : "Index should not be negative";
    assert index < getColLength() * getRowLength() : "Index should be less than total number of cells";

    return new int[] {index / getColLength(), index % getColLength()};
  }

}
