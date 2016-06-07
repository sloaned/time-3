

module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.user.service',
	    function ($scope, $state, userService) {
	    	$scope.user = {};
	    	$scope.username = "";
	    	$scope.password = "";

	    	$scope.submit = function(user) {
	    		userService.create(user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	};

	    	$scope.login = function(username, password) {
	    	    userService.login(username, password).$promise.then(function (response)) {
	    	        if (response == true) {
	    	            $state.go('user');
	    	        } else {
	    	            console.log("login failed");
	    	        }
	    	    }
	    	};
	    }
	];