<template>
	<div>
		
		<div class="rigtop">
				<Form ref="classTable"  inline>
					<FormItem>
						<Row>
							<Col span="6" style="text-align: center;">
							成员编号
							</Col>
							<Col span="18">
							<Input placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Row>
							<Col span="6" style="text-align: center;">
							成员姓名
							</Col>
							<Col span="18">
							<!-- <Select v-model="notic.nTitle" filterable>
										<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item}}</Option>
									</Select> -->
							<Input  placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Row>
							<Col span="6" style="text-align: center;">
							部门
							</Col>
							<Col span="18">
							<!-- <Select v-model="notic.nTitle" filterable>
										<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item}}</Option>
									</Select> -->
							<Input  placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Row>
							<Col span="6" style="text-align: center;">
								时间
							</Col>
							<Col span="16">
							<DatePicker type="daterange" placement="bottom-end" placeholder="Select date" style="width: 200px"></DatePicker>
							</Col>
						</Row>
					</FormItem>
					<FormItem>
						<Button>快速导出</Button>
					</FormItem>
				</Form>
			</div>
		
		
		<!-- <Form ref="formValidate" :model="personalMeritRecord" :label-width="80">
			<FormItem>
				<Button type="success" @click="add()" long>添加</Button>
			</FormItem>
		</Form> -->
		<Table border :columns="columns7" :data="data6"  height="520" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal14" draggable scrollable title="添加个人评优" @on-ok="oks">
			<div>
				<Form ref="formInline" :model="personalMeritRecord" :label-width="80">
					<FormItem label="工作编号" prop="mUser">
						<Row>
							<Col span="11">
							<Select v-model="personalMeritRecord.mUser" filterable placeholder="请输入编号" :remote-method="selectMname" :loading="loading">
								<Option v-for="item in menberInforMation" :value="item.mUser" :key="item.mUser">{{ item.mUser }}</Option>
							</Select>
							</Col>
							<Col span="11">
							<Input v-model="personalMeritRecord.lMName" disabled placeholder="姓名"></Input>
							</Col>
						</Row>
					</FormItem>
					<FormItem label="标题">
						<Input v-model="personalMeritRecord.pTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择时间" v-model="personalMeritRecord.pDate" @on-change="getStartTime"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>

					<FormItem label="内容" prop="pContexts">
						<Input v-model="personalMeritRecord.pContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/personalMeritRecord/upload">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="personalMeritRecord.pFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
				</Form>
			</div>
		</Modal>
		<Modal v-model="modal13" draggable scrollable title="编辑个人评优" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="personalMeritRecord" :label-width="80">
					<FormItem label="成员编号">
						<Input v-model="personalMeritRecord.mUser" disabled placeholder="成员编号"></Input>
					</FormItem>
					<FormItem label="成员姓名">
						<Input v-model="personalMeritRecord.lMName" disabled placeholder="成员姓名"></Input>
					</FormItem>
					<FormItem label="标题">
						<Input v-model="personalMeritRecord.pTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择时间" v-model="personalMeritRecord.pDate" @on-change="getStartTime"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>

					<FormItem label="内容" prop="pContexts">
						<Input v-model="personalMeritRecord.pContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/personalMeritRecord/upload">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="personalMeritRecord.pFile" disabled placeholder="没有文件" /></Col>
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
				url: "http://localhost:8080/",
				count: 10,
				modal14: false,
				modal13: false,
				loading:false,
				menberInforMation: [],
				columns7: [{
						title: '部门名称',
						key: 'dName',
						align: 'center',
					},
					{
						title: '成员编号',
						key: 'mUser',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.mUser)
							]);
						}
					},
					{
						title: '成员姓名',
						key: 'lMName',
						align: 'center'
					},
					{
						title: '标题',
						key: 'pTitle',
						align: 'center',
					},
					{
						title: '时间',
						key: 'pDate',
						align: 'center',
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '内容',
						key: 'pContexts',
						width: 200,
						tooltip:true,
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.pContexts.substring(0, 50))
							]);
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

											this.remove(params.row.pId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				personalMeritRecord: {
					pId: 0,
					dName: "",
					mUser: "",
					lMName: "",
					pTitle: "",
					pDate: "",
					pFile: "",
					mName: "",
					pContextss: ""
				}
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.personalMeritRecord.pId = data.pId;
				this.personalMeritRecord.mUser = data.mUser;
				this.personalMeritRecord.dName = data.dName;
				this.personalMeritRecord.lMName = data.lMName;
				this.personalMeritRecord.pTitle = data.pTitle;
				this.personalMeritRecord.pDate = data.pDate;
				this.personalMeritRecord.pFile = data.pFile;
				this.personalMeritRecord.pContexts = data.pContexts;
			},
			getStartTime(starTime) {
				this.personalMeritRecord.pDate = starTime;
			},
			selectMname(value) {
				this.loading = true;
				const th = this;
				axios.get(th.url + 'memberInformation/iUserName', {
					params: {
						mUser: value
					}
				}).then(function(res) {
					th.menberInforMation = res.data.data;
					for (let s of res.data.data) {
						th.personalMeritRecord.lMName = s.mName;
					}
					th.loading = false;
				})
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + 'personalMeritRecord/selectAll', {
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
					this.personalMeritRecord.pFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			remove(pId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + 'personalMeritRecord/deleteByPrimaryKey', {
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
				axios.post(th.url + 'personalMeritRecord/updateByPrimaryKey', th.personalMeritRecord, {
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
				this.personalMeritRecord.mUser = "";
				this.personalMeritRecord.dName = "";
				this.personalMeritRecord.lMName = "";
				this.personalMeritRecord.pTitle = "";
				this.personalMeritRecord.pFile = "无路径";
				this.personalMeritRecord.pContexts = "";
				this.modal14 = true;
			},
			oks() {
				const th = this;
				axios.post(th.url + 'personalMeritRecord/insert', th.personalMeritRecord, {
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
			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
