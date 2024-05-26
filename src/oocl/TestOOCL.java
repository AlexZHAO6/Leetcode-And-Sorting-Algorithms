package oocl;

import java.util.Date;

public class TestOOCL {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        Payment alipay = AlipayFactory.getInstance().create();
        Payment weChatPay = WeChatPayFactory.getInstance().create();
        Payment creditCardPay = CreditCardPayFactory.getInstance().create();

        AlipayFactory.getInstance().setValues((Alipay) alipay,"aaa@email.com", "123456", 100L);
        WeChatPayFactory.getInstance().setValues((WeChatPay) weChatPay,"weChatNumber", "18633333333", 200L);
        CreditCardPayFactory.getInstance().setValues((CreditCardPay) creditCardPay,"99999999", "123456", new Date(1111111L), 300L);

        alipay.pay();
        weChatPay.pay();
        creditCardPay.pay();
    }
}
