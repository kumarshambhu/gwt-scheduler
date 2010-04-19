package gwtscheduler.client.mockClasses;

import gwtscheduler.client.widgets.common.ComplexGrid;

/**
 * Utilit class for lasso tests.
 * @author malp
 */
public class MockLassoSubject implements ComplexGrid {

  final int rows, cols;

  /**
   * Creates a new mock lasso subject, with a 100px x 100px grid.
   * @param rows the number of rows
   * @param cols the number of cols
   */
  public MockLassoSubject(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public int getColNum() {
    return cols;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public int getWidth() {
    return 100;
  }

  @Override
  public int getHeight() {
    return 100;
  }

  @Override
  public int getEffectiveHeight() {
    return 100;
  }

  @Override
  public int getEffectiveWidth() {
    return 100;
  }

}