package gwtscheduler.client.widgets.common.navigation;

import org.cobogw.gwt.user.client.ui.RoundedPanel;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author malp
 */
public class TabPanelContainer extends RoundedPanel {

  /**
   * Default constructor.
   */
  public TabPanelContainer() {
    super(RoundedPanel.ALL, 4);
    //TODO move to CSS
    setCornerColor("#92C1F0");
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    getWidget().fireEvent(event);
  }
}
