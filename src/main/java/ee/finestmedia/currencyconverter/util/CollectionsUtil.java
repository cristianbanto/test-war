package ee.finestmedia.currencyconverter.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for operations on collections
 * 
 * @author Anton Dubov
 */
public class CollectionsUtil {
  
  /**
   * Returns collection as sorted ArrayList
   * 
   * @param collection
   * @return sorted list
   */
  public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> collection) {
    List<T> list = new ArrayList<T>(collection);
    Collections.sort(list);
    return list;
  }

}
