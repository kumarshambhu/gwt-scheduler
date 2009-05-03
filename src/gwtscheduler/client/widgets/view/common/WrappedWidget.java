package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Utility Widget that serves to add an additional div to the impl Widget.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class WrappedWidget extends Composite {

  /** Wrapper widget */
  private SimplePanel wrapper;

  /**
   * Default constructor. Should call {@link #wrapWidget(Widget)} here to
   * initalize the widget.
   */
  public WrappedWidget() {
    wrapper = new SimplePanel();
    super.initWidget(wrapper);
  }

  @Override
  protected void initWidget(Widget widget) {
    throw new IllegalArgumentException("Please call wrapWidget(Widget) instead");
  }

  /**
   * Wraps the widget.
   * 
   * @param widget the widget to wrap
   */
  protected void wrapWidget(Widget widget) {
    wrapper.add(widget);
    widget.setSize("100%", "100%");
  }
}
