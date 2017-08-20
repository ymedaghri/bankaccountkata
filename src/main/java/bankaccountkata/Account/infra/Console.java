package bankaccountkata.Account.infra;


import bankaccountkata.Account.domain.AccountHistoryPrinter;

/**
 * Created by ymedaghri on 18/08/2017.
 */
public class Console implements AccountHistoryPrinter
{
  public void printLine(String line){
    System.out.println(line);
  }
}
