package ee.finestmedia.currencyconverter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ee.finestmedia.currencyconverter.util.CollectionsUtil;

public class DataFeed implements Serializable {

  private static final long serialVersionUID = 1L;

  private String dataFeedSourceDisplayName;
  private Set<Entry> entries = new HashSet<>();

  public String getDataFeedSourceDisplayName() {
    return dataFeedSourceDisplayName;
  }

  public void setDataFeedSourceDisplayName(String dataFeedSourceDisplayName) {
    this.dataFeedSourceDisplayName = dataFeedSourceDisplayName;
  }

  public Set<Entry> getEntries() {
    return entries;
  }

  public List<Entry> getEntriesAsSortedList() {
    return CollectionsUtil.asSortedList(entries);
  }

  public static class Entry implements Comparable<Entry>, Serializable {

    private static final long serialVersionUID = 1L;

    private String currencyCode;
    private String displayName;
    private Date date;
    private BigDecimal rate;

    public Entry() {
    }

    public Entry(String currencyCode, Date date, BigDecimal rate) {
      this.setCurrencyCode(currencyCode);
      this.date = date;
      this.rate = rate;
    }

    public String getCurrencyCode() {
      return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
      this.currencyCode = currencyCode;
    }

    public String getDisplayName() {
      return displayName;
    }

    public void setDisplayName(String displayName) {
      this.displayName = displayName;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public BigDecimal getRate() {
      return rate;
    }

    public void setRate(BigDecimal rate) {
      this.rate = rate;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Entry other = (Entry) obj;
      if (currencyCode == null) {
        if (other.currencyCode != null)
          return false;
      } else if (!currencyCode.equals(other.currencyCode))
        return false;
      return true;
    }

    @Override
    public int compareTo(Entry entry) {
      return displayName.compareTo(entry.getDisplayName());
    }

  }
}
