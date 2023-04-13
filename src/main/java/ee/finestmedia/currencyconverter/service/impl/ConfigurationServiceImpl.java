package ee.finestmedia.currencyconverter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import ee.finestmedia.currencyconverter.generated.DataFeedSources;
import ee.finestmedia.currencyconverter.service.ConfigurationService;
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
  
  private static final String XML_CONFIGURATION_PATH = "dataFeedSources.xml";
  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

  @Autowired
  private XMLProcessingService xmlProcessingService;
  
  private DataFeedSources dataFeedSources = new DataFeedSources();

  public synchronized DataFeedSources getDataFeedSources() {
    if (isThereAnyDataFeedSources()) {
      return dataFeedSources;
    }
    try {
      dataFeedSources = (DataFeedSources) xmlProcessingService.unmarshalXMLFromFile(XML_CONFIGURATION_PATH, DataFeedSources.class);
    } catch (JAXBException | IOException e) {
      LOG.error("Could not load currency data source configuration");
      LOG.error(e.getMessage(), e);
    }
    return dataFeedSources;
  }
  
  private boolean isThereAnyDataFeedSources() {
    return !(dataFeedSources.getDataFeedSource() == null || dataFeedSources.getDataFeedSource().isEmpty());
  }

}
