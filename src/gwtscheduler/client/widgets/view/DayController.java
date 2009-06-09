package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDecorator;
import gwtscheduler.client.widgets.decorator.DateTimeLabelDecorator;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.gwt.user.client.Element;
import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayController extends
    AbstractViewController<AbstractCompositeDaysPanel> {

  /** elements decorator */
  IDecorator<Element> decorator = new DateTimeLabelDecorator();

  @Override
  protected AbstractCompositeDaysPanel createView() {
    return new AbstractCompositeDaysPanel() {
      @Override
      protected AbstractDayPanel createDayView() {
        return new AbstractDayPanel() {

          @Override
          protected int getColumns() {
            return 1;
          }

          @Override
          protected int getRows() {
            return 48; // 24*2
          }

        };
      }
    };
  }

  public String getTabLabel() {
    return "Day";
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }

}
