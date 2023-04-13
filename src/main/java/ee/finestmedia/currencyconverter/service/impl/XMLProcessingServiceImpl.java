package ee.finestmedia.currencyconverter.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ee.finestmedia.currencyconverter.service.XMLProcessingService;

@Service
public class XMLProcessingServiceImpl implements XMLProcessingService {
  
  private static final String METHOD_GET = "GET";

  @Override
  public Object unmarshalXMLFromURL(String uri, Class<?> responseType) throws JAXBException, IOException {
    HttpURLConnection connection = getHttpConnection(uri);
    InputStream xml = connection.getInputStream();

    JAXBContext jc = JAXBContext.newInstance(responseType);
    Object unmarshalledObject = jc.createUnmarshaller().unmarshal(xml);

    connection.disconnect();
    return unmarshalledObject;
  }

  @Override
  public Object unmarshalXMLFromFile(String fileName, Class<?> classToUnmarshal) throws JAXBException, IOException {
    JAXBContext jc = JAXBContext.newInstance(classToUnmarshal);
    return jc.createUnmarshaller().unmarshal(new ClassPathResource(fileName).getFile());
  }

  private HttpURLConnection getHttpConnection(String uri) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
    connection.setRequestMethod(METHOD_GET);
    connection.setRequestProperty("Accept", "application/xml");
    return connection;
  }

}
