<style>
	.rigtop { width: 100%; left: 20; height: 45px;border: 1px solid #dcdee2;padding: 5px;line-height: 45px;margin:5px 0;}
	.rigtop form{
		height: 35px;
	}
	.rigtop .ivu-select-selection,.ivu-select-input, .rigtop .ivu-input{
		height: 28px;
	}
	.rigtop .ivu-btn {
		padding: 4px 5px 4px;
	}
</style>
<style scoped="scoped">

	.ivu-layout-header{
		z-index: 999;
}
.ivu-menu-horizontal,.layout-logo,.ivu-layout-header{
	height: 50px;
	line-height:50px; 
	top:0px;
}
.ivu-menu-vertical{
	height: 607px;
}
.layout{
    background: #f5f7f9;
    position: relative;
    overflow: hidden;
}
.layout-logo{
    width: 100px;
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
				<Menu mode="horizontal" :theme="theme" active-name="6" :style="{position: 'fixed', width: '100%'}">
					<div class="layout-logo" style="left:-50px;top:0px;width:200px;text-align: center;background:#515a6e ">
						<img src="http://localhost:8080/image/logos.png" style="width: 100%; height: 100%;" />
					</div>
					<div class="layout-logo" style="left:-50px;top:0px;width:50%;text-align: center;background:#515a6e ">
						<Menu mode="horizontal" :theme="theme" active-name="1">
							
							<MenuItem name="1">
							<Icon type="ios-paper" />
							控制台
							</MenuItem>
							
							<router-Link to="/index/adminSelect">
							<MenuItem name="2">
							<Icon type="ios-people" />
							工作安排
							</MenuItem>
							</router-Link>
							<router-Link to="/index/workArrangementSelect">
							<MenuItem name="2">
							<Icon type="ios-people" />
							公告管理
							</MenuItem>
							</router-Link>
							<router-Link to="/index/workArrangementSelect">
							<MenuItem name="2">
							<Icon type="ios-people" />
							管理员
							</MenuItem>
							</router-Link>
							
						</Menu>
					</div>


					<div class="layout-nav">
						<MenuItem name="5">
						<Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
						<Dropdown>
							小邦哥
							<Icon type="ios-arrow-down"></Icon>

							<DropdownMenu slot="list">
								<DropdownItem>安全设置</DropdownItem>
								<DropdownItem>修改密码</DropdownItem>
							</DropdownMenu>
						</Dropdown>
						</MenuItem>
						<Submenu name="6">
							<template slot="title">
								<Icon type="ios-shirt-outline" />
								皮肤
							</template>
							<MenuItem name="3-1">新增和启动</MenuItem>
							<MenuItem name="3-2">活跃分析</MenuItem>
							<MenuItem name="3-3">时段分析</MenuItem>

						</Submenu>
						<router-link to="/">
							<MenuItem name="3">
							<Icon type="ios-exit-outline" />
							注销
							</MenuItem>
						</router-link>
					</div>
				</Menu>
			</Header>
			<Layout>
				<!--左边-->
				<Sider hide-trigger :style="{background: '#fff'}">
					<Menu active-name="1-2" :theme="theme" accordion width="auto" :open-names="['1']">
						<Submenu name="5">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								财务管理
							</template>
							<router-Link to="/index/financialManagementSelect">
								<MenuItem name="5-1">余额查询</MenuItem>
							</router-Link>
							<router-Link to="/index/financialManagementadd">
								<MenuItem name="5-2">支出收入</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="6">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								记录情况
							</template>
							<router-Link to="/index/minutesOfTheMeetingSelect">
								<MenuItem name="6-1">会议记录</MenuItem>
							</router-Link>
							<router-Link to="/index/monthlyPlanSummarySelect">
								<MenuItem name="6-2">月计划月总结</MenuItem>
							</router-Link>
							<router-Link to="/index/leaveRecordSelect">
								<MenuItem name="6-3">请假记录</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="7">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								出勤管理
							</template>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="7-1">出勤查询</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="8">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								评优管理
							</template>
							<router-Link to="/index/personalMeritRecordSelect">
								<MenuItem name="8-1">个人评优</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="8-2">部门评优</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="12">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								成员管理
							</template>
							<router-Link to="/index/memberInformationSelect">
								<MenuItem name="12-1">成员信息</MenuItem>
							</router-Link>
							<router-Link to="/index/memberInformationAdd">
								<MenuItem name="12-2">添加成员</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="13">
							<template slot="title">
								<Icon type="ios-keypad"></Icon>
								其他管理
							</template>
							<router-Link to="/index/jurisdictionSelect">
								<MenuItem name="13-1">权限管理</MenuItem>
							</router-Link>
							<router-Link to="/index/classTableSelect">
								<MenuItem name="13-2">班级管理</MenuItem>
							</router-Link>
							<router-Link to="/index/typeOfMeetingSelect">
								<MenuItem name="13-3">会议类型</MenuItem>
							</router-Link>
							<router-Link to="/index/exchangeTableSelect">
								<MenuItem name="13-4">会名管理</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentTypeSelect">
								<MenuItem name="13-5">部门管理</MenuItem>
							</router-Link>
							<router-Link to="/index/positionTypeSelect">
								<MenuItem name="13-6">职位管理</MenuItem>
							</router-Link>
						</Submenu>
					</Menu>
				</Sider>
				<!--右边-->
				<Layout>
					<!--导航栏-->
					<Breadcrumb :style="{margin: '5px'}">
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
		data () {
					 return {
						 theme:"dark",
					 }
		}
	}
</script>
