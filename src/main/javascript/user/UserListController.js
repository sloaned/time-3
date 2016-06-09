
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
                } else {
                    userService.readAll().then(function(response) {
                        console.log(response.data);
                    });
                }
            });


	    }
	];