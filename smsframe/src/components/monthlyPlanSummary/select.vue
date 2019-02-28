<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form inline>
				<FormItem label="部门">
					<Row>
						<Col span="16">
						<Select v-model="MonthlyPlanSummary.dId" @on-change="selectdName(1)" filterable>
							<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="上传时间">
					<Row>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(1,($event))" placeholder="时间查询" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 65px">
					<FormItem label="操作员">
						<Row>
							<Col span="16">
							<Input v-model="MonthlyPlanSummary.lMName" placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Button @click="select(1)">
							<Icon type="ios-sync" />快速查询
						</Button>
					</FormItem>
					<FormItem>
						<Button @click="changePage(1)">
							<Icon type="ios-football-outline" />全部查询
						</Button>
					</FormItem>
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
				<Page :total="count" :current="1" @on-change="selectpage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="修改备注" @on-ok="ok" @on-cancel="cancel">
			<Form :model="MonthlyPlanSummary" :label-width="80">
				<FormItem>
					<Input v-model="MonthlyPlanSummary.mRemarks" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="备注"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="添加月计划月总结" @on-ok="oks()">
			<div>
				<Form ref="formInline" :model="MonthlyPlanSummary" :label-width="80">
					<FormItem label="标题">
						<Input v-model="MonthlyPlanSummary.mTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="部门">
						<Row>
							<Col span="16">
							<Select v-model="MonthlyPlanSummary.dId" filterable>
								<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
							</Select>
							</Col>
						</Row>
					</FormItem>
					<FormItem label="内容" >
						<Input v-model="MonthlyPlanSummary.mContexts" type="textarea" :autosize="{minRows: 4,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="备注" >
						<Input v-model="MonthlyPlanSummary.mRemarks" type="textarea" :autosize="{minRows: 4,maxRows: 8}" placeholder="备注"></Input>
					</FormItem>
					
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/monthlyPlanSummary">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="MonthlyPlanSummary.mFile" disabled placeholder="没有文件" /></Col>
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
				loading: true,
				departmentType: "",
				memberInformationType: [],
				url: "http://47.100.245.30:8080",
				count: 10,
				baDate: [],
				bd: "",
				modal13: false,
				modal14: false,
				columns7: [{
						title: '标题',
						key: 'mTitle',
						width: 200,
						tooltip: true,
						align: 'center'
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center',
					},
					{
						title: '上传时间',
						key: 'mDate',
						align: 'center',
						width: 150,
						
						tooltip: true
					},
					{
						title: '内容',
						key: 'mContexts',
						width: 150,
						tooltip: true,
						align: 'center',
					},
					{
						title: '备注',
						key: 'mRemarks',
						width: 200,
						tooltip: true,
						align: 'center',
					},
					{
						title: '操作员',
						key: 'mName',
						align: 'center'
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
								}, '备注'),
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {

											this.remove(params.row.mId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				MonthlyPlanSummary: {
					mId: 0,
					mTitle: "",
					dId: "",
					mDate: "",
					mFile: "",
					mRemarks: "",
					mContexts: "",
					mName: ""

				},
				mRemarks: ""
			}
		},
		methods: {
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '月计划月总结'
				});
			},
			//弹出备注
			show(data) {
				this.modal13 = true;
				this.MonthlyPlanSummary = data;
				this.mRemarks = data.mRemarks;
			},
			//间隔时间
			selectTime(page, starTime) {
				const th = this;
				this.loading = true;
				th.baDate = starTime;
				th.bd = "starTime";
				axios.get(th.url + '/monthlyPlanSummary/selectDate', {
					params: {
						pageNum: page,
						beforeDate: starTime[0],
						afterDate: starTime[1]
					}
				}).then(function(res) {
					const resdata = res.data.data.map((e) => {
						e.dName = e.departmenttype.dName;
						return e;
					})
					th.data6 = resdata;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//分页查询
			selectpage(page) {
				this.loading = true;
				if (this.bd == "dName") {
					this.selectdName(page);
				}
				if (this.bd == "mName") {
					this.select(page);
				}
				if (this.bd == "mName") {
					this.selectTime(page, th.baDate);
				} 
				else{
					this.changePage(page);
				}
				this.loading = false;
			},
			//查询全部
			changePage(page) {
				const th = this;
				this.loading = true;
				axios.get(th.url + '/monthlyPlanSummary/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					const resdata = res.data.data.map((e) => {
						e.dName = e.departmenttype.dName;
						return e;
					})

					th.data6 = resdata;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//上传文件
			resultMsg(res) {
				if (res.code === 1224) {
					this.MonthlyPlanSummary.mFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//快速查询
			select(page) {
				this.loading = true;
				const th = this;
				th.bd = "mName";
				axios.get(th.url + '/monthlyPlanSummary/selectmName', {
					params: {
						pageNum: page,
						mName: th.MonthlyPlanSummary.lMName
					}
				}).then(function(res) {
					const resdata = res.data.data.map((e) => {
						e.dName = e.departmenttype.dName;
						return e;
					})
					th.data6 = resdata;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//删除
			remove(mId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/monthlyPlanSummary/deleteByPrimaryKey', {
							params: {
								mId: mId
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
			//添加弹出
			add(){
				this.modal14 = true;
				this.MonthlyPlanSummary = {
					mId: 0,
					mTitle: "",
					dId: "",
					mDate: "",
					mFile: "",
					mRemarks: "",
					mContexts: "",
					mName: ""
				
				};
			},
			//修改
			ok() {
				const th = this;
				axios.post(th.url + '/monthlyPlanSummary/updateByPrimaryKey', th.MonthlyPlanSummary, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
						th.MonthlyPlanSummary.mRemarks = th.mRemarks;
					}
				})
			},
			//添加
			oks() {
				const th = this;
				th.MonthlyPlanSummary.mName = localStorage.getItem("mName");
				axios.post(th.url + '/monthlyPlanSummary/insert', th.MonthlyPlanSummary, {
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
			//取消按钮
			cancel() {
				this.MonthlyPlanSummary.mRemarks = this.mRemarks;
			},
			//部门查询
			selectdName(page) {
				const th = this;
				this.loading = true;
				th.bd = "dName";
				axios.get(th.url + '/monthlyPlanSummary/selectdId', {
					params: {
						pageNum: page,
						dId: th.MonthlyPlanSummary.dId
					}
				}).then(function(res) {
					const resdata = res.data.data.map((e) => {
						e.dName = e.departmenttype.dName;
						return e;
					})
					th.data6 = resdata;
					th.count = res.data.count;
				})
				this.loading = false;
			}

		},
		created() {
			this.changePage(1);
			const th = this;
			//部门
			axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
		}
	}
</script>
