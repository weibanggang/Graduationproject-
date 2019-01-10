
<template>
	<div style="height: 100%;background-image: url(http://localhost:8080/image/bj.jpg);background-size: 100% 100%;">
		<div style="height: 6%;"></div>
		<div style="height: 88%;text-align: center;margin: auto;">
			<Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
				<FormItem prop="user">
					<Input type="text" v-model="formInline.user" placeholder="Username">
					<Icon type="ios-person-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem prop="password">
					<Input type="password" v-model="formInline.password" placeholder="Password">
					<Icon type="ios-lock-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem>
					<router-link :to="{path:'/index'}" >
					<Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
					</router-link>
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
                formInline: {
                    user: '',
                    password: ''
                },
                ruleInline: {
                    user: [
                        { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                        { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            handleSubmit(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success('Success!');
                    } else {
                        this.$Message.error('Fail!');
                    }
                })
            }
        }
    }
</script>
