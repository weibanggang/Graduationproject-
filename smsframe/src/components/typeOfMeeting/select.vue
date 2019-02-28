<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form  :model="typeofMeeting" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
							会议名称
						</Col>
						<Col span="18" >
						<Select v-model="typeofMeeting.tId" @on-change="selectName()" filterable>
							<Option v-for="item in typeofMeetingName" :value="item.tId" :key="item.tId">{{ item.tName }}</Option>
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
							<Icon type="ios-add-circle-outline" />新增部门
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
		<Modal v-model="modal13" draggable scrollable title="会议类型" @on-ok="ok">
			<Form ref="formValidate" :model="typeofMeeting" :label-width="80">
				<FormItem label="会议名称" prop="tName">
					<Input v-model="typeofMeeting.tName" placeholder="请输入会议名称"></Input>
				</FormItem>
				<FormItem label="会议状态" rop="status">
					<i-switch v-model="typeofMeeting.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="会议备注" prop="tRemarks">
					<Input v-model="typeofMeeting.tRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入会议备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="tSort">
					<InputNumber :max="100" :min="1" v-model="typeofMeeting.tSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="会议类型" @on-ok="oks">
			<Form ref="formValidate" :model="typeofMeeting" :label-width="80">
				<FormItem label="会议名称" prop="tName">
					<Input v-model="typeofMeeting.tName" placeholder="请输入会议名称"></Input>
				</FormItem>
				<FormItem label="会议状态" rop="status">
					<i-switch v-model="typeofMeeting.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="会议备注" prop="tRemarks">
					<Input v-model="typeofMeeting.tRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入会议备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="tSort">
					<InputNumber :max="100" :min="1" v-model="typeofMeeting.tSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				title: "添加会议类型",
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				loading:true,
				status: "true",
				modal14: false,
				typeofMeetingName:"",
				columns7: [{
						title: '会议编号',
						key: 'tId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.tId)
							]);
						}
					},
					{
						title: '会议名称',
						key: 'tName',
						align: 'center',
					},
					{
						title: '会议状态',
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
											axios.get(th.url + '/typeofMeeting/updateStatus', {
												params: {
													tId: params.row.tId,
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
						title: '会议备注',
						key: 'tRemarks',
						align: 'center',
						width:250,
						tooltip:true
					},
					{
						title: '排序',
						key: 'tSort',
						align: 'center',
						render: (h, params) => {
													return h('div', [
														h('InputNumber', {
															props: {
																size: "large",
																value: params.row.tSort
															},
															on: {
																'on-change': (value) => {
																	this.typeofMeeting.tId = params.row.tId;
																	this.typeofMeeting.tSort = value;
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
											this.remove(params.row.tId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				typeofMeeting: {
					tId: 0,
					tName: "",
					status: true,
					tRemarks: "",
					tSort: 1
				}
			}
		},
		methods: {
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '会议信息'
				});
			},
			//编辑弹出
			show(data) {
				this.modal13 = true;
				this.typeofMeeting.tId = data.tId;
				this.typeofMeeting.tName = data.tName;
				this.typeofMeeting.status = data.status == "true";
				this.typeofMeeting.tRemarks = data.tRemarks;
				this.typeofMeeting.tSort = data.tSort;
			},
			//根据名称查询
			selectName(){
				const th = this;
				axios.get(th.url + '/typeofMeeting/selectByPrimaryKey', {
					params: {
						tId:th.typeofMeeting.tId
					}
				}).then(function(res) {
					th.data6 = [];
					th.data6.push(res.data.data);
					th.count = res.data.count;
				})
			},
			//修改序号
			updateSort() {
				const th = this;
				th.loading=true;
				axios.post(th.url + '/typeofMeeting/updateSort', th.typeofMeeting, {
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
			//查询
			changePage(page) {
				const th = this;
				th.loading = true;
				axios.get(th.url + '/typeofMeeting/selectAllStatus', {
					params: {
						pageNum: page,
						status:th.status
					}
				}).then(function(res) {
					console.log(res.data);
					th.data6 = res.data.data;
					th.count = res.data.count;
					th.loading = false;
				})
			},
			//删除
			remove(tId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/typeofMeeting/deleteByPrimaryKey', {
							params: {
								tId: tId
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
				axios.post(th.url + '/typeofMeeting/updateByPrimaryKey', th.typeofMeeting, {
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
				axios.post(th.url + '/typeofMeeting/insert', th.typeofMeeting, {
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
			//添加弹出
			insert() {
				this.typeofMeeting.tName = "";
				this.typeofMeeting.tRemarks = "";
				this.modal14 = true;
			},
		},
		created() {
			this.changePage(1);
			const th = this;
			axios.get(th.url + '/typeofMeeting/iselectAllStatus')
			.then(function(res) {
				th.typeofMeetingName = res.data.data;
			})
		}
	}
</script>
