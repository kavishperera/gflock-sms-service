package lk.kavishmanjitha.gflock_sms_service.main.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "main_s_sms_sent_numbers")
public class MainSsmsNumbers implements Serializable {

    private Integer indexNo;
    private String number;
    private String name;
    private boolean smsStatus;

    public MainSsmsNumbers() {
    }

    @Id
    @Column(name = "index_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sms_status")
    public boolean isSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(boolean smsStatus) {
        this.smsStatus = smsStatus;
    }

    @Override
    public String toString() {
        return "MainSsmsNumbers{" +
                "indexNo=" + indexNo +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", smsStatus=" + smsStatus +
                '}';
    }
}
