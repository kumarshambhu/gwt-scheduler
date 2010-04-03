package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.view.common.AbstractGridOverlay;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventsPanel extends AbstractGridOverlay  {

  /** holds appointmets and correspondent widgets */
  Map<AppointmentEvent, Widget> events;
  /** bookeeping */
  Map<Widget, EventSpan> widgetSpans;

  /**
   * Default constructor.
   * @param owner the owner
   */
  public EventsPanel() {
    getElement().getStyle().setPosition(Position.ABSOLUTE);
    events = new HashMap<AppointmentEvent, Widget>();
    widgetSpans = new HashMap<Widget, EventSpan>();
  }

  /**
   * Adds an event.
   * @param evt
   * @param eventSpan
   */
  public void addAppointment(AppointmentEvent evt, EventSpan eventSpan) {
    Widget evtWidget = new HTML("<b>Event</b>");
    evtWidget.getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
    add(evtWidget);

    events.put(evt, evtWidget);
    widgetSpans.put(evtWidget, eventSpan);

    positionEvent(evt, evtWidget);
  }


  /**
   * Forces the layout of this panel's elements
   */
  public void forceLayout() {
    DeferredCommand.addCommand(new Command(){
      @Override
      public void execute() {
        for (AppointmentEvent event : events.keySet()) {
          positionEvent(event, events.get(event));
        }
      }
    });
  }

  @Override
  public void clear() {
    super.clear();
    events.clear();
    widgetSpans.clear();
  }

  /**
   * Positions the appointment.
   * @param event the event
   * @param w the widget
   */
  private void positionEvent(AppointmentEvent event, Widget w) {
    EventSpan span = widgetSpans.get(w);
    int[] coords = span.owner.getAbsolutePositionForCell(span.from);
    setWidgetPositionImpl(w, coords[0], coords[1]);
  }

}
