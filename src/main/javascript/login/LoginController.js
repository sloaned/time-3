

module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.login.service',
	    function ($scope, $state, loginService) {
	    	$scope.user = {};
	    	$scope.username = "";
	    	$scope.password = "";
	    	$scope.credentials = {};
	    	$scope.loginFailed = false;
	    	$scope.loginFailMessage = "* Invalid credentials. ";

	    	$scope.login = function() {
                $scope.loginFailed = false;
	    	    console.log("username = " + $scope.username + ", password = " + $scope.password);
	    	    $scope.credentials.username = $scope.username;
	    	    $scope.credentials.password = $scope.password;
	    	    loginService.login($scope.credentials).then(function (response) {
	    	        console.log(response.data);
	    	        if (response.data !== null && response.data !== "") {
	    	            sessionStorage.userId = response.data.id;
	    	            sessionStorage.loginToken = response.data.loginToken;
	    	            console.log(response.data);
	    	            console.log("here's the id: ");
	    	            console.log(response.data.id);
	    	            $state.go('user');
	    	        } else {
	    	            console.log("login failed");
	    	            $scope.loginFailed = true;
	    	        }
	    	    });
	    	};
	    }
	];