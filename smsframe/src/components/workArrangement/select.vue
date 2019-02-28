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
			<Form :model="workArrangement" inline>
				<FormItem label="工作标题">
					<Row>
						<Col span="16">
						<Select v-model="workArrangement.wId" @on-change="selectName()" filterable>
							<Option v-for="item in workArrangementTitle" :value="item.wId" :key="item.wId">{{ item.wTitle}}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				
					<FormItem label="负责人">
						<Row>
							<Col span="16">
							<Select v-model="workArrangement.mName" @on-change="selectmName()" filterable>
								<Option v-for="item in memberInformationType" :value="item.mName" :key="item.mId">{{ item.mName }}</Option>
							</Select>
							</Col>
						</Row>
					</FormItem>
					
						<FormItem>
							<Button @click="changePage(1)">
								<Icon type="ios-sync" />全部查询
							</Button>
						</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />新增部门
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
			<Form inline>
				
				<FormItem label="负责部门">
					<Row>
						<Col span="16">
						<Select v-model="workArrangement.dName" @on-change="selectdName()" filterable>
							<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				
				<FormItem label="发布时间">
					<Row>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" @on-change="getStartTime(($event),1)" placeholder="时间查询" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
			</Form>
		</div>

		<Table border :columns="columns7" :data="data6" height="420" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="seleAll($event)"></Page>
			</div>
		</div>

		<Modal v-model="modal13" :styles="{top: '40px'}" draggable scrollable title="编辑工作安排" @on-ok="ok">
			<Form ref="formValidate" :model="workArrangement" :label-width="80">
				<FormItem label="工作标题" prop="wTitle">
					<Input v-model="workArrangement.wTitle" placeholder="请输入工作标题"></Input>
				</FormItem>
				<FormItem label="负责人" prop="mName">
					<Select v-model="workArrangement.mName" placeholder="请选择负责人">
						<Option v-for="item in memberInformationType" :value="item.mName" :key="item.mId">{{ item.mName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="负责部门" prop="dName">
					<Select v-model="workArrangement.dId" placeholder="请选择部门">
						<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="时间">
					<Row>
						<Col span="11">
						<FormItem prop="wDate">
							<DatePicker type="date" placeholder="请选择时间" @on-change="getTime(($event))" v-model="workArrangement.wDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="工作内容" prop="wContents">
					<Input v-model="workArrangement.wContents" type='textarea' :autosize="{minRows: 3,maxRows: 6}" placeholder="请输入工作内容"></Input>
				</FormItem>
				<FormItem label="工作备注" prop="wRemarks">
					<Input v-model="workArrangement.wRemarks" type='textarea' :autosize="{minRows: 3,maxRows: 6}" placeholder="请输入工作备注"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" :styles="{top: '40px'}" draggable scrollable title="添加工作安排" @on-ok="oks">
			<Form ref="formValidate" :model="workArrangement" :label-width="80">
				<FormItem label="工作标题" prop="wTitle">
					<Input v-model="workArrangement.wTitle" placeholder="请输入工作标题"></Input>
				</FormItem>
				<FormItem label="负责人" prop="mName">
					<Select v-model="workArrangement.mName" placeholder="请选择负责人">
						<Option v-for="item in memberInformationType" :value="item.mName" :key="item.mId">{{ item.mName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="负责部门" prop="dName">
					<Select v-model="workArrangement.dId" placeholder="请选择部门">
						<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="时间">
					<Row>
						<Col span="11">
						<FormItem prop="wDate">
							<DatePicker type="date" placeholder="请选择时间" @on-change="getTime(($event))" v-model="workArrangement.wDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="工作备注" prop="wRemarks">
					<Input v-model="workArrangement.wRemarks" type='textarea' :autosize="{minRows: 3,maxRows: 6}" placeholder="请输入工作备注"></Input>
				</FormItem>
				<FormItem label="工作内容" prop="wContents">
					<Input v-model="workArrangement.wContents" type='textarea' :autosize="{minRows: 3,maxRows: 6}" placeholder="请输入工作内容"></Input>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				title: "添加工作类型",
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14: false,
				bd:"",
				baDate:[],
				workArrangementTitle: "",
				departmentType: [],
				columns7: [{
						title: '工作编号',
						key: 'wId',
						width:100,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.wId)
							]);
						}
					},
					{
						title: '工作标题',
						key: 'wTitle',
						width:150,
						tooltip:true,
						align: 'center',
					},
					{
						title: '负责部门',
						key: 'dName',
						align: 'center',
					},
					{
						title: '总负责人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '时间',
						key: 'wDate',
						align: 'center',
					},
					{
						title: '备注',
						key: 'wRemarks',
						align: 'center',
						width: 200,
						tooltip: true
					},
					{
						title: '工作内容',
						key: 'wContents',
						align: 'center',
						width: 200,
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
											this.remove(params.row.wId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				loading: true,
				memberInformationType: [],
				workArrangement: {
					wId: 0,
					wTitle: "",
					wRemarks: "",
					dName: "",
					dId: "",
					mName: "",
					wDate: "",
					wContents: ""
				}
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
			getStartTime(starTime,page) {
				this.loading = true;
				
				if(starTime.length > 0){
					this.baDate = starTime;
				}
				this.bd = "starTime";
				const th = this;
				axios.get(th.url + '/workArrangement/selectDate', {
					params: {
						pageNum: page,
						beforeDate:th.baDate[0],
						afterDate:th.baDate[1],
					}
				}).then(function(res) {
					var datares = res.data.data.map((e) => {
						e.dName = e.departmentType.dName;
						return e
					});
					th.data6 = datares;
					th.count = res.data.count;
				
				})
				this.loading = false;
				
			},
			//时间
			getTime(starTime) {
				this.workArrangement.wDate = starTime;
			},
			//添加的时候弹出
			//添加弹出
			add() {
				this.modal14 = true;
				this.workArrangement.wRemarks = "";
				this.workArrangement.wTitle = "";
				this.workArrangement.wContents = "";
			},
			//根据负责部门查询
			selectdName() {
				const th = this
				this.loading = true;
				axios.get(th.url + '/workArrangement/selectAllVagueName', {
					params: {
						dId: th.workArrangement.dName
					}
				}).then(function(res) {
					var datares = res.data.data.map((e) => {
						e.dName = e.departmentType.dName;
						return e
					});
					th.data6 = datares;
					th.count = res.data.count;

				})
				this.loading = false;
			},
			//根据名称查询
			selectName() {
				const th = this;
				axios.get(th.url + '/workArrangement/selectByPrimaryKey', {
					params: {
						wId: th.workArrangement.wId
					}
				}).then(function(res) {
					th.data6 = [];
					th.data6.push({
						wId: res.data.data.wId,
						wTitle: res.data.data.wTitle,
						wRemarks: res.data.data.wRemarks,
						dName: res.data.data.departmentType.dName,
						mName: res.data.data.mName,
						wDate: res.data.data.wDate,
						wContents: res.data.data.wContents
					});
					th.count = res.data.count;
				})
			},
			//根据负责人查询
			selectmName() {
				const th = this;
				axios.get(th.url + '/workArrangement/selectmName', {
					params: {
						mName: th.workArrangement.mName
					}
				}).then(function(res) {
					th.data6 = [];
					if (res.data.data.length == 0) {
						th.count = 0;
						return;
					}
					var datares = res.data.data.map((e) => {
						e.dName = e.departmentType.dName;
						return e
					});
					th.data6 = datares;
					th.count = res.data.count;

				})
			},
			//编辑
			show(data) {
				this.modal13 = true;
				this.workArrangement.wId = data.wId;
				this.workArrangement.dId = data.dId;
				this.workArrangement.wTitle = data.wTitle;
				this.workArrangement.wRemarks = data.wRemarks;
				this.workArrangement.wDate = data.wDate;
				this.workArrangement.wContents = data.wContents;
				this.workArrangement.mName = data.mName;
			},
			//分页查询
			seleAll(page){
				
				if(this.bd == "all"){
					changePage(page);
				}
				if(this.bd = "starTime"){
					getStartTime("",page);
				}
			},
			//查询
			changePage(page) {
				this.bd = "all";
				this.loading = true;
				const th = this;
				axios.get(th.url + '/workArrangement/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					var datares = res.data.data.map((e) => {
						e.dName = e.departmentType.dName;
						return e
					});
					th.data6 = datares;
					th.count = res.data.count;

				})
				this.loading = false;

			},
			//第一次查询
			selectAdd() {
				const th = this;
				//部门
				axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
					th.departmentType = res.data.data;
				})
				//这里4是主席团
				axios.get(th.url + '/memberInformation/iSelectName', {
					params: {
						pId: 4
					}
				}).then(function(res) {
					th.memberInformationType = res.data.data;
				})
				//标题
				axios.get(th.url + '/workArrangement/selectGroupBy').then(function(res) {
					th.workArrangementTitle = res.data.data;
				})
			},
			//删除
			remove(wId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/workArrangement/deleteByPrimaryKey', {
							params: {
								wId: wId
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
				axios.post(th.url + '/workArrangement/updateByPrimaryKey', th.workArrangement, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
					}
				})
			},
			//添加
			oks() {
				const th = this;
				axios.post(th.url + '/workArrangement/insert', th.workArrangement, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
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
		},
		created() {
			this.selectAdd();
			this.changePage(1);
		}
	}
</script>
