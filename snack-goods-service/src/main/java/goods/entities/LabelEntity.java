package goods.entities;

import javax.persistence.*;

@Entity
@Table(name = "goods_label")
public class LabelEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
