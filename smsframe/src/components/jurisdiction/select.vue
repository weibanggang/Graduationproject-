<style>
	.ivu-table td, .ivu-table-border td{
		height: 41px;
	}
</style>
<template>
	<div>
		
		<div class="rigtop">
				<Form ref="roles" :model="role" inline>
				<FormItem>
					<Row>
						<Col span="6" style="text-align: center;">
						角&nbsp;色
						</Col>
						<Col span="18">
						<Select v-model="role.rName" @on-change="changePage(1)" filterable>
							<Option v-for="item in roles" :value="item.rName" :key="item.rId">{{ item.rName }}</Option>
						</Select>
						</Col>
					</Row>
				</FormItem>
				<FormItem style="position: absolute;right: 30px">
					<FormItem>
						<Button @click="insertRoles()">
							<Icon type="ios-add-circle-outline" />添加角色
						</Button>
					</FormItem>
					<Button @click="insertPermissions()">
						<Icon type="ios-download-outline" />添加权限
					</Button>
				</FormItem>
			</Form>
		</div>
		
		<Table border :columns="columns7" :data="data6" height="450" stripe size='default' ></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="count" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>
		<Modal v-model="modal14" draggable scrollable title="添加角色" @on-ok="roleOk">
			<Form ref="formValidate" :model="role"  :label-width="80">
				<FormItem label="角色名称" prop="jName">
					<Input v-model="role.rName" placeholder="请输入角色名称"></Input>
				</FormItem>
				<FormItem  label="角色备注" prop="jRemarks">
					<Input v-model="role.rRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入角色备注"></Input>
				</FormItem>
			</Form>
		</Modal>
		<Modal v-model="modal13" draggable scrollable title="设置权限" @on-ok="permissionOk">
			<Form ref="formValidate" :model="GeUserPermissionsAssociate"  :label-width="80">
				<FormItem  label="角色名称" prop="rName">
				<Select v-model="GeUserPermissionsAssociate.rolesId"  filterable>
					<Option v-for="item in roles" :value="item.rId" :key="item.rId">{{ item.rName }}</Option>
				</Select>
				</FormItem>
				<FormItem label="权限名称" prop="jName">
					<Select v-model="GeUserPermissionsAssociate.permissionsId"  filterable>
						<Option v-for="item in permission" :value="item.id" :key="item.id">{{ item.name }}</Option>
					</Select>
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
				modal13: false,
				modal14: false,
				roles:"",
				permission:"",
				role:{
					rId:0,
					rName:"",
					rRemarks:""
				},
				permissions:{
					id:0,
					name:"",
					url:""
				},
				GeUserPermissionsAssociate:{
					rolesId:0,
					permissionsId:0
				},
				columns7: [{
						title: '编号',
						key: 'id',
						align: 'center',
						render: (h, params) => {
							return h('div', [
								h('Icon', {
									props: {
										type: 'person'
									}
								}),
								h('strong', params.row.id)
							]);
						}
					},
					{
						title: '角色名称',
						key: 'rName',
						align: 'center',
					},
					{
						title: '角色备注',
						key: 'rRemarks',
						align: 'center',
						width:250,
						tooltip:true
					},
					{
						title: '权限名称',
						key: 'name',
						align: 'center',
						width:150,
						tooltip:true
					},
					{
						title: '权限地址',
						key: 'url',
						align: 'center',
						width:150,
						tooltip:true
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
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {
											this.remove(params.row.id)
										}
									}
								}, '移除')
							]);
						}
					}
				],
				data6: []
			}
		},
		methods: {
			changePage(page) {
				const th = this;
				axios.get(th.url + '/roles/PermissionsSelect', {
					params: {
						pageNum: page,
						rName:th.role.rName
					}
				}).then(function(res) {
					th.data6 = res.data.data;
					th.count = res.data.count;
				})
			},
			insertRoles(){
				this.role.name="";
				this.role.rRemarks="";
				this.modal14=true;
			},
			insertPermissions(){
				this.permissions.name="";
				this.permissions.url="";
				this.modal13=true;
			},
			remove(id) {
				this.$Modal.confirm({
					title: '删除提示',
					content: '<p>移除后不可恢复，确定继续？</p>',
					onOk: () => {
						const th = this;
						axios.get(th.url + '/roles/gupamDel', {
							params: {
								id: id
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
			//添加角色
			roleOk() {
				const th = this;
				console.log(th);
				axios.post(th.url + '/roles/insert', th.role, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					}else {
						th.$Message.error(res.data.message);
						th.modal14=true;
					}
				})
			},
			//设置权限
			permissionOk() {
				const th = this;
				axios.post(th.url + '/roles/gupam', th.GeUserPermissionsAssociate, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.changePage(1);
					} else {
						th.$Message.error(res.data.message);
						th.modal13=true;
					}
				})
			},
		},
		created() {
			var th = this;
			axios.get(th.url + '/roles/iSelectAll')
			.then(function(res) {
				th.roles = res.data.data;
			})
			axios.get(th.url + '/roles/selectPermissions')
			.then(function(res) {
				th.permission = res.data.data;
			})
			this.changePage(1);
		}
	}
</script>
