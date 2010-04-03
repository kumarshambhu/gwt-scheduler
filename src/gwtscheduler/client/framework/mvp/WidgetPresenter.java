package gwtscheduler.client.framework.mvp;

import gwtscheduler.client.framework.EventBus;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Base class for widget presenters.
 * @author mping
 * @param <D> the display type
 */
public abstract class WidgetPresenter<D extends Display> implements Presenter {

  /**
   * The display for the presenter.
   */
  protected final D display;

  /** The {@link EventBus} for the application. */
  protected final EventBus eventBus;

  /** the list of handler registrars */
  private List<HandlerRegistration> handlerRegistrations = new java.util.ArrayList<HandlerRegistration>();

  /**
   * Creates a new presenter
   * @param display the display
   * @param eventBus the event bus
   */
  public WidgetPresenter(D display, EventBus eventBus) {
    this.display = display;
    this.eventBus = eventBus;
  }

  public void bind() {
    onBind();
  }

  /**
   * Any {@link HandlerRegistration}s added will be removed when
   * {@link #unbind()} is called. This provides a handy way to track event
   * handler registrations when binding and unbinding.
   * @param handlerRegistration The registration.
   */
  protected void registerHandler(HandlerRegistration handlerRegistration) {
    handlerRegistrations.add(handlerRegistration);
  }

  public void unbind() {
    for (HandlerRegistration reg : handlerRegistrations) {
      reg.removeHandler();
    }
    handlerRegistrations.clear();
    onUnbind();
  }

  /**
   * This method is called when binding the presenter. Any additional bindings
   * should be done here.
   */
  protected abstract void onBind();

  /**
   * This method is called when unbinding the presenter. Any handler
   * registrations recorded with {@link #registerHandler(HandlerRegistration)}
   * will have already been removed at this point.
   */
  protected abstract void onUnbind();

  /**
   * Returns the display for the presenter.
   * @return The display.
   */
  public D getDisplay() {
    return display;
  }
}
