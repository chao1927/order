-- 业务数据表 --
CREATE TABLE `biz_info` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '业务数据id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
    `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户身份证号',
    `phone` varchar(11) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号',
    `bankcard` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户银行卡号',
    `order_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
    `source` tinyint NOT NULL COMMENT '用户来源：1.A, 2.B, 3.C',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除. 0. 否， 1.是',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`) USING BTREE COMMENT '姓名索引',
    KEY `idx_phone` (`phone`) USING BTREE COMMENT '手机号索引',
    KEY `idx_bankcard` (`bankcard`) USING BTREE COMMENT '银行卡号索引',
    KEY `idx_order_no` (`order_no`) USING BTREE COMMENT '订单号索引',
    KEY `idx_source` (`source`) USING BTREE COMMENT '来源索引',
    KEY `idx_id_card` (`id_card`) USING BTREE COMMENT '身份证号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业务数据表';


-- 业务数据教育经历 --
CREATE TABLE `edu_experience` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '教育经历id',
      `biz_info_id` bigint NOT NULL COMMENT '业务数据id',
      `start_date` date NOT NULL COMMENT '开始时间',
      `end_date` date DEFAULT NULL COMMENT '结束时间',
      `title` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
      `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
      `certifier` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证明人',
      `certifier_phone` varchar(11) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证明人手机号',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
      `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
      PRIMARY KEY (`id`),
      KEY `idx_biz_info_id` (`biz_info_id`) USING BTREE COMMENT '业务数据id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业务数据-教育信息';

-- 业务数据家庭成员 --
CREATE TABLE `family_member` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '家庭成员id',
     `biz_info_id` bigint NOT NULL COMMENT '业务数据id',
     `identity` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员身份',
     `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员名称',
     `job` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '家庭成员工作',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
     PRIMARY KEY (`id`),
     KEY `idx_biz_info_id` (`biz_info_id`) USING BTREE COMMENT '业务数据id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业务数据-家庭成员';

-- 业务数据工作经历 --
CREATE TABLE `work_experience` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作经历id',
   `biz_info_id` bigint NOT NULL COMMENT '业务数据id',
   `start_date` date NOT NULL COMMENT '开始时间',
   `end_date` date NOT NULL COMMENT '结束时间',
   `company` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司',
   `position` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职位',
   `certifier` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证明人',
   `certifier_phone` varchar(11) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证明人手机号',
   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0.否，1.是',
   PRIMARY KEY (`id`),
   KEY `idx_biz_info_id` (`biz_info_id`) USING BTREE COMMENT '业务数据id 索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业务数据工作经历';

-- 变量 --
CREATE TABLE `param` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '变量id',
     `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量名称',
     `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量描述',
     `expression` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量表达式',
     `result_type` int NOT NULL COMMENT '结果类型',
     `version` int NOT NULL COMMENT '版本号',
     `create_at` bigint DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` bigint DEFAULT NULL COMMENT '修改人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0.否，1.是',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uni_name` (`name`) USING BTREE COMMENT '变量名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='变量';

-- 版本变量 --
CREATE TABLE `version_param` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本变量id',
     `param_id` bigint NOT NULL COMMENT '变量id',
     `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量名称',
     `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量描述',
     `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量表达式',
     `result_type` int NOT NULL COMMENT '结果类型',
     `version` int NOT NULL COMMENT '版本号',
     `status` int NOT NULL COMMENT '状态：（1. 已发布，2.已下架，3.已删除）',
     `create_at` bigint DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` bigint DEFAULT NULL COMMENT '修改人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0.否，1.是',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uni_param_id_version` (`param_id`,`version`) USING BTREE COMMENT '变量id和版本号唯一索引',
     KEY `idx_name` (`name`) USING BTREE COMMENT '变量名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本变量';

-- 规则 --
CREATE TABLE `rule` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规则id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则名称',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '规则描述',
    `expression` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则表达式',
    `result_type` int NOT NULL COMMENT '规则结果类型',
    `version` int NOT NULL COMMENT '版本号',
    `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_name` (`name`) USING BTREE COMMENT '规则名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='规则';

-- 规则子项 --
CREATE TABLE `rule_item` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规则子项名称',
     `rule_id` bigint NOT NULL COMMENT '规则id',
     `sort` int NOT NULL COMMENT '规则子项序号',
     `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则子项名称',
     `type` int NOT NULL COMMENT '规则子项类型（变量/规则），1.变量，2.规则',
     `ref_id` bigint NOT NULL COMMENT '规则子项id （变量id/规则id）',
     `ref_version` int NOT NULL COMMENT '规则子项id版本号 （变量版本号/规则版本号）',
     `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0.否，1.是',
     PRIMARY KEY (`id`),
     KEY `idx_rule_id` (`rule_id`) USING BTREE COMMENT '规则id索引',
     KEY `idx_type_ref_id_version` (`type`,`ref_id`, `ref_version`) USING BTREE COMMENT '规则类型-子项id-版本号唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='规则子项';

-- 版本规则 --
CREATE TABLE `version_rule` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本规则id',
    `rule_id` bigint NOT NULL COMMENT '版本规则id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本规则名称',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本规则描述',
    `expression` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本规则表达式',
    `result_type` int NOT NULL COMMENT '版本规则结果类型',
    `version` int NOT NULL COMMENT '版本号',
    `status` int NOT NULL COMMENT '状态：（1. 已发布，2.已下架）',
    `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_rule_id_version` (`rule_id`,`version`) USING BTREE COMMENT '规则id和版本号唯一索引',
    KEY `idx_name` (`name`) USING BTREE COMMENT '版本规则名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本规则';

-- 版本规则子项 --
CREATE TABLE `version_rule_item` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本规则子项名称',
     `version_rule_id` bigint NOT NULL COMMENT '版本规则id',
     `sort` int NOT NULL COMMENT '版本规则子项序号',
     `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本规则子项名称',
     `type` int NOT NULL COMMENT '版本规则子项类型（变量/规则），1.变量，2.规则',
     `ref_id` bigint NOT NULL COMMENT '版本规则子项id （变量id/规则id）',
     `ref_version` int NOT NULL COMMENT '版本规则子项id版本号 （变量版本号/规则版本号）',
     `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0.否，1.是',
     PRIMARY KEY (`id`),
     KEY `idx_version_rule_id` (`version_rule_id`) USING BTREE COMMENT '版本规则id索引',
     KEY `idx_type_ref_id_ref_version` (`type`,`ref_id`, `ref_version`) USING BTREE COMMENT '版本规则类型-子项id-版本号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本规则子项';

-- action --
CREATE TABLE `action` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'action id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action名称',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'action 描述',
    `version` int NOT NULL COMMENT '版本号',
    `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_name` (`name`) USING BTREE COMMENT 'action 名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='action ';


-- version action --
CREATE TABLE `version_action` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本 action id',
    `action_id` bigint NOT NULL COMMENT 'action id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本action名称',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本action描述',
    `version` int NOT NULL COMMENT '版本号',
    `status` int NOT NULL COMMENT '状态：（1. 已发布，2.已下架，3.已删除）',
    `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_action_id_version` (`action_id`, `version`) USING BTREE COMMENT 'action id和版本号索引',
    KEY `idx_name` (`name`) USING BTREE COMMENT '版本action名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本action';

-- 流程定义 --
CREATE TABLE `flow` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程 id',
      `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程名称',
      `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程描述',
      `version` int NOT NULL COMMENT '流程版本号',
      `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
      PRIMARY KEY (`id`),
      UNIQUE KEY `uni_name` (`name`) USING BTREE COMMENT '流程名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程';

-- 流程节点定义 --
CREATE TABLE `flow_node` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程节点 id',
  `flow_id` bigint NOT NULL COMMENT '流程 id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程节点名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程节点描述',
  `type` int NOT NULL COMMENT '流程节点类型：1.start，2.end，3.rule，4.action，5.judge',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程节点内容',
  `ref_id` bigint DEFAULT NULL COMMENT '引用的id',
  `ref_version` int DEFAULT NULL COMMENT '引用的版本号',
  `create_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
  PRIMARY KEY (`id`),
  KEY `idx_flow_id` (`flow_id`) USING BTREE COMMENT '流程id索引',
  KEY `idx_type` (`type`) USING BTREE COMMENT '类型索引',
  KEY `idx_ref_id_version` (`ref_id`,`ref_version`) USING BTREE COMMENT '引用的id+版本号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程节点';

-- 流程边定义 --
CREATE TABLE `flow_line` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程边 id',
     `flow_id` bigint NOT NULL COMMENT '流程 id',
     `pre_node_id` bigint NOT NULL COMMENT '前置节点 id',
     `pre_node_name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '前置节点名称',
     `next_node_id` bigint NOT NULL COMMENT '后置节点 id',
     `next_node_name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '后置节点名称',
     `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程边内容',
     `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
     PRIMARY KEY (`id`),
     KEY `idx_flow_id` (`flow_id`) USING BTREE COMMENT '流程 id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程边';

-- 流程定义 --
CREATE TABLE `version_flow` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本流程 id',
    `flow_id` bigint NOT NULL COMMENT '流程 id',
    `name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本流程名称',
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本流程描述',
    `version` int NOT NULL COMMENT '版本流程版本号',
    `status` int NOT NULL COMMENT '状态：（1. 已发布，2.已下架，3.已删除）',
    `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_flow_id_version` (`flow_id`, `version`) USING BTREE COMMENT '流程 id和版本号唯一索引',
    KEY `idx_name` (`name`) USING BTREE COMMENT '版本流程名称索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本流程';

-- 流程节点定义 --
CREATE TABLE `version_flow_node` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本流程节点 id',
  `version_flow_id` bigint NOT NULL COMMENT '版本流程 id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '版本流程节点名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本流程节点描述',
  `type` int NOT NULL COMMENT '版本流程节点类型：1.start，2.end，3.rule，4.action，5.judge',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本流程节点内容',
  `ref_id` bigint DEFAULT NULL COMMENT '引用id',
  `ref_version` int DEFAULT NULL COMMENT '引用版本号',
  `create_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
  PRIMARY KEY (`id`),
  KEY `idx_version_flow_id` (`version_flow_id`) USING BTREE COMMENT '版本流程id索引',
  KEY `idx_type` (`type`) USING BTREE COMMENT '类型索引',
  KEY `idx_ref_id_version` (`ref_id`,`ref_version`) USING BTREE COMMENT '引用id+版本号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本流程节点';

-- 流程边定义 --
CREATE TABLE `version_flow_line` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '版本流程边 id',
     `version_flow_id` bigint NOT NULL COMMENT '版本流程 id',
     `pre_node_id` bigint NOT NULL COMMENT '前置节点 id',
     `pre_node_name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '前置节点名称',
     `next_node_id` bigint NOT NULL COMMENT '后置节点 id',
     `next_node_name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '后置节点名称',
     `content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本流程边内容',
     `create_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_at` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
     PRIMARY KEY (`id`),
     KEY `idx_version_flow_id` (`version_flow_id`) USING BTREE COMMENT '版本流程 id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本流程边';

-- 入口 --
CREATE TABLE `entry` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '入口 id',
      `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入口名称',
      `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '入口描述',
      `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入口表达式',
      `flow_id` bigint NOT NULL COMMENT '流程id',
      `flow_version` int NOT NULL COMMENT '流程版本号',
      `version` int NOT NULL COMMENT '版本号',
      `create_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_name` (`name`) USING BTREE COMMENT '入口名称索引',
  KEY `idx_flow_id_version` (`flow_id`,`flow_version`) USING BTREE COMMENT '流程 id+流程版本号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='入口';

-- 版本入口 --
CREATE TABLE `version_entry` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '入口 id',
  `entry_id` bigint NOT NULL COMMENT '入口 id',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入口名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '入口描述',
  `expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入口表达式',
  `flow_id` bigint NOT NULL COMMENT '流程id',
  `flow_version` int NOT NULL COMMENT '流程版本号',
  `version` int NOT NULL COMMENT '版本号',
  `status` int NOT NULL COMMENT '状态：（1. 已发布，2.已下架，3.已删除）',
  `create_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0.否，1.是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_entry_id_version` (`entry_id`,`version`) USING BTREE COMMENT '入口版本唯一索引',
  KEY `uni_name` (`name`) USING BTREE COMMENT '入口名称索引',
  KEY `idx_flow_id_version` (`flow_id`,`flow_version`) USING BTREE COMMENT '流程 id+流程版本号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='入口';


CREATE TABLE `entry_task` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '入口任务表id',
      `entry_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务编号',
      `version_entry_id` bigint NOT NULL COMMENT '版本入口id',
      `entry_id` bigint NOT NULL COMMENT '入口id',
      `entry_version` int NOT NULL COMMENT '入口版本号',
      `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
      `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
      `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
      `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
      `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`id`),
      KEY `idx_entry_task_no` (`entry_task_no`) USING BTREE COMMENT '入口任务编号索引',
      KEY `idx_version_entry_id` (`version_entry_id`) USING BTREE COMMENT '版本入口编号索引',
      KEY `idx_entry_id_version` (`entry_id`,`entry_version`) USING BTREE COMMENT '入口id和版本号索引',
      KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id 索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='入口任务表';

CREATE TABLE `flow_task` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程任务表id',
      `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
      `entry_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '入口任务编号',
      `version_flow_id` bigint NOT NULL COMMENT '版本流程id',
      `flow_id` bigint NOT NULL COMMENT '流程id',
      `flow_version` int NOT NULL COMMENT '流程版本号',
      `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
      `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
      `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
      `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
      `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`id`),
      KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
      KEY `idx_entry_task_no` (`entry_task_no`) USING BTREE COMMENT '入口任务编号索引',
      KEY `idx_version_flow_id` (`version_flow_id`) USING BTREE COMMENT '版本流程编号索引',
      KEY `idx_flow_id_version` (`flow_id`,`flow_version`) USING BTREE COMMENT '流程id和版本号索引',
      KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程任务表';

CREATE TABLE `flow_node_task` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程节点任务表id',
     `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
     `version_flow_node_id` bigint NOT NULL COMMENT '版本流程节点id',
     `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
     `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
     `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
     `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
     `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
     KEY `idx_version_flow_node_id` (`version_flow_node_id`) USING BTREE COMMENT '版本流程节点编号索引',
     KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程节点任务表';

CREATE TABLE `flow_line_task` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '流程边任务表id',
      `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
      `version_flow_line_id` bigint NOT NULL COMMENT '版本流程边id',
      `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
      `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
      `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
      `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
      `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      PRIMARY KEY (`id`),
      KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
      KEY `idx_version_flow_line_id` (`version_flow_line_id`) USING BTREE COMMENT '版本流程边编号索引',
      KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流程边任务表';

CREATE TABLE `action_task` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'action 任务表id',
     `action_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action 任务编号',
     `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
     `version_flow_node_id` bigint NOT NULL COMMENT '版本流程节点id',
     `version_action_id` bigint NOT NULL COMMENT '版本 action id',
     `action_id` bigint NOT NULL COMMENT 'action id',
     `action_version` int NOT NULL COMMENT 'action 版本号',
     `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
     `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
     `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
     `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
     `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx_action_task_no` (`action_task_no`) USING BTREE COMMENT 'action任务编号索引',
     KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
     KEY `idx_version_flow_node_id` (`version_flow_node_id`) USING BTREE COMMENT '版本流程节点编号索引',
     KEY `idx_version_action_id` (`version_action_id`) USING BTREE COMMENT '版本 action 编号索引',
     KEY `idx_action_id_version` (`action_id`,`action_version`) USING BTREE COMMENT 'action id和版本号索引',
     KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='action 任务表';

CREATE TABLE `rule_task` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规则任务表id',
   `rule_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则任务编号',
   `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
   `task_type` tinyint NOT NULL COMMENT '任务类型：1：来源于流程节点的规则任务，2：来源于规则子项的规则任务',
   `ref_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务编号: taskType为1：来源于流程节点的任务，则为流程任务的id: flowTaskNo,taskType 为2：来源于规则子项的任务，则为规则任务的id：ruleTaskNo',
   `version_rule_id` bigint NOT NULL COMMENT '版本规则id',
   `rule_id` bigint NOT NULL COMMENT '规则id',
   `rule_version` int NOT NULL COMMENT '规则版本号',
   `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
   `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
   `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
   `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
   `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`),
   KEY `idx_rule_task_no` (`rule_task_no`) USING BTREE COMMENT '规则任务编号索引',
   KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
   KEY `idx_task_type_ref_task_no` (`task_type`,`ref_task_no`) USING BTREE COMMENT '任务类型和任务编号索引',
   KEY `idx_version_rule_id` (`version_rule_id`) USING BTREE COMMENT '版本规则编号索引',
   KEY `idx_rule_id_version` (`rule_id`,`rule_version`) USING BTREE COMMENT '规则id和版本号索引',
   KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='规则任务表';


CREATE TABLE `rule_item_task` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '规则子项任务表id',
     `rule_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则任务编号',
     `version_rule_item_id` bigint NOT NULL COMMENT '版本规则子项id',
     `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
     `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
     `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
     `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
     `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx_rule_task_no` (`rule_task_no`) USING BTREE COMMENT '规则任务编号索引',
     KEY `idx_version_rule_item_id` (`version_rule_item_id`) USING BTREE COMMENT '版本规则子项编号索引',
     KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='规则子项任务表';

CREATE TABLE `param_task` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '变量任务表id',
     `param_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '变量任务编号',
     `rule_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则任务编号',
     `flow_task_no` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程任务编号',
     `version_param_id` bigint NOT NULL COMMENT '版本变量id',
     `param_id` bigint NOT NULL COMMENT '变量id',
     `param_version` int NOT NULL COMMENT '变量版本号',
     `biz_id` varchar(40) COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务编号',
     `status` tinyint NOT NULL COMMENT '执行状态：1.待执行，2.执行中，3.执行成功，4.执行失败，5.任务超时',
     `result` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行结果',
     `fail_reason` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行失败原因',
     `finish_time` datetime DEFAULT NULL COMMENT '执行完成时间',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx_param_task_no` (`param_task_no`) USING BTREE COMMENT '变量任务编号索引',
     KEY `idx_rule_task_no` (`rule_task_no`) USING BTREE COMMENT '规则任务编号索引',
     KEY `idx_flow_task_no` (`flow_task_no`) USING BTREE COMMENT '流程任务编号索引',
     KEY `idx_version_param_id` (`version_param_id`) USING BTREE COMMENT '版本变量编号索引',
     KEY `idx_param_id_version` (`param_id`,`param_version`) USING BTREE COMMENT '变量id和版本号索引',
     KEY `idx_biz_id` (`biz_id`) USING BTREE COMMENT '业务id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='变量任务表';