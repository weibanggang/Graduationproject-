<template>
	<div>

		<div class="rigtop">
			<Form ref="classTable"  inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						会议标题
						</Col>
						<Col span="18">
						<Input placeholder="姓名"></Input>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						会议类型
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
							上传时间
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



		<Table border :columns="columns7" :data="data6" height="520" stripe size='default'></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="编辑会议记录" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="minutesOfTheMeeting" :label-width="80">
					<FormItem label="会议标题">
						<Input v-model="minutesOfTheMeeting.mTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="会议类型" prop="dId">
						<Select v-model="minutesOfTheMeeting.tId" placeholder="请选择会议类型">
							<Option v-for="item in typeofMeeting" :value="item.tId" :key="item.tId">{{ item.tName }}</Option>
						</Select>
					</FormItem>
					<FormItem label="内容" prop="mContexts">
						<Input v-model="minutesOfTheMeeting.mContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>

					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/minutesOfTheMeeting/upload">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="minutesOfTheMeeting.mFile" disabled placeholder="没有文件" /></Col>
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
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				columns7: [{
						title: '编号',
						key: 'mId',
						align: 'center',
						width: 100
					},
					{
						title: '会议标题',
						key: 'mTitle',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.mTitle)
							]);
						}
					},
					{
						title: '会议类型',
						key: 'tName',
						align: 'center'
					},
					{
						title: '上传时间',
						key: 'mDate',
						align: 'center',
					}, {
						title: '操作人',
						key: 'mName',
						width: 100,
						align: 'center'
					},
					{
						title: '内容',
						key: 'mContexts',
						width: 200,
						tooltip: true,
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
				minutesOfTheMeeting: {
					mId: 0,
					mTitle: "",
					tId: "",
					nDate: "",
					mFile: "",
					mName: "",
					mContexts: "",
				},
				typeofMeeting: []
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.minutesOfTheMeeting.mId = data.mId;
				this.minutesOfTheMeeting.tId = data.tId;
				this.minutesOfTheMeeting.mTitle = data.mTitle;
				this.minutesOfTheMeeting.mFile = data.mFile;
				this.minutesOfTheMeeting.mName = data.mName;
				this.minutesOfTheMeeting.mContexts = data.mContexts;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/minutesOfTheMeeting/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					var datares = res.data.data.map((e) => {
						e.tName = e.typeOfMeeting.tName;
						return e;
					})
					th.data6 = datares;
					th.count = res.data.count;
				})
			},
			resultMsg(res) {
				if (res.code === 1224) {
					this.minutesOfTheMeeting.mFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			remove(mId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/minutesOfTheMeeting/deleteByPrimaryKey', {
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
				axios.post(th.url + '/minutesOfTheMeeting/updateByPrimaryKey', th.minutesOfTheMeeting, {
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
			const th = this;
			axios.get(th.url + '/typeofMeeting/iselectAllStatus')
				.then(function(res) {
					th.typeofMeeting = res.data.data;
				})
		}
	}
</script>
