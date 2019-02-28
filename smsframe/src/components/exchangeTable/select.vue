<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form  :model="exchangeTable" inline>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
							学生会名称
						</Col>
						<Col span="17" >
						<Select v-model="exchangeTable.eId"  @on-change="selectName()" filterable>
							<Option v-for="item in exchangeTableName" :value="item.eId" :key="item.eName">{{ item.eName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<RadioGroup v-model="status" @on-change="changePage(1)">
						<Radio label="true">
							<Icon type="ios-eye" />
							<span>正常</span>
						</Radio>
						<Radio label="false">
							<Icon type="ios-eye-off" />
							<span>冻结</span>
						</Radio>
						<Radio label="all">
							<Icon type="ios-football-outline" />
							<span>全部</span>
						</Radio>
					</RadioGroup>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="insert()">
							<Icon type="ios-add-circle-outline" />新增会名
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="450" stripe size='default' :loading="loading" ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="学生会信息" @on-ok="ok">
			<Form ref="formValidate" :model="exchangeTable" :label-width="80">
				<FormItem label="学生会名称" prop="eName">
					<Input v-model="exchangeTable.eName" placeholder="请输入学生会名称"></Input>
				</FormItem>
				<FormItem label="学生会状态" rop="status">
					<i-switch v-model="exchangeTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="学生会备注" prop="eRemarks">
					<Input v-model="exchangeTable.eRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入学生会备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="eSort">
					<InputNumber :max="100" :min="1" v-model="exchangeTable.eSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="学生会类型" @on-ok="oks">
			<Form ref="formValidate" :model="exchangeTable" :label-width="80">
				<FormItem label="学生会名称" prop="eName">
					<Input v-model="exchangeTable.eName" placeholder="请输入学生会名称"></Input>
				</FormItem>
				<FormItem label="学生会状态" rop="status">
					<i-switch v-model="exchangeTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="学生会备注" prop="eRemarks">
					<Input v-model="exchangeTable.eRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入学生会备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="eSort">
					<InputNumber :max="100" :min="1" v-model="exchangeTable.eSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				title: "添加学生会类型",
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14: false,
				loading:true,
				status:"true",
				exchangeTableName:"",
				columns7: [{
						title: '编号',
						key: 'eId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.eId)
							]);
						}
					},
					{
						title: '学生会名称',
						key: 'eName',
						align: 'center',
					},
					{
						title: '状态',
						key: 'status',
						align: 'center',
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
											axios.get(th.url + '/exchangeTable/updateStatus', {
												params: {
													eId: params.row.eId,
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
											innerHTML: '冻结'
										}
									}),
								]
							)
						}

					},
					{
						title: '备注',
						key: 'eRemarks',
						align: 'center',
						width:250,
						tooltip:true
					},
					{
						title: '排序',
						key: 'eSort',
						align: 'center',
						render: (h, params) => {
													return h('div', [
														h('InputNumber', {
															props: {
																size: "large",
																value: params.row.eSort
															},
															on: {
																'on-change': (value) => {
																	this.exchangeTable.eId = params.row.eId;
																	this.exchangeTable.eSort = value;
																	this.updateSort();
						
																}
															}
														})
													])
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
											this.remove(params.row.eId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				exchangeTable: {
					eId: 0,
					eName: "",
					status: true,
					eRemarks: "",
					eSort: 1
				}
			}
		},
		methods: {
			//修改序号
			updateSort() {
				const th = this;
				th.loading=true;
				axios.post(th.url + '/exchangeTable/updateSort', th.exchangeTable, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
					}
				})
				th.loading=false;
			},
			//编辑
			show(data) {
				this.modal13 = true;
				this.exchangeTable.eId = data.eId;
				this.exchangeTable.eName = data.eName;
				this.exchangeTable.status = data.status == "true";
				this.exchangeTable.eRemarks = data.eRemarks;
				this.exchangeTable.eSort = data.eSort;
			},
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '学生会信息'
				});
			},
			//根据班级名称查询
			selectName() {
				//this.fycx == "cName"
				const th = this;
				th.loading = true;
				axios.get(th.url + '/exchangeTable/selectByPrimaryKey', {
					params: {
						eId: th.exchangeTable.eId
					}
				}).then(function(res) {
					th.data6 = [];
					th.data6.push(res.data.data)
				})
				th.loading = false;
			},
			//查询数据
			changePage(page) {
				const th = this;
				th.loading = true;
				axios.get(th.url + '/exchangeTable/selectAllStatus', {
					params: {
						pageNum: page,
						status:th.status
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
					th.loading = false;
				})
			},
			//删除
			remove(eId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/exchangeTable/deleteByPrimaryKey', {
							params: {
								eId: eId
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
				axios.post(th.url + '/exchangeTable/updateByPrimaryKey', th.exchangeTable, {
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
				axios.post(th.url + '/exchangeTable/insert', th.exchangeTable, {
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
			},
			//添加弹出
			insert() {
				this.exchangeTable.eName = "";
				this.exchangeTable.eRemarks = "";
				this.modal14 = true;
			},
		},
		created() {
			this.changePage(1);
			const th = this;
			axios.get(th.url + '/exchangeTable/iSelectAllStatus')
			.then(function(res) {
				th.exchangeTableName = res.data.data;
			})
		}
	}
</script>
