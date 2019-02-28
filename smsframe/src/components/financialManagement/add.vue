<template>
	<div>
		<Form  :model="FinancialManagement">
			<FormItem prop="aUsername" label="金额">
				<div>
				<InputNumber
            :max="10000"
            v-model="FinancialManagement.fMoney"
            :formatter="value => `$ ${value}`.replace(/B(?=(d{3})+(?!d))/g, ',')"
            :parser="value => value.replace(/$s?|(,*)/g, '')"></InputNumber>
			</div>
			</FormItem>
			<FormItem label="类型">
				<RadioGroup v-model="FinancialManagement.fType">
					<Radio label="支出"></Radio>
					<Radio label="收入"></Radio>
				</RadioGroup>
			</FormItem>
			<FormItem label="时间">
				<Row>
					<Col span="11">
					<FormItem prop="rAdmissionDate">
						<DatePicker type="date" placeholder="请选择时间" v-model="FinancialManagement.fDate"></DatePicker>
					</FormItem>
					</Col>
				</Row>
			</FormItem>
			<FormItem label="文件上传">
				<div>
					<Row>
						<Col span="6">
						<Upload name='file' :show-upload-list='false' :on-success='resultMsg' action="http://47.100.245.30:8080/upload/financialManagement">
							<Button icon="ios-cloud-upload-outline">可拖动上传</Button>
						</Upload>
						</Col>
						<Col span="6"><Input icon="ios-cloud-upload-outline" v-model="FinancialManagement.fFile" disabled placeholder="没有文件" /></Col>
					</Row>
				</div>
			</FormItem>
			<FormItem>
				<Input v-model="FinancialManagement.fRemarks" type="textarea" :autosize="{minRows: 6,maxRows: 8}" placeholder="备注"></Input>
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
				FinancialManagement: {
					fType: "支出",
					fMoney: 0,
					fDate: "",
					fFile: "",
					fRemarks: ""
				},
				url: "http://47.100.245.30:8080/"
			};
		},
		methods: {
			add() {
console.log(this.FinancialManagement);
				const th = this;
				axios.post(th.url + 'financialManagement/insert', th.FinancialManagement, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(function(res) {
					if (res.data.code === 1028) {
						th.$Message.success(res.data.message);
						th.FinancialManagement.fMoney = 0;
						th.FinancialManagement.fFile = "";
						th.FinancialManagement.fRemarks = "";
					} else {
						th.$Message.error(res.data.message);
					}
				})

			},resultMsg(res) {
				if (res.code === 1224) {
					this.FinancialManagement.fFile = res.data;
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
