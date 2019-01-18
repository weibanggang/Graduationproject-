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
	<div style="height: 100%;background-image: url(http://localhost:8080/image/bj.jpg);background-size: 100% 100%;">
		<div style="height: 6%;"></div>
		<div  class="login">
			
			<Form style="width: 350px;margin: auto;" :model="admin" >
				<FormItem prop="user">
					<br/>
					<h1>南方学生会登录系统</h1>
				</FormItem>
				<FormItem prop="用户名">
					<Input type="text" v-model="admin.user" placeholder="用户名">
					<Icon type="ios-person-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem prop="密码">
					<Input type="password" v-model="admin.password" placeholder="密码">
					<Icon type="ios-lock-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem prop="验证码">
					<Row>
        <Col span="12"><Input type="text"  placeholder="手机号码"/></Col>
        <Col span="12"><Button type="success" onclick="return false" ghost> 发 送  </Button></Col>
		</Row>
					
				</FormItem>
				<FormItem>
					<a href="#/index" @click="handleSubmit()" > 
						<Button type="success" onclick="return false" ghost> 登 录  </Button>
					</a>
				</FormItem>
			</Form>
		</div>
		<div style="height:6%;"></div>
	</div>
</template>
<script>
    export default {
        data () {
            return {
                admin: {
                    user: '',
                    password: ''
                },
				code:1024,
				url: "http://localhost:8080"
            }
        },
        methods: {
            handleSubmit() {
				if(this.admin.user.length ==0 || this.admin.user.trim() == ""){
					 this.$Message.warning('请输入账号');
					 return false;
				}if(this.admin.password.length ==0 || this.admin.password.trim() == ""){
					 this.$Message.warning('请输入密码');
					 return false;
				}
				var th = this;
				axios.get(th.url + '/admin/index', {
					params: {
						aUsername:th.admin.user,
						aPassword:th.admin.password
					}
				}).then(function(res) {
					th.bd(res);
					if(res.data.code == 1028){
						setTimeout(function(){
							window.location.href="/#/index/";
						},900);
					}
				});
				return false;
            },
			bd(res){
				if(res.data.code == 1028){
					 this.$Message.success(res.data.message);
				}else{
					this.admin.password = "";
					this.$Message.error(res.data.message);
				}
				 
			}
			
        }
    }
</script>
