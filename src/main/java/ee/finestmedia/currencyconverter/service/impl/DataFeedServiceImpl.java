package ee.finestmedia.currencyconverter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.ClientFactory;
import ee.finestmedia.currencyconverter.client.DataFeedSourceClient;
import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.model.DataFeed;
import ee.finestmedia.currencyconverter.service.DataFeedService;
import ee.finestmedia.currencyconverter.util.CustomKeyGeneratorUtil;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

@Service
public class DataFeedServiceImpl implements DataFeedService {

  private static final Logger LOG = LoggerFactory.getLogger(DataFeedServiceImpl.class);

  @Autowired
  private ClientFactory clientFactory;

  @Cacheable(value = "feedByDateCache", key = CustomKeyGeneratorUtil.SPEL_EXPRESSION_GENERATE_KEY_FOR_FEED_BY_DATE_CACHE)
  @Override
  public DataFeed getDataFeedFromDataFeedSourceClient(DataFeedSource dataFeedSource, Date date) throws JAXBException,
                                                                                               IOException,
                                                                                               MappingException,
                                                                                               ParseException {
    LOG.debug("feedByDateCache for key {} was not found. Creating a new one.", CustomKeyGeneratorUtil.getKey(dataFeedSource.getId(), date));
    DataFeedSourceClient dataFeedClient = clientFactory.getClient(dataFeedSource.getId());
    DataFeed dataFeed = dataFeedClient.getDataFeed(dataFeedSource, date);
    return dataFeed;
  }

}
