

module.exports = [
	    '$scope',
	    '$state',
	    'timeclockApp.register.service',
	    function ($scope, $state, registerService) {

	        $scope.clearButtonText = "Clear";

            $scope.confirmPassword = "";
            $scope.firstNameError = false;
            $scope.lastNameError = false;
            $scope.usernameError = false;
            $scope.passwordError = false;
            $scope.passwordMatchError = false;
            $scope.emailError = false;

            $scope.emailErrorMessage = "* Email is required *";

            $scope.user = { username: "", firstName: "", lastName: "", password: "", email: ""};

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


            $scope.clearErrors = function() {
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
                } else {
                    $scope.usernameError = false;
                }
                if ($scope.user.password === null || $scope.user.password.length === 0) {
                    $scope.passwordErrorMessage = "* Password is required *";
                    $scope.passwordError = true;
                    valid = false;
                } else if (!validatePassword($scope.user.password)) {
                    valid = false;
                }
                else {
                    $scope.passwordError = false;
                }
                if ($scope.confirmPassword === null || $scope.confirmPassword != $scope.user.password) {
                    $scope.passwordMatchError = true;
                    valid = false;
                }
                else {
                    $scope.passwordMatchError = false;
                }
                console.log($scope.user.email);
                if ($scope.user.email === null || $scope.user.email === "") {
                    $scope.emailError = true;
                    valid = false;
                } else {
                    $scope.emailError = false;
                }

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
                if (validatePassword($scope.user.password)) {
                    $scope.comparePasswords();
                }
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

                console.log("password = " + password);
                var uppercase = /[A-Z]+/;
                var lowercase = /[a-z]+/;
                var number = /[0-9]+/;
                var special = /[!@#$%^&*()_+\-=\[\]{};:\\|,.<>\/?]+/;

                console.log("uppercase test:")
                console.log(uppercase.test(password));
                console.log("lowercase test");
                console.log(lowercase.test(password));
                console.log("number test:");
                console.log(number.test(password));
                console.log("special test:");
                console.log(special.test(password));

                $scope.passwordErrorMessage = "* Password still requires: ";

                if (!uppercase.test(password)) {
                    $scope.passwordErrorMessage += "uppercase letter -- ";
                }
                if (!lowercase.test(password)) {
                    $scope.passwordErrorMessage += "lowercase letter -- ";
                }
                if (!number.test(password)) {
                    $scope.passwordErrorMessage += "number -- ";
                }
                if (!special.test(password)) {
                    $scope.passwordErrorMessage += "special character ";
                }
                $scope.passwordErrorMessage += "*";

                if (uppercase.test(password) && lowercase.test(password) && number.test(password) && special.test(password)) {
                    $scope.passwordError = false;
                    return true;
                } else {
                   // $scope.passwordErrorMessage = "* Password must contain an uppercase letter, a lowercase letter, a number and a special character *";
                    $scope.passwordError = true;
                    return false;
                }

            };

            $scope.comparePasswords = function() {
                if ($scope.user.password === $scope.confirmPassword || $scope.confirmPassword === null || $scope.confirmPassword === "") {
                    $scope.passwordMatchError = false;
                } else {
                    $scope.passwordMatchError = true;
                }
            };

	    }
	 ];