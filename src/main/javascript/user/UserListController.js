
module.exports = [
	    '$scope',
	    'timeclockApp.user.service',
	    function ($scope, userService) {
	    	$scope.users = userService.readAll();
	    }
	];