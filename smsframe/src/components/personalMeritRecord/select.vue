<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="classTable" inline>
				<FormItem label="姓名">
					<Row>
						<Col span="18">
						<Input placeholder="姓名" v-model="lMName"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Button @click="selectName()">
						<Icon type="ios-sync" />模糊查询
					</Button>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="name" label="">部门</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="personalMeritRecord.dName" filterable>
							<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Col span="8" style="text-align: center;">
					<Checkbox v-model="dates" label="">时间</Checkbox>
					</Col>
					<Row>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(($event))" placeholder="时间查询" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem  style="position: relative;left: 70px">
					<Button @click="select(1,'bd')">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-sync" />新增信息
						</Button>
					</FormItem>
					<FormItem>
						<Button @click="exportData()">
							<Icon type="ios-download-outline" />数据导出
						</Button>
					</FormItem>
				</FormItem>
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="450" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="selectpage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal14" draggable scrollable title="添加个人评优" @on-ok="oks">
			<div>
				<Form ref="formInline" :model="personalMeritRecord" :label-width="80">
					<FormItem label="工作编号" prop="mUser">
						<Row>
							<Col span="11">
							<Select v-model="personalMeritRecord.mUser" filterable placeholder="请输入编号" :remote-method="selectMname" :loading="loading">
								<Option v-for="item in menberInforMation" :value="item.mUser" :key="item.mUser">{{ item.mUser }}</Option>
							</Select>
							</Col>
							<Col span="11">
							<Input v-model="personalMeritRecord.lMName" disabled placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem label="标题">
						<Input v-model="personalMeritRecord.pTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择时间" v-model="personalMeritRecord.pDate" @on-change="getStartTime"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>

					<FormItem label="内容" prop="pContexts">
						<Input v-model="personalMeritRecord.pContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/personalMeritRecord">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="personalMeritRecord.pFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
				</Form>
			</div>
		</Modal>
		<Modal v-model="modal13" draggable scrollable title="编辑个人评优" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="personalMeritRecord" :label-width="80">
					<FormItem label="成员编号">
						<Input v-model="personalMeritRecord.mUser" disabled placeholder="成员编号"></Input>
					</FormItem>
					<FormItem label="成员姓名">
						<Input v-model="personalMeritRecord.lMName" disabled placeholder="成员姓名"></Input>
					</FormItem>
					<FormItem label="标题">
						<Input v-model="personalMeritRecord.pTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择时间" v-model="personalMeritRecord.pDate" @on-change="getStartTime"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>

					<FormItem label="内容" prop="pContexts">
						<Input v-model="personalMeritRecord.pContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/personalMeritRecord">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="personalMeritRecord.pFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
				</Form>
			</div>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				name: false,
				dates: false,
				url: "http://47.100.245.30:8080/",
				count: 10,
				lMName: "",
				
				modal14: false,
				modal13: false,
				loading: false,
				departmentType: [],
				menberInforMation: [],
				columns7: [{
						title: '部门名称',
						key: 'dName',
						align: 'center',
					},
					{
						title: '成员编号',
						key: 'mUser',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.mUser)
							]);
						}
					},
					{
						title: '成员姓名',
						key: 'lMName',
						align: 'center'
					},
					{
						title: '标题',
						key: 'pTitle',
						align: 'center',
					},
					{
						title: '时间',
						key: 'pDate',
						align: 'center',
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '内容',
						key: 'pContexts',
						width: 200,
						tooltip: true,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.pContexts.substring(0, 50))
							]);
						}
					},
					{
						title: '操作',
						key: 'action',
						width: 150,
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
											this.show(params.row)
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

											this.remove(params.row.pId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				baDate: [],
				bd: "",
				personalMeritRecord: {
					pId: 0,
					dName: "",
					mUser: "",
					lMName: "",
					pTitle: "",
					pDate: "",
					pFile: "",
					mName: "",
					pContextss: ""
				}
			}
		},
		methods: {
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '个人评优'
				});
			},
			//条件查询
			select(page, bd) {
				if (bd == "bd") {
					this.bd = bd;
				}
				this.loading = true;
				if (!this.name) {
					this.personalMeritRecord.dName = null;
				}
				if (!this.dates) {
					this.baDate = ["", ""];
				}
				const th = this;
				axios.get(th.url + '/personalMeritRecord/selects', {
					params: {
						pageNum: page,
						dName: th.personalMeritRecord.dName,
						beforeDate: th.baDate[0],
						afterDate: th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//模糊查询
			selectName() {
				this.loading = true;
				const th = this;
				axios.get(th.url + 'personalMeritRecord/selectName', {
					params: {
						lMName: th.lMName
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
			//分页查询
			selectpage(page) {
				if (this.bd == "bd") {
					select(page);
				} else {
					changePage(page);
				}
			},
			//编辑弹出
			show(data) {
				this.modal13 = true;
				this.personalMeritRecord.pId = data.pId;
				this.personalMeritRecord.mUser = data.mUser;
				this.personalMeritRecord.dName = data.dName;
				this.personalMeritRecord.lMName = data.lMName;
				this.personalMeritRecord.pTitle = data.pTitle;
				this.personalMeritRecord.pDate = data.pDate;
				this.personalMeritRecord.pFile = data.pFile;
				this.personalMeritRecord.pContexts = data.pContexts;
			},
			//时间
			getStartTime(starTime) {
				this.personalMeritRecord.pDate = starTime;
			},
			//时间间隔查询
			selectTime(value) {
				this.baDate = value;
			},
			//根据编号查询姓名
			selectMname(value) {
				this.loading = true;
				const th = this;
				axios.get(th.url + 'memberInformation/iUserName', {
					params: {
						mUser: value
					}
				}).then(function(res) {
					th.menberInforMation = res.data.data;
					for (let s of res.data.data) {
						th.personalMeritRecord.lMName = s.mName;
						th.personalMeritRecord.dName = s.dId;
					}
					th.loading = false;
				})
			},
			//查询
			changePage(page) {
				const th = this;
				axios.get(th.url + 'personalMeritRecord/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			//上传文件
			resultMsg(res) {
				if (res.code === 1224) {
					this.personalMeritRecord.pFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//删除
			remove(pId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + 'personalMeritRecord/deleteByPrimaryKey', {
							params: {
								pId: pId
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
				const th = this;
				axios.post(th.url + 'personalMeritRecord/updateByPrimaryKey', th.personalMeritRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal13 = true;
					}
				})
			},
			//添加弹出
			add() {
				this.personalMeritRecord.mUser = "";
				this.personalMeritRecord.dName = "";
				this.personalMeritRecord.lMName = "";
				this.personalMeritRecord.pTitle = "";
				this.personalMeritRecord.pFile = "无路径";
				this.personalMeritRecord.pContexts = "";
				this.modal14 = true;
			},
			//添加
			oks() {
				const th = this;
				th.personalMeritRecord.mName = localStorage.getItem("mName");
				axios.post(th.url + 'personalMeritRecord/insert', th.personalMeritRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
						this.modal14 = true;
					}
				})
			}
		},
		created() {
			this.changePage(1);
			//部门查询
			const th = this;
			axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
		}
	}
</script>
