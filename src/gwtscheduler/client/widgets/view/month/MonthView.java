package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.LassoStrategy;
import gwtscheduler.client.widgets.common.decoration.HasMultipleDecorables;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.HasWidgetRedrawHandlers;
import gwtscheduler.client.widgets.common.event.WidgetRedrawEvent;
import gwtscheduler.client.widgets.common.event.WidgetRedrawHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.view.common.LassoAwarePanel;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;
import gwtscheduler.client.widgets.view.events.EventSpan;
import gwtscheduler.client.widgets.view.events.EventsPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines the composite month view.
 */
public class MonthView extends Composite implements LassoAwarePanel.LassoHandler, MonthDisplay, HasMultipleDecorables<Element>,
    HasWidgetRedrawHandlers {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  @UiField
  VerticalPanel impl;
  @UiField
  FlexTable header;
  @UiField
  LassoAwarePanel lassoAwarePanel;
  @UiField
  MonthPanel monthPanel;
  @UiField
  EventsPanel eventsPanel;

  /** top view cells */
  protected List<Cell<Element>> topLabels;

  private final int WeekSize;

  /** ui binder instance */
  private static MonthViewUiBinder uiBinder = GWT.create(MonthViewUiBinder.class);

  /** ui binder interface */
  interface MonthViewUiBinder extends UiBinder<Widget, MonthView> {
  }

  /**
   * Default constructor.
   */
  public MonthView() {
    AppConfiguration config = AppInjector.GIN.getInjector().getConfiguration();
    WeekSize = config.daysInWeek();
    initWidget(uiBinder.createAndBindUi(this));
    eventsPanel.setComplexGrid(this);
    lassoAwarePanel.setOverflowY(false);
    lassoAwarePanel.setLassoHandler(this);
    lassoAwarePanel.addWidgetResizeHandler(monthPanel.getWidgetResizeHandler());
  }

  /**
   * Creates the top view.
   * @return the top view widget
   */
  @UiFactory
  public FlexTable buildTopHeader() {
    FlexTable g = new FlexTable();
    g.addStyleName(CSS.genericContainer());
    g.setWidth("100%");

    topLabels = new ArrayList<Cell<Element>>(WeekSize);
    for (int i = 0; i < 7; i++) {
      Cell<Element> cell = new BaseCell(0, i);
      cell.getCellElement().setInnerHTML(0 + ", " + i);
      topLabels.add(cell);

      g.setWidget(0, i, DOMUtils.wrapElement(cell.getCellElement()));
      g.getCellFormatter().setWidth(0, 0, ((float) 100 / 7) + "%");
      g.getFlexCellFormatter().setHorizontalAlignment(0, i, HasHorizontalAlignment.ALIGN_CENTER);
    }
    return g;
  }

  @Override
  public void forceLayout() {
    lassoAwarePanel.doDeferRedrawResize(new WidgetResizeEvent(), new WidgetRedrawEvent());
  }

  @Override
  public int getColNum() {
    return monthPanel.getColumns();
  }

  @Override
  public int getRowNum() {
    return monthPanel.getRows();
  }

  @Override
  public void forceLayout(Widget lassoPanel, WidgetResizeEvent event) {
    lassoPanel.setPixelSize(event.width, event.height);
    eventsPanel.setPixelSize(event.width, event.height);
    eventsPanel.forceLayout();
  }

  public List<Cell<Element>> getColumnsDecorableElements() {
    return Collections.unmodifiableList(topLabels);
  }

  public List<Cell<Element>> getContentDecorableElements() {
    return Collections.unmodifiableList(monthPanel.getMainElements());
  }

  public List<Cell<Element>> getRowsDecorableElements() {
    return null;
  }

  @Override
  public HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler) {
    return lassoAwarePanel.addWidgetRedrawHandler(handler);
  }

  @Override
  public void initLasso(LassoStrategy strat, ComplexGrid subject) {
    lassoAwarePanel.initLasso(strat, subject);
  }

  @Override
  public void addAppointment(AppointmentEvent evt, EventSpan eventSpan) {
    eventsPanel.addAppointment(evt, eventSpan);
  }

  /**
   * Shows a given number of rows, hiding the others.
   * @param rowNum the number of rows
   */
  public void showRows(int rowNum) {
    monthPanel.showRows(rowNum);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void startProcessing() {
  }

  @Override
  public void stopProcessing() {
  }

  @Override
  public HasMultipleDecorables<Element> getDecorables() {
    return this;
  }

  @Override
  public int getHeight() {
    return monthPanel.getHeight();
  }

  @Override
  public int getWidth() {
    return monthPanel.getWidth();
  }

  @Override
  public List<Cell<Element>> getVisibleElements() {
    return monthPanel.getMainElements();
  }

  @Override
  public int getVisibleRows() {
    return monthPanel.getVisibleRowsSize();
  }

}
