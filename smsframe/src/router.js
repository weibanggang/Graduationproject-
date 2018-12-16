import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
    	path: '/adminSelect',
    	name: 'adminSelect',
    	component: () => import('./components/admin/select.vue')
    },
    {
    	path: '/adminAdd',
    	name: 'adminAdd',
    	component: () => import('./components/admin/add.vue')
    },
		{
			path: '/departmentTypeSelect',
			name: 'departmentTypeSelect',
			component: () => import('./components/departmentType/select.vue')
		},
		{
			path: '/departmentTypeAdd',
			name: 'departmentTypeAdd',
			component: () => import('./components/departmentType/add.vue')
		},
		{
			path: '/positionTypeSelect',
			name: '/positionTypeSelect',
			component: () => import('./components/positionType/select.vue')
		},{
			path: '/leaveRecordAdd',
			name: '/leaveRecordAdd',
			component: () => import('./components/leaveRecord/add.vue')
		},
		{
			path: '/leaveRecordSelect',
			name: '/leaveRecordSelect',
			component: () => import('./components/leaveRecord/select.vue')
		},
		{
			path: '/positionTypeAdd',
			name: 'positionTypeAdd',
			component: () => import('./components/positionType/add.vue')
		},
		{
			path: '/noticSelect',
			name: '/noticSelect',
			component: () => import('./components/notic/select.vue')
		},
		{
			path: '/noticAdd',
			name: 'noticAdd',
			component: () => import('./components/notic/add.vue')
		},{
			path: '/memberInformationSelect',
			name: '/memberInformationSelect',
			component: () => import('./components/memberInformation/select.vue')
		},
		{
			path: '/memberInformationAdd',
			name: 'memberInformationAdd',
			component: () => import('./components/memberInformation/add.vue')
		},
		{
			path: '/jurisdictionSelect',
			name: '/jurisdictionSelect',
			component: () => import('./components/jurisdiction/select.vue')
		},{
			path: '/classTableSelect',
			name: '/classTableSelect',
			component: () => import('./components/classTable/select.vue')
		},{
			path: '/typeOfMeetingSelect',
			name: '/typeOfMeetingSelect',
			component: () => import('./components/typeOfMeeting/select.vue')
		},{
			path: '/exchangeTableSelect',
			name: '/exchangeTableSelect',
			component: () => import('./components/exchangeTable/select.vue')
		},{
			path: '/workArrangementSelect',
			name: '/workArrangementSelect',
			component: () => import('./components/workArrangement/select.vue')
		},
  ]
})
