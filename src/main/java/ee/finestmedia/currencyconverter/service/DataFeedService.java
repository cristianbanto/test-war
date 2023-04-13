package ee.finestmedia.currencyconverter.service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

/**
 * Service for communication with DataFeedSourceClients and caching responses
 * 
 * @author Anton Dubov
 */
public interface DataFeedService {

  /**
   * Gets data feed for given date from DataFeedSourceClient implementation defined by dataFeedSource.id. Response is cached.
   * 
   * @param dataFeedSource
   * @param date
   * @return data feed
   * @throws JAXBException
   * @throws IOException
   * @throws MappingException
   * @throws ParseException
   */
  DataFeed getDataFeedFromDataFeedSourceClient(DataFeedSource dataFeedSource, Date date) throws JAXBException, IOException, MappingException, ParseException;

}
