package awell.xin.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity{
    @Id
    private String userId;//微信的token
    @Column
    @OneToMany
    private ArrayList<AddressEntity> addressList;
    @Column
    private long defaultAddressId;
    @Column
    @OneToMany
    private ArrayList<ShoppingCartItemEntity> shoppingCartItemEntityList;

    @Column
    @OneToMany
    private ArrayList<HistoryEntity> historyEntityArrayList;

    @Column
    @OneToMany
    private ArrayList<Long> collectionGoodsList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<AddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<AddressEntity> addressList) {
        this.addressList = addressList;
    }

    public long getDefaultAddressId() {
        return defaultAddressId;
    }

    public void setDefaultAddressId(long defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    public ArrayList<ShoppingCartItemEntity> getShoppingCartItemEntityList() {
        return shoppingCartItemEntityList;
    }

    public void setShoppingCartItemEntityList(ArrayList<ShoppingCartItemEntity> shoppingCartItemEntityList) {
        this.shoppingCartItemEntityList = shoppingCartItemEntityList;
    }

    public ArrayList<HistoryEntity> getHistoryEntityArrayList() {
        return historyEntityArrayList;
    }

    public void setHistoryEntityArrayList(ArrayList<HistoryEntity> historyEntityArrayList) {
        this.historyEntityArrayList = historyEntityArrayList;
    }

    public ArrayList<Long> getCollectionGoodsList() {
        return collectionGoodsList;
    }

    public void setCollectionGoodsList(ArrayList<Long> collectionGoodsList) {
        this.collectionGoodsList = collectionGoodsList;
    }
}
