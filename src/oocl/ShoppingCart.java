package oocl;

import java.util.*;

//饿汉
public class ShoppingCart {
    private List<Product> products;
    private static ShoppingCart instance = new ShoppingCart();
    private ShoppingCart(){
        this.products = new ArrayList<>();
    };
    public static ShoppingCart getInstance(){
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }
}

//在 多线程版本下做出的进一步改动:
//1.使用双重 if 判定 , 降低锁竞争的频率 .
//2.给 instance 加上了 volatile.
class Singleton {
    //Java中的volatile关键字主要用于确保多线程环境下共享变量的可见性和禁止指令重排
    private static volatile Singleton instance = null;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
