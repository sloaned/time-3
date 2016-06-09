
var angular = require('angular');

module.exports = angular.module('timeclockApp.timetracker', [require('angular-resource'), require('angular-ui-router')])
	.factory('timeclockApp.timetracker.service', require('./TimetrackerService'))
	.controller('timeclockApp.timetracker.timetracker-controller', require('./TimetrackerController'));