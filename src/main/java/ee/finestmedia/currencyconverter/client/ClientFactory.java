package ee.finestmedia.currencyconverter.client;


public interface ClientFactory {
  
  public DataFeedSourceClient getClient(String id);

}
