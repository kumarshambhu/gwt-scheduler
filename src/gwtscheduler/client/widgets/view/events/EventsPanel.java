package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.view.common.AbstractGridOverlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is responsible for displaying events.
 * @author malp
 * FIXME migrate to MVP
 */
public abstract class EventsPanel extends AbstractGridOverlay {

  /** holds appointmets and correspondent widgets */
  protected Map<AppointmentEvent, List<Widget>> events;
  /** bookeeping */
  protected Map<Widget, EventRange> widgetSpans;

  /**
   * Default constructor.
   * @param owner the owner
   */
  public EventsPanel() {
    getElement().getStyle().setPosition(Position.ABSOLUTE);
    events = new HashMap<AppointmentEvent, List<Widget>>();
    widgetSpans = new HashMap<Widget, EventRange>();
  }

  /**
   * Maybe splits an event range into several sub event ranges.
   * @param eventRange the event range
   * @return the list of event ranges, possibly containing more than one
   */
  protected abstract List<EventRange> maybeSplit(EventRange eventRange);

  /**
   * Adds an appointment to the events panel.
   * @param app the appointment
   * @param eventRange the event range
   */
  public void addAppointment(AppointmentEvent app, EventRange eventRange) {
    for(EventRange subRange : maybeSplit(eventRange)) {
      doAddAppointment(app, subRange);
    }
  }

  /**
   * Adds a single appointment/event/widget
   * @param evt the event
   * @param eventRange the event range
   */
  protected void doAddAppointment(AppointmentEvent evt, EventRange eventRange) {
    EventWidget evtWidget = new EventWidget();
    evtWidget.getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
    add(evtWidget);

    List<Widget> widgets = events.containsKey(evt) ? events.get(evt) : new ArrayList<Widget>();
    widgets.add(evtWidget);

    events.put(evt, widgets);
    widgetSpans.put(evtWidget, eventRange);

    positionEvent(evt, evtWidget);
    resizeEvent(evt, evtWidget);
  }

  /**
   * Forces the layout of this panel's elements
   */
  public void forceLayout() {
    DeferredCommand.addCommand(new Command() {
      @Override
      public void execute() {
        for (AppointmentEvent event : events.keySet()) {
          for (Widget widget : events.get(event)) {
            positionEvent(event, widget);
            resizeEvent(event, widget);
          }
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
   * @param evtWidget the widget
   */
  protected void positionEvent(AppointmentEvent event, Widget evtWidget) {
    EventRange span = widgetSpans.get(evtWidget);
    int[] position = span.owner.getAbsolutePositionForCell(span.from);
    setWidgetPositionImpl(evtWidget, position[0], position[1]);
  }

  /**
   * Resizes the event
   * @param event the event
   * @param evtWidget the widget
   */
  protected void resizeEvent(AppointmentEvent event, Widget evtWidget) {
    EventRange range = widgetSpans.get(evtWidget);
    float width = (float) range.owner.getEffectiveWidth() / range.owner.getColNum();
    float height = (float) range.owner.getEffectiveHeight() / range.owner.getRowNum();

    int rowspan = range.to[0] - range.from[0] + 1;
    int colspan = range.to[1] - range.from[1] + 1;

    evtWidget.setPixelSize((int) width * colspan, (int) height * rowspan);
  }

}
