package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;

/**
 * Holds event draw information.
 * @author mping
 */
public class EventRange {

  public final AbstractCalendarPresenter<?> owner;
  /** the start position */
  public final int[] from;
  /** the end position */
  public final int[] to;

  /**
   * Default constructor.
   * @param owner the presenter owner
   * @param from the from grid pos
   * @param to the to grid pos
   */
  public EventRange(AbstractCalendarPresenter<?> owner, int[] from, int[] to) {
    this.owner = owner;
    this.from = from;
    this.to = to;
  }

  /**
   * Indicates if this event range spans more than one row.
   * @return <code>true</code> if this event range spans more than one row
   */
  public boolean isMultiRow() {
    return from[0] != to[0];
  }

  /**
   * Indicates if this event range spans more than one row.
   * @return <code>true</code> if this event range spans more than one row
   */
  public boolean isMultiColumn() {
    return from[1] != to[1];
  }

}
