public enum TestEnum {
    BUYER_BEST_OFFER(0, "BUYER_BEST_OFFER");
//    BUYER_COUNTER_OFFER("BUYER_COUNTER_OFFER", 1),
//    SELLER_COUNTER_OFFER("SELLER_COUNTER_OFFER", 2);

    private final String code;
    private final int id;
    private TestEnum(int id , String code) {
        this.code = code;
        this.id = id;
    }
}
