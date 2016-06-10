
module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.user.service',
	    function ($scope, $state, userService) {
	        userService.loggedIn().then(function(response) {
                console.log(response);
                console.log(response.data);
                var role = sessionStorage.userRole;
                console.log(role);
                if (!response.data) {
                    $state.go('login');
                } /*else if(role != "ADMIN") {
                    $state.go('timetracker');
                } */
            });

	    	$scope.user = {};
	    	
	    	$scope.submit = function(user) {
	    		userService.create(user).$promise.then(function (updatedUser) {
	    			$state.go('user-profile', {userId:updatedUser.id});
	    		});
	    	}

            $scope.logout = function() {
                userService.logout();
                $state.go('login');
            };
	    }
	];