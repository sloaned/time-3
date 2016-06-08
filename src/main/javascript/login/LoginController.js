

module.exports = [
	    '$scope',
	    '$state',
	    '$cacheFactory',
	    'timeclockApp.login.service',
	    function ($scope, $state, $cacheFactory, loginService) {
	    	$scope.user = {};
	    	$scope.username = "";
	    	$scope.password = "";
	    	$scope.credentials = {};
	    	$scope.cache = $cacheFactory('cacheId');
	    	$scope.loginFailed = false;
	    	$scope.loginFailMessage = "* Invalid credentials. ";

	    	$scope.login = function() {
                $scope.loginFailed = false;
	    	    console.log("username = " + $scope.username + ", password = " + $scope.password);
	    	    $scope.credentials.username = $scope.username;
	    	    $scope.credentials.password = $scope.password;
	    	    loginService.login($scope.credentials).then(function (response) {
	    	        console.log(response);
	    	        if (response.data !== null && response.data !== "") {
	    	            console.log(response.data);
	    	            $state.go('user');
	    	        } else {
	    	            console.log("login failed");
	    	            $scope.loginFailed = true;
	    	        }
	    	    });
	    	};
	    }
	];