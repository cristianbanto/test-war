package ee.finestmedia.currencyconverter.util.exception;

/**
 * Exception indicates that there is a problem with data mapping
 * 
 * @author Anton Dubov
 */
public class MappingException extends CurrencyConverterException {
  
  private static final long serialVersionUID = 1L;

  public MappingException(String message) {
    super(message);
  }

}
