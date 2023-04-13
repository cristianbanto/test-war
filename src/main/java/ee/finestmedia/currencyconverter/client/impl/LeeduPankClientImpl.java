package ee.finestmedia.currencyconverter.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

import ee.finestmedia.currencyconverter.client.parser.ParserFactory;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.util.CurrencyUtil;
import ee.finestmedia.currencyconverter.util.exception.EURNotFoundException;
import ee.finestmedia.currencyconverter.util.exception.MappingException;
import generated.ExchangeRates;

@Repository("leedupank")
public class LeeduPankClientImpl extends AbstractBaseClientImpl {

  private static final Logger LOG = LoggerFactory.getLogger(LeeduPankClientImpl.class);

  private static final Class<ExchangeRates> RESPONSE_TYPE = ExchangeRates.class;
  private static final String DATA_TYPE = "xml";
  private static final String RESPONSE_DATE_FORMAT = "YYYY.MM.DD";

  @Autowired
  private ParserFactory parserFactory;

  @Override
  protected DataFeed mapParserResponseToDataFeedModel(Object parserResponse) throws MappingException, ParseException {
    if (!RESPONSE_TYPE.isAssignableFrom(parserResponse.getClass())) {
      throw new MappingException(RESPONSE_DOES_NOT_MATCH);
    }

    ExchangeRates exchangeRates = (ExchangeRates) parserResponse;
    DataFeed dataFeed = new DataFeed();
    Set<DataFeed.Entry> entries = dataFeed.getEntries();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RESPONSE_DATE_FORMAT);

    try {
      BigDecimal rateOfEURToLTL = getRateOfEURToLTL(exchangeRates);

      for (ExchangeRates.Item item : exchangeRates.getItem()) {
        Date date = simpleDateFormat.parse(item.getDate());
        BigDecimal rateOfEURToCurrency = getRateOfEURToCurrency(item, rateOfEURToLTL);
        DataFeed.Entry entry = new DataFeed.Entry(item.getCurrency(), date, rateOfEURToCurrency);
        entry.setDisplayName(Currency.getInstance(item.getCurrency()).getDisplayName());
        entries.add(entry);
      }
    } catch (EURNotFoundException e) {
      LOG.error(e.getMessage(), e);
    }
    return dataFeed;
  }

  private BigDecimal getRateOfEURToLTL(ExchangeRates exchangeRates) throws EURNotFoundException {
    for (ExchangeRates.Item item : exchangeRates.getItem()) {
      if (EUR.equals(item.getCurrency())) {
        return CurrencyUtil.divide(item.getRate(), item.getQuantity());
      }
    }
    throw new EURNotFoundException(EUR_IS_MISSING_FROM_THE_FEED);
  }

  private BigDecimal getRateOfEURToCurrency(ExchangeRates.Item item, BigDecimal rateOfEURToLTL) {
    BigDecimal rateOfCurrencyToLTL = CurrencyUtil.divide(item.getRate(), item.getQuantity());
    BigDecimal rateOfEURToCurrency = CurrencyUtil.divide(rateOfEURToLTL, rateOfCurrencyToLTL);
    return rateOfEURToCurrency;
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
