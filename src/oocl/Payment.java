package oocl;

public abstract class Payment {
    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    protected String paymentName;
    protected Long amount;

    public abstract void pay();
}
