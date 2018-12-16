<template>
	<div>
		<Form ref="formInline" :model="leaveRecord" :label-width="80">
			<FormItem label="工作编号" prop="mUser" >
				<Row>
					<Col span="11">
				<Select v-model="leaveRecord.mUser" filterable placeholder="请输入编号" :remote-method="selectMname"
                :loading="loading" >
				<Option v-for="item in menberInforMation" :value="item.mUser" :key="item.mUser">{{ item.mUser }}</Option>
				</Select>
				</Col>
				<Col span="11">
					<Input v-model="leaveRecord.lMName" disabled placeholder="姓名"></Input>
				</Col>
				</Row>
			</FormItem>
			<FormItem label="会议标题">
				<Input v-model="leaveRecord.lTitle" placeholder="会议标题"></Input>
			</FormItem>
			<FormItem label="请假类型">
				<Input v-model="leaveRecord.lType" placeholder="请假类型"></Input>
			</FormItem>
			<FormItem label="请假时间">
				<Row>
					<Col span="11">
					<FormItem prop="rAdmissionDate">
						<DatePicker type="date" placeholder="请选择请假时间" v-model="leaveRecord.pDate"></DatePicker>
					</FormItem>
					</Col>
				</Row>
			</FormItem>
			<FormItem label="文件上传">
				<div>
						<Row>
							<Col span="12"> 
							<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://localhost:8080/leaveRecord/upload">
							<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
							</Upload>
							</Col>
							<Col span="12"  ><Input icon="ios-cloud-upload-outline" v-model="leaveRecord.lFile" disabled placeholder="没有文件" /></Col>
						</Row>
				</div>
			</FormItem>
			<FormItem label="请假内容" prop="lContexts">
				<Input v-model="leaveRecord.lContexts" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="内容"></Input>
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
				loading:false,
				loadingStatus: false,
				leaveRecord: {
					mUser: "",
					pDate: "",
					lTitle: "",
					lType: "",
					lFile: "无文件",
					lContexts: "",
					lMName:"",
					dName:""
				},
				menberInforMation:[],
				url: "http://localhost:8080/"
			};
		},
		methods: {
			add() {
				const th = this;
				console.log(th.leaveRecord);
			axios.post(th.url + '/leaveRecord/insert', th.leaveRecord, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
					} else {
						th.$Message.error(res.data.message);
					}
				})

			},
			selectMname(value){
				this.loading=true;
				const th = this;
				axios.get(th.url + 'memberInformation/iUserName', {
					params: {
						mUser: value
					}
				}).then(function(res) {
					th.menberInforMation = res.data.data;
					for(let s of res.data.data){
						th.leaveRecord.lMName=s.mName;
						th.leaveRecord.dName=s.dId;
					}
					th.loading=false;
				}) 
			},
			resultMsg(res) {
				if (res.code === 1224) {
					this.leaveRecord.lFile = res.data;
					this.$Message.success(res.message);
				} else {
					this.$Message.error(res.message);
				}
			}

		}
	}
</script>

<style>

</style>
