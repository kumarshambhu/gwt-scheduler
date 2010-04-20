package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.resources.Resources;
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
public abstract class EventsPanel extends AbstractGridOverlay {

  /** holds appointmets and correspondent widgets */
  protected Map<AppointmentEvent, Widget> events;
  /** bookeeping */
  protected Map<Widget, EventSpan> widgetSpans;

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
    //TODO use a .gwt.xml template instead
    Widget evtWidget = new HTML("<b>Event</b>");
    evtWidget.getElement().setClassName(Resources.dayWeekCss().eventElement());
    evtWidget.getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
    add(evtWidget);

    events.put(evt, evtWidget);
    widgetSpans.put(evtWidget, eventSpan);

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
          Widget widget = events.get(event);
          positionEvent(event, widget);
          resizeEvent(event, widget);
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
    EventSpan span = widgetSpans.get(evtWidget);
    int[] coords = span.owner.getAbsolutePositionForCell(span.from);
    setWidgetPositionImpl(evtWidget, coords[0], coords[1]);
  }

  /**
   * Resizes the event
   * @param event the event
   * @param evtWidget the widget
   */
  protected void resizeEvent(AppointmentEvent event, Widget evtWidget) {
    EventSpan span = widgetSpans.get(evtWidget);
    float width = (float) span.owner.getEffectiveWidth() / span.owner.getColNum();
    float height = (float) span.owner.getEffectiveHeight() / span.owner.getRowNum();

    //TODO account for scroll bar
    int rowspan = span.to[0] - span.from[0] + 1;
    int colspan = span.to[1] - span.from[1] + 1;

    evtWidget.setPixelSize((int) width * colspan, (int) height * rowspan);
  }

}
