<template>
	<div  :style="{width: '1100px'}">
		<div id="myChart" :style="{width: '1100px', height: '500px'}"></div>
	</div>
	
</template>

<script>
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
				axios.get(th.url + '/memberInformation/homeReport')
				.then(function(res) {
					if(res.data.code === 1224 && res.data.data != null){
						th.xr(res);
					}else{
						th.xrs();
					}
					})
			},
			xr(res){
				const th = this;
				var person = new Array();
				var type = new Array();
				console.log(res.data.data[0]);
				console.log('=======')
				
				for(let i =0 ;i < res.data.data.length;i++){
					person.push({name: res.data.data[i].dName, value: res.data.data[i].count});
					type.push(res.data.data[i].dName);
				}
				
// 				res.data.data.forEcath(function(item){
// 					person.push({name: item.dName, value: item.count});
// 					type.push(item.dName);
// 				});
				var option = {
					title: {
						text: ' ',
						subtext: '南方学院(虚拟数据)',
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
				
			},
			xrs(){
				const th = this;
				var person = [
					{name: "指导老师", value: 4},
					{name: "主席团", value: 4},
					{name: "纪检部", value: 20},
					{name: "生活部", value: 25},
					{name: "播音部", value: 10},
					{name: "文体部", value: 28},
					{name: "秘书部", value: 6}];
				var type = ["指导老师","主席团", "纪检部","生活部","播音部","文体部", "秘书部"];
			
				
				var option = {
					title: {
						text: ' ',
						subtext: '南方学院(虚拟数据)',
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
				
			}
		}
	}
</script>
