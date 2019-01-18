<template>
	<div  :style="{width: '1100px'}">
		<div id="myChart" :style="{width: '1100px', height: '500px'}"></div>
	</div>
	
</template>

<script>
	export default {
		mounted() {
			this.drawLine();
		},
		methods: {
			drawLine() {
				var type = ["指导老师", "主席团", "秘书部", "文体部"];
				var person = [{
						name: "文体部",
						value: 17
					},
					{
						name: "主席团",
						value: 5
					},
					{
						name: "指导老师",
						value: 3
					},
					{
						name: "秘书部",
						value: 5
					}
				]
				var option = {
					title: {
						text: ' ',
						subtext: '南方学院',
						x: 'center'
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						type: 'scroll',
						orient: 'vertical',
						right: 10,
						top: 10,
						bottom: 10,
						data: type,
						selected: false
					},
					series: [{
						name: '部门',
						type: 'pie',
						//设置图形大小
						radius: '55%',
						center: ['50%', '50%'],
						data: person,
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}]
				};
				// 基于准备好的dom，初始化echarts实例
				let myChart = this.$echarts.init(document.getElementById('myChart'))
				// 绘制图表
				myChart.setOption(option);
			}
		}
	}
</script>
