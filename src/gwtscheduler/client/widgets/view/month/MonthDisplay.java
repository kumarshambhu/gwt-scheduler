package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.common.GenericCalendarDisplay;

/**
 * Display class for months.
 * @author malp
 */
public interface MonthDisplay extends GenericCalendarDisplay {

  /**
   * Shows only the supplied rows.
   * @param rows the number of weeks to show
   */
  void showRows(int rows);

  /**
   * Gets the number of visible rows
   * @return
   */
  int getVisibleRows();

  /**
   * Gets the title height in pixels, for the specified cell
   * @param row the row index
   * @param col the col index
   * @return the title bar height
   */
  int getTitleHeight(int row, int col);

}
