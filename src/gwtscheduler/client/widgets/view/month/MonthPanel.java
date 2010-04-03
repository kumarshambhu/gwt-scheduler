package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.MonthCssResource;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.DefaultResizeHandler;
import gwtscheduler.client.widgets.common.event.HasWidgetResizeHandlers;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * View class for months. Handles its own resizes.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class MonthPanel extends Composite implements WidgetResizeHandler, HasWidgetResizeHandlers {

  /** CSS resources */
  private static final MonthCssResource MonthCss = Resources.monthCss();

  /** Main container */
  @UiField
  FlowPanel container;
  /** root container */
  @UiField
  SimplePanel simplePanel;

  /** resize handler */
  private DefaultResizeHandler handler;
  /** collection of month rows */
  private List<MonthRow> monthRows;
  /** list of hidden rows */
  private List<MonthRow> hiddenRows;
  /** application config retrieved value */
  private final int WeekSize;

  /** ui binder instance */
  private static MonthPanelUiBinder uiBinder = GWT.create(MonthPanelUiBinder.class);

  /** ui binder interface */
  interface MonthPanelUiBinder extends UiBinder<Widget, MonthPanel> {
  }

  /**
   * Default constructor.
   * @param ctrl the controller
   */
  public MonthPanel() {
    AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();
    WeekSize = config.daysInWeek();
    initWidget(uiBinder.createAndBindUi(this));

    handler = new DefaultResizeHandler(this);

    monthRows = new ArrayList<MonthRow>();
    hiddenRows = new ArrayList<MonthRow>();

    //6 is an estimate for the necessary rows
    for (int i = 0; i < 6; i++) {
      MonthRow row = new MonthRow(WeekSize);
      monthRows.add(row);
      container.add(row);
    }
  }

  /**
   * Gets the resize handler for this widget.
   * @return the resize handler
   */
  WidgetResizeHandler getWidgetResizeHandler() {
    return this;
  }

  @Override
  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    setRowHeights();
  }

  /**
   * Sets the row height for the month rows.
   */
  protected void setRowHeights() {
    float height = ((float) 100 / monthRows.size());
    for (int i = 0; i < monthRows.size(); i++) {
      Element rowElement = monthRows.get(i).getElement();
      //this is incompatible with 'position:relative'
      // DOM.setStyleAttribute(rowElement, "top", (((float) 100 / monthRows.size()) * i) + "%"); 
      DOM.setStyleAttribute(rowElement, "height", height + "%");
    }
  }

  public void onResize(WidgetResizeEvent event) {
    // we delegate to default handler
    handler.onResize(event);
    for (MonthRow row : monthRows) {
      row.onResize(event);
    }
    fireEvent(event);
  }

  /**
   * Unhides a number of rows.
   * @param amount the number of rows to unhide
   */
  void unhideRows(int amount) {
    final int limit = Math.max(0, hiddenRows.size() - amount);
    for (int i = hiddenRows.size() - 1; i >= limit; i--) {
      MonthRow mr = hiddenRows.remove(i);
      mr.setVisible(true);
      monthRows.add(mr);
    }
    setRowHeights();
  }

  /**
   * Hides a given amount of rows
   * @param rowNum the number of rows to hide
   */
  void hideRows(int rowNum) {
    assert rowNum > 0 : "rowNum cannot be negative";
    for (int i = 0; i < rowNum; i++) {
      MonthRow mr = monthRows.remove(monthRows.size() - 1);
      mr.setVisible(false);
      hiddenRows.add(mr);
    }
    resizeAllRows();
  }

  /**
   * Shows only the given amount of rows, hiding the rest.
   * @param rowNum the number of rows to show
   */
  void showRows(int rowNum) {
    while (monthRows.size() < rowNum) {
      MonthRow row = new MonthRow(WeekSize);
      monthRows.add(row);
      container.add(row);
    }
    int diff = monthRows.size() - rowNum;
    if (diff > 0) {
      hideRows(diff);
    } else {
      resizeAllRows();
    }
  }

  /**
   * Resizes all visible rows.
   */
  protected void resizeAllRows() {
    setRowHeights();
    for (MonthRow row : monthRows) {
      row.resizeRows();
    }
  }

  /**
   * Gets the number of visible rows.
   * @return the number of visible rows
   */
  int getVisibleRowsSize() {
    return monthRows.size();
  }

  /**
   * Gets the main cell elements.
   * @return the main cell elements
   */
  List<Cell<Element>> getMainElements() {
    List<Cell<Element>> mergedList = new ArrayList<Cell<Element>>();
    for (MonthRow mr : monthRows) {
      List<Cell<Element>> rl = mr.getTitleElements();
      mergedList.addAll(rl);
    }
    return mergedList;
  }

  /**
   * Gets the height.
   * @return
   */
  int getHeight() {
    return container.getElement().getOffsetHeight() + MonthCss.monthCellPadTopPx();
  }

  /**
   * Gets the width.
   * @return
   */
  int getWidth() {
    return container.getElement().getOffsetWidth();
  }

  /**
   * Gets the number of columns.
   * @return
   */
  int getColumns() {
    return WeekSize;
  }

  /**
   * Gets the number of visible rows.
   * @return
   */
  int getRows() {
    return getVisibleRowsSize();
  }

  /**
   * Gets the specified month row
   * @param row the row index
   * @return the month row
   */
  public MonthRow getMonthRow(int row) {
    return monthRows.get(row);
  }

}
