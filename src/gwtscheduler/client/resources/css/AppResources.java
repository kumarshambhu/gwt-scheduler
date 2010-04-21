package gwtscheduler.client.resources.css;

import com.google.gwt.resources.client.ClientBundle;

/**
 * Defines structural css resources. "st" in filename stands for "Structural".
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface AppResources extends ClientBundle {

  /**
   * This is the main stylesheet for days and weeks.
   * @return the day/week stylesheet resource
   */
  @Source(value = {"base.css", "base-day-week.css"})
  public DayWeekCssResource dayWeekCss();

  /**
   * This is the main stylesheet for months.
   * @return the month stylesheet resource
   */
  @Source(value = {"base.css", "base-month.css"})
  public MonthCssResource monthCss();

}
