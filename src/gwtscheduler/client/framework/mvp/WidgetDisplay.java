package gwtscheduler.client.framework.mvp;

import com.google.gwt.user.client.ui.Widget;

/**
 * Display for widgets.
 * @author mping
 */
public interface WidgetDisplay extends Display {

  /**
   * Returns the display as a GWT {@link Widget}. This may be the same Display
   * instance, or another object completely different
   * @return the widget
   */
  Widget asWidget();
}
