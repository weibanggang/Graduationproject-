import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes: [{
		path: '/',
		name: '',
		component: () => import('./login.vue')
	}, {
		path: '/index',
		name: 'index',
		component: () => import('./index.vue'),
		children: [
			{
					path: '/',
					name: '',
					component: () => import('./components/echarts/index.vue')
			},
			{
				path: '/index/departmentTypeSelect',
				name: 'departmentTypeSelect',
				component: () => import('./components/departmentType/select.vue')
			},
			{
				path: '/index/departmentTypeAdd',
				name: 'departmentTypeAdd',
				component: () => import('./components/departmentType/add.vue')
			},
			{
				path: '/index/positionTypeSelect',
				name: '/positionTypeSelect',
				component: () => import('./components/positionType/select.vue')
			},
			{
				path: '/index/leaveRecordSelect',
				name: '/leaveRecordSelect',
				component: () => import('./components/leaveRecord/select.vue')
			},
			{
				path: '/index/positionTypeAdd',
				name: 'positionTypeAdd',
				component: () => import('./components/positionType/add.vue')
			},
			{
				path: '/index/noticSelect',
				name: '/noticSelect',
				component: () => import('./components/notic/select.vue')
			},
			{
				path: '/index/noticAdd',
				name: 'noticAdd',
				component: () => import('./components/notic/add.vue')
			}, {
				path: '/index/memberInformationSelect',
				name: '/memberInformationSelect',
				component: () => import('./components/memberInformation/select.vue')
			},
			{
				path: '/index/memberInformationAdd',
				name: '/memberInformationAdd',
				component: () => import('./components/memberInformation/add.vue')
			},
			{
				path: '/index/memberInformationselectEcharts',
				name: '/memberInformationselectEcharts',
				component: () => import('./components/memberInformation/selectEcharts.vue')
			}, {
				path: '/index/financialManagementSelect',
				name: '/financialManagementSelect',
				component: () => import('./components/financialManagement/select.vue')
			},
			{
				path: '/index/financialManagementAdd',
				name: '/financialManagementAdd',
				component: () => import('./components/financialManagement/add.vue')
			},
			{
				path: '/index/monthlyPlanSummarySelect',
				name: '/monthlyPlanSummarySelect',
				component: () => import('./components/monthlyPlanSummary/select.vue')
			},
			{
				path: '/index/jurisdictionSelect',
				name: '/jurisdictionSelect',
				component: () => import('./components/jurisdiction/select.vue')
			}, {
				path: '/index/classTableSelect',
				name: '/classTableSelect',
				component: () => import('./components/classTable/select.vue')
			}, {
				path: '/index/typeOfMeetingSelect',
				name: '/typeOfMeetingSelect',
				component: () => import('./components/typeOfMeeting/select.vue')
			}, {
				path: '/index/exchangeTableSelect',
				name: '/exchangeTableSelect',
				component: () => import('./components/exchangeTable/select.vue')
			}, {
				path: '/index/workArrangementSelect',
				name: '/workArrangementSelect',
				component: () => import('./components/workArrangement/select.vue')
			}, {
				path: '/index/departmentalExcellenceRecordSelect',
				name: '/departmentalExcellenceRecordSelect',
				component: () => import('./components/departmentalExcellenceRecord/select.vue')
			}, {
				path: '/index/personalMeritRecordSelect',
				name: '/personalMeritRecordSelect',
				component: () => import('./components/personalMeritRecord/select.vue')
			}, {
				path: '/index/attendanceSelect',
				name: '/attendanceSelect',
				component: () => import('./components/attendance/select.vue')
			}, {
				path: '/index/minutesOfTheMeetingSelect',
				name: '/minutesOfTheMeetingSelect',
				component: () => import('./components/minutesOfTheMeeting/select.vue')
			},
		]
	}, ]
})
