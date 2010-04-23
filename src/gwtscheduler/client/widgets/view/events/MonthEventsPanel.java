package gwtscheduler.client.widgets.view.events;

import java.util.ArrayList;
import java.util.List;

import gwtscheduler.client.widgets.common.event.AppointmentEvent;

import com.google.gwt.user.client.ui.Widget;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class MonthEventsPanel extends EventsPanel {

  @Override
  protected List<EventRange> maybeSplit(EventRange eventRange) {
    List<EventRange> ranges = new ArrayList<EventRange>();

    if (eventRange.isMultiRow()) {
      int startRow = eventRange.from[0];
      int endRow = eventRange.to[0];

      for (int row = startRow; row <= endRow; row++) {
        int[] from = (row == startRow) ? eventRange.from : new int[] {row, 0};
        int[] to = (row == endRow) ? eventRange.to : new int[] {row, eventRange.owner.getColNum()};

        EventRange subRange = new EventRange(eventRange.owner, from, to);
        ranges.add(subRange);
      }
    } else {
      ranges.add(eventRange);
    }
    return ranges;
  }

  @Override
  protected void resizeEvent(AppointmentEvent event, Widget evtWidget) {
    EventRange range = widgetSpans.get(evtWidget);
    float width = (float) range.owner.getEffectiveWidth() / range.owner.getColNum();
    //    float height = (float) span.owner.getEffectiveHeight() / span.owner.getRowNum();
    //    int rowspan = span.to[0] - span.from[0] + 1;

    //when the appointment comes from a day/week, the colspan would be 0
    int colspan = Math.max(range.to[1] - range.from[1] + 1, 1);

    evtWidget.setWidth((int) width * colspan + "px");
    evtWidget.setHeight("1em");
    //evtWidget.setPixelSize((int) width * colspan, (int) height * rowspan);
  }
}
