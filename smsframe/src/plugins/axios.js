"use strict";

import Vue from 'vue';
import axios from "axios";
import router from '../router'
// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: process.env.baseURL || process.env.apiUrl || ""
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
  function(config) {
  var accessToken = localStorage.getItem('accessToken');
   // 判断是否存在Authorization，如果存在的话，则每个http header都加上Authorization
	if (accessToken && accessToken !== '') {
  	config.headers.common['Authorization'] = accessToken;
  }
    return config;
  },
  function(error) {
    // Do something with request error
    return Promise.reject(error);
  }
);
// 响应拦截器获取 headers，设置(刷新) Token
// Add a response interceptor
_axios.interceptors.response.use(
  function(response) {
    // Do something with response data
		var accessToken = response.headers['authorization'];
		if (accessToken && accessToken !== '') {
			localStorage.setItem('accessToken',accessToken);
		}
			if(response.data.code === 502){
				router.push({
					path:"/"
				})
			}
		return response;
  },
  function(error) {
    // Do something with response error
		console.log("用户访问页面出错！");
    return Promise.reject(error);
  }
);

Plugin.install = function(Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
};

Vue.use(Plugin)

export default Plugin;
