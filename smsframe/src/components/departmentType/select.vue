<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>

		<div class="rigtop">
			<Form :model="DepartmentType" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						类型名称
						</Col>
						<Col span="18">
						<Select v-model="DepartmentType.dId" filterable @on-change="selectDName()">
							<Option v-for="item in DepartmentTypeName" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<RadioGroup v-model="status" @on-change="changePage(1)">
						<Radio label="all">
							<Icon type="ios-football-outline" />
							<span>全部</span>
						</Radio>
						<Radio label="true">
							<Icon type="ios-eye" />
							<span>正常</span>
						</Radio>
						<Radio label="false">
							<Icon type="ios-eye-off" />
							<span>冻结</span>
						</Radio>
					</RadioGroup>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="insert()"><Icon type="ios-add-circle-outline" />新增部门</Button>
					</FormItem>
					<Button @click="exportData()"><Icon type="ios-download-outline"/>数据导出</Button>
				</FormItem>
			</Form>
		</div>

		<Table  border :columns="columns7" :data="data6" height="450" :loading="loading" ref="table"  stripe size='default'></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="部门类型" @on-ok="ok">
			<Form ref="formValidate" :model="DepartmentType" :label-width="80">
				<FormItem label="部门名称" prop="dName">
					<Input v-model="DepartmentType.dName" placeholder="请输入部门名称"></Input>
				</FormItem>
				<FormItem label="部门简介" prop="dSynopsis">
					<Input v-model="DepartmentType.dSynopsis" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入部门简介"></Input>
				</FormItem>
				<FormItem label="部门状态" rop="status">
					<i-switch v-model="DepartmentType.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>

				<FormItem label="部门备注" prop="dRemarks">
					<Input v-model="DepartmentType.dRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入部门备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="dSort">
					<InputNumber :max="100" :min="1" v-model="DepartmentType.dSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" :styles="{top: '40px'}" draggable scrollable title="新增部门"  @on-ok="add">
					<Form ref="formValidate" :model="DepartmentType" :label-width="80">
						<FormItem label="部门名称" prop="dName">
							<Input v-model="DepartmentType.dName" placeholder="请输入部门名称"></Input>
						</FormItem>
						<FormItem label="部门简介" prop="dSynopsis">
							<Input v-model="DepartmentType.dSynopsis" type='textarea' :autosize="{minRows: 4,maxRows: 6}" placeholder="请输入部门简介"></Input>
						</FormItem>
						<FormItem label="部门状态" rop="status">
							<i-switch v-model="DepartmentType.status" size="large">
								<span slot="open">正常</span>
								<span slot="close">冻结</span>
							</i-switch>
						</FormItem>
		
						<FormItem label="部门备注" prop="dRemarks">
							<Input v-model="DepartmentType.dRemarks" type='textarea' :autosize="{minRows: 4,maxRows: 6}" placeholder="请输入部门备注"></Input>
						</FormItem>
						<FormItem label="排序" prop="dSort">
							<InputNumber :max="100" :min="1" v-model="DepartmentType.dSort"></InputNumber>
						</FormItem>
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
				modal13: false,
				modal14:false,
				loading:true,
				status: "all",
				DepartmentTypeName: "",
				columns7: [{
						title: '部门编号',
						key: 'dId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.dId)
							]);
						}
					},
					{
						title: '类型名称',
						key: 'dName',
						align: 'center',
					},
					{
						title: '部门状态',
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
											axios.get(th.url + '/departmentType/updateStatus', {
												params: {
													dId: params.row.dId,
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
						title: '部门简介',
						key: 'dSynopsis',
						align: 'center',
						width: 250,
						tooltip: true
					},
					{
						title: '部门备注',
						key: 'dRemarks',
						align: 'center',
						width: 250,
						tooltip: true
					},
					{
						title: '排序',
						key: 'dSort',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('InputNumber', {
									props: {
										size: "large",
										value: params.row.dSort
									},
									on: {
										'on-change': (value) => {
											this.DepartmentType.dId = params.row.dId;
											this.DepartmentType.dSort = value;
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
						width: 250,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'warning',
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
											this.remove(params.row.dId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				DepartmentType: {
					dId: 0,
					dName: "",
					status: true,
					dSynopsis: "",
					dRemarks: "",
					dSort: 1
				}
			}
		},
		methods: {
			//导出数据
			exportData(){
				this.$refs.table.exportCsv({
                        filename: '部门类型表'
                    });
			},
			//弹出
			insert(){
				this.modal14=true;
			},
			//根据标题查询
			selectDName(){
				const th = this;
				axios.get(th.url + '/departmentType/selectAllVague', {
					params: {
						dName: th.DepartmentType.dName
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				},
			//弹出层
			show(data) {
				console.log(data.status);
				this.modal13 = true;
				this.DepartmentType.dId = data.dId;
				this.DepartmentType.dName = data.dName;
				this.DepartmentType.status = data.status == "true";
				this.DepartmentType.dSynopsis = data.dSynopsis;
				this.DepartmentType.dRemarks = data.dRemarks;
				this.DepartmentType.dSort = data.dSort;
			},
			//根据状态查询+页数
			changePage(page) {
				const th = this;
				this.loading=true;
				axios.get(th.url + '/departmentType/selectAllStatus', {
					params: {
						pageNum: page,
						status:th.status
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
					th.loading=false;
				})
				
			},
			//修改序号
			updateSort() {
				const th = this;
				th.loading=true;
				axios.post(th.url + '/departmentType/updateSort', th.DepartmentType, {
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
			//删除
			remove(dId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/departmentType/deleteByPrimaryKey', {
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
			//编辑
			ok() {
				const th = this;
				axios.post(th.url + '/departmentType/updateByPrimaryKey', th.DepartmentType, {
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
			//添加
			add(){
					const th=this;
					axios.post(th.url+'/departmentType/insert',th.DepartmentType,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.DepartmentType.dName="";
							th.DepartmentType.dSynopsis="";
							th.DepartmentType.dRemarks="";
							th.DepartmentType.dSort="";
							th.changePage(1);
						}else{
							th.$Message.error(res.data.message);
							th.modal14=true;
						}
					})
					
				}
			
		},
		created() {
			this.changePage(1);
			const th = this;
			axios.get(th.url + '/departmentType/iselectAllStatus')
			.then(function(res) {
				th.DepartmentTypeName = res.data.data;
			})
		}
	}
</script>
