package goods.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods_group")
public class GoodsGroupEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long groupId;

    @OneToOne(targetEntity = TypeEntity.class)
    private TypeEntity kinds;

    public TypeEntity getKinds() {
        return kinds;
    }

    public void setKinds(TypeEntity kinds) {
        this.kinds = kinds;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(targetEntity = LabelEntity.class)
    private List<LabelEntity> labelList = new ArrayList<>();

    public List<LabelEntity> getLabelsList() {
        return labelList;
    }

    public void setLabelsList(List<LabelEntity> labelsList) {
        this.labelList = labelsList;
    }

    @OneToMany(targetEntity = GoodsEntity.class)
    private List<GoodsEntity> goodsList = new ArrayList<>();

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    public List<GoodsEntity> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsEntity> goodsList) {
        this.goodsList = goodsList;
    }

    public void addGoods(GoodsEntity... goodsEntities){
        for(GoodsEntity item : goodsEntities){
            boolean find = false;
            for(GoodsEntity entity : this.goodsList){
                if (entity.getId().equals(item.getId())){
                    find = true;
                    break;
                }
            }

            if(find == false){
                this.goodsList.add(item);
            }
        }
    }

    public void addLabel(LabelEntity entity){
        for(LabelEntity labelEntity : labelList){
            if(labelEntity.getLabel().equals(entity.getLabel())){
                return;
            }
        }

        this.labelList.add(entity);
    }
}
