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
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				columns7: [{
						title: '类型',
						key: 'aType',
						align: 'center'
					},
						{
						title: '成员工作编号',
						key: 'mUser',
						align: 'center'
					},
					{
						title: '成员部门',
						key: 'dName',
						align: 'center'
					},
					{
						title: '标题',
						key: 'aTitile',
						align: 'center',
					},
					{
						title: '时间',
						key: 'aDate',
						align: 'center',
					},{
						title: '操作员',
						key: 'mName',
						width:80,
						align: 'center',
					},
					{
						title: '操作',
						key: 'action',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {

											this.remove(params.row.aId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				attendance: {
					aId: 0,
					aType:"",
					mUser:"",
					dName:"",
					aTitile: "",
					aDate: "",
					mName: "",
				}
			}
		},
		methods: {
			changePage(page) {
				const th = this;
				axios.get(th.url + '/attendance/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					console.log(res);
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			resultMsg(res) {
				if (res.code === 1224) {
					this.attendance.nFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			remove(aId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/attendance/deleteByPrimaryKey', {
							params: {
								aId: aId
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
				axios.post(th.url + '/attendance/updateByPrimaryKey', th.attendance, {
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
			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
