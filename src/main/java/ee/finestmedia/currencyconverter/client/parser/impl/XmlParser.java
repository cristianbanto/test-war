package ee.finestmedia.currencyconverter.client.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ee.finestmedia.currencyconverter.client.parser.Parser;
import ee.finestmedia.currencyconverter.generated.DataFeedSources.DataFeedSource;
import ee.finestmedia.currencyconverter.service.XMLProcessingService;

/**
 * XmlParser should be used to get responses from web-services which provide data feeds in xml format.
 * 
 * @author Anton Dubov
 */
@Repository("xmlParser")
public class XmlParser implements Parser {

  @Autowired
  XMLProcessingService xmlProcessingService;

  public Object getDataFeed(DataFeedSource dataFeedSource, Date date, Class<?> responseType) throws JAXBException, IOException {
    String url = getUrlWithAppliedDate(dataFeedSource, date);
    return xmlProcessingService.unmarshalXMLFromURL(url, responseType);
  }

  private String getUrlWithAppliedDate(DataFeedSource dataFeedSource, Date date) {
    String baseUrl = dataFeedSource.getUrl();
    DateFormat dateFormat = new SimpleDateFormat(dataFeedSource.getDateFormat());
    return baseUrl + dateFormat.format(date);
  }

}
