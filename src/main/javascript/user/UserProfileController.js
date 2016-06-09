
module.exports = [
	    '$scope',
	    '$state',
	    '$stateParams',
	    'timeclockApp.user.service',
	    function ($scope, $state, $stateParams, userService) {
	        userService.loggedIn().then(function(response) {
                console.log(response);
                console.log(response.data);
                if (response.data != true) {
                    $state.go('login');
                }
            });

	    	$scope.user = userService.read($stateParams.userId);
	    	
	    	$scope.remove = function(userId) {
	    		userService.remove(userId).$promise.then(function () {
	    			$state.go('user');
	    		});
	    	};
	    }
	];