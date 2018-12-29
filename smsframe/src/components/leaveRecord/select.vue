<template>
	<div>



		<div class="rigtop">
			<Form ref="classTable" :model="classTable" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						模糊姓名
						</Col>
						<Col span="18">
						<Input v-model="leaveRecord.lMName"  placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						工作编号
						</Col>
						<Col span="18">
						<!-- <Select v-model="notic.nTitle" filterable>
							<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item}}</Option>
						</Select> -->
						<Input v-model="leaveRecord.lMName"  placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						部门
						</Col>
						<Col span="18">
						<!-- <Select v-model="notic.mName" filterable>
							<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item }}</Option>
						</Select> -->
						<Input v-model="leaveRecord.lMName"  placeholder="姓名"></Input>
						</Col>
					</Row>
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
		<Modal v-model="modal13" draggable scrollable title="修改请假信息" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="leaveRecord" :label-width="80">
					<FormItem label="姓名" prop="lMName">
						<Input v-model="leaveRecord.lMName" disabled placeholder="姓名"></Input>
					</FormItem>
					<FormItem label="会议标题">
						<Input v-model="leaveRecord.lTitle" placeholder="会议标题"></Input>
					</FormItem>
					<FormItem label="请假类型">
						<Input v-model="leaveRecord.lType" placeholder="请假类型"></Input>
					</FormItem>
					<FormItem label="请假时间">
						<Row>
							<Col span="11">
							<FormItem prop="rAdmissionDate">
								<DatePicker type="date" placeholder="请选择请假时间" v-model="leaveRecord.pDate"></DatePicker>
							</FormItem>
							</Col>
						</Row>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/leaveRecord/upload">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="leaveRecord.lFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
					<FormItem label="请假内容" prop="lContexts">
						<Input v-model="leaveRecord.lContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
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
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				columns7: [{
						title: '姓名',
						key: 'lMName',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.lMName)
							]);
						}
					},
					{
						title: '工作编号',
						key: 'mUser',
						align: 'center'
					},
					{
						title: '部门',
						key: 'dName',
						align: 'center'
					},
					{
						title: '请假时间',
						key: 'pDate',
						align: 'center'
					},
					{
						title: '标题',
						key: 'lTitle',
						align: 'center'
					},
					{
						title: '请假类型',
						key: 'lType',
						align: 'center'
					},
					{
						title: '操作人',
						key: 'mName',
						align: 'center',
					},

					{
						title: '请假内容',
						key: 'lContexts',
						width: 200,
						align: 'center',
						tooltip: true
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

											this.remove(params.row.lId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				leaveRecord: {
					lId: 0,
					dName: "",
					mUser: "",
					pDate: "",
					lTitle: "",
					lType: "",
					lFile: "",
					mName: "",
					lContexts: "",
					lMName: "",
				}
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.leaveRecord.lMName = data.lMName;
				this.leaveRecord.lId = data.lId;
				this.leaveRecord.lFile = data.lFile;
				this.leaveRecord.lTitle = data.lTitle;
				this.leaveRecord.lContexts = data.lContexts;
				this.leaveRecord.pDate = data.pDate;
				this.leaveRecord.lType = data.lType;
			},
			getStartTime(starTime) {
				this.leaveRecord.pDate = starTime;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/leaveRecord/selectAll', {
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
					this.leaveRecord.lFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			remove(lId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/leaveRecord/deleteByPrimaryKey', {
							params: {
								lId: lId
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
				axios.post(th.url + '/leaveRecord/updateByPrimaryKey', th.leaveRecord, {
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
