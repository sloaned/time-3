
module.exports = [
        '$scope',
        '$state',
        'timeclockApp.timetracker.service',
         function ($scope, $state, timetrackerService) {
            timetrackerService.loggedIn().then(function(response) {
                console.log(response);
                console.log(response.data);
                if (response.data != true) {
                    $state.go('login');
                }
            });



		$scope.prop = 'Here is a value!';

        $scope.logout = function() {
            timetrackerService.logout();
            $state.go('login');
        };
	}];