package oocl;

import java.util.Date;

public class CreditCardPayFactory implements IPaymentFacory{
    private static CreditCardPayFactory instance = new CreditCardPayFactory();
    private CreditCardPayFactory(){}

    public static CreditCardPayFactory getInstance(){
        return instance;
    }
    @Override
    public Payment create() {
        return new CreditCardPay();
    }
    public void setValues(CreditCardPay creditCardPay, String bankAccount, String passWord, Date validDate, Long amount){
        creditCardPay.setBankAccount(bankAccount);
        creditCardPay.setPassWord(passWord);
        creditCardPay.setAmount(amount);
        creditCardPay.setValidDate(validDate);
        creditCardPay.setPaymentName("CreditCardPay");
    }
}
