-- 修改pg_user表，增加openID,qqNumber和is_del字段
ALTER table pg_user add column openId varchar(32)  DEFAULT NULL COMMENT 'openId   小程序独有';
ALTER table pg_user add column qq_number varchar(12)  DEFAULT NULL COMMENT 'qq号，程序员结单的联系方式';
ALTER table pg_user add column is_del varchar(2)  DEFAULT NULL COMMENT '是否删除标志  0 未删  1 已删';