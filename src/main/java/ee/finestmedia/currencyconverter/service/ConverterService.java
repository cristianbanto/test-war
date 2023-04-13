/**
 * 
 */
package ee.finestmedia.currencyconverter.service;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.text.ParseException;

import ee.finestmedia.currencyconverter.model.UIRequest;
import ee.finestmedia.currencyconverter.model.UIResponse;
import ee.finestmedia.currencyconverter.model.UnifiedDataFeed;
import ee.finestmedia.currencyconverter.util.exception.MappingException;

/**
 * Service which handles currency conversion
 * 
 * @author Anton Dubov
 */
public interface ConverterService {

  /**
   * Retrieves list of all currencies available for conversion. Fetches data feeds from the previous day.
   * 
   * @return unified data feed
   */
  UnifiedDataFeed getUnifiedDataFeedForThePreviousDay();

  UIResponse convertCurrency(UIRequest request) throws JAXBException, IOException, MappingException, ParseException;

}
