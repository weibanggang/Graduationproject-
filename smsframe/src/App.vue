<style scoped>
	.layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
}
.layout-logo{
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}
.layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
}
</style>
<template>
	<div class="layout">
		<Layout>
			<!--头部-->
			<Header>
				<Menu mode="horizontal" theme="dark" active-name="1">
					<div class="layout-logo"></div>
					<div class="layout-nav">
						<MenuItem name="1">
						<Icon type="ios-navigate"></Icon>
						Item 1
						</MenuItem>
						<MenuItem name="2">
						<Icon type="ios-keypad"></Icon>
						Item 2
						</MenuItem>
						<MenuItem name="3">
						<Icon type="ios-analytics"></Icon>
						Item 3
						</MenuItem>
						<MenuItem name="4">
						<Icon type="ios-paper"></Icon>
						Item 4
						</MenuItem>
					</div>
				</Menu>
			</Header>
			<Layout>
				<!--左边-->
				<Sider hide-trigger :style="{background: '#fff'}">
					<Menu active-name="1-2" theme="light" accordion width="auto" :open-names="['1']">
						<Submenu name="1">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								工作安排
							</template>
						</Submenu>
						<Submenu name="1">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								成员管理
							</template>
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
							<MenuItem name="11-1" >查询工作</MenuItem>
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
				</Sider>
				<!--右边-->
				<Layout :style="{padding: '0 24px 24px'}">
					<!--导航栏-->
					<Breadcrumb :style="{margin: '24px 0'}">
						<BreadcrumbItem>管理员</BreadcrumbItem>
						<BreadcrumbItem>查询信息</BreadcrumbItem>
					</Breadcrumb>
					<!--内容-->
					<Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
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
