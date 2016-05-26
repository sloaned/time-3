
var angular = require('angular');

module.exports = angular.module('timeclockApp.home', [])
	.controller('timeclockApp.home.controller', require('./HomeController'));