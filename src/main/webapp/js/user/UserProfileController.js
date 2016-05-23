
angular.module('timeclockApp.user')
	.controller('timeclockApp.user.profile-controller', [
	    '$scope',
	    '$state',
	    '$stateParams',
	    'timeclockApp.user.service',
	    function ($scope, $state, $stateParams, userService) {
	    	$scope.user = userService.read($stateParams.userId);
	    	
	    	$scope.remove = function(userId) {
	    		userService.remove(userId).$promise.then(function () {
	    			$state.go('user');
	    		});
	    	};
	    }
	]);