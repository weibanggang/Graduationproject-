<style scoped="scoped">
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
	.rigtop{
		height:80px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="mName" label="">模糊姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="memberInformation.mName" placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						<Checkbox v-model="dName" label="">部门</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="memberInformation.dId" filterable>
							<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						<Checkbox v-model="pName" label="">职位</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="memberInformation.pId" filterable>
							<Option v-for="item in positionType" :value="item.pId" :key="item.pId">{{ item.pName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Button @click="select(1)">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />新增信息
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>

			</Form>
			<Form inline>

				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="eName" label="">所属届</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="memberInformation.eId" filterable>
							<Option v-for="item in exchangeTable" :value="item.eId" :key="item.eId">{{ item.eName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="cName" label="">班级</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="memberInformation.cId" filterable>
							<Option v-for="item in classTable" :value="item.cId" :key="item.cId">{{ item.cName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					状态 :
					<RadioGroup v-model="status">
						<Radio label="true">
							<Icon type="ios-eye" />
							<span>正常</span>
						</Radio>
						<Radio label="false">
							<Icon type="ios-eye-off" />
							<span>退会</span>
						</Radio>
						<Radio label="all">
							<Icon type="ios-football-outline" />
							<span>全部</span>
						</Radio>
					</RadioGroup>
				</FormItem>
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="420" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="select($event)"></Page>
			</div>
		</div>

		<Modal v-model="modal13" fullscreen :title="title" @on-ok="ok">
			<Form :model="memberInformation" :label-width="80" inline>
				<div style="width:70%;position:relative;float: left;">
					<Row>
						<Col span="8">
						<FormItem label="姓名" prop="mName">
							<Input v-model="memberInformation.mName" placeholder="姓名"></Input>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="工作编号" prop="mUser">
							<Input v-model="memberInformation.mUser" placeholder="工作编号"></Input>
						</FormItem>
						</Col>
						<Col span="8">

						<FormItem label="职位">
							<Select v-model="memberInformation.pId" placeholder="请选择职位">
								<Option v-for="item in positionType" :value="item.pId" :key="item.pId">{{ item.pName }}</Option>
							</Select>
						</FormItem>
						</Col>
					</Row>
					<Row>
						<Col span="8">
						<FormItem label="联系方式" prop="cPhone">
							<Input v-model="memberInformation.cPhone" placeholder="联系方式"></Input>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="QQ" prop="mQq">
							<Input v-model="memberInformation.mQq" placeholder="QQ"></Input>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="部门" prop="dId">
							<Select v-model="memberInformation.dId" placeholder="请选择部门">
								<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
							</Select>
						</FormItem>
						</Col>
					</Row>

					<Row>
						<Col span="8">
						<FormItem label="性别">
							<i-switch size="large" v-model="sex" @on-change='off($event)'>
								<span slot="open">男</span>
								<span slot="close">女</span>
							</i-switch>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="所属届" prop="eId">
							<Select v-model="memberInformation.eId" placeholder="请选择所属届">
								<Option v-for="item in exchangeTable" :value="item.eId" :key="item.eId">{{ item.eName }}</Option>
							</Select>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="班级" prop="cId">
							<Select v-model="memberInformation.cId" placeholder="请选择班级">
								<Option v-for="item in classTable" :value="item.cId" :key="item.cId">{{ item.cName }}</Option>
							</Select>
						</FormItem>
						</Col>
					</Row>

					<Row>
						<Col span="8">
						<FormItem label="角色" prop="rId">
							<Select v-model="memberInformation.rId" placeholder="请      选      择     角     色">
								<Option v-for="item in roles" :value="item.rId" :key="item.rId">{{ item.rName }}</Option>
							</Select>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="入会时间">
							<Row>
								<Col span="21">
								<FormItem>
									<DatePicker type="date" placeholder="请选择入会时间" @on-change="getStartTime" v-model="memberInformation.rAdmissionDate"></DatePicker>
								</FormItem>
								</Col>
							</Row>
						</FormItem>
						</Col>
						<Col span="8">
						<FormItem label="状态">
							<i-switch size="large" v-model="memberInformation.status">
								<span slot="open">正常</span>
								<span slot="close">退会</span>
							</i-switch>
						</FormItem>
						</Col>
					</Row>
					<Row>
						<FormItem label="备注">
							<Input v-model="memberInformation.pRemarks" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="备注"></Input>
						</FormItem>
					</Row>
				</div>
				<div style="width: 28%;position:relative;float: left;margin: auto; text-align: center;">
					<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/memberInformation">
						<p><img :src="memberInformation.pPhoto" onerror="this.src='http://47.100.245.30:8080/image/tx.png'" id="myCanvas"
							 width="200" height="250" style="border:1px solid #d3d3d3;" /></p>
						<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
					</Upload>
				</div>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				title: "编辑部门类型",
				url: "http://47.100.245.30:8080",
				count: 10,
				sex: true,
				interface:"",
				mName: false,
				dName: false,
				cName: false,
				pName: false,
				eName: false,
				status: "true",
				modal13: false,
				loading: true,
				departmentType: '',
				classTable: '',
				positionType: '',
				exchangeTable: '',
				roles: '',
				columns7: [{
						title: '姓名',
						key: 'mName',
						width: 120,
						fixed: 'left',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.mName)
							]);
						}
					},
					{
						title: '成员工号',
						key: 'mUser',
						align: 'center',
						width: 120,
					},
					{
						title: '性别',
						key: 'mSex',
						align: 'center',
						width: 80,
					},
					{
						title: '联系方式',
						key: 'cPhone',
						align: 'center',
						width: 120,
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center',
						width: 100,
					}, {
						title: '职位',
						key: 'pName',
						align: 'center',
						width: 100,
					},
					{
						title: '所属届',
						key: 'eName',
						align: 'center',
						width: 150,
					},
					{
						title: '角色',
						key: 'rName',
						align: 'center',
						width: 100,
					},
					{
						title: '班级',
						key: 'cName',
						align: 'center',
						width: 100,
					}, {
						title: '班主任',
						key: 'cHeadmasterName',
						align: 'center',
						width: 100,
					}, {
						title: '班主任联系方式',
						key: 'cPhone',
						align: 'center',
						width: 150,
					},
					{
						title: 'QQ',
						key: 'mQq',
						align: 'center',
						width: 120,
					},
					{
						title: '入会时间',
						key: 'rAdmissionDate',
						align: 'center',
						width: 120,
					},
					{
						title: '退会时间',
						key: 'rTuihuiDate',
						align: 'center',
						width: 120,
					},
					{
						title: '备注',
						key: 'pRemarks',
						width: 250,
						align: 'center',
						tooltip: true
					},
					{
						title: '操作',
						key: 'action',
						width: 150,
						fixed: 'right',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'primary',
										size: 'small'
									},
									style: {
										marginRight: '5px'
									},
									on: {
										click: () => {
											
											this.show(params.row.mId);
										}
									}
								}, '编辑'),
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {
											this.remove(params.row.dId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}, {
						title: '状态',
						key: 'status',
						align: 'center',
						fixed: 'right',
						width: 100,
						render: (h, params) => {
							//return h('元素',{元素的性质},'内容')
							return h('i-switch', {
									props: {
										size: "large",
										value: params.row.status == "true"
									},
									on: {
										'on-change': (value) => {
											params.row.status = value;
											const th = this;
											axios.get(th.url + '/memberInformation/updateStatus', {
												params: {
													dId: params.row.mId,
													status: value
												}
											}).then(function(res) {
												if (res.data.code === 1028) {
													th.$Message.success(res.data.message);
												} else {
													th.$Message.error(res.data.message);
													th.changePage(1);
												}
											})

										}
									}
								},
								[h('span', {
										slot: "open",
										domProps: {
											innerHTML: '正常'
										}
									}),
									h('span', {
										slot: "close",
										domProps: {
											innerHTML: '退会'
										}
									}),
								]
							)
						}

					}
				],
				data6: [],
				memberInformation: {
					mId: 0,
					mUser: '',
					mPassword: '686868',
					mName: '',
					mSex: '男',
					cId: 1,
					cPhone: '',
					dId: 1,
					pPhoto: '',
					eId: 1,
					rId: 1,
					pId:1,
					mQq: '',
					status: true,
					rAdmissionDate: '',
					rTuihuiDate:"",
					pRemarks: ''
				},
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
					rId: [{
						required: true,
						message: '请选择角色',
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
			//添加按钮
			add() {
				this.interface = "insert";
				this.modal13 = true;
				this.title = "添加成员";
				this.memberInformation = {
					mId: 0,
					mUser: '',
					mPassword: '686868',
					mName: '',
					mSex: '男',
					cId: 1,
					cPhone: '',
					dId: 1,
					pPhoto: '',
					eId: 1,
					rId: 1,
					pId:1,
					mQq: '',
					status: true,
					rAdmissionDate: '',
					rTuihuiDate:"",
					pRemarks: ''
				};
			},
				//时间
			getStartTime(starTime) {
				this.memberInformation.rAdmissionDate = starTime;
			},
			//男女开关
			off(value) {
				this.memberInformation.mSex = value == true ? "男" : "女";
			},
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '成员信息'
				});
			},
			//上传
			resultMsg(res) {
				if (res.code === 1224) {
					this.memberInformation.pPhoto = this.url + "/file/"+res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//查询部门类型
			selectAdd() {
				const th = this;
				axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
					th.departmentType = res.data.data;
				})
				axios.get(th.url + '/classTable/iSelectAllStatus').then(function(res) {
					th.classTable = res.data.data;
				})
				axios.get(th.url + '/positionType/iSelectAllStatus').then(function(res) {
					th.positionType = res.data.data;
				})
				axios.get(th.url + '/exchangeTable/iSelectAllStatus').then(function(res) {
					th.exchangeTable = res.data.data;
				})
				axios.get(th.url + '/roles/iSelectAll').then(function(res) {
					th.roles = res.data.data;
				})
			},
			//编辑
			show(mId) {
				this.interface = "updateByPrimaryKey";
				this.modal13 = true;
				this.title = "编辑成员信息";
				var th = this;
				axios.get(th.url + '/memberInformation/selectByPrimaryKey', {
					params: {
						mId: mId,
					}
				}).then(function(res) {
					th.memberInformation.mId = res.data.data.mId;
					th.memberInformation.mUser =  res.data.data.mUser;
					th.memberInformation.mPassword =  res.data.data.mPassword;
					th.memberInformation.mName =  res.data.data.mName;
					th.sex =  res.data.data.mSex == "男" ? true : false;
					th.memberInformation.cId =  res.data.data.cId;
					th.memberInformation.cPhone =  res.data.data.cPhone;
					th.memberInformation.dId =  res.data.data.dId;
					th.memberInformation.pPhoto =  res.data.data.pPhoto;
					console.log(th.memberInformation.pPhoto);
					th.memberInformation.eId =  res.data.data.eId;
					th.memberInformation.rId =  res.data.data.rId;
					th.memberInformation.pId =  res.data.data.pId;
					th.memberInformation.mQq =  res.data.data.mQq;
					th.memberInformation.status =  res.data.data.status == "true" ? true : false;
					th.memberInformation.rAdmissionDate =  res.data.data.rAdmissionDate;
					th.memberInformation.rTuihuiDate =  res.data.data.rTuihuiDate;
					th.memberInformation.pRemarks =  res.data.data.pRemarks;
				})
			},
			//查询
			select(page) {
				if (!this.mName) {
					this.memberInformation.mName = null;
				}
				if (!this.dName) {
					this.memberInformation.dId = 0;
				}
				if (!this.cName) {
					this.memberInformation.cId = 0;
				}
				if (!this.pName) {
					this.memberInformation.pId = 0;
				}
				if (!this.eName) {
					this.memberInformation.eId = 0;
				}
				this.loading = true;
				const th = this;
				axios.get(th.url + '/memberInformation/selects', {
					params: {
						pageNum: page,
						cId: th.memberInformation.cId,
						pId: th.memberInformation.pId,
						dId: th.memberInformation.dId,
						eId: th.memberInformation.eId,
						mName: th.memberInformation.mName,
						status: th.status
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//删除
			remove(dId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/memberInformation/deleteByPrimaryKey', {
							params: {
								dId: dId
							}
						}).then(function(res) {
							if (res.data.code === 1028) {
								th.$Message.success(res.data.message);
								th.changePage(1);
							} else {
								th.$Message.error(res.data.message);
							}
						})
					}
				});
			},
			//修改
			ok() {
				const th=this;
				axios.post(th.url+'/memberInformation/'+th.interface,th.memberInformation,{
					headers:{
						"Content-Type":"application/json;charset=utf-8"
					}
				}).then(function(res){
					if(res.data.code===1028){
						th.$Message.success(res.data.message);
						th.select(1);
					}else{
						th.$Message.error(res.data.message);
						this.modal13 = true;
					}
				}) 
			},
		},
		created() {
			this.select(1);
			var th = this;
			this.selectAdd();
		}
	}
</script>
