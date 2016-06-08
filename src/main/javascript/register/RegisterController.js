

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
            $scope.confirmPassword = "";
            $scope.email = "";
            $scope.birthday = "";
            $scope.firstNameError = false;
            $scope.lastNameError = false;
            $scope.usernameError = false;
            $scope.passwordError = false;
            $scope.passwordMatchError = false;
            $scope.emailError = false;

            $scope.user = {};

            $scope.register = function() {

                validateFields();
            }



            $scope.clearFields = function() {
                console.log("clear button clicked!");
                $scope.firstName = "";
                $scope.lastName = "";
                $scope.username = "";
                $scope.password = "";
                $scope.confirmPassword = "";
                $scope.email = "";
                $scope.birthday = "";
                $scope.firstNameError = false;
                $scope.lastNameError = false;
                $scope.usernameError = false;
                $scope.passwordError = false;
                $scope.passwordMatchError = false;
                $scope.emailError = false;
            };


            var validateFields = function() {
                if ($scope.firstName === null || $scope.firstName.length == 0) { $scope.firstNameError = true; }
                else { $scope.firstNameError = false; }
                if ($scope.lastName === null || $scope.lastName.length == 0) { $scope.lastNameError = true; }
                else { $scope.lastNameError = false; }
                if ($scope.username === null || $scope.username.length == 0) { $scope.usernameError = true; }
                else { $scope.usernameError = false; }
                if ($scope.password === null || $scope.password.length == 0) { $scope.passwordError = true; }
                else { $scope.passwordError = false; }
                if ($scope.confirmPassword === null || $scope.confirmPassword != $scope.password) { $scope.passwordMatchError = true; }
                else { $scope.passwordMatchError = false; }
                if ($scope.email === null || $scope.email.length == 0) { $scope.emailError = true; }
                else { $scope.emailError = false; }
            };

	    }
	 ];