package ee.finestmedia.currencyconverter.service;

import javax.xml.bind.JAXBException;

import java.io.IOException;

public interface XMLProcessingService {

  Object unmarshalXMLFromURL(String uri, Class<?> classToUnmarshal) throws JAXBException, IOException;

  Object unmarshalXMLFromFile(String fileName, Class<?> classToUnmarshal) throws JAXBException, IOException;

}
