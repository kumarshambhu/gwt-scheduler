package gwtscheduler.client.widgets.view.events;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class DaysEventsPanel extends EventsPanel {

  @Override
  protected List<EventRange> maybeSplit(EventRange eventRange) {
    List<EventRange> ranges = new ArrayList<EventRange>();
    if (eventRange.isMultiColumn()) {
      int startCol = eventRange.from[1];
      int endCol = eventRange.to[1];

      for (int col = startCol; col <= endCol; col++) {
        int[] from = (col == startCol) ? eventRange.from : new int[] {0, col};
        int[] to = (col == endCol) ? eventRange.to : new int[] {eventRange.owner.getRowNum() - 1, col};

        EventRange subRange = new EventRange(eventRange.owner, from, to);
        ranges.add(subRange);
      }
    } else {
      ranges.add(eventRange);
    }
    return ranges;
  }

}
