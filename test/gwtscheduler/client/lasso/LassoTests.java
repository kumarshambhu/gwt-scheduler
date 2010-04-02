package gwtscheduler.client.lasso;

import static gwtscheduler.client.TestUtils.assertInstantDate;
import gwtscheduler.client.AbstractClientTestCase;
import gwtscheduler.client.mockClasses.MockDateTimeAwarePresenter;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.PeriodType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for date factory.
 * @author malp
 */
public class LassoTests extends AbstractClientTestCase {

  MockDateTimeAwarePresenter subject;


  @Before
  @Override
  public void gwtSetUp() throws Exception {
    super.gwtSetUp();
    subject = new MockDateTimeAwarePresenter(10, 10);
  }

  @Test
  public void testGetInstantForCell() {
    Instant i = subject.getInstantForCell(new int[] {0, 0});
    assertInstantDate(i, 2009, 01, 01);
  }

  @Test
  public void testGetInstantForCell2() {
    Instant i = subject.getInstantForCell(new int[] {1, 0});
    assertInstantDate(i, 2009, 01, 11);
  }

  @Test
  public void testGetInstantForCell3() {
    Instant i = subject.getInstantForCell(new int[] {1, 3});
    assertInstantDate(i, 2009, 01, 14);
  }

  @Test
  public void testGetInstantForCell4() {
    //1-14
    Interval i = subject.getIntervalForRange(new int[] {0, 0}, new int[] {1, 3});
    int days = i.toDuration().toPeriod(PeriodType.days()).getDays();
    assertEquals(14, days);
  }

}
