package ee.finestmedia.currencyconverter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for cache key generation
 * 
 * @author Anton Dubov
 */
public class CustomKeyGeneratorUtil {
  
  public static final String SPEL_EXPRESSION_GENERATE_KEY_FOR_FEED_BY_DATE_CACHE = "T(ee.finestmedia.currencyconverter.util.CustomKeyGeneratorUtil).getKey(#dataFeedSource.getId(), #date)";
  
  /**
   * Generate cache key based on DataFeedSource.id and date in dd-MM-YYYY format
   * 
   * @param displayName
   * @param date
   * @return generated key
   */
  public static String getKey(String id, Date date) {
    return id + "_" + new SimpleDateFormat("dd-MM-YYYY").format(date);
  }

}
