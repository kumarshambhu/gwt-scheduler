package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.framework.EventBus;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.event.AbstractLassoEvent;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.LassoCancelSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEndSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEventHandler;
import gwtscheduler.client.widgets.common.event.LassoStartSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoUpdateSelectionEvent;
import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.AllDayAppointment;
import gwtscheduler.common.model.event.SimpleAppointment;

import org.goda.time.Duration;
import org.goda.time.Interval;

/**
 * Mediates event handling.
 * @author malp
 */
public class EventsMediator implements LassoEventHandler {

  private CalendarPresenter presenter;
  private EventBus eventBus;

  /**
   * Creates a new events mediator.
   * @param pr the presenter
   * @param eventBus the event bus
   */
  public EventsMediator(CalendarPresenter pr, EventBus eventBus) {
    this.presenter = pr;
    this.eventBus = eventBus;
    eventBus.addHandler(AbstractLassoEvent.getType(), this);
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    if (event.subject == presenter) {
      AbstractAppointment appointment = null;
      Interval time = presenter.getIntervalForRange(event.cell, event.endCell);
      if (time.toDuration().isLongerThan(new Duration(1000 * 60 * 60 * 24))) {
        appointment = new AllDayAppointment(time);
      } else {
        appointment = new SimpleAppointment(time);
      }
      AppointmentEvent evt = new AppointmentEvent(appointment);
      eventBus.fireEvent(evt);
    }
  }

  @Override
  public void onCancelSelection(LassoCancelSelectionEvent event) {
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
  }
}
