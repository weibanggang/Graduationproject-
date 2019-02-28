<template>
	<div>
		<Form :model="notic" :label-width="80">
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
							<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/upload/notic">
							<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
							</Upload>
							</Col>
							<Col span="12"  ><Input icon="ios-cloud-upload-outline" v-model="notic.nFile" disabled placeholder="没有文件" /></Col>
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
			<FormItem>
				<Button type="success" @click="add()" long>添加</Button>
			</FormItem>
		</Form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				file: null,
				loadingStatus: false,
				notic: {
					nId: 0,
					nTitle: "",
					nDate: "",
					nFile: "无文件",
					mName: "",
					status: "",
					nContext: "",
					nSort: 1
				},
				url: "http://47.100.245.30:8080/"
			};
		},
		methods: {
			//添加
			add() {
				const th = this;
				th.notic.mName = localStorage.getItem("mName");
				axios.post(th.url + '/notic/insert', th.notic, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.notic.nId = "";
						th.notic.aPassword = "";
						th.notic.aRemarks = "";
					} else {
						th.$Message.error(res.data.message);
					}
				}) 

			},
			//上传文件
			resultMsg(res) {
				if (res.code === 1224) {
					this.notic.nFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			},

		}
	}
</script>

<style>

</style>
