<style>
	.rigtop { width: 100%; left: 20; height: 45px;border: 1px solid #dcdee2;padding: 5px;line-height: 45px;margin:5px 0;}
</style>
<style scoped="scoped">
	
	.ivu-layout-header{
		z-index: 9999999;
}
.ivu-menu-horizontal,.layout-logo,.ivu-layout-header{
	height: 50px;
	line-height:50px; 
	top:0px;
}
	.layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
}
.layout-logo{
    width: 100px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}
.layout-nav{
    width: 430px;
    margin: 0 auto;
    margin-right: -20px;
}
</style>

<template>
	<div class="layout">
		<Layout>
			<!--头部-->
			<Header>
				<Menu mode="horizontal" theme="dark" active-name="1" :style="{position: 'fixed', width: '100%'}">
					<div class="layout-logo" style="left:-50px;font-size: 24px;top:0px;width:250px;text-align: center; color: rgba(255, 255, 255, 0.7);">学生会管理系统</div>
					<div class="layout-nav">
						<MenuItem name="1">
						<Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
						<Dropdown>
							<a href="javascript:void(0)">
								小邦哥
								<Icon type="ios-arrow-down"></Icon>
							</a>
							<DropdownMenu slot="list">
								<DropdownItem>安全设置</DropdownItem>
								<DropdownItem>修改密码</DropdownItem>
							</DropdownMenu>
						</Dropdown>
						</MenuItem>
						<Submenu name="2">
							<template slot="title">
								<Icon type="ios-shirt-outline" />
								皮肤
							</template>
							<MenuItem name="3-1">新增和启动</MenuItem>
							<MenuItem name="3-2">活跃分析</MenuItem>
							<MenuItem name="3-3">时段分析</MenuItem>

						</Submenu>
						<MenuItem name="3">
						<Icon type="ios-exit-outline" />
						注销
						</MenuItem>
					</div>
				</Menu>
			</Header>
			<Layout>
				<!--左边-->
				<Sider hide-trigger :style="{background: '#fff'}">
					<Menu active-name="1-2" theme="light" accordion width="auto" :open-names="['1']">
						<Submenu name="5">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								财务管理
							</template>
							<router-Link to="/financialManagementSelect">
								<MenuItem name="5-1">余额查询</MenuItem>
							</router-Link>
							<router-Link to="/financialManagementadd">
								<MenuItem name="5-2">支出收入</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="6">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								记录情况
							</template>
							<router-Link to="/minutesOfTheMeetingSelect">
								<MenuItem name="6-1">会议记录</MenuItem>
							</router-Link>
							<router-Link to="/monthlyPlanSummarySelect">
								<MenuItem name="6-2">月计划总结表</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="7">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								出勤管理
							</template>
							<router-Link to="/attendanceSelect">
								<MenuItem name="7-1">出勤查询</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="8">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								评优管理
							</template>
							<router-Link to="/personalMeritRecordSelect">
								<MenuItem name="8-1">个人评优</MenuItem>
							</router-Link>
							<router-Link to="/departmentalExcellenceRecordSelect">
								<MenuItem name="8-2">部门评优</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="9">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								请假管理
							</template>
							<router-Link to="/leaveRecordSelect">
								<MenuItem name="9-1">请假记录</MenuItem>
							</router-Link>
							<router-Link to="/leaveRecordAdd">
								<MenuItem name="9-2">添加请假</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="10">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								公告管理
							</template>
							<router-Link to="/noticSelect">
								<MenuItem name="10-1">查询公告</MenuItem>
							</router-Link>
							<router-Link to="/noticAdd">
								<MenuItem name="10-2">添加公告</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="11">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								工作安排
							</template>
							<router-Link to="/workArrangementSelect">
								<MenuItem name="11-1">查询工作</MenuItem>
							</router-Link>
							<router-Link to="/workArrangementAdd">
								<MenuItem name="11-2">安排工作</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="12">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								成员管理
							</template>
							<router-Link to="/memberInformationSelect">
								<MenuItem name="12-1">成员信息</MenuItem>
							</router-Link>
							<router-Link to="/memberInformationAdd">
								<MenuItem name="12-2">添加成员</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="13">
							<template slot="title">
								<Icon type="ios-keypad"></Icon>
								其他管理
							</template>
							<router-Link to="/jurisdictionSelect">
								<MenuItem name="13-1">权限管理</MenuItem>
							</router-Link>
							<router-Link to="/classTableSelect">
								<MenuItem name="13-2">班级管理</MenuItem>
							</router-Link>
							<router-Link to="/typeOfMeetingSelect">
								<MenuItem name="13-3">会议类型</MenuItem>
							</router-Link>
							</router-Link>
							<router-Link to="/exchangeTableSelect">
								<MenuItem name="13-4">会名管理</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="14">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								部门职位
							</template>
							<router-Link to="/departmentTypeSelect">
								<MenuItem name="14-1">部门信息</MenuItem>
							</router-Link>
							<router-Link to="/departmentTypeAdd">
								<MenuItem name="14-2">添加部门</MenuItem>
							</router-Link>
							<router-Link to="/positionTypeSelect">
								<MenuItem name="14-3">职位信息</MenuItem>
							</router-Link>
							<router-Link to="/positionTypeAdd">
								<MenuItem name="14-4">添加职位</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="15">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								管理员
							</template>
							<router-Link to="/adminSelect">
								<MenuItem name="15-1">查询信息</MenuItem>
							</router-Link>
							<router-Link to="/adminAdd">
								<MenuItem name="15-2">添加信息</MenuItem>
							</router-Link>
						</Submenu>
					</Menu>
					<!-- <Menu :theme="light" active-name="1">
						<MenuGroup title="财务管理">
							<router-Link to="/financialManagementSelect">
								<MenuItem name="1">
								<Icon type="md-document" />
								余额查询
								</MenuItem>
							</router-Link>
							<router-Link to="/financialManagementadd">
								<MenuItem name="2">
								<Icon type="md-chatbubbles" />
								支出收入
								</MenuItem>
							</router-Link>
						</MenuGroup>
						<MenuGroup title="记录情况">
							<router-Link to="/minutesOfTheMeetingSelect">
								<MenuItem name="3">
								<Icon type="md-document" />
								会议记录
								</MenuItem>
							</router-Link>
							<router-Link to="/monthlyPlanSummarySelect">
								<MenuItem name="4">
								<Icon type="md-chatbubbles" />
								月计划总结表
								</MenuItem>
							</router-Link>
						</MenuGroup>
						<MenuGroup title="出勤管理">
							<router-Link to="/attendanceSelect">
								<MenuItem name="4">
								<Icon type="md-document" />
								出勤查询
								</MenuItem>
							</router-Link>
						</MenuGroup>
						<MenuGroup title="评优管理">
							<router-Link to="/personalMeritRecordSelect">
								<MenuItem name="5">
								<Icon type="md-document" />
								个人评优
								</MenuItem>
							</router-Link>
							<router-Link to="/departmentalExcellenceRecordSelect">
								<MenuItem name="6">
								<Icon type="md-chatbubbles" />
								部门评优
								</MenuItem>
							</router-Link>
						</MenuGroup>
						<MenuGroup title="请假管理">
							<router-Link to="/leaveRecordSelect">
								<MenuItem name="7">
								<Icon type="md-document" />
								请假记录
								</MenuItem>
							</router-Link>
							<router-Link to="/leaveRecordAdd">
								<MenuItem name="8">
								<Icon type="md-chatbubbles" />
								添加请假
								</MenuItem>
							</router-Link>
						</MenuGroup>
						<MenuGroup title="公告管理">
							<router-Link to="/noticSelect">
								<MenuItem name="9">
								<Icon type="md-document" />
								查询公告
								</MenuItem>
							</router-Link>
							<router-Link to="/noticAdd">
								<MenuItem name="10">
								<Icon type="md-chatbubbles" />
								添加公告
								</MenuItem>
							</router-Link>
						</MenuGroup>
						
					</Menu> -->
				</Sider>
				<!--右边-->
				<Layout>
					<!--导航栏-->
					<Breadcrumb :style="{margin: '10px 10px'}">
						<b>位置：</b>首页 > 财务管理 > 查询余额
					</Breadcrumb>
					<!--内容-->
					<Content :style="{padding: '0 25px', minHeight: '280px', background: '#fff'}">
						<router-view></router-view>
					</Content>
				</Layout>
			</Layout>
		</Layout>

	</div>
</template>

<script>
	export default {
		components: {

		}
	}
</script>
