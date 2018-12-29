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
		
		
		<!-- <Form ref="formValidate" :model="departmentalExcellenceRecord" :label-width="80">
			<FormItem>
				<Button type="success" @click="add()" long>添加</Button>
			</FormItem>
		</Form> -->
		<Table border :columns="columns7" :data="data6" height="520" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal14" draggable scrollable title="添加部门评优" @on-ok="oks">
			<div>
				<Form ref="formInline" :model="departmentalExcellenceRecord" :label-width="80">
									<FormItem label="部门" prop="dId">
										<Select v-model="departmentalExcellenceRecord.dName" placeholder="请选择部门" >
										<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
										</Select>
									</FormItem>
									<FormItem label="标题">
										<Input v-model="departmentalExcellenceRecord.dTitle" placeholder="标题"></Input>
									</FormItem>
									<FormItem label="时间">
										<Row>
											<Col span="11">
											<FormItem prop="rAdmissionDate">
												<DatePicker type="date" placeholder="请选择时间" v-model="departmentalExcellenceRecord.dDate" @on-change="getStartTime"></DatePicker>
											</FormItem>
											</Col>
										</Row>
									</FormItem>
				
									<FormItem label="内容" prop="dContexts">
										<Input v-model="departmentalExcellenceRecord.dContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
									</FormItem>
									<FormItem label="文件上传">
										<div>
											<Row>
												<Col span="12">
												<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/departmentalExcellenceRecord/upload">
													<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
												</Upload>
												</Col>
												<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="departmentalExcellenceRecord.dFile" disabled placeholder="没有文件" /></Col>
											</Row>
										</div>
									</FormItem>
								</Form>
			</div>
		</Modal>
		<Modal v-model="modal13" draggable scrollable title="编辑部门评优" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="departmentalExcellenceRecord" :label-width="80">
					<FormItem label="部门名称">
						<Input v-model="departmentalExcellenceRecord.dName" disabled ></Input>
					</FormItem>
					<FormItem label="标题">
						<Input v-model="departmentalExcellenceRecord.dTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择时间" v-model="departmentalExcellenceRecord.dDate" @on-change="getStartTime"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>

					<FormItem label="内容" prop="dContexts">
						<Input v-model="departmentalExcellenceRecord.dContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/departmentalExcellenceRecord/upload">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="departmentalExcellenceRecord.dFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
				</Form>
			</div>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				url: 'http://localhost:8080/',
				count: 10,
				modal14: false,
				modal13: false,
				loading:false,
				menberInforMation: [],
				departmentType: '',
				columns7: [{
						title: '部门名称',
						key: 'dName',
						align: 'center',
					},
					{
						title: '标题',
						key: 'dTitle',
						align: 'center',
					},
					{
						title: '时间',
						key: 'dDate',
						align: 'center',
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '内容',
						key: 'dContexts',
						width: 200,
						tooltip:true,
						align: 'center'
					},
					{
						title: '操作',
						key: 'action',
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
				departmentalExcellenceRecord: {
					dId: 0,
					dName: "",
					dTitle: "",
					dDate: "",
					dFile: "",
					mName: "",
					dContextss: ""
				}
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.departmentalExcellenceRecord.dId = data.dId;
				this.departmentalExcellenceRecord.dName = data.dName;
				this.departmentalExcellenceRecord.dTitle = data.dTitle;
				this.departmentalExcellenceRecord.dDate = data.dDate;
				this.departmentalExcellenceRecord.dFile = data.dFile;
				this.departmentalExcellenceRecord.dContexts = data.dContexts;
				this.departmentalExcellenceRecord.mName = data.mName;
			},
			getStartTime(starTime) {
				this.departmentalExcellenceRecord.dDate = starTime;
			},
			selectMname(value) {
				this.loading = true;
				const th = this;
				console.log("hgjh");
				console.log(th.url);
				axios.get(th.url + 'memberInformation/iUserName', {
					params: {
						mUser: value
					}
				}).then(function(res) {
					th.menberInforMation = res.data.data;
					for (let s of res.data.data) {
						th.departmentalExcellenceRecord.lMName = s.mName;
					}
					th.loading = false;
				})
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + 'departmentalExcellenceRecord/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			resultMsg(res) {
				if (res.code === 1224) {
					this.departmentalExcellenceRecord.dFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			remove(dId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + 'departmentalExcellenceRecord/deleteByPrimaryKey', {
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
				axios.post(th.url + 'departmentalExcellenceRecord/updateByPrimaryKey', th.departmentalExcellenceRecord, {
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
			},
			add() {
				this.departmentalExcellenceRecord.dName = "";
				this.departmentalExcellenceRecord.lMName = "";
				this.departmentalExcellenceRecord.dTitle = "";
				this.departmentalExcellenceRecord.dFile = "无路径";
				this.departmentalExcellenceRecord.dContexts = "";
				this.modal14 = true;
			},
			oks() {
				const th = this;
				axios.post(th.url + 'departmentalExcellenceRecord/insert', th.departmentalExcellenceRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.changePage(1);
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
						th.modal14=true;
					}
				})
			}
		},
		created() {
			const th=this;
			axios.get(th.url + 'departmentType/iSelectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
			this.changePage(1);
			
		}
	}
</script>
