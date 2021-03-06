package gwtscheduler.client.widgets.common;

/**
 * Defines a lasso component. The lasso component is responsible for drawing the
 * lasso selection on the screen.
 * @author malp
 */
public interface ComplexGrid {

  /**
   * Gets the number of rows within the lasso subject.
   * @return the number of rows
   */
  int getRowNum();

  /**
   * Gets the number of columns
   * @return the number of columns
   */
  int getColNum();

  /**
   * Gets the subject's width.
   * @return the width in px
   */
  int getWidth();

  /**
   * Gets the subject's height.
   * @return the height in px
   */
  int getHeight();

  /**
   * Gets the effective width.
   * @return the width in px
   */
  public int getEffectiveWidth();

  /**
   * Gets the effective height.
   * @return the height in px
   */
  public int getEffectiveHeight();

}
