package gwtscheduler.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author mping
 */
public abstract class AbstractClientTestCase extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "gwtscheduler.Tests";
  }
}
