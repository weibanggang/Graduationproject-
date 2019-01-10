<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		<div class="rigtop">
			<Form ref="classTable" :model="classTable" inline>
				<FormItem style="position: absolute;right: 30px">
					<Button @click="add()">
						<Icon type="ios-add-circle-outline" />添加管理
					</Button>
				</FormItem>
			</Form>
		</div>

		<Table border :columns="columns7" :data="data6" height="450" stripe size='default'></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal13" draggable scrollable title="编辑管理" @on-ok="ok" @on-cancel="cancel">
			<Form :model="Admin" :label-width="80">
				<FormItem prop="password" label="密码">
				    <Input type="password" v-model="Admin.aPassword" placeholder="aPassword">
				        <Icon type="ios-lock-outline" slot="prepend"></Icon>
				    </Input>
				</FormItem>
				<FormItem label="备注">
					<Input v-model="Admin.aRemarks" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="备注"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal14" draggable scrollable title="添加管理" @on-ok="oks">
			<Form :model="Admin" :label-width="80">
				 <FormItem prop="aUsername" label="账号" >
				    <Input type="text" v-model="Admin.aUsername" placeholder="aUsername">
				        <Icon type="ios-person-outline" slot="prepend"></Icon>
				    </Input>
				</FormItem>
				<FormItem prop="password" label="密码">
				    <Input type="password" v-model="Admin.aPassword" placeholder="aPassword">
				        <Icon type="ios-lock-outline" slot="prepend"></Icon>
				    </Input>
				</FormItem>
				<FormItem label="备注">
				    <Input v-model="Admin.aRemarks" type="textarea" :autosize="{minRows: 4,maxRows: 8}" placeholder="备注"></Input>
				</FormItem>
				<FormItem label="状态">
				    <i-switch v-model="Admin.status" size="large">
				        <span slot="open">正常</span>
				        <span slot="close">冻结</span>
				    </i-switch>
				</FormItem>
			</Form>
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
				modal14: false,
				columns7: [{
						title: '编号',
						key: 'aId',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.aId)
							]);
						}
					},
					{
						title: '账号',
						key: 'aUsername',
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
											axios.get(th.url + '/admin/updateStatus', {
												params: {
													aUsername: params.row.aUsername,
													status: value
												}
											}).then(function(res) {
												if (res.data.code === 1028) {
													th.$Message.success(res.data.message);
												} else {
													th.$Message.error(res.data.message);
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
						title: '备注',
						key: 'aRemarks',
						align: 'center',
						width: 250,
						tooltip: true
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

											this.remove(params.row.aId, params.index)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: [],
				status:true,
				Admin: {
					aId: 0,
				aUsername:"",
				aPassword:"",
				status:true,
				aRemarks:""
				},
				aRemarks: ""
			}
		},
		methods: {
			//状态
			off(value){
				this.Admin.status = value == true ? "true" : "false";
			},
			//编辑
			show(data) {
				this.modal13 = true;
				this.Admin = data;
				this.aRemarks = data.aRemarks;
			},
			//查询
			changePage(page) {
				const th = this;
				axios.get(th.url + '/admin/selectAll', {
					params: {
						pageNum: page
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			//删除
			remove(aId, index) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/admin/deleteByPrimaryKey', {
							params: {
								aId: aId
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
				axios.post(th.url + '/admin/updateByPrimaryKey', th.Admin, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
						th.Admin.aRemarks = th.aRemarks;
					}
				})
			},
			//添加弹出
			add() {
				this.Admin = {
					aId: 0,
					aUsername:"",
					aPassword:"",
					status:true,
					aRemarks:""
				};
				this.modal14 = true;
			},
			//添加
			oks(){
					const th=this;
					axios.post(th.url+'/admin/insert',th.Admin,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.changePage(1); 
						}else{
							th.$Message.error(res.data.message);
						}
					})
					
				},
			cancel() {
				this.Admin.aRemarks = this.aRemarks;
			}
		},
		created() {
			this.changePage(1);
		}
	}
</script>
