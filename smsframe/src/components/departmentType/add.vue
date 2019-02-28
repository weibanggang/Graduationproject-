<template>
	<div>
       <Form ref="formValidate" :model="DepartmentType"  :label-width="80">
       	<FormItem label="部门名称" prop="dName">
       		<Input v-model="DepartmentType.dName" placeholder="请输入部门名称"></Input>
       	</FormItem>
       	<FormItem label="部门简介" prop="dSynopsis">
       		<Input v-model="DepartmentType.dSynopsis" type='textarea' :autosize="{minRows: 5,maxRows: 6}" placeholder="请输入部门简介"></Input>
       	</FormItem>
       	<FormItem label="部门状态" rop="status" >
       	<i-switch v-model="DepartmentType.status" size="large">
       	<span slot="open">正常</span>
       	<span slot="close">冻结</span>
       	</i-switch>
       	</FormItem>
       	
       	<FormItem  label="部门简介" prop="dRemarks">
       		<Input v-model="DepartmentType.dRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入部门备注"></Input>
       	</FormItem>
       	<FormItem label="排序" prop="dSort">
       		<InputNumber :max="100" :min="1" v-model="DepartmentType.dSort"></InputNumber>
       	</FormItem>
      
        <FormItem>
            <Button type="success"  @click="add()" long>添加</Button>
        </FormItem>
			</Form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				DepartmentType: {
					dId: 0,
					dName: "",
					status: true,
					dSynopsis: "",
					dRemarks: "",
					dSort: 1
				},
				url:"http://47.100.245.30:8080/"
			};
		},
		methods:{
			add(){
					const th=this;
					axios.post(th.url+'/departmentType/insert',th.DepartmentType,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.DepartmentType.dName="";
							th.DepartmentType.dSynopsis="";
							th.DepartmentType.dRemarks="";
							th.DepartmentType.dSort="";
						}else{
							th.$Message.error(res.data.message);
						}
					})
					
				}
				
		}
	}
</script>

<style>

</style>
