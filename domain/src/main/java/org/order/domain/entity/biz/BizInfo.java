package org.order.domain.entity.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 业务数据模型
 *
 * @author chaobo
 * @date 2024/11/12
 */
@Entity
@Table(name = "biz_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizInfo {

/**
     * 业务数据id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 用户姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 用户手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 用户银行卡号
     */
    @Column(name = "bankcard")
    private String bankcard;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户来源：1.A, 2.B, 3.C
     */
    @Column(name = "source")
    private Integer source;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除. 0. 否， 1.是
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public BizInfo(String name, String idCard, String phone, String bankcard, String orderNo, Integer source) {
        this.name = name;
        this.idCard = idCard;
        this.phone = phone;
        this.bankcard = bankcard;
        this.orderNo = orderNo;
        this.source = source;
    }
}
