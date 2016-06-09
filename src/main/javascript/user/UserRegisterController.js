
module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.user.service',
	    function ($scope, $state, userService) {
	        userService.loggedIn().then(function(response) {
                console.log(response);
                console.log(response.data);
                if (response.data != true) {
                    $state.go('login');
                }
            });

	    	$scope.user = {};
	    	
	    	$scope.submit = function(user) {
	    		userService.create(user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	}
	    }
	];