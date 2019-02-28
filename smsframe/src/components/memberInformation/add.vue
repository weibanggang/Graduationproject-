<template>
	<Form ref="memberInformation" :model="memberInformation" :rules="ruleValidate" :label-width="80">
		<FormItem label="工作编号" prop="mUser">
			<Input v-model="memberInformation.mUser" placeholder="工作编号"></Input>
		</FormItem>
		<FormItem label="姓名" prop="mName">
			<Input v-model="memberInformation.mName" placeholder="姓名"></Input>
		</FormItem>
		<FormItem label="性别" >
			<i-switch size="large" v-model="sex" @on-change='off($event)'>
				<span slot="open">男</span>
				<span slot="close">女</span>
			</i-switch>
		</FormItem>
		<FormItem label="联系方式" prop="cPhone">
			<Input v-model="memberInformation.cPhone" placeholder="联系方式"></Input>
		</FormItem>
		<FormItem label="QQ" prop="mQq">
			<Input v-model="memberInformation.mQq" placeholder="QQ"></Input>
		</FormItem>
		<FormItem label="部门" prop="dId">
			<Select v-model="memberInformation.dId" placeholder="请选择部门" >
			<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
			</Select>
		</FormItem>
		<FormItem label="职位" prop="pId">
			<Select v-model="memberInformation.pId" placeholder="请选择职位" >
				<Option v-for="item in positionType" :value="item.pId" :key="item.pId">{{ item.pName }}</Option>
			</Select>
		</FormItem>
		<FormItem label="所属届" prop="eId">
			<Select v-model="memberInformation.eId" placeholder="请选择所属届" >
			<Option v-for="item in exchangeTable" :value="item.eId" :key="item.eId">{{ item.eName }}</Option>
			</Select>
		</FormItem>
		<FormItem label="班级" prop="cId">
			<Select v-model="memberInformation.cId" placeholder="请选择班级" >
				<Option v-for="item in classTable" :value="item.cId" :key="item.cId">{{ item.cName }}</Option>
			</Select>
		</FormItem>
		<FormItem label="权限" prop="jId">
			<Select v-model="memberInformation.jId" placeholder="请选择权限" >>
				<Option v-for="item in jurisdiction" :value="item.jId" :key="item.pId">{{ item.jName }}</Option>
			</Select>
		</FormItem>

		<FormItem label="入会时间">
			<Row>
				<Col span="11">
				<FormItem prop="rAdmissionDate">
					<DatePicker type="date" placeholder="请选择入会时间" v-model="memberInformation.rAdmissionDate"></DatePicker>
				</FormItem>
				</Col>
			</Row>
		</FormItem>
		<FormItem label="状态">
			<i-switch size="large" v-model="memberInformation.status">
				<span slot="open">正常</span>
				<span slot="close">冻结</span>
			</i-switch>
		</FormItem>

		<FormItem label="备注" prop="pRemarks">
			<Input v-model="memberInformation.pRemarks" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="备注"></Input>
		</FormItem>
		<FormItem>
			<Button type="primary" @click="handleSubmit('memberInformation')">录入</Button>
			<Button @click="handleReset('memberInformation')" style="margin-left: 8px">重置</Button>
		</FormItem>
	</Form>
</template>
<script>
	export default {
		data() {
			return {
				url: 'http://47.100.245.30:8080/',
				sex:true,
				memberInformation: {
					mId: '',
					mUser: '',
					mPassword: '686868',
					mName: '',
					mSex: '男',
					cId: '',
					cPhone: '',
					dId: '',
					pPhoto: '',
					eId: '',
					jId: '',
					mQq: '',
					status: true,
					rAdmissionDate: '',
					pRemarks: '',
				},
				departmentType: '',
				classTable: '',
				positionType: '',
				exchangeTable: '',
				jurisdiction: '',
				ruleValidate: {
					mUser: [{
						required: true,
						message: '请输入编号',
						trigger: 'blur'
					}],mName: [{
						required: true,
						message: '请输入姓名',
						trigger: 'blur'
					}],
					dId: [{
						required: true,
						message: '请选择部门',
						trigger: 'change'
					}],
					pId: [{
						required: true,
						message: '请选择职位',
						trigger: 'change'
					}],
					jId: [{
						required: true,
						message: '请选择权限',
						trigger: 'change'
					}],
					eId: [{
						required: true,
						message: '请选择所属届',
						trigger: 'change'
					}],
					cId: [{
						required: true,
						message: '请选择班级',
						trigger: 'change'
					}],
					rAdmissionDate: [{
						required: true,
						type: 'date',
						message: '请选择时间',
						trigger: 'change'
					}],
				
				}
			}
		},
		methods: {
			handleSubmit(name) {
				
				this.$refs[name].validate((valid) => {
					/* if (valid) {
						this.$Message.success('Success!');
					} else {
						this.$Message.error('Fail!');
					} */
					const th=this;
					axios.post(th.url+'/memberInformation/insert',th.memberInformation,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.Admin.aUsername="";
							th.Admin.aPassword="";
							th.Admin.aRemarks="";
						}else{
							th.$Message.error(res.data.message);
						}
					})
				})
			},
			off(value){
				this.memberInformation.mSex=value==true?"男":"女";
			},
			handleReset(name) {
				this.$refs[name].resetFields();
			},
			//查询部门类型
			selectAdd() {
				const th = this;
				axios.get(th.url + 'departmentType/iSelectAllStatus').then(function(res) {
					th.departmentType = res.data.data;
					console.log(res.data);
				})
				axios.get(th.url + 'classTable/iSelectAllStatus').then(function(res) {
					th.classTable = res.data.data;
					console.log(res.data);
				})
				axios.get(th.url + 'positionType/iSelectAllStatus').then(function(res) {
					th.positionType = res.data.data;
					console.log(res.data);
				})
				axios.get(th.url + 'exchangeTable/iSelectAllStatus').then(function(res) {
					th.exchangeTable = res.data.data;
					console.log(res.data);
				})
				axios.get(th.url + 'jurisdiction/iSelectAllStatus').then(function(res) {
					th.jurisdiction = res.data.data;
					console.log(res.data);
				})
			}
		},
		created() {
			this.selectAdd()
		}
	}
</script>
