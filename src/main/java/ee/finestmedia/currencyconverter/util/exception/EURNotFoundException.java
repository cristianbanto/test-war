package ee.finestmedia.currencyconverter.util.exception;

/**
 * Exception indicates that there is no EUR currency in the feed
 * 
 * @author Anton Dubov
 */
public class EURNotFoundException extends CurrencyNotFoundException {
  
  private static final long serialVersionUID = 1L;

  public EURNotFoundException(String message) {
    super(message);
  }

}
