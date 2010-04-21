package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.modules.AppInjector;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class DayView extends AbstractDaysView {

  @Override
  protected AbstractDaysPanel buildDaysPanel() {
    return new DayPanel();
  }

  @Override
  protected int getColumnsSize() {
    return 1;
  }

  @Override
  public int getEffectiveWidth() {
    return getWidth() /*- CSS.smallPaddingPx()*/;
  }

  @Override
  public int getEffectiveHeight() {
    return getHeight();
  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class DayPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 1;
    }

    @Override
    protected int getRows() {
      return AppInjector.GIN.getInjector().getConfiguration().rowsInDay();
    }
  }
}