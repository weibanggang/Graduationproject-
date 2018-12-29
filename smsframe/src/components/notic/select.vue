<template>
	<div>
		
		
		<div class="rigtop">
			<Form ref="classTable" :model="classTable" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
							标题
						</Col>
						<Col span="18" >
						<Select v-model="notic.nTitle" filterable>
							<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item}}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
							发布人
						</Col>
						<Col span="18" >
						<Select v-model="notic.mName" filterable>
							<Option v-for="item in noticTitle"  :value="item" :key="item">{{ item }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
							发布时间
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
		
		
		<Table border :columns="columns7" :data="data6" height="520" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="编辑公告" @on-ok="ok" >
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
								<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/notic/upload">
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
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				noticTitle:"",
				columns7: [{
						title: '标题',
						key: 'nTitle',
						align: 'center',
						width:150,
						tooltip:true
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
											console.log(value);
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
						width:200,
						align: 'center',
						tooltip:true
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
			show(data) {
				console.log(data);
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
			changePage(page) {
				const th = this;
				axios.get(th.url + '/notic/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.noticTitle = res.data.data.map((e) => {
						return e.nTitle;
					})
					th.count = res.data.count;
				})
			},
			resultMsg(res) {
				if (res.code === 1224) {
					this.notic.nFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},
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
			ok() {
				const th = this;
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
			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
