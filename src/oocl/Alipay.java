package oocl;

public class Alipay extends Payment{
    private String email;
    private String passWord;

    public Alipay(){
    }

    public Alipay(String email, String passWord, Long amount){
        this.email = email;
        this.passWord = passWord;
        this.amount = amount;
        this.paymentName = "AliPay";
    }
    @Override
    public void pay() {
        System.out.println(email + " " + passWord + " pay amount: " + amount + " payName: " + this.paymentName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
