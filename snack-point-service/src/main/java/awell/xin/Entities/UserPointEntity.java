package awell.xin.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ssx on 2018/1/18.
 */

@Entity
@Table
public class UserPointEntity {
    @Id
    private String userId;
    @JoinTable(name = "signInRecordList")
    @ElementCollection
    private List<Date> signInRecordList = new ArrayList<>();
    @Column
    private int point;//用户分数
    @Column
    private String nickName;//冗余用户昵称属性，减少向UserService的查询次数
    @Column
    private int day;//连续签到天数
    @Column
    private int totalDay;//总签到天数

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Date> getSignInRecordList() {
        return signInRecordList;
    }

    public void setSignInRecordList(List<Date> signInRecordList) {
        this.signInRecordList = signInRecordList;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }
}
