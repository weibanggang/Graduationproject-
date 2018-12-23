<template>
	<div>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="修改备注" @on-ok="ok" @on-cancel="cancel">
			<Form :model="MonthlyPlanSummary" :label-width="80">
				<FormItem>
					<Input v-model="MonthlyPlanSummary.mRemarks" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="备注"></Input>
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
				columns7: [{
						title: '标题',
						key: 'mTitle',
						align: 'center'
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center',
					},
					{
						title: '上传时间',
						key: 'mDate',
						align: 'center',
					},
					{
						title: '备注',
						key: 'mRemarks',
						align: 'center',
					},
					{
						title: '操作员',
						key: 'mName',
						align: 'center'
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

										this.remove(params.row.mId, params.index)
									}
								}
							}, '移除')
						]);
					}
				}
			],
			data6: [],
				MonthlyPlanSummary: {
					mId: 0,
					mTitle: "",
					dId: "",
					mDate: "",
					mFile: "",
					mRemarks: "",
					mContexts: "",
					mName: ""

				},
				mRemarks: ""
		}
	},
	methods: {
			show(data) {
				this.modal13 = true;
				this.MonthlyPlanSummary = data;
				this.mRemarks = data.mRemarks;

			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/monthlyPlanSummary/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					const resdata = res.data.data.map( (e) => {
						e.dName = e.departmenttype.dName;
						return e;
					})

					th.data6 = resdata;
					th.count = res.data.count;
				})
			},

			remove(mId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/monthlyPlanSummary/deleteByPrimaryKey', {
							params: {
								mId: mId
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
				axios.post(th.url + '/monthlyPlanSummary/updateByPrimaryKey', th.MonthlyPlanSummary, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
				
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
						th.MonthlyPlanSummary.mRemarks = th.mRemarks;
					}
				})
			},
			cancel() {
				this.MonthlyPlanSummary.mRemarks = this.mRemarks;
			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
