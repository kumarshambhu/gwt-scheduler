package gwtscheduler.client.framework.mvp;

/**
 * Generic presenter class
 * @author mping
 */
public interface Presenter {

  /**
   * Called then the presenter is initialised. This is called before any other
   * methods. Any event handlers and other setup should be done here rather than
   * in the constructor.
   */
  void bind();

  /**
   * Called after the presenter and display have been finished with for the
   * moment.
   */
  void unbind();

  /**
   * Returns the {@link Display} for the current presenter.
   * @return The display.
   */
  Display getDisplay();
}
