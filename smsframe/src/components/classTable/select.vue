<template>
	<div>
		<Form ref="formValidate" :model="classTable" :label-width="80">
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
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				modal14: false,
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
			show(data) {
				this.modal13 = true;
				this.classTable.cId = data.cId;
				this.classTable.cName = data.cName;
				this.classTable.status = data.status=="true";
				this.classTable.cHeadmasterName = data.cHeadmasterName;
				this.classTable.cPhone = data.cPhone;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/classTable/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},

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
			clone(){
				this.classTable.cName="";
				this.classTable.cHeadmasterName="";
				this.classTable.cPhone="";
			},
			add() {
				this.clone();
				this.modal14 = true;
			},
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
			},//添加
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
