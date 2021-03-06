package gwtscheduler.client.widgets.common.event;

import gwtscheduler.client.widgets.common.ComplexGrid;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoUpdateSelectionEvent extends AbstractLassoEvent {

  /**
   * @see AbstractLassoEvent#AbstractLassoEvent(ComplexGrid, int[])
   */
  public LassoUpdateSelectionEvent(ComplexGrid subject, int[] pos) {
    super(subject, pos);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onUpdateSelection(this);
  }

}
