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
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}
.layout-nav{
   right: 50px;
    position: absolute;
}
</style>

<template>
	<div class="layout">
		<Layout>
			<!--头部-->
			<Header>
				<Menu mode="horizontal" :theme="theme" active-name="6" :style="{position: 'fixed', width: '100%'}">
					<div class="layout-logo" style="left:-50px;top:0px;width:50%;text-align: center; ">
						<Menu mode="horizontal" @on-select="topfu" :accordion="true" :theme="theme" active-name="1">
							<div class="layout-logo" style="left:0;top:0;width: 200px;;text-align: center;padding-left: 6px;">
								<router-Link to="/index/">
									<MenuItem name="控制台">
									<h3>南方学生会后台系统</h3>
									</MenuItem>
								</router-Link>
							</div>

							<router-Link to="/index/">
								<MenuItem name="控制台">
								<Icon type="ios-paper" />
								控制台
								</MenuItem>
							</router-Link>

							<router-Link to="/index/noticSelect">
								<MenuItem name="工作安排">
								<Icon type="ios-people" />
								工作安排
								</MenuItem>
							</router-Link>
							<router-Link to="/index/workArrangementSelect">
								<MenuItem name="公告管理">
								<Icon type="ios-people" />
								公告管理
								</MenuItem>
							</router-Link>

						</Menu>
					</div>

					<div class="layout-nav">
						<MenuItem name="5">
						<Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
						<Dropdown>
							{{mName}}
							<Icon type="ios-arrow-down"></Icon>

							<DropdownMenu slot="list">
								<DropdownItem  @click.native="updatepassword">修改密码</DropdownItem>
							</DropdownMenu>
						</Dropdown>
						<Modal v-model="modal13" draggable scrollable title="修改密码" @on-ok="ok">
							<Form ref="formValidate" :model="user" :label-width="80">
								<FormItem label="旧密码" prop="password">
									<Input type="password" v-model="user.password" placeholder="请输入旧密码"></Input>
								</FormItem>
								<FormItem  label="新密码" prop="passwords">
									<Input type="password" v-model="user.passwords" placeholder="请输入新密码"></Input>
								</FormItem>
								<FormItem  label="确认密码" prop="passwordss">
									<Input type="password" v-model="user.passwordss" placeholder="请输入确认密码"></Input>
								</FormItem>
							</Form>
						</Modal>
						</MenuItem>
						<Submenu name="6">
							<template slot="title">
								<Icon type="ios-shirt-outline" />
								更换主题
							</template>
							<div style="background-color: #1b315e">
								<RadioGroup v-model="theme">
									<Radio label="dark">
										<Icon type="social-apple"></Icon>
										<span>黑色主题</span>
									</Radio>
									<Radio label="light">
										<Icon type="social-android"></Icon>
										<span>白色主题</span>
									</Radio>
									<Radio label="primary">
										<Icon type="social-windows"></Icon>
										<span>飘色主题</span>
									</Radio>
								</RadioGroup>
							</div>

						</Submenu>
							<MenuItem name="3" @click.native="logout">
							<Icon type="ios-exit-outline" />
							<span>注销</span>
							</MenuItem>
					</div>
				</Menu>
			</Header>
			<Layout>
				<!--左边-->
				<Sider hide-trigger :style="{background: '#fff'}">
					<Menu @on-open-change="fu" @on-select="ze" :accordion="true" active-name="1-2" :theme="theme" width="auto"
					 :open-names="['1']">
						<Submenu name="财务管理">
							<template slot="title">
								<Icon type="logo-yen" />
								财务管理
							</template>
							<router-Link to="/index/financialManagementSelect">
								<MenuItem name="余额查询">余额查询</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="记录情况">
							<template slot="title">
								<Icon type="ios-paper-outline" />
								记录情况
							</template>
							<router-Link to="/index/minutesOfTheMeetingSelect">
								<MenuItem name="会议记录">会议记录</MenuItem>
							</router-Link>
							<router-Link to="/index/monthlyPlanSummarySelect">
								<MenuItem name="月计划月总结">月计划月总结</MenuItem>
							</router-Link>
							<router-Link to="/index/leaveRecordSelect">
								<MenuItem name="请假记录">请假记录</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="出勤管理">
							<template slot="title">
								<Icon type="md-done-all"></Icon>
								出勤管理
							</template>
							<router-Link to="/index/attendanceSelect">
								<MenuItem name="出勤查询">出勤查询</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="评优管理">
							<template slot="title">
								<Icon type="ios-ribbon" />
								评优管理
							</template>
							<router-Link to="/index/personalMeritRecordSelect">
								<MenuItem name="个人评优">个人评优</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentalExcellenceRecordSelect">
								<MenuItem name="部门评优">部门评优</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="成员管理">
							<template slot="title">
								<Icon type="ios-people"></Icon>
								成员管理
							</template>
							<router-Link to="/index/memberInformationSelect">
								<MenuItem name="成员信息">成员信息</MenuItem>
							</router-Link>
							<router-Link to="/index/memberInformationselectEcharts">
								<MenuItem name="报表">报表</MenuItem>
							</router-Link>
						</Submenu>
						<Submenu name="其他管理">
							<template slot="title">
								<Icon type="ios-keypad"></Icon>
								其他管理
							</template>
							<router-Link to="/index/jurisdictionSelect">
								<MenuItem name="权限管理">权限管理</MenuItem>
							</router-Link>
							<router-Link to="/index/classTableSelect">
								<MenuItem name="班级管理">班级管理</MenuItem>
							</router-Link>
							<router-Link to="/index/typeOfMeetingSelect">
								<MenuItem name="会议类型">会议类型</MenuItem>
							</router-Link>
							<router-Link to="/index/exchangeTableSelect">
								<MenuItem name="会名管理">会名管理</MenuItem>
							</router-Link>
							<router-Link to="/index/departmentTypeSelect">
								<MenuItem name="部门管理">部门管理</MenuItem>
							</router-Link>
							<router-Link to="/index/positionTypeSelect">
								<MenuItem name="职位管理">职位管理</MenuItem>
							</router-Link>
						</Submenu>
					</Menu>
				</Sider>
				<!--右边-->
				<Layout>
					<!--导航栏-->
					<Breadcrumb :style="{margin: '5px'}">
						<span>
							<Icon type="ios-pin-outline" />
							当前位置：</span>
						<span>{{ parentTag }}</span>
						<span>{{ childTag }}</span>
						<span style="float: right; margin-right: 20px;">{{years}}</span>
					</Breadcrumb>
					<!--内容-->
					<Content :style="{padding: '0 25px', minHeight: '280px', background: '#fff'}">
						<router-view>

						</router-view>
					</Content>
				</Layout>
			</Layout>
		</Layout>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				url: "http://47.100.245.30:8080",
				years: "",
				mName:"小邦哥1",
				theme: "primary",
				parentTag: "控制台",
				modal13:false,
				childTag: "",
				user:{
					password:"",
					passwords:"",
					passwordss:""
				}
			}
		},
		methods: {
			logout(){
				var th = this;
				axios.get(th.url + '/login/logout').then(function(res) {
					if (res.data.code == 1028) {
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken",null);
						setTimeout(function() {
							window.location.href = "/";
						}, 900);
						
					}
				});
				
			},
			ok(){
				var th = this;
				if(th.user.password.length < 6){
					th.$Message.warning("密码最少为6位");
					return;
				}
				if(th.user.passwords != th.user.passwordss ){
					th.$Message.warning("两次密码不一致");
					return;
				}
				th.user.mUser = localStorage.getItem("mUser");
				axios.get(th.url + '/memberInformation/upassword', {
					params: {
						mUser:th.user.mUser,
						password:th.user.password,
						passwords:th.user.passwords
					}
				}).then(function(res) {
					if (res.data.code == 1028) {
						th.$Message.success(res.data.message);
						localStorage.setItem("accessToken",null);
						axios.get(th.url + '/login/logout');
						setTimeout(function() {
							window.location.href = "/";
						}, 500);
					}else{
						th.$Message.error(res.data.message);
					}
				})
			},
			updatepassword(){
				this.modal13 = true;
			},
			fu(e) {
				this.parentTag = e[0];
				this.childTag = "";
			},ze(e) {
				this.childTag = " > " + e;
			},
			topfu(e) {
				this.parentTag = e;
				this.childTag = "";
			},
		},
		created() {
			this.mName = localStorage.getItem("mName");
			var data = new Date();
			this.years = data.getFullYear() + "年" + (data.getMonth() + 1) + "月" + data.getDate() + "日";
		}
	}
</script>
