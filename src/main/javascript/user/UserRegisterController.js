
module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.user.service',
	    function ($scope, $state, userService) {
	    	$scope.user = {};
	    	
	    	$scope.submit = function(user) {
	    		userService.create(user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	}
	    }
	];