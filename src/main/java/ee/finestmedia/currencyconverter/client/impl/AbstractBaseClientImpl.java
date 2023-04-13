package ee.finestmedia.currencyconverter.client.impl;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.DataFeedSourceClient;
import ee.finestmedia.currencyconverter.client.parser.Parser;
import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

public abstract class AbstractBaseClientImpl implements DataFeedSourceClient {

  public static final String EUR = "EUR";
  public static final String RESPONSE_DOES_NOT_MATCH = "Parser response does not match expected type!";
  public static final String EUR_IS_MISSING_FROM_THE_FEED = "EUR is missing from the feed!";

  private static final String PARSER_POSTFIX = "Parser";

  public DataFeed getDataFeed(DataFeedSource dataFeedSource, Date date) throws JAXBException, IOException, MappingException, ParseException {
    Parser parser = getParserFactory().getParser(getDataType() + PARSER_POSTFIX);
    Object parserResponse = parser.getDataFeed(dataFeedSource, date, getResponseType());
    DataFeed dataFeed = mapParserResponseToDataFeedModel(parserResponse);
    dataFeed.setDataFeedSourceDisplayName(dataFeedSource.getDisplayName());
    return dataFeed;
  }

  protected abstract DataFeed mapParserResponseToDataFeedModel(Object parserResponse) throws MappingException, ParseException;

  protected abstract Class<?> getResponseType();

  protected abstract ParserFactory getParserFactory();

  protected abstract String getDataType();

}
