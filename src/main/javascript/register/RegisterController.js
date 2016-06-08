

module.exports = [
	    '$scope',
	    '$state',
	    '$cacheFactory',
	    'timeclockApp.register.service',
	    function ($scope, $state, $cacheFactory, userService) {
            $scope.firstName = "";
            $scope.lastName = "";
            $scope.username = "";
            $scope.password = "";
            $scope.email = "";
            $scope.birthday = "";


            $scope.clearFields = function() {
                console.log("clear button clicked!");
                $scope.firstName = "";
                $scope.lastName = "";
                $scope.username = "";
                $scope.password = "";
                $scope.email = "";
                $scope.birthday = "";
            };

	    }
	 ];