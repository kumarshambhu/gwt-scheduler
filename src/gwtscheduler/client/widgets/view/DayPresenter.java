package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
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
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayPresenter extends AbstractCalendarPresenter<AbstractDaysView> {

  /** number of rows */
  public final int rows;

  @Day
  @Inject
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayPresenter(AppConfiguration cfg, @Day AbstractDaysView view, EventBus bus) {
    super(view, bus);
    rows = cfg.rowsInDay();
    getDisplay().initLasso(new VerticalLassoStrategy(false), this);
  }

  public String getTabLabel() {
    return "Day";
  }

  @Override
  public int getColNum() {
    return 1;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getDisplay().getDecorables());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getDisplay().getDecorables());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getDisplay().getDecorables());
    return period;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.add(getDurationPerCells(distance));
    return time.toInstant();
  }

  @Override
  public Duration getDurationPerCells(int count) {
    int minutesPerCell = (24 * 60) / getRowNum();
    return new Period(0, minutesPerCell * count, 0, 0).toStandardDuration();
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

  @Override
  protected int[] getPositionForCellIndex(int index) {
    assert index > 0 : "Index should be bigger than zero";
    assert index < getColLength() : "Index should be less than total number of cells";

    return new int[] {index, 0};
  }

}
