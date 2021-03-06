package gwtscheduler.client.modules.views;

import gwtscheduler.client.framework.EventBus;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationEvent;
import gwtscheduler.client.widgets.common.navigation.CalendarNavigationHandler;
import gwtscheduler.client.widgets.common.navigation.HasCalendarNavigationHandlers;

import java.util.ArrayList;
import java.util.List;

import org.goda.time.ReadableDateTime;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Holds all calendar controllers. Is responsible for assembling the views.
 * @author malp
 */
@Singleton
public class DefaultUIRegistry implements UIManager, HasCalendarNavigationHandlers {

  /** holds the views data */
  private ArrayList<CalendarPresenter> views;
  /** event bus instance */
  private EventBus eventBus;

  /**
   * Default constructor.
   * @param eventBus the event bus
   * @param day the day controller
   * @param week the week controller
   * @param month the month controller
   */
  @Inject
  public DefaultUIRegistry(EventBus evtBus, @Day CalendarPresenter day, @Week CalendarPresenter week, @Month CalendarPresenter month) {
    eventBus = evtBus;
    views = new ArrayList<CalendarPresenter>();
    addPresenter(day);
    addPresenter(week);
    addPresenter(month);
  }

  //TODO: navigation could be optimized, if the controller
  //is aware of its own visibility. can defer the advance for non-visible controller views

  public void addPresenter(CalendarPresenter presenter) {
    views.add(presenter);
    addCalendarNavigationHandler(presenter.getCalendarNavigationHandler());
  }

  public List<CalendarPresenter> getControllers() {
    return views;
  }

  public void fireDateNavigation(ReadableDateTime date) {
    eventBus.fireEvent(new CalendarNavigationEvent(date));
  }

  @Override
  public HandlerRegistration addCalendarNavigationHandler(CalendarNavigationHandler handler) {
    return eventBus.addHandler(CalendarNavigationEvent.getType(), handler);
  }

}