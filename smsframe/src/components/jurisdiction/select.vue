<template>
	<div>
		
		<div class="rigtop">
			<!-- <Form ref="FinancialManagement" :model="FinancialManagement" inline>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
							<Checkbox v-model="FinancialManagement.CmName" label="">操作人</Checkbox>
						</Col>
						<Col span="16" >
						<Select v-model="FinancialManagement.mName" filterable>
							<Option v-for="item in FinancialManagementGroup" :value="item.mName" :key="item.mName">{{ item.mName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem prop="user">
					<Row>
						<Col span="7" style="text-align: center;">
							<Checkbox v-model="FinancialManagement.CDate" label="">交易时间</Checkbox>
						</Col>
						<Col span="17">
						<DatePicker type="daterange" placement="bottom-end" placeholder="Select date" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<RadioGroup v-model="FinancialManagement.fType">
						<Radio label="全部">
							<Icon type="ios-football-outline" />
							<span>全部</span>
						</Radio>
						<Radio label="收入">
							<Icon type="md-log-in" />
							<span>收入</span>
						</Radio>
						<Radio label="支出">
							<Icon type="md-log-out" />
							<span>支出</span>
						</Radio>
					</RadioGroup>
				</FormItem>
				<FormItem>
					<Button>快速查询</Button>
				</FormItem>
				<FormItem>
					<Button>快速导出</Button>
				</FormItem>
			</Form> -->
		</div>
		
		
		<!-- <Form ref="formValidate" :model="jurisdiction"  :label-width="80">
		<FormItem>
				<Button type="success"  @click="add()" long>添加</Button>
		</FormItem>
		</Form> -->
		<Table border :columns="columns7" :data="data6" height="520" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="编辑权限类型" @on-ok="ok">
			<Form ref="formValidate" :model="jurisdiction"  :label-width="80">
				<FormItem label="权限名称" prop="jName">
					<Input v-model="jurisdiction.jName" placeholder="请输入权限名称"></Input>
				</FormItem>
				<FormItem label="权限状态" rop="status" >
				<i-switch v-model="jurisdiction.status" size="large">
                <span slot="open">正常</span>
                <span slot="close">冻结</span>
				</i-switch>
				</FormItem>
				<FormItem  label="权限备注" prop="jRemarks">
					<Input v-model="jurisdiction.jRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入权限备注"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="添加权限类型" @on-ok="oks">
			<Form ref="formValidate" :model="jurisdiction"  :label-width="80">
				<FormItem label="权限名称" prop="jName">
					<Input v-model="jurisdiction.jName" placeholder="请输入权限名称"></Input>
				</FormItem>
				<FormItem label="权限状态" rop="status" >
				<i-switch v-model="jurisdiction.status" size="large">
				<span slot="open">正常</span>
				<span slot="close">冻结</span>
				</i-switch>
				</FormItem>
				<FormItem  label="权限备注" prop="jRemarks">
					<Input v-model="jurisdiction.jRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入权限备注"></Input>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				modal14: false,
				columns7: [{
						title: '权限编号',
						key: 'jId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.jId)
							]);
						}
					},
					{
						title: '权限名称',
						key: 'jName',
						align: 'center',
					},
					{
						title: '权限状态',
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
											axios.get(th.url + '/jurisdiction/updateStatus', {
												params: {
													jId: params.row.jId,
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
						title: '权限备注',
						key: 'jRemarks',
						align: 'center',
						width:350,
						tooltip:true
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
											this.remove(params.row.jId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				jurisdiction: {
					jId: 0,
					jName: "",
					status: true,
					jRemarks: ""
				}
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.jurisdiction.jId=data.jId;
				this.jurisdiction.jName=data.jName;
				this.jurisdiction.status=data.status=="true";
				this.jurisdiction.jRemarks=data.jRemarks;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/jurisdiction/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			add(){
				this.jurisdiction.jName="";
				this.jurisdiction.jRemarks="";
				this.modal14=true;
				},
			remove(jId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/jurisdiction/deleteByPrimaryKey', {
							params: {
								jId: jId
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
				console.log(th);
				axios.post(th.url + '/jurisdiction/updateByPrimaryKey', th.jurisdiction, {
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
				console.log(th);
				axios.post(th.url + '/jurisdiction/insert', th.jurisdiction, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal14=true;
					}
				})
			},
		},
		created() {
			this.changePage(1);
		}
	}
</script>
