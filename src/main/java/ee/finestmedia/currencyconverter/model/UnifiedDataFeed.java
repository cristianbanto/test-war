package ee.finestmedia.currencyconverter.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.CollectionsUtil;

/**
 * This model is used to hold: 1) unifiedDataFeedEntries: Set of all available for conversion currencies, 2) dataFeeds: List of received data feeds
 * 
 * @author Anton Dubov
 */
public class UnifiedDataFeed {

  private List<DataFeed> dataFeeds = new ArrayList<>();

  private Set<DataFeed.Entry> unifiedDataFeedEntries = new HashSet<>();

  public void addDataFeed(DataFeed dataFeed) {
    this.unifiedDataFeedEntries.addAll(dataFeed.getEntries());
    this.dataFeeds.add(dataFeed);
  }

  public List<DataFeed> getDataFeeds() {
    return dataFeeds;
  }

  /**
   * Gets entries set as is, without any modifications. Use for better performance.
   * 
   * @return entries as set
   */
  public Set<DataFeed.Entry> getUnifiedDataFeedEntriesAsSet() {
    return unifiedDataFeedEntries;
  }

  /**
   * Gets entries set as sorted list
   * 
   * @return
   */
  public List<DataFeed.Entry> getUnifiedDataFeedEntries() {
    return CollectionsUtil.asSortedList(unifiedDataFeedEntries);
  }

}
