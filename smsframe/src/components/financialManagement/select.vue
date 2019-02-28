<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form :model="FinancialManagement" inline>
				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="FinancialManagement.CmName" label="">操作人</Checkbox>
						</Col>
						<Col span="16">
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
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(($event))" placeholder="时间" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<RadioGroup v-model="fType">
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
					<Button @click="changePage(1)">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">

					<FormItem>
						<Button @click="insert()">
							<Icon type="ios-add-circle-outline" />添加记录
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
		</div>

		<Table border :columns="columns7" :data="data6" height="450" stripe size='default' ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" :styles="{top: '40px'}" draggable scrollable title="交易记录" @on-ok="add">
			<Form :model="FinancialManagement" >
				<FormItem prop="aUsername" label="金额">
					<div>
						<InputNumber
            :max="10000"
            v-model="FinancialManagement.fMoney"
            :formatter="value => `￥ ${value}`.replace(/B(?=(d{3})+(?!d))/g, ',')"
            :parser="value => value.replace(/￥s?|(,*)/g, '')"></InputNumber>
					</div>
				</FormItem>
				<FormItem label="类型">
					<RadioGroup v-model="FinancialManagement.fType">
						<Radio label="支出"></Radio>
						<Radio label="收入"></Radio>
					</RadioGroup>
				</FormItem>
				<FormItem label="时间">
					<Row>
						<Col span="11">
						<FormItem prop="rAdmissionDate">
							<DatePicker type="date" placeholder="请选择时间"  @on-change="getStartTime(($event))" v-model="FinancialManagement.fDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem label="文件上传">
					<div>
						<Row>
							<Col span="6">
							<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/financialManagement">
								<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
							</Upload>
							</Col>
							<Col span="6"><Input icon="ios-cloud-upload-outline" v-model="FinancialManagement.fFile" disabled placeholder="没有文件" /></Col>
						</Row>
					</div>
				</FormItem>
				<FormItem label="备注">
					<Row>
						<Col span="18">
					<Input v-model="FinancialManagement.fRemarks" type="textarea" :autosize="{minRows: 5,maxRows: 8}" placeholder="备注"></Input>
						</Col>
					</Row>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				url: "http://47.100.245.30:8080",
				count: 10,
				fType: "全部",
				baDate: [],
				modal13: false,
				columns7: [{
						title: '编号',
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
						width: 120
					}
				],
				data6: [],
				FinancialManagementGroup: '',
				FinancialManagement: {
					fId: 0,
					fType: "支出",
					fFrontMoney: "",
					fAfterMoney: "",
					fMoney: 0,
					fDate: "",
					fMrDate: "",
					fRemarks: "",
					fFile: "",
					mName: "",
					CmName: false,
					CDate: false
				},
				aRemarks: ""
			}
		},
		methods: {
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '余额记录'
				});
			},
			//时间
			getStartTime(starTime) {
				this.FinancialManagement.fDate = starTime;
			},
			//添加弹出框
			insert() {
				this.FinancialManagement.fMoney = 0;
				this.FinancialManagement.fFile = "";
				this.FinancialManagement.fRemarks = "";
				this.modal13 = true;
			},
			//间隔时间
			selectTime(starTime) {
				this.baDate = starTime;
			},
			//编辑弹出
			show(data) {
				this.modal13 = true;
			},
			//添加
			add() {
				const th = this;
				th.FinancialManagement.mName = localStorage.getItem("mName");
				axios.post(th.url + '/financialManagement/insert', th.FinancialManagement, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.FinancialManagement.fMoney = 0;
						th.FinancialManagement.fFile = "";
						th.FinancialManagement.fRemarks = "";
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
					}
				})

			},
			//上传文件
			resultMsg(res) {
				if (res.code === 1224) {
					this.FinancialManagement.fFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//查询
			changePage(page) {
				this.loading = true;
				if (!this.FinancialManagement.CmName) {
					this.FinancialManagement.mName = null;
				}
				if (!this.FinancialManagement.CDate) {
					this.baDate = ["", ""];
				}
				if (this.baDate.length == 0) {
					this.baDate = ["", ""];
				}
				const th = this;
				var fType = this.fType;
				if (fType == "全部") {
					fType = "";
				}
				axios.get(th.url + '/financialManagement/selects', {
					params: {
						pageNum: page,
						mName: th.FinancialManagement.mName,
						fType: fType,
						beforeDate: th.baDate[0],
						afterDate: th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;

			},
			//分组查询
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
