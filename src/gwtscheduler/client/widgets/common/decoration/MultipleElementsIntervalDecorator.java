package gwtscheduler.client.widgets.common.decoration;

import org.goda.time.ReadableInterval;

import com.google.gwt.user.client.Element;

/**
 * Defines a generic decorator.
 * @author malp
 * @param <T> the decorable
 */
//FIXME: Gin doesn't like generic interfaces...
public interface MultipleElementsIntervalDecorator/* <T extends Element> */{

  /**
   * Fired when the decorator should decorate elements.
   * @param interval the time interval
   * @param decorable the decorable
   */
  void decorate(ReadableInterval interval, HasMultipleDecorables<Element> decorable);
}
