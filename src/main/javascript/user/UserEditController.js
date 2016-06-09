
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
	    	
	    	$scope.user.$promise.then(function (user) {
	    		$scope.oldId = user.id;
	    		$scope.user.birthday = new Date(user.birthday);
	    		$scope.user.createdOn = new Date(user.createdOn);
	    	});
	    	
	    	$scope.submit = function(oldId, user) {
	    		userService.update(oldId, user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	}
	    }
	];