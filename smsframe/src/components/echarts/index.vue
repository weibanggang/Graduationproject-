<template>
	<div id="myChart" :style="{width: '1150px', height: '550px'}"></div>
</template>

<script>
	import echarts from 'echarts'
	export default {
		data() {
			return {
			url: "http://47.100.245.30:8080",
			}
		},
		mounted() {
			this.drawLine();
		},
		methods: {
			drawLine() {
				
				const th = this;
				let myChart = th.$echarts.init(document.getElementById('myChart'))
				
				myChart.setOption({
					title: {
						text: '南方学生会人数',
						subtext: '校内信息'
					},
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'shadow'
						}
					},
					legend: {
						data: ['2016年','2017年', '2018年']
					},
					grid: {
						left: '0%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis: {
						type: 'value',
						boundaryGap: [0, 0.01]
					},
					yAxis: {
						type: 'category',
						data: ['书记', '主席团', '文体部', '纪检部', '党团部', '秘书部', '播音部', '社团部', '学习部', '生活部', '外联部', '总人数']
					},
					series: [
						
						{
							name: '2016年',
							type: 'bar',
							data: [1, 8, 20, 40, 60, 20, 60, 32, 10, 70, 90, 280]
						},{
							name: '2017年',
							type: 'bar',
							data: [10, 20, 50, 80, 20, 30, 60, 73, 50, 50, 80, 240]
						},
						{
							name: '2018年',
							type: 'bar',
							data: [1, 4, 20, 50, 10, 10, 40, 42, 20, 40, 50, 200]
						},
					]
				})
				
				axios.get(th.url + '/memberInformation/homeReport')
				.then(function(res) {
					
					// 基于准备好的dom，初始化echarts实例
					/* 
					var category = [];
					var lineData = new Array();
					var barData = new Array();
					res.data.data.forEach(function(item){
						category.push({name:item.dName,value:item.count});
						lineData.push();
						barData.push(item.time);
					}) */
					/* for(var i=0;i<data.length;i)
					console.log(); */
					
				})
				
			}
		}
	}
</script>
