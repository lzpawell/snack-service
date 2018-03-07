package goods.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private float price;
    private ArrayList<String> topicUrlList;    //有序，商品的轮播展示图， 第一页用于商品的默认图片
    private ArrayList<String> detailUrlList;   //有序，商品的详细介绍
    private String taste;  //口味
    private int salesNum;//销量

    private Long goodsGroupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<String> getTopicUrlList() {
        return topicUrlList;
    }

    public void setTopicUrlList(ArrayList<String> topicUrlList) {
        this.topicUrlList = topicUrlList;
    }

    public ArrayList<String> getDetailUrlList() {
        return detailUrlList;
    }

    public void setDetailUrlList(ArrayList<String> detailUrlList) {
        this.detailUrlList = detailUrlList;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public Long getGoodsGroupId() {
        return goodsGroupId;
    }

    public void setGoodsGroupId(Long goodsGroupId) {
        this.goodsGroupId = goodsGroupId;
    }
}
