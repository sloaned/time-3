
module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.user.service',
	    function ($scope, $state, userService) {

	        userService.loggedIn().then(function(response) {
                console.log(response);
                console.log(response.data);
               // var role = sessionStorage.userRole;
                if (response.data != true) {
                    $state.go('login');
                } /*else if(role != "ADMIN") {
                    $state.go('timetracker');
                } */ else {
                    userService.readAll().then(function(response) {
                        console.log(response.data);
                        $scope.users = response.data;
                    });
                }
            });

            $scope.logout = function() {
                userService.logout();
                $state.go('login');
            };
	    }
	];