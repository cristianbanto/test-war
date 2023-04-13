package ee.finestmedia.currencyconverter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UIResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<BankAndAmount> results;
  private int resultCode;

  public List<BankAndAmount> getResults() {
    if (results != null) {
      Collections.sort(results);      
    } else {
      results = new ArrayList<>();
    }
    return results;
  }

  public void setResults(List<BankAndAmount> results) {
    this.results = results;
  }

  public int getResultCode() {
    return resultCode;
  }

  public void setResultCode(int resultCode) {
    this.resultCode = resultCode;
  }

  public static class BankAndAmount implements Serializable, Comparable<BankAndAmount> {

    private static final long serialVersionUID = 1L;

    private String bank;
    private String amount;

    public BankAndAmount() {
    }

    public BankAndAmount(String bank, String amount) {
      this.bank = bank;
      this.amount = amount;
    }

    public String getBank() {
      return bank;
    }

    public void setBank(String bank) {
      this.bank = bank;
    }

    public String getAmount() {
      return amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    @Override
    public int compareTo(BankAndAmount bankAndAmount) {
      return bank.compareTo(bankAndAmount.getBank());
    }
  }

}
