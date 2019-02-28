<style scoped="scoped">
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
	.rigtop{
		height:80px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form :model="notic" inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="title" label="">模糊标题</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="notic.nTitle" placeholder="请输入关键字"></Input>
						</Col>
					</Row>
				</FormItem>

				<FormItem>
					<Row>
						<Col span="7" style="text-align: center;">
						<Checkbox v-model="dates" label="">发布时间</Checkbox>
						</Col>
						<Col span="16">
						<DatePicker type="daterange" placement="bottom-end" placeholder="时间" style="width: 200px"></DatePicker>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: relative;left: 15px">
					<Button @click="select(1,'bd')">
						<Icon type="ios-sync" />快速查询
					</Button>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="add()">
							<Icon type="ios-add-circle-outline" />新增部门
						</Button>
					</FormItem>
					<Button @click="exportData()">
						<Icon type="ios-download-outline" />数据导出
					</Button>
				</FormItem>
			</Form>
			<Form inline>
				<FormItem>
					<Row>
						<Col span="8" style="text-align: center;">
						<Checkbox v-model="name" label=""> 发 布 人</Checkbox>
						</Col>
						<Col span="16">
						<Input v-model="notic.mName" placeholder="请输入名字"></Input>
						</Col>
					</Row>
				</FormItem>

				<FormItem style="position: relative;left: 15px">
					<RadioGroup v-model="status">
						<Radio label="true">
							<Icon type="ios-eye" />
							<span>正常</span>
						</Radio>
						<Radio label="false">
							<Icon type="ios-eye-off" />
							<span>冻结</span>
						</Radio>
						<Radio label="all">
							<Icon type="ios-football-outline" />
							<span>全部</span>
						</Radio>
					</RadioGroup>
				</FormItem>
			</Form>
		</div>


		<Table border :columns="columns7" :data="data6" height="420" size='default' :loading="loading" stripe ref="table"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="selectpage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" :styles="{top: '40px'}" draggable scrollable title="编辑公告" @on-ok="ok">
			<div>
				<Form ref="formInline" :model="notic" :label-width="80">
					<FormItem label="标题">
						<Input v-model="notic.nTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="内容" prop="nContext">
						<Input v-model="notic.nContext" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/notic">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="notic.nFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
					<FormItem label="状态">
						<i-switch v-model="notic.status" size="large">
							<span slot="open">正常</span>
							<span slot="close">冻结</span>
						</i-switch>
					</FormItem>
					<FormItem label="排序">
						<InputNumber :max="100" :min="1" v-model="notic.nSort"></InputNumber>
					</FormItem>
				</Form>
			</div>
		</Modal>

		<Modal v-model="modal14" :styles="{top: '40px'}" draggable scrollable title="添加公告" @on-ok="oks">
			<div>
				<Form ref="formInline" :model="notic" :label-width="80">
					<FormItem label="标题">
						<Input v-model="notic.nTitle" placeholder="标题"></Input>
					</FormItem>
					<FormItem label="内容" prop="nContext">
						<Input v-model="notic.nContext" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
					</FormItem>
					<FormItem label="文件上传">
						<div>
							<Row>
								<Col span="12">
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/notic">
									<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
								</Upload>
								</Col>
								<Col span="12"><Input icon="ios-cloud-upload-outline" v-model="notic.nFile" disabled placeholder="没有文件" /></Col>
							</Row>
						</div>
					</FormItem>
					<FormItem label="状态">
						<i-switch v-model="notic.status" size="large">
							<span slot="open">正常</span>
							<span slot="close">冻结</span>
						</i-switch>
					</FormItem>
					<FormItem label="排序">
						<InputNumber :max="100" :min="1" v-model="notic.nSort"></InputNumber>
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
				url: "http://47.100.245.30:8080",
				count: 10,
				modal13: false,
				modal14: false,
				noticTitle: "",
				title: false,
				name: false,
				dates: false,
				loading: true,
				status: "true",
				bd: "",
				baDate: [],
				memberInformationType: [],
				columns7: [{
						title: '标题',
						key: 'nTitle',
						align: 'center',
						width: 150,
						tooltip: true
					},
					{
						title: '编号',
						key: 'nId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.nId)
							]);
						}
					},
					{
						title: '发布时间',
						key: 'nDate',
						align: 'center'
					},
					{
						title: '发布人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '排序',
						key: 'nSort',
						align: 'center',
					},
					{
						title: '状态',
						key: 'status',
						align: 'center',
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
											axios.get(th.url + '/notic/updateStatus', {
												params: {
													nId: params.row.nId,
													status: value
												}
											}).then(function(res) {
												if (res.data.code === 1028) {
													th.$Message.success(res.data.message);
												} else {
													th.$Message.error(res.data.message);
													this.changePage(1);
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
											innerHTML: '冻结中'
										}
									}),
								]
							)
						}

					},
					{
						title: '公告内容',
						key: 'nContext',
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

											this.remove(params.row.nId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				notic: {
					nId: 0,
					nTitle: "",
					nDate: "",
					nFile: "",
					mName: "",
					status: true,
					nContext: "",
					nSort: 1
				}
			}
		},
		methods: {
			//导出数据
			exportData() {
				this.$refs.table.exportCsv({
					filename: '公告信息'
				});
			},
			//间隔时间
			getStartTime(starTime) {
				this.baDate = starTime;
			},
			//编辑弹出
			show(data) {
				this.modal13 = true;
				this.notic.nId = data.nId;
				this.notic.nTitle = data.nTitle;
				this.notic.nDate = data.nDate;
				this.notic.nFile = data.nFile;
				this.notic.mName = data.mName;
				this.notic.status = data.status == "true" ? true : false;
				this.notic.nSort = data.nSort;
				this.notic.nContext = data.nContext;
			},
			//查询
			changePage(page) {
				const th = this;
				th.loading = true;
				axios.get(th.url + '/notic/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
				th.loading = false;
			},
			//文件上传
			resultMsg(res) {
				if (res.code === 1224) {
					this.notic.nFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
			//删除
			remove(nId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/notic/deleteByPrimaryKey', {
							params: {
								nId: nId
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
			//修改
			ok() {
				const th = this;
				th.notic.mName = localStorage.getItem("mName");
				axios.post(th.url + '/notic/updateByPrimaryKey', th.notic, {
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
			//添加
			oks() {
				
				const th = this;
				th.notic.mName = localStorage.getItem("mName");
				axios.post(th.url + '/notic/insert', th.notic, {
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
			//分页查询
			selectpage(page) {
				if (this.bd == "bd") {
					select(page, bd);
				} else {
					changePage(page);
				}
			},
			//添加弹出
			add() {
				this.modal14 = true;
				this.notic = {
					nId: 0,
					nTitle: "",
					nDate: "",
					nFile: "",
					mName: "",
					status: true,
					nContext: "",
					nSort: 1
				};
			},
			//快速查询
			select(page, bd) {
				if (bd == "bd") {
					this.bd = bd;
				}
				this.loading = true;
				if (!this.title) {
					this.notic.nTitle = null;
				}
				if (!this.name) {
					this.notic.mName = null;
				}
				if (!this.dates) {
					this.baDate = ["", ""];
				}
				const th = this;
				axios.get(th.url + '/notic/selects', {
					params: {
						pageNum: page,
						nTitle: th.notic.nTitle,
						mName: th.notic.mName,
						status: th.status,
						beforeDate: th.baDate[0],
						afterDate: th.baDate[1],
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.noticTitle = res.data.data.map((e) => {
						return e.nTitle;
					})
					th.count = res.data.count;
				})
				this.loading = false;
			}
		},
		created() {
			this.changePage(1);

		}
	}
</script>
