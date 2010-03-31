package gwtscheduler.client.widgets.view.events;

import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.AbstractGridOverlay;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Label;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventsPanel extends AbstractGridOverlay implements WidgetResizeHandler {

  /**
   * Default constructor.
   * @param owner the owner
   */
  public EventsPanel() {
    getElement().getStyle().setPosition(Position.ABSOLUTE);
  }

  public void addAppointment(AppointmentEvent evt) {
    //TODO fix this, this is bad
    // we need the caller to supply coordinates
    int[] from = calculateLeftTop(evt.from);
    //use event factory
    Label label = new Label("ABCDEFGHIJKL");
    label.getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
    add(label, from[0], from[1]);
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    //redraw/resize all child events
    super.onResize(event);
  }

}
