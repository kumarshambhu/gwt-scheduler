package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.framework.EventBus;
import gwtscheduler.client.framework.mvp.WidgetPresenter;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.GenericCalendarDisplay;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.AppointmentHandler;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationHandler;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.events.EventRange;
import gwtscheduler.client.widgets.view.events.EventsMediator;

import org.goda.time.DateTime;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Generic class for a calendar presenter.
 * @author malp
 */
public abstract class AbstractCalendarPresenter<T extends GenericCalendarDisplay> extends WidgetPresenter<T> implements CalendarPresenter,
    CalendarNavigationHandler, ComplexGrid, AppointmentHandler {

  @Inject
  private DateGenerator generator;

  /**
   * Default constructor.
   * @param display the display
   * @param eventBus the event bus
   */
  protected AbstractCalendarPresenter(T display, EventBus eventBus) {
    super(display, eventBus);
    new EventsMediator(this, eventBus);
    eventBus.addHandler(AppointmentEvent.getType(), this);
  }

  @Override
  public Interval getCurrentInterval() {
    return getDateGenerator().interval();
  }

  @Override
  public CalendarNavigationHandler getCalendarNavigationHandler() {
    return this;
  }

  @Override
  public ReadableDateTime getNextDate(ReadableDateTime from) {
    return getDateGenerator().nextDate();
  }

  @Override
  public ReadableDateTime getPreviousDate(ReadableDateTime from) {
    return getDateGenerator().previousDate();
  }

  /**
   * Events methods.
   */

  @Override
  public void onAddEvent(AppointmentEvent evt) {
    Interval interval = evt.appointment.interval();
    if (isWithinDateRange(interval)) {
      int[] from = getCellPositionFor(interval.getStart(), true);
      int[] to = getCellPositionFor(interval.getEnd(), false);
      EventRange eventSpan = new EventRange(this, from, to);
      getDisplay().addAppointment(evt, eventSpan);
    }
  }

  @Override
  public boolean isWithinDateRange(Interval interval) {
    return getDateGenerator().interval().contains(interval);
  }

  /**
   * Presenter methods.
   */

  @Override
  public Widget getWidgetDisplay() {
    return getDisplay().asWidget();
  }

  @Override
  public void forceLayout() {
    getDisplay().forceLayout();
  }

  /**
   * Gets the date factory.
   * @return the date factory
   */
  protected DateGenerator getDateGenerator() {
    return generator;
  }

  /**
   * Lasso Subject methods.
   */

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  public int getEffectiveWidth() {
    return getDisplay().getEffectiveWidth();
  }

  public int getEffectiveHeight() {
    return getDisplay().getEffectiveHeight();
  }

  /**
   * Gets the length of a row.
   * @return the row length
   */
  protected final int getRowLength() {
    return getColNum();
  }

  /**
   * Gets the length of a column.
   * @return the col length
   */
  protected final int getColLength() {
    return getRowNum();
  }

  /****************************************
   * Cell calculations & date calculations .
   *****************************************/
  @Override
  public abstract Instant getInstantForCell(int[] start);

  /**
   * Gets the duration per cells.
   * @param count the number of cells
   * @return the duration per cells
   */
  protected abstract Duration getDurationPerCells(int count);

  /**
   * Gets the row and column values for a given cell index.
   * @param index the index
   * @return the row and column indexes
   */
  protected abstract int[] getPositionForCellIndex(int index);

  /**
   * Gets a cell position for a given date.
   * @param date the date
   * @param include <code>true</code> to include the date
   * @return the cell position in [row, column] format
   */
  protected int[] getCellPositionFor(ReadableDateTime date, boolean include) {
    int count = 0;
    Duration cellDuration = getDurationPerCells(1);
    DateTime current = getDateGenerator().interval().getStart();

    //TODO this could be heavily optimized
    //we calculate the cell index, then we defer the row|col calculation
    Interval i = new Interval(current, cellDuration);
    while (!i.contains(date)) {
      current = current.plus(cellDuration);
      i = new Interval(current, cellDuration);
      count++;
    }
    return getPositionForCellIndex(include ? count : Math.max(count - 1, 0));
  }

  /**
   * Gets the absolute coordinates for a given cell.
   * @param coordinates the cell position
   * @return the coordinates in pixels
   */
  //TODO maybe this method belongs in the display...
  public int[] getAbsolutePositionForCell(int[] cellPos) {
    assert cellPos[0] < getRowNum() : "cell row num > presnter row num!";
    assert cellPos[1] < getColNum() : "cell row num > presnter row num!";
    assert cellPos != null : "Cell position cannot be null";
    assert cellPos.length == 2 : "Position length != 2";
    
    int rowH = Math.round((float) getHeight() / getRowNum());
    int colW = Math.round((float) getWidth() / getColNum());
    return new int[] {cellPos[1] * colW, cellPos[0] * rowH};
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end).plus(getDurationPerCells(1));
    //this is to make sure that [0,0] is at least one cell's duration
    return new Interval(from, to);
  }

  /*******************
   * Display methods.
   *******************/

  @Override
  protected void onBind() {
  }

  @Override
  protected void onUnbind() {
  }

  /********************************************************
   * Setters for testing. These are not actually used live
   *********************************************************/

  /**
   * Sets the date generator factory.
   * @param factory the factory
   */
  public void setFactory(DateGenerator factory) {
    this.generator = factory;
  }

}
