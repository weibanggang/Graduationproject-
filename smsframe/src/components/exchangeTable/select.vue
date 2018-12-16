<template>
	<div>
		<Form ref="formValidate" :model="exchangeTable" :label-width="80">
			<FormItem>
				<Button type="success" @click="add()" long>添加</Button>
			</FormItem>
		</Form>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="会议类型" @on-ok="ok">
			<Form ref="formValidate" :model="exchangeTable" :label-width="80">
				<FormItem label="会议名称" prop="eName">
					<Input v-model="exchangeTable.eName" placeholder="请输入会议名称"></Input>
				</FormItem>
				<FormItem label="会议状态" rop="status">
					<i-switch v-model="exchangeTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="会议备注" prop="eRemarks">
					<Input v-model="exchangeTable.eRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入会议备注"></Input>
				</FormItem>
				<FormItem label="排序" prop="eSort">
					<InputNumber :max="100" :min="1" v-model="exchangeTable.eSort"></InputNumber>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="会议类型" @on-ok="oks">
			<Form ref="formValidate" :model="exchangeTable" :label-width="80">
				<FormItem label="会议名称" prop="eName">
					<Input v-model="exchangeTable.eName" placeholder="请输入会议名称"></Input>
				</FormItem>
				<FormItem label="会议状态" rop="status">
					<i-switch v-model="exchangeTable.status" size="large">
						<span slot="open">正常</span>
						<span slot="close">冻结</span>
					</i-switch>
				</FormItem>
				<FormItem label="会议备注" prop="eRemarks">
					<Input v-model="exchangeTable.eRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入会议备注"></Input>
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
				title: "添加会议类型",
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				modal14: false,
				columns7: [{
						title: '会议编号',
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
						title: '会议名称',
						key: 'eName',
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
						title: '会议备注',
						key: 'eRemarks',
						align: 'center',
					},
					{
						title: '排序',
						key: 'eSort',
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
			show(data) {
				this.modal13 = true;
				this.exchangeTable.eId = data.eId;
				this.exchangeTable.eName = data.eName;
				this.exchangeTable.status = data.status == "true";
				this.exchangeTable.eRemarks = data.eRemarks;
				this.exchangeTable.eSort = data.eSort;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/exchangeTable/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},

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
			ok() {
				const th = this;
				console.log(th);
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
			oks() {
				const th = this;
				console.log(th);
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
			add() {
				this.exchangeTable.eName = "";
				this.exchangeTable.eRemarks = "";
				this.modal14 = true;
			},
		},
		created() {
			this.changePage(1);
		}
	}
</script>
