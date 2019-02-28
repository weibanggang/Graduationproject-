<style>
	.login{
	height: 300px;
    width: 450px;
    top: 100px;
    position: relative;
	border-radius: 30px;
    text-align: center;
	background: rgba(0,50,100,0.8);
	margin: auto;
	}
</style>
<template>
	<div style="height: 100%;background-image: url(http://47.100.245.30:8080/image/bj.jpg);background-size: 100% 100%;">
		<div style="height: 6%;"></div>
		<div class="login">
			<br />
			<br />
			<h1 style="color: white;">学生会后台系统</h1>
			<br />
			<Form style="width: 350px;margin: auto;" :model="user">
				<div :style="yhm">
					<FormItem>
						<Input type="text" v-model="user.name" :maxlength="15" placeholder="用户名" >
						<Icon type="ios-person-outline" slot="prepend"></Icon>
						</Input>
					</FormItem>
					<FormItem>
						<Input type="password" v-model="user.password" :maxlength="20" placeholder="密码">
						<Icon type="ios-lock-outline" slot="prepend"></Icon>
						123456</Input>
						<FormItem style="float: left;">
							<a href="#/index" @click="bdyz()">
								<Button type="text" onclick="return false" ghost>{{btn}}</Button>
							</a>
						</FormItem>
					</FormItem>
				</div>
				<div :style="yzm">
					<FormItem>
						<Input type="text" v-model="user.phone" :maxlength="11" placeholder="手机号码">
						<Icon type="md-phone-portrait" slot="prepend"></Icon>
						</Input>
					</FormItem>
					<FormItem>
						<Input type="text" v-model="user.yzm" :maxlength="6" placeholder="验证码">
						<Icon type="md-key" slot="prepend"></Icon>
						</Input>
						<FormItem style="float: right;bottom: 32px;position: relative; z-index: 999999;right: 10px;">
							<Button type="warning" size="small" :disabled="user.btn1" @click="bdyzm($event)" ghost>{{btnhqyzm}}</Button>
						</FormItem>
						<FormItem style="float: left;">
							<a href="#/index" @click="bdyz()">
								<Button type="text" onclick="return false" ghost>{{btn}}</Button>
							</a>
						</FormItem>
					</FormItem>
				</div>
				<FormItem>
					<a href="#/index" @click="handleSubmit()">
						<Button type="success" onclick="return false" long> 登 录 </Button>
					</a>
				</FormItem>
			</Form>
		</div>
		<div style="height:6%;"></div>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				btn: "用短信验证码登录",
				yzm: "display: none;",
				yhm: "display: block;",
				btnhqyzm: "获取验证码",
				user: {
					name: '',
					password: '',
					phone: '',
					yzm: '',
					btn1: false
				},
				code: 1024,
				url: "http://47.100.245.30:8080"
			}
		},
		methods: {
			bdyz() {
				if (this.btn == "用短信验证码登录") {
					this.btn = "用密码登录";
					this.yzm = "display: block;"
					this.yhm = "display: none;"
				} else {
					this.btn = "用短信验证码登录";
					this.yhm = "display: block;"
					this.yzm = "display: none;"
				}
			},
			handleSubmit() {
				if (this.btn == "用短信验证码登录") {
					if (this.user.name.length == 0 || this.user.name.trim() == "") {
						this.$Message.warning('请输入账号!');
						return false;
					}
					if (this.user.password.length == 0 || this.user.password.trim() == "") {
						this.$Message.warning('请输入密码!');
						return false;
					}
					var th = this;
					axios.get(th.url + '/login/auth', {
						params: {
							name: th.user.name,
							password: th.user.password
						}
					}).then(function(res) {
						th.bd(res);
					});
				} else {
					if (this.user.phone.length == 0 || this.user.phone.trim() == "") {
						this.$Message.warning('请输入手机号码!');
						return false;
					}
					if (this.user.yzm.length == 0 || this.user.yzm.trim() == "") {
						this.$Message.warning('请输入验证码!');
						return false;
					}
					if (this.user.yzm.length != 6) {
						this.$Message.warning('验证码不正确!');
						return false;
					}
					var th = this;
					axios.get(th.url + '/login/yzm', {
						params: {
							phone: th.user.phone,
							yzm: th.user.yzm
						}
					}).then(function(res) {
						th.bd(res);
					});
				}

				return false;
			},
			bd(res) {
			if (res.data.code == 1028) {
					this.$Message.success(res.data.message);
					localStorage.setItem('accessToken',res.data.data.sign);
					localStorage.setItem('mName',res.data.data.mName);
					localStorage.setItem('mUser',res.data.data.mUser);
					setTimeout(function() {
						window.location.href = "/#/index/";
					}, 900);
				} else {
					this.user.password = "";
					this.$Message.error(res.data.message);
				}
			},
			bdyzm(e) {
				var th = this;
				var jsdx = 60;
				if (this.user.phone.trim() == null || this.user.phone.length == 0) {
					this.$Message.warning("请输入手机号码!");
				} else {
					if (th.user.btn1) {
						return;
					}
					th.user.btn1 = false;
					axios.get(th.url + '/login/getyzm', {
						params: {
							phone: th.user.phone
						}
					}).then(function(res) {
						if (res.data.code == 1203) {
							th.$Message.error("手机号码无效!");
							return;
						}
						res = JSON.parse(res.data.data);
						if (res.result == 0) {
							th.$Message.success("发送成功!");
							var interval = setInterval(function() {
								th.btnhqyzm = "倒计" + jsdx-- + "s";
								if (jsdx == 0) {
									th.user.btn1 = false;
									th.btnhqyzm = "重新发送";
									th.disabled = "";
									clearInterval(interval);
								}
							}, 1000);
						} else {
							th.$Message.success("发送失败,请重试!");
						}
						th.user.btn1 = true;
					});
				}
			}

		}
	}
</script>
