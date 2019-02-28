<template>
	<div>
       <Form ref="formValidate" :model="positionType"  :label-width="80">
       	<FormItem label="职位名称" prop="pName">
       		<Input v-model="positionType.pName" placeholder="请输入职位名称"></Input>
       	</FormItem>
       	<FormItem label="职位状态" rop="status" >
       	<i-switch v-model="positionType.status" size="large">
       	<span slot="open">正常</span>
       	<span slot="close">冻结</span>
       	</i-switch>
       	</FormItem>
       	<FormItem  label="职位备注" prop="pRemarks">
       		<Input v-model="positionType.pRemarks" type='textarea' :autosize="{minRows: 5,maxRows: 6}"  placeholder="请输入职位备注"></Input>
       	</FormItem>
       	<FormItem label="排序" prop="pSort">
       		<InputNumber :max="100" :min="1" v-model="positionType.pSort"></InputNumber>
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
				positionType: {
					pId: 0,
					pName: "",
					status: true,
					pRemarks: "",
					pSort: 1
				},
				url:"http://47.100.245.30:8080/"
			};
		},
		methods:{
			add(){
					
					const th=this;
					axios.post(th.url+'/positionType/insert',th.positionType,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.positionType.pName="";
							th.positionType.pRemarks="";
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
