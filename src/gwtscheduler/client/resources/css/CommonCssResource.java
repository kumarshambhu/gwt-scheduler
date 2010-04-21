package gwtscheduler.client.resources.css;

import com.google.gwt.resources.client.CssResource;

/**
 * Common Css Resources. This will hold structural css values.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
interface CommonCssResource extends CssResource {

  /**
   * Generic container.
   * @return the css class for a generic container
   */
  String genericContainer();

  /**
   * Gets the lasso panel class name.
   * @return the css class for the lasso panel
   */
  String lassoPanel();

  /**
   * Gets the lasso element class name.
   * @return the css class for a lasso element
   */
  String lassoElement();

  /**
   * Gets the generic container padding.
   * @return the generic container padding in pixels
   */
  int genericContainerPaddingPx();
}