package gwtscheduler.client;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.TabPanelMainView;

import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today;
  TabPanelMainView main;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    // let's test a registration
    final AppInjector uiResources = AppInjector.GIN.getInjector();
    final UIManager registry = uiResources.getUIRegistry();

    main = uiResources.getMainPanel();

    //TODO stick this in a widget
    back = new Button("&laquo;", this);
    forward = new Button("&raquo;", this);
    today = new Button("today", this);
    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);

    RootPanel.get("nav").add(nav);
    RootPanel.get("main").add(main);
    main.selectTab(0);

    registry.fireDateNavigation(getCurrentDate());
  }

  /**
   * Gets the current date.
   * @return the current date
   */
  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    return start;
  }

  public void onClick(ClickEvent event) {
    AppInjector uiResources = AppInjector.GIN.getInjector();
    UIManager registry = uiResources.getUIRegistry();

    CalendarPresenter curr = main.getCurrentPresenter();
    ReadableDateTime navDate = null;

    if (event.getSource() == back) {
      navDate = curr.getPreviousDate(navDate);
    } else if (event.getSource() == forward) {
      navDate = curr.getNextDate(navDate);
    } else if (event.getSource() == today) {
      navDate = getCurrentDate();
    }

    registry.fireDateNavigation(navDate);

  }
}
