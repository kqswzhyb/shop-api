create table user (
  user_id           varchar(64)      not null auto_increment    comment '用户ID',
  role_id           varchar(64)      default null               comment '角色ID',
  phone         varchar(13)     default null                   comment '手机号',
  user_name         varchar(30)     not null                   comment '用户账号',
  nick_name         varchar(30)     not null                   comment '用户昵称',
  password          varchar(100)    default ''                 comment '密码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_at       datetime                                   comment '创建时间',
  update_at       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表'