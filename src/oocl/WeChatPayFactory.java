package oocl;

public class WeChatPayFactory implements IPaymentFacory{
    private static WeChatPayFactory instance = new WeChatPayFactory();
    private WeChatPayFactory(){}

    public static WeChatPayFactory getInstance(){
        return instance;
    }
    @Override
    public Payment create() {
        return new WeChatPay();
    }
    public void setValues(WeChatPay weChatPay, String wechatNumber, String phoneNumber, Long amount){
        weChatPay.setWechatNumber(wechatNumber);
        weChatPay.setPhoneNumber(phoneNumber);
        weChatPay.setAmount(amount);
        weChatPay.setPaymentName("WeChatPayMethod");
    }
}
