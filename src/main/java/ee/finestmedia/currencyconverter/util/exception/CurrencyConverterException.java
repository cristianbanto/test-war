package ee.finestmedia.currencyconverter.util.exception;

/**
 * Basic exception for currency converter project.
 * 
 * @author Anton Dubov
 */
public class CurrencyConverterException extends Exception {

  private static final long serialVersionUID = 1L;
  
  public CurrencyConverterException(String message) {
    super(message);
  }

}
