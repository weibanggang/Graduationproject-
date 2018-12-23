<template>
	<div>
		<div class="rigtop">
			<Form ref="FinancialManagement" :model="FinancialManagement" inline>
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
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="520" stripe size='default'></Table>
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
						title: '编号',
						tableHeight: 450,
						key: 'fId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.fId)
							]);
						}
					}, {
						title: '当前余额',
						key: 'fAfterMoney',
						align: 'center',
					},
					{
						title: '类型',
						key: 'fType',
						align: 'center',
					},
					{
						title: '交易前余额',
						key: 'fFrontMoney',
						align: 'center',
						"sortable": true
					},
					{
						title: '交易金额',
						key: 'fMoney',
						align: 'center',
					},

					{
						title: '交易时间',
						key: 'fDate',
						align: 'center',
					},
					{
						title: '操作时间',
						key: 'fMrDate',
						align: 'center',
						tooltip: true,
						width: 120
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '备注',
						key: 'fRemarks',
						align: 'center',
						tooltip: true,
						width: 100
					}
				],
				data6: [],
				FinancialManagementGroup: '',
				FinancialManagement: {
					fId: 0,
					fType: "全部",
					fFrontMoney: "",
					fAfterMoney: "",
					fMoney: "",
					fDate: "",
					fMrDate: "",
					fRemarks: "",
					fFile: "",
					mName: "",
					CmName:false,
					CDate:false
				},
				aRemarks: ""
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/financialManagement/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})

			},
			GroupType() {
				const th = this;
				axios.get(th.url + '/financialManagement/selectGroupBymName').then(function(res) {
					th.FinancialManagementGroup = res.data.data;
				})
			}
		},
		created() {
			this.changePage(1);
			this.GroupType();
		}
	}
</script>
