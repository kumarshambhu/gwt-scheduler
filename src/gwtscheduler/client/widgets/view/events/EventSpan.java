package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.widgets.common.CalendarPresenter;

/**
 * @author mping
 */
public class EventSpan {

  public final CalendarPresenter owner;
  public final int[] from;
  public final int[] to;

  /**
   * @param owner
   * @param from
   * @param to
   */
  public EventSpan(CalendarPresenter owner, int[] from, int[] to) {
    super();
    this.owner = owner;
    this.from = from;
    this.to = to;
  }

}
