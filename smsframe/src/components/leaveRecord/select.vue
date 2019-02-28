<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form  inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="name" label="">模糊姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="leaveRecords.lMName" placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="dates" label="">请假时间</Checkbox>
						</Col>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(($event))" placeholder="请假时间" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						<Checkbox v-model="dname" label="">部门</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="leaveRecords.dName" filterable>
							<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;right: 15px">
					<Button @click="select(1,'bd')">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">

					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />添加记录
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
		</div>




		<Table border :columns="columns7" :data="data6" height="450" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" :styles="{top: '40px'}"  draggable scrollable title="编辑请假信息" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="leaveRecord" :label-width="80">
					<FormItem label="姓名" prop="lMName">
						<Input v-model="leaveRecord.lMName" disabled placeholder="姓名"></Input>
					</FormItem>
					<FormItem label="会议标题">
						<Input v-model="leaveRecord.lTitle" placeholder="会议标题"></Input>
					</FormItem>
					<FormItem label="请假类型">
						<RadioGroup v-model="leaveRecord.lType">
							<Radio label="病假">
								<Icon type="ios-football-outline" />
								<span>病假</span>
							</Radio>
							<Radio label="事假">
								<Icon type="md-log-in" />
								<span>事假</span>
							</Radio>
							<Radio label="其他">
								<Icon type="md-log-out" />
								<span>其他</span>
							</Radio>
						</RadioGroup>
					</FormItem>
					<FormItem label="请假时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择请假时间"  @on-change="getStartTime(($event))"  v-model="leaveRecord.pDate"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/leaveRecord">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="leaveRecord.lFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
					<FormItem label="请假内容" prop="lContexts">
						<Input v-model="leaveRecord.lContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>

				</Form>
			</div>
		</Modal>
			<Modal v-model="modal14" :styles="{top: '40px'}" draggable scrollable title="添加请假信息" @on-ok="oks">
			<Form ref="formInline" :model="leaveRecord" :label-width="80">
				<FormItem label="工作编号" prop="mUser" >
					<Row>
						<Col span="11">
					<Select v-model="leaveRecord.mUser" filterable placeholder="请输入编号" :remote-method="selectMname"
			        :loading="loading" >
					<Option v-for="item in menberInforMation" :value="item.mUser" :key="item.mUser">{{ item.mUser }}</Option>
					</Select>
					</Col>
					<Col span="11">
						<Input v-model="leaveRecord.lMName" disabled placeholder="姓名"></Input>
					</Col>
					</Row>
				</FormItem>
				<FormItem label="会议标题">
					<Input v-model="leaveRecord.lTitle" placeholder="会议标题"></Input>
				</FormItem>
				<FormItem label="请假类型">
					<RadioGroup v-model="leaveRecord.lType">
						<Radio label="病假">
							<Icon type="ios-football-outline" />
							<span>病假</span>
						</Radio>
						<Radio label="事假">
							<Icon type="md-log-in" />
							<span>事假</span>
						</Radio>
						<Radio label="其他">
							<Icon type="md-log-out" />
							<span>其他</span>
						</Radio>
					</RadioGroup>
				</FormItem>
				<FormItem label="请假时间">
					<Row>
						<Col span="11">
						<FormItem prop="rAdmissionDate">
							<DatePicker type="date" placeholder="请选择请假时间" @on-change="getStartTime(($event))"  v-model="leaveRecord.pDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="文件上传">
					<div>
						<Row>
							<Col span="12">
							<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/leaveRecord">
								<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
							</Upload>
							</Col>
							<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="leaveRecord.lFile" disabled placeholder="没有文件" /></Col>
						</Row>
					</div>
				</FormItem>
				<FormItem label="请假内容" prop="lContexts">
					<Input v-model="leaveRecord.lContexts" type="textarea" :autosize="{minRows: 4,maxRows: 8}" placeholder="内容"></Input>
				</FormItem>
			</Form>
			</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				dname: false,
				name: false,
				dates: false,
				baDate: [],
				bd: "",
				loading: true,
				departmentType: [],
				menberInforMation:[],
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14:false,
				columns7: [{
						title: '姓名',
						key: 'lMName',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.lMName)
							]);
						}
					},
					{
						title: '工作编号',
						key: 'mUser',
						align: 'center'
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center'
					},
					{
						title: '请假时间',
						key: 'pDate',
						align: 'center'
					},
					{
						title: '标题',
						key: 'lTitle',
						align: 'center',
						width: 100,
						tooltip: true
					},
					{
						title: '请假类型',
						key: 'lType',
						align: 'center'
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},

					{
						title: '请假内容',
						key: 'lContexts',
						width: 200,
						align: 'center',
						tooltip: true
					},
					{
						title: '操作',
						key: 'action',
						align: 'center',
						width: 125,
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

											this.remove(params.row.lId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				leaveRecord: {
					lId: 0,
					dName: "",
					mUser: "",
					pDate: "",
					lTitle: "",
					lType: "病假",
					lFile: "",
					mName: "",
					lContexts: "",
					lMName: "",
				},
				leaveRecords: {
					lId: 0,
					dName: "",
					mUser: "",
					pDate: "",
					lTitle: "",
					lType: "",
					lFile: "",
					mName: "",
					lContexts: "",
					lMName: "",
				},
			}
		},
		methods: {

			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '工作信息'
				});
			},
			 //间隔时间
			selectTime(starTime) {
				this.baDate = starTime;
			},
			//编辑弹出
			show(data) {
				this.modal13 = true;
				this.leaveRecord.lMName = data.lMName;
				this.leaveRecord.lId = data.lId;
				this.leaveRecord.lFile = data.lFile;
				this.leaveRecord.lTitle = data.lTitle;
				this.leaveRecord.lContexts = data.lContexts;
				this.leaveRecord.pDate = data.pDate;
				this.leaveRecord.lType = data.lType;
			},
			//时间
			getStartTime(starTime) {
				this.leaveRecord.pDate = starTime;
			},
			//查询
			changePage(page) {
				this.loading = true;
				const th = this;
				axios.get(th.url + '/leaveRecord/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//分页查询
			selectpage(page) {
				if (this.bd == "bd") {
					select(page);
				} else {
					changePage(page);
				}
			},
			//上传
			resultMsg(res) {
				if (res.code === 1224) {
					this.leaveRecord.lFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//删除
			remove(lId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/leaveRecord/deleteByPrimaryKey', {
							params: {
								lId: lId
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
			//查询
			select(page, bd) {
				if (bd == "bd") {
					this.bd = bd;
				}
				this.loading = true;
				if (!this.name) {
					this.leaveRecords.lMName = null;
				}
				if (!this.dname) {
					this.leaveRecords.dName = null;
				}
				if (!this.dates) {
					this.baDate = ["", ""];
				}
				const th = this;
				axios.get(th.url + '/leaveRecord/selects', {
					params: {
						pageNum: page,
						lMName:th.leaveRecords.lMName,
						dName:th.leaveRecords.dName,
						beforeDate:th.baDate[0],
						afterDate:th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//修改
			ok() {
				const th = this;
				axios.post(th.url + '/leaveRecord/updateByPrimaryKey', th.leaveRecord, {
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
				this.leaveRecord = {
					lId: 0,
					dName: "",
					mUser: "",
					pDate: "",
					lTitle: "",
					lType: "病假",
					lFile: "",
					mName: "",
					lContexts: "",
					lMName: "",
				};
				this.modal14 = true;
			},
			//添加
			oks(){
				const th = this;
				th.leaveRecord.mName = localStorage.getItem("mName");
				axios.post(th.url + '/leaveRecord/insert', th.leaveRecord, {
						headers: {
							"Content-Type": "application/json;charset=utf-8"
						}
					}).then(function(res) {
						if (res.data.code === 1028) {
							th.$Message.success(res.data.message);
							th.changePage(1);
						} else {
							th.$Message.error(res.data.message);
							th.modal14 = true;
						}
					})
				
			},
			//编号查询
			selectMname(value){
			const th = this;
			axios.get(th.url + '/memberInformation/iUserName', {
				params: {
					mUser: value
				}
			}).then(function(res) {
				th.menberInforMation = res.data.data;
				for(let s of res.data.data){
					th.leaveRecord.lMName=s.mName;
					th.leaveRecord.dName=s.dId;
				}
			}) 
		},
		},
		created() {
			this.changePage(1);
			const th = this;
			//部门查询
			axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
		}
	}
</script>
