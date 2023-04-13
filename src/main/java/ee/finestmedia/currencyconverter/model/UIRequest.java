package ee.finestmedia.currencyconverter.model;

import java.io.Serializable;

public class UIRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String originCode;
  private String destinationCode;
  private String date;
  private String amount;

  public UIRequest() {
  }

  public UIRequest(String originCode, String destinationCode, String date, String dateFormat, String amount) {
    this.originCode = originCode;
    this.destinationCode = destinationCode;
    this.date = date;
    this.amount = amount;
  }

  public String getOriginCode() {
    return originCode;
  }

  public void setOriginCode(String originCode) {
    this.originCode = originCode;
  }

  public String getDestinationCode() {
    return destinationCode;
  }

  public void setDestinationCode(String destinationCode) {
    this.destinationCode = destinationCode;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

}
