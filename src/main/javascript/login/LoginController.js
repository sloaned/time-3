

module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.login.service',
	    function ($scope, $state, userService) {
	    	$scope.user = {};
	    	$scope.username = "";
	    	$scope.password = "";
	    	$scope.credentials = {};

	    	$scope.submit = function(user) {
	    		userService.create(user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	};

	    	$scope.login = function() {

	    	    console.log("username = " + $scope.username + ", password = " + $scope.password);
	    	    $scope.credentials.username = $scope.username;
	    	    $scope.credentials.password = $scope.password;
	    	    userService.login($scope.credentials).then(function (response) {
	    	        console.log(response);
	    	        if (response.data === true) {
	    	            $state.go('user');
	    	        } else {
	    	            console.log("login failed");
	    	        }
	    	    });
	    	};
	    }
	];