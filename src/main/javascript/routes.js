
var angular = require('angular');

module.exports = angular.module('timeclockApp.routes', ['ui.router'])
	.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
		
		$urlRouterProvider.otherwise('/login');
		
		$stateProvider
			.state('home', {
				url: '/',
				templateUrl: '/templates/home/home.html',
				controller: 'timeclockApp.home.controller'
			})

			.state('login', {
			    url: '/login',
			    templateUrl: '/templates/login/login.html',
			    controller: 'timeclockApp.login.login-controller'
			})

			.state('register', {
			    url: '/register',
			    templateUrl: '/templates/register/register.html',
			    controller: 'timeclockApp.register.register-controller'
			})

			.state('timetracker', {
			    url: '/timetracker',
			    templateUrl: '/templates/timetracker/timetracker.html',
			    controller: 'timeclockApp.timetracker.timetracker-controller'
			})
			
			.state('user', {
				url: '/user',
				templateUrl: '/templates/user/user-list.html',
				controller: 'timeclockApp.user.list-controller'
			})
			
			.state('user-profile', {
				url: '/user/profile/:userId',
				templateUrl: '/templates/user/user-profile.html',
				controller: 'timeclockApp.user.profile-controller'
			})
			
			.state('user-profile-edit', {
				url: '/user/edit/:userId',
				templateUrl: '/templates/user/user-edit.html',
				controller: 'timeclockApp.user.edit-controller'
			})
			
			.state('user-register', {
				url: '/user/register',
				templateUrl: '/templates/user/user-register.html',
				controller: 'timeclockApp.user.register-controller'
			});
	}]);