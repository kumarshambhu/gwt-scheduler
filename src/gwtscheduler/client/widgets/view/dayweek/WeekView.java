package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.modules.AppInjector;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class WeekView extends AbstractDaysView {

  @Override
  protected AbstractDaysPanel buildDaysPanel() {
    return new WeekPanel();
  }

  @Override
  protected int getColumnsSize() {
    return 7;
  }

  @Override
  public int getEffectiveWidth() {
    int padding = getColNum() * (CSS.mediumBorderPx() - CSS.smallBorderPx());
    return getWidth() - padding;
  }

  @Override
  public int getEffectiveHeight() {
    return getHeight();
  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class WeekPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 7;
    }

    @Override
    protected int getRows() {
      return AppInjector.GIN.getInjector().getConfiguration().rowsInDay();
    }
  }
}