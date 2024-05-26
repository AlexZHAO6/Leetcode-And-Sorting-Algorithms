package oocl;
import java.util.*;
public class CreditCardPay extends Payment {
    private String bankAccount;
    private String passWord;
    private Date validDate;
    public CreditCardPay(){}
    public CreditCardPay(String bankAccount, String passWord, Date validDate, Long amount){
        this.bankAccount = bankAccount;
        this.passWord = passWord;
        this.validDate = validDate;
        this.amount = amount;
        this.paymentName = "CreditCardPay";
    }
    @Override
    public void pay() {
        System.out.println(bankAccount + " " + validDate + " " +  passWord + " pay amount: " + amount + " payName: " + this.paymentName);
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
}
