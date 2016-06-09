
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
                } else {
                    userService.read($stateParams.userId).then(function(response) {
                        console.log(response.data);
                        $scope.user = response.data;
                    });
                }
            });


	    	
	    	$scope.remove = function(userId) {
	    		userService.remove(userId).$promise.then(function () {
	    			$state.go('user');
	    		});
	    	};
	    }
	];