package ee.finestmedia.currencyconverter.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.CurrencyUtil;
import ee.finestmedia.currencyconverter.util.exception.EURNotFoundException;
import ee.finestmedia.currencyconverter.util.exception.MappingException;
import info.eestipank.producers.types.Report;
import info.eestipank.producers.types.Report.Body.Currencies.Currency;

@Repository("eestipank")
public class EestiPankClientImpl extends AbstractBaseClientImpl {

  private static final Logger LOG = LoggerFactory.getLogger(EestiPankClientImpl.class);

  private static final Class<?> RESPONSE_TYPE = Report.class;
  private static final String DATA_TYPE = "xml";
  private static final String RESPONSE_DATE_FORMAT = "DD.MM.YY";

  @Autowired
  private ParserFactory parserFactory;

  @Override
  protected DataFeed mapParserResponseToDataFeedModel(Object parserResponse) throws MappingException, ParseException {
    if (!RESPONSE_TYPE.isAssignableFrom(parserResponse.getClass())) {
      throw new MappingException(RESPONSE_DOES_NOT_MATCH);
    }

    Report report = (Report) parserResponse;
    DataFeed dataFeed = new DataFeed();

    if (report.getBody().getCurrencies().getCurrency().isEmpty()) {
      return dataFeed;
    }

    Set<DataFeed.Entry> entries = dataFeed.getEntries();

    String fixingsDateAsString = report.getBody().getFixingsDate();
    Date fixingsDate = new SimpleDateFormat(RESPONSE_DATE_FORMAT).parse(fixingsDateAsString);

    try {
      BigDecimal rateOfEURToEEK = getRateOfEURToEEK(report);

      for (Currency currency : report.getBody().getCurrencies().getCurrency()) {
        BigDecimal rateOfCurrencyToEEK = parseRateAsBigDecimal(currency.getRate());
        DataFeed.Entry entry = new DataFeed.Entry(currency.getName(), fixingsDate, CurrencyUtil.divide(rateOfEURToEEK, rateOfCurrencyToEEK));
        entry.setDisplayName(currency.getText());
        entries.add(entry);
      }
    } catch (EURNotFoundException e) {
      LOG.error(e.getMessage(), e);
    }

    return dataFeed;
  }

  private BigDecimal parseRateAsBigDecimal(String rate) {
    return new BigDecimal(CurrencyUtil.removeSpacesReplaceCommas(rate));
  }

  private BigDecimal getRateOfEURToEEK(Report report) throws EURNotFoundException {
    for (Currency currency : report.getBody().getCurrencies().getCurrency()) {
      if (EUR.equals(currency.getName())) {
        return parseRateAsBigDecimal(currency.getRate());
      }
    }
    throw new EURNotFoundException(EUR_IS_MISSING_FROM_THE_FEED);
  }

  @Override
  protected ParserFactory getParserFactory() {
    return parserFactory;
  }

  @Override
  protected Class<?> getResponseType() {
    return RESPONSE_TYPE;
  }

  @Override
  protected String getDataType() {
    return DATA_TYPE;
  }

}
