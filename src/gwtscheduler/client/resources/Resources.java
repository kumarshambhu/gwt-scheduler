package gwtscheduler.client.resources;

import gwtscheduler.client.resources.css.AppResources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.resources.css.MonthCssResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;

/**
 * "Shortcut" class for general resources. Will lazy {@link GWT#create(Class)}
 * the resources as needed on first call.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public final class Resources {

  /** Holds the common css resource */
  private static AppResources AppResources;

  /**
   * Injects all stylesheets.
   */
  public static void injectAllStylesheets() {
    StyleInjector.injectAtStart(Resources.dayWeekCss().getText());
    StyleInjector.injectAtStart(Resources.monthCss().getText());
  }

  /**
   * Maybe initializes the css resources.
   */
  private static synchronized void maybeInitialize() {
    if (AppResources == null) {
      AppResources = GWT.create(AppResources.class);
    }
  }

  /**
   * Gets the day week css resource.
   * @return the day week css resource
   */
  public static final DayWeekCssResource dayWeekCss() {
    maybeInitialize();
    return AppResources.dayWeekCss();
  }

  /**
   * Gets the month css resource.
   * @return the month css resource
   */
  public static final MonthCssResource monthCss() {
    maybeInitialize();
    return AppResources.monthCss();
  }

}
