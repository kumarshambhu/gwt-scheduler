package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import gwtscheduler.common.calendar.IntervalType;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import org.goda.time.Days;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

import com.google.gwt.user.client.Element;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthPresenter extends AbstractCalendarPresenter<MonthDisplay>
    implements LassoSubject {

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
  public MonthPresenter(AppConfiguration cfg, @Month MonthDisplay display,
      EventBus bus) {
    super(display, bus);
    WeekSize = cfg.daysInWeek();
    display.asLassoPanel().initLasso(new HorizontalLassoStrategy(), this);
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

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.MONTH, date);
    }
    Interval intv = getFactory().interval();
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
  protected void adjustVisibleRows(Interval intv) {
    //must show the necessary rows only
    int weeks = (Days.daysIn(intv).getDays() + 1) / WeekSize;
    getDisplay().showRows(weeks);
  }

  /**
   * LassoSubject impl.
   */

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Interval i = new Interval(getInstantForCell(start), getInstantForCell(end));
    return i;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.addDays(distance);
    return time.toInstant();
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
  public final List<Cell<Element>> getLassoSubjects() {
    return getDisplay().getVisibleElements();
  }

}
