package gwtscheduler.client.widgets.view.events;

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
  RoundedPanel roundedPanel;

  @UiField
  SimplePanel container;

  @UiField
  HTML impl;

  static final int CornerHeight = 4;

  /**
   * Default constructor.
   */
  public EventWidget() {
    roundedPanel = new RoundedPanel(RoundedPanel.ALL, 2);
    roundedPanel.setCornerColor("#92C1F0");
//    roundedPanel.setBorderColor("silver");
    initWidget(uiBinder.createAndBindUi(this));

  }

  @Override
  public void setPixelSize(int width, int height) {
    //we resize the impl instead
    impl.setPixelSize(width, height - ( CornerHeight));
  }

}
