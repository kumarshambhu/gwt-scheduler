package gwtscheduler.client.modules.views;

import gwtscheduler.client.widgets.common.CalendarPresenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a main calendar view.
 * @author malp
 */
public interface CalendarMainView {

  /**
   * @return
   */
  Widget asWidget();

  /**
   * Forces the layout.
   */
  void forceLayout();

  /**
   * Gets the current displaying presenter.
   * @return the presenter
   */
  CalendarPresenter getCurrentPresenter();
}
