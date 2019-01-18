<template>
	<div id="myChart" :style="{width: '1150px', height: '550px'}"></div>
</template>

<script>
	import echarts from 'echarts'
	export default {
		mounted() {
			this.drawLine();
		},
		methods: {
			drawLine() {

				// 基于准备好的dom，初始化echarts实例
				let myChart = this.$echarts.init(document.getElementById('myChart'))

				var category = ['书记', '主席团', '文体部', '纪检部', '党团部', '秘书部', '播音部', '社团部', '学习部', '生活部', '外联部'];
				var lineData = [10, 20, 50, 80, 20, 30, 60, 73, 50, 50, 80];
				var barData = [1, 4, 20, 50, 10, 10, 40, 42, 20, 40, 50];


				// 绘制图表
				/* myChart.setOption({
					title: {
						text: '冬季销量统计'
					},
					tooltip: {},
					xAxis: {
						data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子", '毛衣', '秋裤', '外套', '短袖', '手套', '帽子', '卫衣', '棒球服']
					},
					yAxis: {},
					series: [{
						name: '销量',
						type: 'bar',
						data: [5, 20, 36, 10, 10, 20, 15, 22, 33, 8, 6, 21, 22, 39]
					}]
				}); */

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
			}
		}
	}
</script>
