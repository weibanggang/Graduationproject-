<template>
	<div>
		<Form ref="formValidate" :model="workArrangement"  :label-width="80">
		<FormItem>
				<Button type="success"  @click="add()" long>添加</Button>
		</FormItem>
		</Form>
		<Table border :columns="columns7" :data="data6"></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="工作安排" @on-ok="ok" >
			<Form ref="formValidate" :model="workArrangement"  :label-width="80">
				<FormItem label="工作标题" prop="wTitle">
					<Input v-model="workArrangement.wTitle" placeholder="请输入工作标题"></Input>
				</FormItem>
				<FormItem label="负责人" prop="mName">
					<Select v-model="workArrangement.mName" placeholder="请选择负责人" >
					<Option v-for="item in memberInformationType" :value="item.mName" :key="item.mId">{{ item.mName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="负责部门" prop="dName">
					<Select v-model="workArrangement.dId" placeholder="请选择部门" >
					<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="入会时间">
					<Row>
						<Col span="11">
						<FormItem prop="wDate">
							<DatePicker type="date" placeholder="请选择入会时间" v-model="workArrangement.wDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem  label="工作备注" prop="wRemarks">
					<Input v-model="workArrangement.wRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入工作备注"></Input>
				</FormItem>
				<FormItem  label="工作内容" prop="wContents">
					<Input v-model="workArrangement.wContents" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入工作内容"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="工作安排" @on-ok="oks">
			<Form ref="formValidate" :model="workArrangement"  :label-width="80">
				<FormItem label="工作标题" prop="wTitle">
					<Input v-model="workArrangement.wTitle" placeholder="请输入工作标题"></Input>
				</FormItem>
				<FormItem label="负责人" prop="mName">
					<Select v-model="workArrangement.mName" placeholder="请选择负责人" >
					<Option v-for="item in memberInformationType" :value="item.mName" :key="item.mId">{{ item.mName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="负责部门" prop="dName">
					<Select v-model="workArrangement.dId" placeholder="请选择部门" >
					<Option v-for="item in departmentType" :value="item.dId" :key="item.dId">{{ item.dName }}</Option>
					</Select>
				</FormItem>
				<FormItem label="入会时间">
					<Row>
						<Col span="11">
						<FormItem prop="wDate">
							<DatePicker type="date" placeholder="请选择入会时间" v-model="workArrangement.wDate"></DatePicker>
						</FormItem>
						</Col>
					</Row>
				</FormItem>
				<FormItem  label="工作备注" prop="wRemarks">
					<Input v-model="workArrangement.wRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入工作备注"></Input>
				</FormItem>
				<FormItem  label="工作内容" prop="wContents">
					<Input v-model="workArrangement.wContents" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入工作内容"></Input>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				title:"添加工作类型",
				url: "http://localhost:8080",
				count: 10,
				modal13: false,
				modal14:false,
				departmentType:[],
				columns7: [{
						title: '工作编号',
						key: 'wId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.wId)
							]);
						}
					},
					{
						title: '工作标题',
						key: 'wTitle',
						align: 'center',
					},
					{
						title: '负责部门',
						key: 'dName',
						align: 'center',
					},
					{
						title: '总负责人',
						key: 'mName',
						align: 'center',
					},
					{
						title: '时间',
						key: 'wDate',
						align: 'center',
					},
					{
						title: '备注',
						key: 'wRemarks',
						align: 'center',
					},
					{
						title: '工作内容',
						key: 'wContents',
						align: 'center',
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
											this.remove(params.row.wId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				memberInformationType:[],
				workArrangement: {
					wId: 0,
					wTitle: "",
					wRemarks: "",
					dName: "",
					dId: "",
					mName: "",
					wDate:"",
					wContents:""
				}
			}
		},
		methods: {
			show(data) {
				this.modal13 = true;
				this.workArrangement.wId=data.wId;
				this.workArrangement.dId=data.dId;
				this.workArrangement.wTitle=data.wTitle;
				this.workArrangement.wRemarks=data.wRemarks;
				this.workArrangement.wDate=data.wDate;
				this.workArrangement.wContents=data.wContents;
				this.workArrangement.mName=data.mName;
			},
			changePage(page) {
				const th = this;
				axios.get(th.url + '/workArrangement/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					
					var datares = res.data.data.map((e) => {
						e.dName = e.departmentType.dName;
						return e
					});
					th.data6=datares;
					console.log(res.data.data);
					/* for(let data of res.data.data){
						th.data6.push({
						"wId":data.wId,
						"dId":data.dId,
						"wTitle":data.wTitle,
						"wRemarks":data.wRemarks,
						"wDate":data.wDate,
						"wContents":data.wContents,
						"dName":data.departmentType.dName,
						"mName":data.mName}
						);
					} */
				})
			},
			selectAdd(){
				const th = this;
				axios.get(th.url + '/departmentType/iSelectAllStatus').then(function(res) {
					th.departmentType = res.data.data;
				})
				axios.get(th.url + '/memberInformation/iSelectName', {
					params: {
						pId: 4
					}
				}).then(function(res) {
					th.memberInformationType = res.data.data;
				})
			},
			add(){
				this.modal14=true;
				},
			remove(wId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/workArrangement/deleteByPrimaryKey', {
							params: {
								wId: wId
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
				axios.post(th.url + '/workArrangement/updateByPrimaryKey', th.workArrangement, {
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
			},oks() {
				const th = this;
				axios.post(th.url + '/workArrangement/insert', th.workArrangement, {
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
			}
		},
		created() {
			this.changePage(1);
			this.selectAdd();
		}
	}
</script>
