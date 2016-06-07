
var angular = require('angular');

module.exports = angular.module('timeclockApp.login', [require('angular-resource'), require('angular-ui-router')])
	.factory('timeclockApp.user.resource', require('./LoginResource'))
	.factory('timeclockApp.user.service', require('../user/UserService'))
	.controller('timeclockApp.login.login-controller', require('./LoginController'));