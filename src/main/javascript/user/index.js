
var angular = require('angular');

module.exports = angular.module('timeclockApp.user', [require('angular-resource'), require('angular-ui-router')])
	.factory('timeclockApp.user.resource', require('./UserResource'))
	.factory('timeclockApp.user.service', require('./UserService'))
	.controller('timeclockApp.user.edit-controller', require('./UserEditController'))
	.controller('timeclockApp.user.list-controller', require('./UserListController'))
	.controller('timeclockApp.user.profile-controller', require('./UserProfileController'))
	.controller('timeclockApp.user.register-controller', require('./UserRegisterController'));