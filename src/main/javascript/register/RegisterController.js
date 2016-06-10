

module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.register.service',
	    function ($scope, $state, registerService) {

            $scope.confirmPassword = "";
            $scope.firstNameError = false;
            $scope.lastNameError = false;
            $scope.usernameError = false;
            $scope.passwordError = false;
            $scope.passwordMatchError = false;
            $scope.emailError = false;

            $scope.user = {};

            $scope.register = function() {

               if (validateFields()) {

                    registerService.register($scope.user).then(function(response) {
                        console.log(response.data);
                        if (response.data !== null && response.data !== "") {
                            sessionStorage.userId = response.data.id;
                            sessionStorage.loginToken = response.data.loginToken;
                            sessionStorage.userRole = response.data.role;
                            $state.go('user');
                        } else {
                            console.log("login failed");
                        }
                    });
               }
            };


            $scope.clearFields = function() {
                console.log("clear button clicked!");
                $scope.user.firstName = "";
                $scope.user.lastName = "";
                $scope.user.username = "";
                $scope.user.password = "";
                $scope.confirmPassword = "";
                $scope.user.email = "";
                $scope.firstNameError = false;
                $scope.lastNameError = false;
                $scope.usernameError = false;
                $scope.passwordError = false;
                $scope.passwordMatchError = false;
                $scope.emailError = false;
            };


            var validateFields = function() {
                var valid = true;

                if ($scope.user.username === null || $scope.user.username.length === 0) {
                    $scope.usernameErrorMessage = "* Username is required *";
                    $scope.usernameError = true;
                    valid = false;
                } else if (!$scope.checkUsername()) {
                    valid = false;
                } else { $scope.usernameError = false; }
                if ($scope.user.password === null || $scope.user.password.length === 0) {
                    $scope.passwordErrorMessage = "* Password is required *";
                    $scope.passwordError = true;
                    valid = false;
                } else if (!validatePassword($scope.password)) {
                    valid = false;
                }
                else { $scope.passwordError = false; }
                if ($scope.confirmPassword === null || $scope.confirmPassword != $scope.user.password) {
                    $scope.passwordMatchError = true;
                    valid = false;
                }
                else { $scope.passwordMatchError = false; }
                if ($scope.user.email === null || $scope.user.email.length === 0 || !$scope.validateEmail($scope.email)) {
                    $scope.emailErrorMessage = "* Valid email is required *";
                    $scope.emailError = true;
                    valid = false;
                }
                else { $scope.emailError = false; }

                return valid;
            };


            $scope.checkUsername = function() {
                if (validateUsername($scope.user.username)) {
                    var valid = checkUsernameInDatabase();
                    return valid;
                } else {
                    $scope.usernameErrorMessage = "* Username may only contain alphanumeric characters, dashes, and underscores *";
                    $scope.usernameError = true;
                    return false;
                }

            };

            $scope.checkPassword = function() {
                if (validatePassword($scope.password)) {
                    $scope.comparePasswords();
                }
            };

            $scope.validateEmail = function(email) {
                var re = /\S+@\S+\.\S+/;
                return re.test(email);
            };

            var validateUsername = function(username) {
                var re = /^[a-zA-Z0-9_-]*$/;
                return re.test(username);
            };

            var checkUsernameInDatabase = function() {
                var valid = true;
                if ($scope.user.username != null && $scope.user.username.length > 0) {
                    registerService.checkUsername($scope.user.username).then(function(response) {
                        if (response.data === true) {
                            $scope.usernameErrorMessage = "* This username is already registered *";
                            $scope.usernameError = true;
                            valid = false;

                        } else {
                            $scope.usernameError = false;
                            valid = true;
                        }

                    });
                } else {
                    $scope.usernameError = false;
                    valid = false;
                }

                return valid;
            };

            var validatePassword = function(password) {

                var uppercase = /[A-Z]+/;
                var lowercase = /[a-z]+/;
                var number = /[0-9]+/;
                var special = /[!@#$%^&*()_+\-=\[\]{};:\\|,.<>\/?]+/;

                if (uppercase.test(password) && lowercase.test(password) && number.test(password) && special.test(password)) {
                    $scope.passwordError = false;
                    return true;
                } else {
                    $scope.passwordErrorMessage = "* Password must contain an uppercase letter, a lowercase letter, a number and a special character *";
                    $scope.passwordError = true;
                    return false;
                }

            };

            $scope.comparePasswords = function() {
                if ($scope.user.password === $scope.confirmPassword || $scope.confirmPassword === null || $scope.confirmPassword === "") { $scope.passwordMatchError = false; }
                else { $scope.passwordMatchError = true; }
            };

	    }
	 ];