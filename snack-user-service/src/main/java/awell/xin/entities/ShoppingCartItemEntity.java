package awell.xin.entities;

import javax.persistence.*;

@Table
@Entity
public class ShoppingCartItemEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long cartItemId;
    @Column
    private String goodsId;
    @Column
    private int count;

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
