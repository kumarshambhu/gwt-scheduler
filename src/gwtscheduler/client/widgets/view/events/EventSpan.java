package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;

/**
 * Holds event draw information.
 * @author mping
 */
public class EventSpan {

  public final AbstractCalendarPresenter<?> owner;

  public final int[] from;
  public final int[] to;

  /**
   * Default constructor.
   * @param owner the presenter owner
   * @param from the from grid pos
   * @param to the to grid pos
   */
  public EventSpan(AbstractCalendarPresenter<?> owner, int[] from, int[] to) {
    this.owner = owner;
    this.from = from;
    this.to = to;
  }

}
