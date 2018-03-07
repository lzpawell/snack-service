package awell.xin.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ssx on 2018/1/18.
 */
@Entity
@Table
public class PointUpdateItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    @Column
    private String userId;
    @Column
    private String description;
    @Column
    private Date updateDate;
    @Column
    private int changPoint;//更改的分数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getChangPoint() {
        return changPoint;
    }

    public void setChangPoint(int changPoint) {
        this.changPoint = changPoint;
    }
}
