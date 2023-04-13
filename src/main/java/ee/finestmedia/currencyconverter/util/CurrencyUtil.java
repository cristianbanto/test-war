package ee.finestmedia.currencyconverter.util;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Utility class for operations on currencies
 * 
 * @author Anton Dubov
 */
public class CurrencyUtil {
  
  public static final int PRECISION_SCALE = 5;
  
  /**
   * Divides double by integer
   * 
   * @param divident
   * @param divisor
   * @return result
   */
  public static BigDecimal divide(double divident, int divisor) {
    BigDecimal dividentAsBigDecimal = BigDecimal.valueOf(divident);
    BigDecimal divisorAsBigDecimal = new BigDecimal(divisor);
    return dividentAsBigDecimal.divide(divisorAsBigDecimal, PRECISION_SCALE, ROUND_HALF_UP);
  }
  
  /**
   * Divides one BigDecimal by another
   * 
   * @param divident
   * @param divisor
   * @return result
   */
  public static BigDecimal divide(BigDecimal divident, BigDecimal divisor) {
    return divident.divide(divisor, PRECISION_SCALE, ROUND_HALF_UP);
  }
  
  public static String removeSpacesReplaceCommas(String string) {
    return string.replace(",", ".").replace(" ", "");
  }
  
  /**
   * 
   * 
   * @param value
   * @return
   */
  public static BigDecimal round(BigDecimal value) {
    return value.setScale(PRECISION_SCALE, ROUND_HALF_UP);
  }

}
