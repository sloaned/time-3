
angular.module('timeclockApp.user')
	.controller('timeclockApp.user.list-controller', [
	    '$scope',
	    'timeclockApp.user.service',
	    function ($scope, userService) {
	    	$scope.users = userService.readAll();
	    }
	]);