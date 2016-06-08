
var angular = require('angular');

module.exports = angular.module('timeclockApp.register', [require('angular-resource'), require('angular-ui-router')])
	.factory('timeclockApp.register.resource', require('./RegisterResource'))
	.factory('timeclockApp.register.service', require('./RegisterService'))
	.controller('timeclockApp.register.register-controller', require('./RegisterController'));