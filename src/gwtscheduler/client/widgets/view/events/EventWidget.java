package gwtscheduler.client.widgets.view.events;

import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Miguel
 */
public class EventWidget extends Composite {

  private static EventWidgetUiBinder uiBinder = GWT.create(EventWidgetUiBinder.class);

  interface EventWidgetUiBinder extends UiBinder<Widget, EventWidget> {
  }

  @UiField(provided = true)
  RoundedLinePanel roundedPanel;

  @UiField
  SimplePanel container;

  @UiField
  HTML impl;

  static final int CornerHeight = 1;

  /**
   * Default constructor.
   */
  public EventWidget() {
    roundedPanel = new RoundedLinePanel(RoundedPanel.ALL, CornerHeight);
    roundedPanel.setCornerColor("#666", "#92C1F0", "red");//.setCornerColor("#92C1F0");
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setPixelSize(int width, int height) {
    //we resize the impl instead
    impl.setPixelSize(width - (2 * CornerHeight), height - (2 * CornerHeight));
  }

}
