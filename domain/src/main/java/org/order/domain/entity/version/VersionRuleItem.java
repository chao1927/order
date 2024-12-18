package org.order.domain.entity.version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 版本规则子项
 *
 * @author chaobo
 * @date 2024/11/17
 */
@Entity
@Table(name = "version_rule_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionRuleItem {
    /**
     * 版本规则子项名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本规则id
     */
    @Column(name = "version_rule_id")
    private Long versionRuleId;

    /**
     * 版本规则子项序号
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 版本规则子项名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 版本规则子项类型（变量/规则），1.变量，2.规则
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 版本规则子项id （变量id/规则id）
     */
    @Column(name = "ref_id")
    private Long refId;

    /**
     * 版本规则子项id版本号 （变量版本号/规则版本号）
     */
    @Column(name = "ref_version")
    private Integer refVersion;

    /**
     * 创建人
     */
    @Column(name = "create_at")
    private String createAt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_at")
    private String updateAt;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除，0.否，1.是
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public VersionRuleItem(Long versionRuleId, Integer sort, String name, Integer type, Long refId, Integer refVersion) {
        this.versionRuleId = versionRuleId;
        this.sort = sort;
        this.name = name;
        this.type = type;
        this.refId = refId;
        this.refVersion = refVersion;
    }
}