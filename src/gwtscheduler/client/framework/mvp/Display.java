package gwtscheduler.client.framework.mvp;

/**
 * Generic Display class.
 * @author mping
 */
public interface Display {
  /**
   * Indicate to the display that processing is being done.
   * @deprecated
   */
  void startProcessing();

  /**
   * Indicate to the display that processing has completed.
   * @deprecated
   */
  void stopProcessing();
}