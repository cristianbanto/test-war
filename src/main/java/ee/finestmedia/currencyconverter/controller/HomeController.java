package ee.finestmedia.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

import ee.finestmedia.currencyconverter.model.UnifiedDataFeed;
import ee.finestmedia.currencyconverter.service.ConverterService;

/**
 * Handles requests for the application home page.
 * 
 * @author Anton Dubov
 */
@Controller
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  private static final String HOME = "home";
  private static final String MESSAGE = "message";
  private static final String MESSAGE_SUCCESS = "success";
  private static final String MESSAGE_ERROR = "error.internal";
  private static final String MESSAGE_NO_COURSES_FOUND = "error.nocoursesfound";
  private static final String FEEDS = "feeds";
  private static final String DEFAULT_ORIGIN = "defaultOrigin";
  private static final String DEFAULT_DESTINATION = "defaultDestination";
  private static final String DEFAULT_ORIGIN_CODE = "EUR";
  private static final String DEFAULT_DESTINATION_CODE = "USD";

  @Autowired
  private ConverterService converterService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    UnifiedDataFeed unifiedFeed = new UnifiedDataFeed();
    try {
      unifiedFeed = converterService.getUnifiedDataFeedForThePreviousDay();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      model.addAttribute(MESSAGE, MESSAGE_ERROR);
    }

    if (unifiedFeed.getUnifiedDataFeedEntriesAsSet().isEmpty()) {
      LOG.error("Feeds are empty");
      model.addAttribute(MESSAGE, MESSAGE_NO_COURSES_FOUND);
    }

    model.addAttribute(DEFAULT_ORIGIN, DEFAULT_ORIGIN_CODE);
    model.addAttribute(DEFAULT_DESTINATION, DEFAULT_DESTINATION_CODE);

    model.addAttribute(FEEDS, unifiedFeed.getUnifiedDataFeedEntries());
    model.addAttribute(MESSAGE, MESSAGE_SUCCESS);

    return HOME;
  }

}
