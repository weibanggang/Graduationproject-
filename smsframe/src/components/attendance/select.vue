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
						<Checkbox v-model="aMName" label="">模糊姓名</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="attendance.aMName" height="20" placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="dates" label="">请假时间</Checkbox>
						</Col>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(($event))" placeholder="时间查询" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>

				<FormItem style="position: relative;left: 10px">
					<Button @click="select(1)">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">

					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />新增记录
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
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="mName" label="">&nbsp;操&nbsp;作&nbsp;员</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="attendance.mName" placeholder="操作员"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="dName" label="">部&nbsp;&nbsp;&nbsp;&nbsp;门</Checkbox>
						</Col>
						<Col span="16">
						<Select v-model="attendance.dName" filterable>
							<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="420" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="select($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal14"  draggable scrollable title="添加出勤记录" @on-ok="oks">
			<Form ref="formInline" :model="attendance" :label-width="80">
				<FormItem label="工作编号" prop="mUser">
					<Row>
						<Col span="11">
						<Select v-model="attendance.mUser" filterable placeholder="请输入编号" :remote-method="selectMname" :loading="loading">
							<Option v-for="item in menberInforMation" :value="item.mUser" :key="item.mUser">{{ item.mUser }}</Option>
						</Select>
						</Col>
						<Col span="11">
						<Input v-model="attendance.aMName" disabled placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="标题">
					<Input v-model="attendance.aTitile" placeholder="标题"></Input>
				</FormItem>
				<FormItem label="出勤类型">
					<Input v-model="attendance.aType" placeholder="出勤类型"></Input>
				</FormItem>
				<FormItem label="备注" prop="lContexts">
					<Input v-model="attendance.aRemaks" type="textarea" :autosize="{minRows: 4,maxRows: 8}" placeholder="备注"></Input>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				loading: true,
				mName: false,
				dName: false,
				aMName: false,
				dates: false,
				baDate: [],
				departmentType: [],
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14: false,
				menberInforMation: [],
				columns7: [{
						title: '类型',
						key: 'aType',
						align: 'center'
					},
					{
						title: '成员工作编号',
						key: 'mUser',
						align: 'center',
						width: 140
					},
					{
						title: '成员姓名',
						key: 'aMName',
						align: 'center'
					},
					{
						title: '成员部门',
						key: 'dName',
						align: 'center'
					},
					{
						title: '标题',
						key: 'aTitile',
						align: 'center',
						width: 120,
						tooltip: true
					},
					{
						title: '时间',
						key: 'aDate',
						align: 'center',
						width: 120,
						tooltip: true
					}, {
						title: '备注',
						key: 'aRemaks',
						align: 'center',
						width: 150,
						tooltip: true
					}, {
						title: '操作员',
						key: 'mName',
						width: 80,
						align: 'center',
					},
					{
						title: '操作',
						key: 'action',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {

											this.remove(params.row.aId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				attendance: {
					aId: 0,
					aType: "",
					mUser: "",
					aMName: "",
					dName: "",
					aTitile: "",
					aDate: "",
					mName: "",
					aRemaks: ""
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
			selectTime(starTime) {
				this.baDate = starTime;
			},
			//查询
			select(page) {

				this.loading = true;
				if (!this.mName) {
					this.attendance.mName = null;
				}
				if (!this.aMName) {
					this.attendance.aMName = null;
				}
				if (!this.dName) {
					this.attendance.dName = null;
				}
				if (!this.dates) {
					this.baDate = ["", ""];
				}
				const th = this;
				axios.get(th.url + '/attendance/selects', {
					params: {
						pageNum: page,
						mName: th.attendance.mName,
						aMName: th.attendance.aMName,
						dName: th.attendance.dName,
						beforeDate: th.baDate[0],
						afterDate: th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//删除
			remove(aId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/attendance/deleteByPrimaryKey', {
							params: {
								aId: aId
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
				axios.post(th.url + '/attendance/updateByPrimaryKey', th.attendance, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.select(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal13 = true;
					}
				})
			},
			//添加弹出
			add() {
				this.modal14 = true;
				this.attendance = {
					aId: 0,
					aType: "",
					mUser: "",
					aMName: "",
					dName: "",
					aTitile: "",
					aDate: "",
					mName: "",
					aRemaks: ""
				};
			},
			//编号查询
			selectMname(value) {
				const th = this;
				axios.get(th.url + '/memberInformation/iUserName', {
					params: {
						mUser: value
					}
				}).then(function(res) {
					th.menberInforMation = res.data.data;
					for (let s of res.data.data) {
						th.attendance.aMName = s.mName;
						th.attendance.dName = s.dId;
					}
				})
			},
			//添加
			oks() {
				const th = this;
					th.attendance.mName = localStorage.getItem("mName");
				axios.post(th.url + '/attendance/insert', th.attendance, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.select(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal13 = true;
					}
				})
			}
		},
		created() {
			this.select(1);
			var th = this;
			//部门查询
			axios.get(th.url + '/departmentType/iselectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
		}
	}
</script>
