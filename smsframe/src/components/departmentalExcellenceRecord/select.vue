<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		
		<div class="rigtop">
			<Form  inline>
				<FormItem >
					<Row>
						<Col span="6" style="text-align: center;">
						<Checkbox v-model="dname" label="">部门</Checkbox>
						</Col>
						<Col span="18">
						<Select v-model="departmentalExcellenceRecord.dName"   filterable>
							<Option v-for="item in departmentType" :value="item.dName" :key="item.dId">{{ item.dName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem >
					<Row>
						<Col span="6" style="text-align: center;">
						<Checkbox v-model="dates" label="">时间</Checkbox>
						</Col>
						<Col span="18">
						<DatePicker type="daterange" placement="bottom-end" @on-change="selectTime(($event))" placeholder="时间查询" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 30px">
						<Button @click="select(1)">
							<Icon type="ios-sync" />快速查询
						</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-sync" />新增信息
						</Button>
					</FormItem>
					<FormItem>
						<Button @click="exportData()">
							<Icon type="ios-download-outline" />数据导出
						</Button>
					</FormItem>
				</FormItem>
			</Form>
		</div>
		<Table border :columns="columns7" :data="data6" height="450" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="select($event)"></Page>
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
												<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/departmentalExcellenceRecord">
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
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/departmentalExcellenceRecord">
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
				url: 'http://47.100.245.30:8080/',
				count: 10,
				dname:false,
				dates:false,
				modal14: false,
				modal13: false,
				loading:false,
				baDate:[],
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
				//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '部门评优信息'
				});
			},
			//根据名称查询
			select(page) {
				
				if(this.baDate.length != 2 || !this.dates){
					this.baDate = ["",""];
				}
				if(!this.dname){
					this.departmentalExcellenceRecord.dName = "";
				}
				const th = this;
				axios.get(th.url + '/departmentalExcellenceRecord/selects', {
					params: {
						pageNum: page,
						dName:th.departmentalExcellenceRecord.dName,
						beforeDate:th.baDate[0],
						afterDate:th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				this.loading = false;
			},
			//时间间隔查询
			selectTime(value) {
				this.baDate = value;
			},
			//编辑弹出
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
			//时间
			getStartTime(starTime) {
				this.departmentalExcellenceRecord.dDate = starTime;
			},
			//成员根据编号查询姓名
			selectMname(value) {
				this.loading = true;
				const th = this;
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
			
			//上传文件
			resultMsg(res) {
				if (res.code === 1224) {
					this.departmentalExcellenceRecord.dFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//删除
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
								th.select(1);
							} else {
								th.$Message.error(res.data.message);
							}
						})
					}
				});
			},
			//修改
			ok() {
				const th = this;
				axios.post(th.url + 'departmentalExcellenceRecord/updateByPrimaryKey', th.departmentalExcellenceRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.select(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal13 = true;
					}
				})
			},
			//添加弹出
			add() {
				this.departmentalExcellenceRecord.dName = "";
				this.departmentalExcellenceRecord.lMName = "";
				this.departmentalExcellenceRecord.dTitle = "";
				this.departmentalExcellenceRecord.dFile = "无路径";
				this.departmentalExcellenceRecord.dContexts = "";
				this.modal14 = true;
			},
			//添加
			oks() {
				const th = this;
				th.departmentalExcellenceRecord.mName = localStorage.getItem("mName");
				axios.post(th.url + 'departmentalExcellenceRecord/insert', th.departmentalExcellenceRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.select(1);
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
			axios.get(th.url + 'departmentType/iselectAllStatus').then(function(res) {
				th.departmentType = res.data.data;
			})
			this.select(1);
			
		}
	}
</script>
