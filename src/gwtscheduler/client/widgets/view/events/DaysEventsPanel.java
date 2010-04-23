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

    } else {
      ranges.add(eventRange);
    }
    return ranges;
  }

}
