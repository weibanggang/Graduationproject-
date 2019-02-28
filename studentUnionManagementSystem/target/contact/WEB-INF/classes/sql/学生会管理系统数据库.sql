drop database StudentUnionManagementSystem ;
create database StudentUnionManagementSystem CHARACTER SET utf8 COLLATE utf8_general_ci;
use StudentUnionManagementSystem;
#1备份表
create table backups(
b_id int auto_increment primary key comment '编号',
b_beforedate datetime default now()  comment '备份时间',
b_file varchar(256) comment '备份路径',
b_afterdate datetime comment '还原时间',
b_brefore_name varchar(20) comment '备份人',
b_after_name varchar(20) comment '还原人'
);
insert into backups(b_file,b_afterdate,b_brefore_name,b_after_name)values
('目前路径为空',null,'韦邦杠',null),
('目前路径为空',null,'欧一乐',null),
('目前路径为空',null,'刘地林',null),
('目前路径为空',null,'唐玉棋',null);

#短信验证
create table VerificationCode(
id int auto_increment primary key,
yzm int comment '验证码',
v_type varchar(10) comment '验证类型',
v_date datetime default now()  comment '时间',
v_phone varchar(11) comment '手机号码'
);
#3部门类型
create table DepartmentType(
d_id int auto_increment primary key comment '部门编号',
d_name varchar(20) comment '部门名称',
status varchar(10) comment '状态',
d_synopsis  longtext comment '部门简介',
d_remarks varchar(256) comment '部门备注',
d_sort int comment '排序'
);
insert into DepartmentType(d_name,status,d_synopsis,d_remarks,d_sort)values
('主席团','true','主席团简介','无备注',1),
('秘书部','true','秘书部简介','无备注',2),
('文体部','true','文体部简介','无备注',3),
('播音部','true','播音部简介','无备注',4),
('宣传部','true','宣传部简介','无备注',5),
('纪检部','true','纪检部简介','无备注',6);
#权限表
CREATE TABLE `permissions` (
  `id` INT NULL primary key auto_increment,
  `name` VARCHAR(45) not NULL COMMENT '权限名称',
  `url` VARCHAR(45) COMMENT '地址'
) COMMENT = '权限表';
insert into permissions(name)values
('*'),
('bll'),
('cll'),
('dll');
#2角色
create table Roles(
r_id int auto_increment primary key comment '编号',
r_name varchar(20)  comment '角色名称',
r_remarks varchar(256) comment '备注'
);
insert into Roles(r_name)values
('admin'),
('auth'),
('bbb'),
('test'),
('aaa');
-- 角色权限关联表
CREATE TABLE `ge_user_permissions_associate` (
  `id` INT NULL primary key auto_increment COMMENT '编号',
  `roles_id` INT  COMMENT '角色ID',
  `permissions_id` INT  COMMENT '权限ID'
) COMMENT = '角色权限关联表';
insert into `ge_user_permissions_associate`(`roles_id`, `permissions_id`) values
(1, 1), (2,2), (3,3), (4,4), (5,5);


#5班级信息
create table ClassTable(
c_id int auto_increment primary key comment '编号',
c_name varchar(20) comment '班级名称',
c_headmaster_name varchar(20) comment '班主任名字',
c_phone varchar(11) comment '联系电话',
status varchar(10) comment '状态'
);
insert into ClassTable(c_name,c_headmaster_name,c_phone,status)values
('s1s143','张婷','15348564582','true'),
('s2s143','张婷','15348564582','true'),
('s3s143','张婷','15348564582','true'),
('s1s147','王莹','15348564855','true'),
('s2s147','王莹','15348564855','true'),
('s3s147','王莹','15348564855','true'),
('s1s150','易微','15348564588','true');
#6会议类型
create table TypeOfMeeting(
t_id int auto_increment primary key comment '编号',
t_name varchar(20) comment '名称',
status varchar(10) comment '状态',
t_remarks varchar(256) comment '备注',
t_sort int comment '排序'
);
insert into TypeOfMeeting(t_name,status,t_remarks,t_sort)values
('全体大会','true','使用全部会议',1),
('部门会议','true','使用部门会议',2),
('培训会议','true','使用培训会议',3);
#7职位类型
create table PositionType(
p_id int auto_increment primary key comment '编号',
p_name varchar(20) comment '类型名称',
status varchar(10) comment '状态',
p_remarks varchar(256)  comment '职位备注',
p_sort int comment '排序'
);
insert into PositionType(p_name,status,p_remarks,p_sort)values
('主席','true','该职位属于主席',1),
('副主席','true','该职位属于副主席',2),
('部长','true','该职位属于部长',3),
('副部长','true','该职位属于副部长',4),
('干事','true','该职位属于干事',5);
#8会名
create table ExchangeTable(
e_id int auto_increment primary key comment'编号',
e_name varchar(30) comment '学生会名称',
e_remarks longtext  comment '备注',
status varchar(10) comment '状态',
e_sort int comment '排序'
);
insert into ExchangeTable(e_name,e_remarks,status,e_sort)values
('第十五届学生会','备注15届学生会','true',1),
('第十四届学生会','备注14届学生会','true',2),
('第十三届学生会','备注13届学生会','true',3),
('第十二届学生会','备注12届学生会','true',4);
#9成员信息
create table MemberInformation(
m_id int auto_increment primary key comment'编号',
m_user varchar(20) unique comment '工作编号账号',
m_password varchar(20) comment '密码',
m_name varchar(10) comment '姓名',
m_sex varchar(2) comment '成员性别',
c_id int comment '成员班级',
c_phone  varchar(11) unique  comment '成员手机',
d_id int comment '成员部门',
p_id int comment '成员职位',
p_photo varchar(256) comment '图片路径',
e_id int comment '第几届',
r_id int comment '角色',
m_qq varchar(15) comment '成员qq',
status varchar(10) comment '状态',
r_admission_date date  comment '入会时间',
r_tuihui_date date  comment '退会时间',
p_remarks  longtext comment '备注',
foreign key(c_id) references  ClassTable(c_id),
foreign key(d_id)  references DepartmentType(d_id),
foreign key(p_id)   references PositionType(p_id),
foreign key(e_id)   references ExchangeTable(e_id),
foreign key(r_id)   references Roles(r_id)
);
insert into MemberInformation(m_user,m_password,m_name,m_sex,c_id,c_phone,d_id,p_id,p_photo,e_id,r_id,m_qq,status,r_admission_date,r_tuihui_date,p_remarks)values
('100010001','123456','小邦哥','男',1,'18776203778',1,4,'',2,1,'761273133','true','2018-01-01','2018-10-10',''),
('100010002','123456','廖珠炫','男',1,'11776235778',3,3,'',2,1,'861273133','true','2018-01-01','2018-10-10',''),
('100010003','123456','朱茂深','男',1,'12776553778',3,4,'',2,2,'361273133','true','2018-01-01','2018-10-10',''),
('100010004','123456','彭金华','男',1,'13346203778',2,3,'',2,3,'111273133','true','2018-01-01','2018-10-10',''),
('100010005','123456','钟世杨','男',1,'14976203778',1,4,'',2,1,'761173133','true','2018-01-01','2018-10-10',''),
('100010006','123456','邹雅','女',1,'15766203778',1,2,'',2,1,'721271133','true','2018-01-01','2018-10-10',''),
('100010007','123456','廖月康','男',1,'11276203778',1,4,'',2,1,'711173133','true','2018-01-01','2018-10-10','');
#10工作安排
create table WorkArrangement(
w_id int auto_increment primary key comment'编号',
w_title varchar(50) comment '标题',
w_contents longtext comment '内容',
d_id int comment '负责工作部门',
m_name varchar(10) comment '总负责人',
w_date date  comment '时间',
w_remarks varchar(256)  comment '备注',
foreign key(d_id) references  DepartmentType(d_id)
);
insert into WorkArrangement(w_title,w_contents,d_id,m_name,w_date)values
('文体部工作安排','关于写策划内容',3,'小丽','2018-10-11'),
('纪检部部工作安排','关于检查内容',6,'老吴','2018-10-11'),
('秘书部工作安排','关于进行秘书内容',2,'老吴','2018-10-11'),
('播音部工作安排','关于放音乐内容',4,'老吴','2018-10-11'),
('宣传部工作安排','关于写宣传内容',5,'小邹','2018-10-11');
#11公告
create table Notic(
n_id int auto_increment primary key comment'编号',
n_title varchar(50) comment '标题',
n_context longtext comment '内容',
n_date datetime default now()   comment '发布时间',
n_file varchar(256) comment '文件路径',
m_name varchar(20) comment '操作人',
status varchar(10) comment '状态',
n_sort int comment '排序'
);
insert into Notic(n_title,n_context,n_file,m_name,status,n_sort)values
('关于国庆放假通知','放假内容','目前无路径','小邦哥','true',1),
('关于元旦放假通知','放假内容','目前无路径','小邦哥','true',2),
('关于五一放假通知','放假内容','目前无路径','朱茂深','true',3),
('关于学生会活动通知','活动内容','目前无路径','小邦哥','true',4);
#12请假记录
create table LeaveRecord(
l_id int auto_increment primary key comment'编号',
d_name varchar(50) comment '部门名称',
m_user int comment '成员工作编号',
l_m_name varchar(20) comment '成员姓名',
p_date date  comment '请假时间',
l_title varchar(20) comment '会议标题',
l_type varchar(20)  comment '请假类型',
l_contexts longtext  comment '请假内容',
l_file varchar(256) comment '文件路径',
m_name varchar(20) comment '操作人'
);

insert into LeaveRecord(d_name, m_user,l_m_name,  p_date, l_title, l_type, l_contexts, l_file, m_name)values
('文体部','100010001','小邦哥','2018-12-11','第二次部门会议','部门会议','特殊情况','无','廖珠炫'),
('文体部','100010004','彭金华','2018-12-11','第二次部门会议','部门会议','特殊情况','无','廖珠炫');
#13个人评优记录
create table PersonalMeritRecord(
p_id int auto_increment primary key comment'编号',
d_name varchar(50) comment '部门名称',
m_user int comment '成员编号',
l_m_name varchar(20) comment '成员姓名',
p_title varchar(30) comment '主题',
p_date datetime comment '时间',
p_contexts longtext comment '内容',
p_file  varchar(256) comment '文件路径',
m_name varchar(20) comment '操作人'
);
insert into PersonalMeritRecord(d_name,m_user,l_m_name,p_title,p_date,p_contexts,p_file,m_name)values
('文体部','100010001','小邦哥','优秀副部长','2018-12-30','最佳副部长','无路径','曾丽玉'),
('文体部','100010002','廖珠炫','优秀部长','2018-12-30','最佳部长','无路径','曾丽玉'),
('文体部','100010004','彭金华','优秀干事','2018-12-30','最佳学长','无路径','老吴');
#14部门评优记录
create table DepartmentalExcellenceRecord(
d_id int auto_increment primary key comment'编号',
d_name varchar(50) comment '部门名称',
d_title varchar(50) comment '标题',
d_date date comment '时间',
d_contexts longtext comment '内容',
d_file varchar(256) comment '文件路径',
m_name varchar(20) comment '操作人'
);
insert into DepartmentalExcellenceRecord(d_name,d_title,d_date,d_contexts,d_file,m_name)values
('文体部','最佳优秀部门','2018-12-30','什么内部内容的','无文件','曾丽玉'),
('生活部','最佳生活管理部门','2018-12-30','生活部内容的','无文件','曾丽玉'),
('纪检部','最佳检查部门','2018-12-30','纪检部内容的','无文件','曾丽玉');
#15财务管理
create table FinancialManagement(
f_id int auto_increment primary key comment'编号',
f_type varchar(5) comment '交易类型',
f_front_money decimal(10,2) comment '交易前余额',
f_after_money decimal(10,2) comment '交易后余额',
f_money decimal(10,2) comment '交易余额',
f_date date  comment '时间',
f_mr_date  datetime default now()  comment '操作时间',
m_name varchar(20) comment '操作人',
f_remarks  varchar(256) comment '备注',
f_file  varchar(256) comment '文件路径'
);
#16出勤情况
create table  Attendance(
a_id int auto_increment primary key comment'编号',
a_type varchar(20) comment '类型',
m_user int comment '成员工作编号',
a_m_name varchar(20) comment '成员姓名',
d_name varchar(20) comment '成员部门',
a_titile  varchar(50) comment '标题',
a_date datetime default now()  comment '时间',
m_name varchar(20) comment '操作人',
a_remaks varchar(256) comment '备注'
);
insert into Attendance(a_type,m_user,a_m_name,d_name,a_titile,m_name,a_remaks) values
('全体会议','100010001','小邦哥','文体部','第一次全体培训大会','小丽','没有备注'),
('全体会议','100010002','廖珠炫','文体部','第一次全体培训大会','小丽','没有备注'),
('全体会议','100010003','彭金华','文体部','第一次全体培训大会','小丽','没有备注');
#17会议记录
create table MinutesOfTheMeeting(
m_id int auto_increment primary key comment'编号',
m_title  varchar(50) comment '标题',
t_id int comment '会议类型',
m_date  datetime default now()  comment '上传时间',
m_file   varchar(256) comment '文件路径',
m_name varchar(20) comment '操作人',
m_contexts varchar(256) comment '内容',
foreign key(t_id) references TypeOfMeeting(t_id)
);
insert into MinutesOfTheMeeting(m_title,t_id,m_file,m_name,m_contexts)values
('第二次全体大会',1,'无','小邦哥','内容正在填写'),
('第二次部门会议',2,'无','小邦哥1','内容正在填写'),
('第三次全体大会',1,'无','小邦哥2','内容正在填写'),
('第四次全体大会',1,'无','小邦哥3','内容正在填写');
#18月计划总结表
create table MonthlyPlanSummary(
m_id int auto_increment primary key comment'编号',
m_title varchar(50) comment '标题',
d_id int comment '部门编号',
m_date datetime default now()  comment '上传时间',
m_file  varchar(256) comment '文件路径',
m_remarks varchar(256) comment '备注',
m_name varchar(20) comment '操作人',
m_contexts longtext comment '内容',
foreign key(d_id) references DepartmentType(d_id)
);
insert into MonthlyPlanSummary (m_title, d_id,  m_remarks,  m_name, m_contexts) values
('九月份总结与十月份计划','1','无','小邦哥','暂时没有内容'),
('九月份总结与十月份计划','2','无','小邦哥','暂时没有内容'),
('九月份总结与十月份计划','4','无','小邦哥','暂时没有内容'),
('九月份总结与十月份计划','3','无','小邦哥','暂时没有内容');

