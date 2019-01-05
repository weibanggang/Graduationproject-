<template>
	<div>
		<div class="rigtop" style="height: 100px;">
				<Form   inline>
					<FormItem>
						<Row>
							<Col span="8" style="text-align: center;">
							<Checkbox v-model="aMName" label="">模糊姓名</Checkbox>
							</Col>
							<Col span="16">
							<Input v-model="attendance.aMName" placeholder="姓名"></Input>
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
					<FormItem>
						<Row>
							<Col span="7" style="text-align: center;">
							<Checkbox v-model="mName" label="">操作员</Checkbox>
							</Col>
							<Col span="16">
							<Input v-model="attendance.mName" placeholder="操作员"></Input>
							</Col>
						</Row>
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
					<Form   inline>
					<FormItem>
						<Row>
							<Col span="6" style="text-align: center;">
							<Checkbox v-model="dName" label="">部门</Checkbox>
							</Col>
							<Col span="16">
							<Select v-model="attendance.dName" filterable>
								<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
							</Select>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Button @click="select(1)">
							<Icon type="ios-sync" />快速查询
						</Button>
					</FormItem>
				</Form>
			</div>
		<Table border :columns="columns7" :data="data6"  height="520" :loading="loading" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="select($event)"></Page>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				loading:true,
				mName: false,
				dName: false,
				aMName: false,
				dates: false,
				baDate: [],
				departmentType:[],
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				columns7: [{
						title: '类型',
						key: 'aType',
						align: 'center'
					},
					{
						title: '成员工作编号',
						key: 'mUser',
						align: 'center'
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
						width:120,
						tooltip:true
					},
					{
						title: '时间',
						key: 'aDate',
						align: 'center',
						width:120,
						tooltip:true
					},{
						title: '备注',
						key: 'aRemaks',
						align: 'center',
						width:150,
						tooltip:true
					},{
						title: '操作员',
						key: 'mName',
						width:80,
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
					aType:"",
					mUser:"",
					aMName:"",
					dName:"",
					aTitile: "",
					aDate: "",
					mName: "",
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
						mName:th.attendance.mName,
						aMName:th.attendance.aMName,
						dName:th.attendance.dName,
						beforeDate:th.baDate[0],
						afterDate:th.baDate[1],
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
						th.changePage(1);
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
