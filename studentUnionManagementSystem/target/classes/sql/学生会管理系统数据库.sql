drop database StudentUnionManagementSystem;
create database StudentUnionManagementSystem;
use StudentUnionManagementSystem;
#1备份表
create table backups(
b_id int auto_increment primary key comment '编号',
b_beforedate datetime default(now()) comment '备份时间',
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
#2管理员
create table Admin(
a_id int auto_increment primary key comment '编号',
a_username varchar(20) unique comment '账号',
a_password varchar(256) comment '密码',
status varchar(10) comment '状态',
a_remarks varchar(256) comment '备注'
);
insert into Admin(a_username,a_password,status,a_remarks) values
('123456','123456','true','账号密码都为123456'),
('456789','456789','true','账号密码都为456789'),
('123321','123321','true','账号密码都为123321'),
('11111','11111','false','账号密码都为11111'),
('22222','22222','false','账号密码都为22222');
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
#4权限
create table Jurisdiction(
j_id int primary key auto_increment comment '编号',
j_name varchar(20) comment '权限名称',
status varchar(10) comment '状态',
j_remarks varchar(256) comment '权限备注'
);
insert into Jurisdiction(j_name,status,j_remarks)values
('高级管理','true','该管理可控制所有系统'),
('中级管理','true','部长使用'),
('普通管理','true','普通部员使用');
#5班级信息
create table ClassTable(
c_id int auto_increment primary key comment '编号',
c_name varchar(20) comment '班级名称',
c_headmaster_name varchar(20) comment '班主任名字',
c_phone varchar(11) comment '联系电话',
status varchar(10) comment '状态'
);
insert into ClassTable(c_name,c_headmaster_name,c_phone,status)values
('s1s143','张婷','15348564582','false'),
('s2s143','张婷','15348564582','false'),
('s3s143','张婷','15348564582','false'),
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
c_phone  varchar(11) comment '成员手机',
d_id int comment '成员部门',
p_id int comment '成员职位',
p_photo varchar(256) comment '图片路径',
e_id int comment '第几届',
j_id int comment '权限',
m_qq varchar(15) comment '成员qq',
status varchar(10) comment '状态',
r_admission_date date  comment '入会时间',
r_tuihui_date date  comment '退会时间',
p_remarks  longtext comment '备注',
foreign key(c_id) references  ClassTable(c_id),
foreign key(d_id)  references DepartmentType(d_id),
foreign key(p_id)   references PositionType(p_id),
foreign key(e_id)   references ExchangeTable(e_id),
foreign key(j_id)   references Jurisdiction(j_id)
);
insert into MemberInformation(m_user,m_password,m_name,m_sex,c_id,c_phone,d_id,p_id,p_photo,e_id,j_id,m_qq,status,r_admission_date,r_tuihui_date,p_remarks)values
('100010001','123456','小邦哥','男',1,'18776203778',1,4,'',2,1,'761273133','true','2018-01-01','2018-10-10',''),
('100010002','123456','廖珠炫','男',1,'18776235778',3,3,'',2,1,'861273133','true','2018-01-01','2018-10-10',''),
('100010003','123456','朱茂深','男',1,'18776553778',3,4,'',2,2,'361273133','true','2018-01-01','2018-10-10',''),
('100010004','123456','彭金华','男',1,'18346203778',2,3,'',2,3,'111273133','true','2018-01-01','2018-10-10',''),
('100010005','123456','钟世杨','男',1,'19976203778',1,4,'',2,1,'761173133','true','2018-01-01','2018-10-10',''),
('100010006','123456','邹雅','女',1,'18766203778',1,2,'',2,1,'721271133','true','2018-01-01','2018-10-10',''),
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
n_date datetime default(now())  comment '发布时间',
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
m_id int comment '成员编号',
p_title varchar(30) comment '主题',
p_date datetime comment '时间',
p_contexts longtext comment '内容',
p_file  varchar(256) comment '文件路径',
p_m_id int comment '操作人',
foreign key(m_id) references  MemberInformation(m_id),
foreign key(p_m_id) references MemberInformation(m_id)
);
#14部门评优记录
create table DepartmentalExcellenceRecord(
d_id int auto_increment primary key comment'编号',
d_d_id int comment '部门编号',
d_title varchar(50) comment '标题',
d_date date comment '时间',
d_contexts longtext comment '内容',
d_file varchar(256) comment '文件路径',
m_id int comment '操作人',
foreign key(d_id) references DepartmentType(d_id),
foreign key(m_id) references  MemberInformation(m_id)
);
#15财务管理
create table FinancialManagement(
f_id int auto_increment primary key comment'编号',
f_type varchar(5) comment '交易类型',
f_front_money decimal(10,2) comment '交易前余额',
f_after_money decimal(10,2) comment '交易后余额',
f_money decimal(10,2) comment '交易余额',
f_date date  comment '时间',
f_mr_date  datetime default(now()) comment '操作时间',
m_id int comment '操作人',
f_remarks  varchar(256) comment '备注',
f_file  varchar(256) comment '文件路径',
foreign key(m_id) references MemberInformation(m_id)
);
#16出勤情况
create table  Attendance(
a_id int auto_increment primary key comment'编号',
a_type varchar(20) comment '类型',
m_user int comment '成员工作编号',
d_name varchar(20) comment '成员部门',
a_titile  varchar(50) comment '标题',
a_date datetime comment '时间',
a_m_id int comment '操作人',
a_remaks varchar(256) comment '备注',
foreign key(a_m_id) references MemberInformation(m_id)
);
#17会议记录
create table MinutesOfTheMeeting(
m_id int auto_increment primary key comment'编号',
m_title  varchar(50) comment '标题',
t_id int comment '会议类型',
m_date  datetime default(now()) comment '上传时间',
m_file   varchar(256) comment '文件路径',
m_m_id int comment '操作人',
m_contexts varchar(256) comment '内容',
foreign key(m_m_id) references MemberInformation(m_id),
foreign key(t_id) references TypeOfMeeting(t_id)
);
#18月计划总结表
create table MonthlyPlanSummary(
m_id int auto_increment primary key comment'编号',
m_title varchar(50) comment '标题',
d_id int comment '部门编号',
m_date datetime default(now()) comment '上传时间',
m_file  varchar(256) comment '文件路径',
m_remarks varchar(256) comment '备注',
m_m_id int comment '操作人',
m_contexts longtext comment '内容',
foreign key(m_m_id) references MemberInformation(m_id),
foreign key(d_id) references DepartmentType(d_id)
);

