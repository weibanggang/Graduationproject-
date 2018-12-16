<template>
	<div>
     <Form ref="formInline" :model="Admin"  >
        <FormItem prop="aUsername" >
            <Input type="text" v-model="Admin.aUsername" placeholder="aUsername">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem prop="password" >
            <Input type="password" v-model="Admin.aPassword" placeholder="aPassword">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
		<FormItem >
            <Input v-model="Admin.aRemarks" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="备注"></Input>
        </FormItem>
		<FormItem label="状态">
            <i-switch v-model="Admin.status" size="large">
                <span slot="open">正常</span>
                <span slot="close">冻结</span>
            </i-switch>
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
				Admin:{
					aUsername:"",
					aPassword:"",
					status:true,
					aRemarks:""
				},
				url:"http://localhost:8080/"
			};
		},
		methods:{
			add(){
					
					const th=this;
					axios.post(th.url+'/admin/insert',th.Admin,{
						headers:{
							"Content-Type":"application/json;charset=utf-8"
						}
					}).then(function(res){
						if(res.data.code===1028){
							th.$Message.success(res.data.message);
							th.Admin.aUsername="";
							th.Admin.aPassword="";
							th.Admin.aRemarks="";
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
