
module.exports = [
        '$scope',
        '$state',
        function ($scope, $state) {
		$scope.prop = 'Here is a value!';





        $scope.logout = function() {
            sessionStorage.userId = "";
            sessionStorage.loginToken = "";
            sessionStorage.userRole = "";
            $state.go('login');
        };

	}];