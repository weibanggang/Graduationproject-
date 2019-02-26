<template>
	<div  :style="{width: '1100px'}">
		<div id="myChart" :style="{width: '1100px', height: '500px'}"></div>
	</div>
	
</template>

<script>
	export default {
		data() {
			return {
			url: "http://localhost:8080",
			}
		},
		mounted() {
			this.drawLine();
		},
		methods: {
			drawLine() {
					const th = this;
				axios.get(th.url + '/memberInformation/homeReport')
				.then(function(res) {
					var person = [];
					var type = [];
					res.data.data.forEach(function(item){
						person.push({name:item.dName,value:item.count});
						type.push(item.dName);
					})
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
					let myChart = th.$echarts.init(document.getElementById('myChart'))
					// 绘制图表
					myChart.setOption(option);
					})
				
			
			}
		}
	}
</script>
