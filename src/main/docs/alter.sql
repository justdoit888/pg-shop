-- 修改pg_user表：增加openID,qqNumber和is_del字段
-- 2019/09/02 提交  by zhjs
ALTER table pg_user add column openId varchar(32)  DEFAULT NULL COMMENT 'openId   小程序独有';
ALTER table pg_user add column qq_number varchar(12)  DEFAULT NULL COMMENT 'qq号，程序员结单的联系方式';
ALTER table pg_user add column is_del varchar(2)  DEFAULT NULL COMMENT '是否删除标志  0 未删  1 已删';


-- 修改pg_order表：增加订单时间字段
-- 修改pg_user表：修改user_name字段为tel
-- 2019/09/16 提交 by zhjs
ALTER table pg_order add column order_time datetime DEFAULT NULL COMMENT '订单时间';
ALTER table pg_user change user_name tel varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号';
