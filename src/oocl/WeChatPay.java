package oocl;

public class WeChatPay extends Payment{
    private String wechatNumber;
    private String phoneNumber;

    public WeChatPay(){}
    public WeChatPay(String wechatNumber, String phoneNumber, Long amount){
        this.wechatNumber = wechatNumber;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.paymentName = "WeChatPay";
    }
    @Override
    public void pay() {
        System.out.println(wechatNumber + " " + phoneNumber + " pay amount: " + amount + " payName: " + this.paymentName);
    }


    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
