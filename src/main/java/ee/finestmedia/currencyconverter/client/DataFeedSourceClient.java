package ee.finestmedia.currencyconverter.client;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

public interface DataFeedSourceClient {

  /**
   * Gets data feed from the specific client
   * 
   * @param dataFeedSource
   * @param date
   * @return data feeds
   * @throws JAXBException
   * @throws IOException
   * @throws MappingException
   * @throws ParseException
   */
  DataFeed getDataFeed(DataFeedSource dataFeedSource, Date date) throws JAXBException, IOException, MappingException, ParseException;

}
