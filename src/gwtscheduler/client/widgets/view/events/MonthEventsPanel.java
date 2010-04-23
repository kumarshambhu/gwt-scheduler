package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.widgets.common.event.AppointmentEvent;

import com.google.gwt.user.client.ui.Widget;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class MonthEventsPanel extends EventsPanel {

  /**
   * Resizes the event
   * @param event the event
   * @param evtWidget the widget
   */
  protected void resizeEvent(AppointmentEvent event, Widget evtWidget) {
    EventSpan span = widgetSpans.get(evtWidget);
    float width = (float) span.owner.getEffectiveWidth() / span.owner.getColNum();
    //    float height = (float) span.owner.getEffectiveHeight() / span.owner.getRowNum();
    //    int rowspan = span.to[0] - span.from[0] + 1;
    int colspan = span.to[1] - span.from[1] + 1;

    evtWidget.setWidth((int) width * colspan + "px");
    evtWidget.setHeight("1em");
    //evtWidget.setPixelSize((int) width * colspan, (int) height * rowspan);
  }
}
