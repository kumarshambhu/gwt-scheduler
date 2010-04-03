package gwtscheduler.client.presenter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.MonthCalendarPresenter;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import net.customware.gwt.presenter.client.DefaultEventBus;

import org.goda.time.ReadableDateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mping
 */
public class MonthPresenterTests  {

  MonthCalendarPresenter presenter;
  AppConfiguration mockConfig;
  MonthDisplay mockDisplay;
  
  ReadableDateTime jan2010;
  DateGenerator mockGenerator;
  
  @Before
  public void gwtSetUp() throws Exception {
    jan2010 = mock(ReadableDateTime.class);
    
    //mock creation
    mockConfig = mock(AppConfiguration.class);
    mockDisplay = mock(MonthDisplay.class);
    mockGenerator = mock(DateGenerator.class);

    //mocking values
    when(mockConfig.daysInWeek()).thenReturn(7);
    when(mockDisplay.getVisibleRows()).thenReturn(6);

    presenter = new MonthCalendarPresenter(mockConfig, mockDisplay, new DefaultEventBus());
    presenter.setFactory(mockGenerator);
    presenter.setDecorator(mock(MultipleElementsIntervalDecorator.class));
  }

  @Test
  public void testRowNumColNumOK() {
    presenter.onNavigateTo(jan2010);
    assertEquals(7, presenter.getColNum());
    assertEquals(6, presenter.getRowNum());
  }
  
}
