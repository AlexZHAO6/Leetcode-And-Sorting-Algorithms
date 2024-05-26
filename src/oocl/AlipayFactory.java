package oocl;

public class AlipayFactory implements IPaymentFacory{
    private static AlipayFactory instance = new AlipayFactory();
    private AlipayFactory(){}

    public static AlipayFactory getInstance(){
        return instance;
    }

    @Override
    public Payment create() {
        return new Alipay();
    }
    public void setValues(Alipay alipay, String email, String passWord, Long amount){
        alipay.setEmail(email);
        alipay.setPassWord(passWord);
        alipay.setAmount(amount);
        alipay.setPaymentName("AliPayMethod");
    }
}
