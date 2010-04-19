package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.utils.Constants;

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
    //TODO make sure this is correct
    int padding = getColNum() * Resources.dayWeekCss().smallPaddingPx();
    return getWidth() - Constants.SCROLLBAR_WIDTH() - padding;
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