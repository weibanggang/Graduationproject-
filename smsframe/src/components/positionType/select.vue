<template>
	<div>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="职位类型" @on-ok="ok" @on-cancel="cancel">
			<Form ref="formValidate" :model="positionType"  :label-width="80">
				<FormItem label="职位名称" prop="pName">
					<Input v-model="positionType.pName" placeholder="请输入职位名称"></Input>
				</FormItem>
				<FormItem label="职位状态" rop="status" >
				<i-switch v-model="positionType.status" size="large">
                <span slot="open">正常</span>
                <span slot="close">冻结</span>
				</i-switch>
				</FormItem>
				<FormItem  label="职位备注" prop="pRemarks">
					<Input v-model="positionType.pRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入职位备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="dSort">
					<InputNumber :max="100" :min="1" v-model="positionType.dSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				title:"添加职位类型",
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				columns7: [{
						title: '职位编号',
						key: 'pId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.pId)
							]);
						}
					},
					{
						title: '职位名称',
						key: 'pName',
						align: 'center',
					},
					{
						title: '职位状态',
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
											axios.get(th.url + '/positionType/updateStatus', {
												params: {
													pId: params.row.pId,
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
						title: '备注备注',
						key: 'pRemarks',
						align: 'center',
					},
					{
						title: '排序',
						key: 'pSort',
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
											this.remove(params.row.pId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				positionType: {
					pId: 0,
					pName: "",
					status: true,
					pRemarks: "",
					pSort: 1
				}
			}
		},
		methods: {
			show(data) {
				
				this.modal13 = true;
				this.positionType.pId=data.pId;
				this.positionType.pName=data.pName;
				this.positionType.status=data.status=="true";
				this.positionType.pRemarks=data.pRemarks;
				this.positionType.pSort=data.pSort;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/positionType/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},

			remove(pId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/positionType/deleteByPrimaryKey', {
							params: {
								pId: pId
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
				console.log(th);
				axios.post(th.url + '/positionType/updateByPrimaryKey', th.positionType, {
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
