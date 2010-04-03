package gwtscheduler.client.widgets.common.navigation;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.CalendarMainView;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.common.CalendarPresenter;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Main navigation panel.
 * @author malp
 */
//TODO migrate to MVP
public class TabPanelMainView extends Composite implements CalendarMainView, BeforeSelectionHandler<Integer>, SelectionHandler<Integer> {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget delegate */
  private DecoratedTabPanel impl;
  /** presenters array */
  private CalendarPresenter[] presenters;
  /** curr sel index */
  private int currIndex;

  /**
   * Default constructor.
   * @param day
   * @param week
   * @param month
   */
  @Inject
  public TabPanelMainView(@Day CalendarPresenter day, @Week CalendarPresenter week, @Month CalendarPresenter month) {
    impl = new DecoratedTabPanel();
    initWidget(impl);
    impl.addBeforeSelectionHandler(this);
    impl.addSelectionHandler(this);

    presenters = new CalendarPresenter[3];
    presenters[0] = day;
    presenters[1] = week;
    presenters[2] = month;

    add(day);
    add(week);
    add(month);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void forceLayout() {
    for (CalendarPresenter p : presenters) {
      p.forceLayout();
    }
  }

  public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
    //    CalendarPresenter presenter = presenters[event.getItem()];
    //    presenter.forceLayout();
  }

  @Override
  public void onSelection(SelectionEvent<Integer> event) {
    currIndex = event.getSelectedItem();
    CalendarPresenter presenter = presenters[currIndex];
    presenter.forceLayout();
  }

  /**
   * Adds a new view to this tab panel.
   * @param presenter the controller
   */
  private void add(CalendarPresenter presenter) {
    Widget view = presenter.getWidgetDisplay();
    TabPanelContainer container = new TabPanelContainer();
    container.add(view);

    impl.add(container, presenter.getTabLabel());
  }

  /**
   * Gets the current displaying presenter
   * @return the presenter
   */
  public CalendarPresenter getCurrentPresenter() {
    return presenters[currIndex];
  }

  /**
   * Selects a tab.
   * @param i the tab index
   */
  public void selectTab(int i) {
    impl.selectTab(i);
  }

}
