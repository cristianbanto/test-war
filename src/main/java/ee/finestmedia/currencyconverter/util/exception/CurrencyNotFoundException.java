package ee.finestmedia.currencyconverter.util.exception;

/**
 * Exception indicates that there is no selected currency in the feed.
 * 
 * @author Anton Dubov
 */
public class CurrencyNotFoundException extends CurrencyConverterException {
  
  private static final long serialVersionUID = 1L;

  public CurrencyNotFoundException(String message) {
    super(message);
  }

}
