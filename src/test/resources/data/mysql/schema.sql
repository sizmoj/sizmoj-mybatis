DROP TABLE sys_user_role;
DROP TABLE sys_user;
DROP TABLE sys_dict;
DROP TABLE sys_log;
DROP TABLE sys_role_menu;
DROP TABLE sys_menu;
DROP TABLE sys_role;

CREATE TABLE sys_user_role
(
	user_id bigint NOT NULL COMMENT '用户编号',
	role_id bigint NOT NULL COMMENT '角色编号',
	PRIMARY KEY (user_id, role_id)
) COMMENT = '用户-角色';

CREATE TABLE sys_user
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
	login_name varchar(100) NOT NULL COMMENT '登录名',
	password varchar(100) NOT NULL COMMENT '密码',
	name varchar(100) NOT NULL COMMENT '姓名',
	email varchar(200) COMMENT '邮箱',
	phone varchar(200) COMMENT '电话',
	mobile varchar(200) COMMENT '手机',
	user_type char(1) COMMENT '用户类型',
	login_ip varchar(100) COMMENT '最后登陆IP',
	login_date datetime COMMENT '最后登陆时间',
	create_by bigint COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by bigint COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '用户表';

CREATE TABLE sys_dict
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
	label varchar(100) NOT NULL COMMENT '标签名',
	value varchar(100) NOT NULL COMMENT '数据值',
	type varchar(100) NOT NULL COMMENT '类型',
	description varchar(100) NOT NULL COMMENT '描述',
	sort int NOT NULL COMMENT '排序（升序）',
	create_by bigint COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by bigint COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '字典表';

CREATE TABLE sys_log
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
	type char(1) DEFAULT '1' COMMENT '日志类型（1：接入日志；2：异常日志）',
	create_by bigint COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	remote_addr varchar(255) COMMENT '操作IP地址',
	user_agent varchar(255) COMMENT '用户代理',
	request_uri varchar(255) COMMENT '请求URI',
	method varchar(5) COMMENT '操作方式',
	params text COMMENT '操作提交的数据',
	exception text COMMENT '异常信息',
	PRIMARY KEY (id)
);

CREATE TABLE sys_role_menu
(
	role_id bigint NOT NULL COMMENT '角色编号',
	menu_id bigint NOT NULL COMMENT '菜单编号',
	PRIMARY KEY (role_id, menu_id)
) COMMENT = '角色-菜单';

CREATE TABLE sys_menu
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
	parent_id bigint NOT NULL COMMENT '父级编号',
	parent_ids varchar(255) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '菜单名称',
	href varchar(255) COMMENT '链接',
	target varchar(20) COMMENT '目标（mainFrame、 _blank、_self、_parent、_top）',
	icon varchar(100) COMMENT '图标',
	sort int NOT NULL COMMENT '排序（升序）',
	is_show char(1) NOT NULL COMMENT '是否在菜单中显示（1：显示；0：不显示）',
	permission varchar(200) COMMENT '权限标识',
	create_by bigint COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by bigint COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '菜单表';

CREATE TABLE sys_role
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
	name varchar(100) NOT NULL COMMENT '角色名称',
	enname varchar(255) COMMENT '英文名称',
	create_by bigint COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by bigint COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '角色表';
CREATE INDEX sys_dict_value ON sys_dict (value ASC);
CREATE INDEX sys_dict_label ON sys_dict (label ASC);
CREATE INDEX sys_dict_del_flag ON sys_dict (del_flag ASC);
CREATE INDEX sys_log_create_by ON sys_log (create_by ASC);
CREATE INDEX sys_log_request_uri ON sys_log (request_uri ASC);
CREATE INDEX sys_log_type ON sys_log (type ASC);
CREATE INDEX sys_log_create_date ON sys_log (create_date ASC);
CREATE INDEX sys_menu_parent_id ON sys_menu (parent_id ASC);
CREATE INDEX sys_menu_parent_ids ON sys_menu (parent_ids ASC);
CREATE INDEX sys_menu_del_flag ON sys_menu (del_flag ASC);
CREATE INDEX sys_role_del_flag ON sys_role (del_flag ASC);
CREATE INDEX sys_user_login_name ON sys_user (login_name ASC);
CREATE INDEX sys_user_update_date ON sys_user (update_date ASC);
CREATE INDEX sys_user_del_flag ON sys_user (del_flag ASC);

--BLOG模块
DROP TABLE blog_post;
DROP TABLE blog_tag;
DROP TABLE blog_post_tag;
DROP TABLE blog_term;
DROP TABLE blog_comment;
DROP TABLE blog_post_term;
DROP TABLE blog_post_comment;
CREATE TABLE blog_post
(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '文章ID',
	user_id bigint NOT NULL COMMENT '文章作者',
	public_Date datetime COMMENT '发布时间',
	content varchar(15535) NOT NULL COMMENT '文章内容',
	post_Title varchar(100) NOT NULL COMMENT '文章标题',
	excerpt varchar(100)  COMMENT '摘录',
	status char(1) DEFAULT '0' NOT NULL COMMENT '文章状态（0：正常；1：删除）',
	password varchar(100)  COMMENT '文章密码',
	post_Modified datetime COMMENT '修改时间',
	parentPost_id bigint COMMENT '上级文章',
	menuOrder int COMMENT '排序',
	comment_Count int COMMENT '评论条数',
	term_id bigint NOT NULL COMMENT '所属分类',
	primary key (id)
) COMMENT = '文章表';

CREATE TABLE blog_tag(
	id bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
	name varchar(100) NOT NULL COMMENT '标签名字',
	primary key(id)
) COMMENT = '标签表';


CREATE TABLE blog_post_tag(
	post_id bigint NOT NULL COMMENT '文章编号',
	tag_id bigint NOT NULL COMMENT '标签编号',
	PRIMARY KEY (post_id, tag_id)
) COMMENT = '文章标签表';

CREATE TABLE blog_term(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
	name varchar(100) NOT NULL COMMENT '名字',
	slug varchar(100) COMMENT '缩略名',
	is_Show char(1) DEFAULT '0' NOT NULL COMMENT '是否在导航栏中显示（0：正常；1：不显示）',
	is_Page char(1) DEFAULT '0' NOT NULL COMMENT '是否是一个页面（0：是；1：不是）',
	primary key(id)
) COMMENT = '分类表';

CREATE TABLE blog_post_term(
	post_id bigint NOT NULL COMMENT '文章编号',
	term_id bigint NOT NULL COMMENT '分类编号',
	PRIMARY KEY (post_id, term_id)
) COMMENT = '文章分类表';

CREATE TABLE blog_comment(
	id bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
	post_id bigint NOT NULL COMMENT '文章',
	author varchar(100) COMMENT '作者',
	email varchar(100) COMMENT '评论者邮箱',
	url varchar(100) COMMENT '评论者主页',
	ip varchar(100) COMMENT '评论者IP',
	dateTime datetime COMMENT '评论时间',
	text varchar(100) COMMENT '评论者内容',
	approved char(1) DEFAULT '0' NOT NULL COMMENT '是否被允许（0：是；1：不是）',
	Agent varchar(100) COMMENT '评论客户端信息',
	parent_Comment_id bigint COMMENT '上一条评论',
	primary key(id)
) COMMENT = '评论表';
CREATE TABLE blog_post_comment(
	post_id bigint NOT NULL COMMENT '文章编号',
	comment_id bigint NOT NULL COMMENT '评论编号',
	PRIMARY KEY (post_id, comment_id)
) COMMENT = '文章评论表';

CREATE INDEX blog_post_term_id ON blog_post (id ASC);
CREATE INDEX blog_post_user_id ON blog_post (user_id ASC);
CREATE INDEX blog_post_status ON blog_post (status ASC);
CREATE INDEX blog_post_public_Date ON blog_post (public_Date ASC);
CREATE INDEX blog_term_name ON blog_term (name ASC);
CREATE INDEX blog_comment_email ON blog_comment (email ASC);