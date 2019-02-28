<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="classTable" :model="classTable" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						班级名称
						</Col>
						<Col span="18">
						<Select v-model="classTable.cId" @on-change="selectCName()" filterable>
							<Option v-for="item in classTableName" :value="item.cId" :key="item.cId">{{ item.cName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						班&nbsp;主&nbsp;任
						</Col>
						<Col span="18">
						<Select v-model="classTable.cHeadmasterName" @on-change="selectHName(1)" filterable>
							<Option v-for="item in classTableGroup" :value="item.cHeadmasterName" :key="item.cId">{{ item.cHeadmasterName }}</Option>
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
							<Icon type="ios-add-circle-outline" />新增班级
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
				<Page :total="count" :current="1" @on-change="Page($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="编辑班级信息" @on-ok="ok">
			<Form ref="formValidate" :model="classTable" :label-width="80">
				<FormItem label="班级名称" prop="cName">
					<Input v-model="classTable.cName" placeholder="请输入权限名称"></Input>
				</FormItem>
				<FormItem label="班主任" prop="cHeadmasterName">
					<Input v-model="classTable.cHeadmasterName" placeholder="请输入班主任名称"></Input>
				</FormItem>
				<FormItem label="联系方式" prop="cPhone">
					<Input v-model="classTable.cPhone" placeholder="请输入联系方式"></Input>
				</FormItem>
				<FormItem label="班级状态" rop="status">
					<i-switch v-model="classTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="添加班级信息" @on-ok="oks">
			<Form ref="formValidate" :model="classTable" :label-width="80">
				<FormItem label="班级名称" prop="cName">
					<Input v-model="classTable.cName" placeholder="请输入权限名称"></Input>
				</FormItem>
				<FormItem label="班主任" prop="cHeadmasterName">
					<Input v-model="classTable.cHeadmasterName" placeholder="请输入班主任名称"></Input>
				</FormItem>
				<FormItem label="联系方式" prop="cPhone">
					<Input v-model="classTable.cPhone" placeholder="请输入联系方式"></Input>
				</FormItem>
				<FormItem label="班级状态" rop="status">
					<i-switch v-model="classTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				fycx: "",
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14: false,
				loading: true,
				status: "true",
				classTableGroup: "",
				classTableName: "",
				columns7: [{
						title: '编号',
						key: 'cId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.cId)
							]);
						}
					},
					{
						title: '班级名称',
						key: 'cName',
						align: 'center',
					},
					{
						title: '班主任',
						key: 'cHeadmasterName',
						align: 'center',
					}, {
						title: '联系方式',
						key: 'cPhone',
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
											axios.get(th.url + '/classTable/updateStatus', {
												params: {
													cId: params.row.cId,
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
											innerHTML: '冻结中'
										}
									}),
								]
							)
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
											this.remove(params.row.cId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				classTable: {
					cId: 0,
					cName: "",
					status: true,
					cHeadmasterName: "",
					cPhone: ""
				}
			}
		},
		methods: {
			//分页条件
			Page(page) {
				//按班级查询分页
				if (this.fycx == "cName") {
					selectCName();
				}
				//按班主任名字查询
				if (this.fycx == "classTableName") {
					selectHName(page);
				} //按状态查询
				if (this.fycx == "status") {
					changePage(page);
				}
			},
			//编辑
			show(data) {
				this.modal13 = true;
				this.classTable.cId = data.cId;
				this.classTable.cName = data.cName;
				this.classTable.status = data.status == "true";
				this.classTable.cHeadmasterName = data.cHeadmasterName;
				this.classTable.cPhone = data.cPhone;
			},
			//查询
			changePage(page) {
				this.fycx == "status"
				const th = this;
				th.loading = true;
				axios.get(th.url + '/classTable/selectAllStatus', {
					params: {
						pageNum: page,
						status: th.status
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
			//删除
			remove(cId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/classTable/deleteByPrimaryKey', {
							params: {
								cId: cId
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
			//清空
			clone() {
				this.classTable.cName = "";
				this.classTable.cHeadmasterName = "";
				this.classTable.cPhone = "";
			},
			//弹出
			insert() {
				this.clone();
				this.modal14 = true;
			},
			//修改
			ok() {
				const th = this;
				axios.post(th.url + '/classTable/updateByPrimaryKey', th.classTable, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						this.changePage(1);
					} else {
						th.$Message.error(res.data.message);
					}
				})
			}, //添加
			//添加
			oks() {
				const th = this;
				console.log(th);
				axios.post(th.url + '/classTable/insert', th.classTable, {
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
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '职位信息'
				});
			},
			//根据班级名称查询
			selectCName() {
				this.fycx == "cName"
				const th = this;
				th.loading = true;
				axios.get(th.url + '/classTable/selectByPrimaryKey', {
					params: {
						cId: th.classTable.cId
					}
				}).then(function(res) {
					th.data6 = [];
					th.data6.push(res.data.data)
				})
				th.loading = false;
			},
			//根据班主任查询
			selectHName(page) {
				this.fycx == "cHeadmasterName"
				const th = this;
				th.loading = true;
				axios.get(th.url + '/classTable/selectByName', {
					params: {
						pageNum: page,
						cHeadmasterName: th.classTable.cHeadmasterName
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;

			},
		},
		created() {
			const th = this;
			th.loading = true;
			axios.get(th.url + '/classTable/selectGroupBy')
				.then(function(res) {
					th.classTableGroup = res.data.data;
				})
			axios.get(th.url + '/classTable/selectAllStatus', {
				params: {
					pageNum: 1,
					status: th.status
				}
			}).then(function(res) {
				th.data6 = res.data.data;
				th.classTableName = res.data.data;
				th.count = res.data.count;
			})
			th.loading = false;

		}
	}
</script>
