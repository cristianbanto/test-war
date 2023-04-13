package ee.finestmedia.currencyconverter.client.parser;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Date;

import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;

/**
 * Parsers should return data feeds from given sources in given format
 * 
 * @author Anton Dubov
 */
public interface Parser {

  Object getDataFeed(DataFeedSource dataFeedSource, Date date, Class<?> responseType) throws JAXBException, IOException;

}
