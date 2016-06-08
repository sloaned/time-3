
var angular = require('angular');

module.exports = angular.module('timeclockApp.login', [require('angular-resource'), require('angular-ui-router')])
	.factory('timeclockApp.login.resource', require('./LoginResource'))
	.factory('timeclockApp.login.service', require('./LoginService'))
	.controller('timeclockApp.login.login-controller', require('./LoginController'));