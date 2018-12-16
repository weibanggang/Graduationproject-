<template>
	<div>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="部门类型" @on-ok="ok" @on-cancel="cancel">
			<Form ref="formValidate" :model="DepartmentType"  :label-width="80">
				<FormItem label="部门名称" prop="dName">
					<Input v-model="DepartmentType.dName" placeholder="请输入部门名称"></Input>
				</FormItem>
				<FormItem label="部门简介" prop="dSynopsis">
					<Input v-model="DepartmentType.dSynopsis" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入部门简介"></Input>
				</FormItem>
				<FormItem label="部门状态" rop="status" >
				<i-switch v-model="DepartmentType.status" size="large">
                <span slot="open">正常</span>
                <span slot="close">冻结</span>
				</i-switch>
				</FormItem>
				
				<FormItem  label="部门备注" prop="dRemarks">
					<Input v-model="DepartmentType.dRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入部门备注"></Input>
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
				title:"编辑部门类型",
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
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
					},
					{
						title: '部门备注',
						key: 'dRemarks',
						align: 'center',
					},
					{
						title: '排序',
						key: 'dSort',
						align: 'center',
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
			show(data) {
				console.log(data.status);
				this.modal13 = true;
				this.DepartmentType.dId=data.dId;
				this.DepartmentType.dName=data.dName;
				this.DepartmentType.status=data.status=="true";
				this.DepartmentType.dSynopsis=data.dSynopsis;
				this.DepartmentType.dRemarks=data.dRemarks;
				this.DepartmentType.dSort=data.dSort;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/departmentType/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},

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
					}
				})
			},
			cancel() {

			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
