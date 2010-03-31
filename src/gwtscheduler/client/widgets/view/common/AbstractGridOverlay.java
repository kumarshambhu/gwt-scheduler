package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Abstract class for overlaying stuff
 * @author malp
 */
public class AbstractGridOverlay extends AbsolutePanel implements WidgetResizeHandler {

  /** the lasso subject grid */
  protected ComplexGrid grid;
  /** lasso container */
  protected AbsolutePanel overlayPanel;

  /**
   * Default constructor.
   * @param strat
   */
  protected AbstractGridOverlay() {
    this(new AbsolutePanel());
  }

  /**
   * Creates a new grid overlay
   * @param container the main container
   */
  protected AbstractGridOverlay(AbsolutePanel container) {
    // lasso/ events set up
    overlayPanel = container;
    add(overlayPanel, 0, 0);
  }

  /**
   * Sets the subject grid.
   * @param subject the subject
   */
  public void setComplexGrid(ComplexGrid subject) {
    assert subject != null : "Complex Grid cannot be null.";
    this.grid = subject;
  }

  /**
   * Calculates the cell position for a given mouse event
   * @param event the mouse event
   * @return the cell position
   */
  protected int[] calculateCellPosition(MouseEvent<?> event) {
    int x = event.getRelativeX(getElement());
    int y = event.getRelativeY(getElement());

    int rowPos = (y / (grid.getHeight() / grid.getRowNum()));
    int colPos = (x / (grid.getWidth() / grid.getColNum()));
    return new int[] {rowPos, colPos};
  }

  /**
   * Calculates the top and left coordinates for a given cell position.
   * @param cellPos the cell position
   * @return
   */
  protected int[] calculateLeftTop(int[] cellPos) {
    assert cellPos != null : "Cell position cannot be null";
    assert cellPos.length == 2 : "Position length != 2";

    int rowH = Math.round((float) grid.getHeight() / grid.getRowNum());
    int colW = Math.round((float) grid.getWidth() / grid.getColNum());
    return new int[] {cellPos[1] * colW, cellPos[0] * rowH};
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    overlayPanel.clear();
  }

}