<template>
	<div>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
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
				columns7: [
					{
						title: '姓名',
						key: 'mName',
						 width: 120,
						fixed: 'left',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.mName)
							]);
						}
					},
					{
						title: '成员工号',
						key: 'mUser',
						align: 'center',width: 120,
					},
					{
						title: '联系方式',
						key: 'cPhone',
						align: 'center',width: 120,
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center',width: 100,
					},{
						title: '职位',
						key: 'pName',
						align: 'center',width: 100,
					},
					{
						title: '所属届',
						key: 'eName',
						align: 'center',width: 150,
					},
					{
						title: '权限',
						key: 'jName',
						align: 'center',width: 100,
					},
					{
						title: '班级',
						key: 'cName',
						align: 'center',width: 100,
					},{
						title: '班主任',
						key: 'cHeadmasterName',
						align: 'center',width: 100,
					},{
						title: '班主任联系方式',
						key: 'cPhone',
						align: 'center',width: 150,
					},
					{
						title: 'QQ',
						key: 'mQq',
						align: 'center',width: 120,
					},
					{
						title: '入会时间',
						key: 'rAdmissionDate',
						align: 'center',width: 120,
					},
					{
						title: '退会时间',
						key: 'rTuihuiDate',
						align: 'center',width: 120,
					},
					{title: '备注',
					key: 'pRemarks',
					width: 250,
					align: 'center',
					render: (h, params) => {
						return h('div', [
							h('Icon', {
								props: {
									type: 'person'
								}
							}),
							h('strong', params.row.pRemarks)
						]);
					},
					},
					{
						title: '操作',
						key: 'action',
						width: 150,
						fixed: 'right',
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
					},{
						title: '状态',
						key: 'status',
						align: 'center',
						fixed: 'right',
						width: 100,
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
											axios.get(th.url + '/memberInformation/updateStatus', {
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

					}
				],
				data6: [],
				memberInformation: {
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
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/memberInformation', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
				
					th.data6 = res.data.data;
					th.count = res.data.count;
					console.log(th.data6);

				})
			},

			remove(dId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/memberInformation/deleteByPrimaryKey', {
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
				axios.post(th.url + '/memberInformation/updateByPrimaryKey', th.memberInformation, {
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
