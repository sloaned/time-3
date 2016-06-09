
var angular = require('angular');

module.exports = angular.module('timeclockApp.home', [require('angular-ui-router')])
	.controller('timeclockApp.home.controller', require('./HomeController'));