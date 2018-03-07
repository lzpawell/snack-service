package goods.entities;

import javax.persistence.*;

@Entity()
@Table(name = "goods_type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private String type;

    private String imgUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
