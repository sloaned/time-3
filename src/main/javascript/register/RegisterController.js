

module.exports = [
	    '$scope',
	    '$state',
	    '$cacheFactory',
	    'timeclockApp.register.service',
	    function ($scope, $state, $cacheFactory, registerService) {
            $scope.firstName = "";
            $scope.lastName = "";
            $scope.username = "";
            $scope.password = "";
            $scope.confirmPassword = "";
            $scope.email = "";
            $scope.firstNameError = false;
            $scope.lastNameError = false;
            $scope.usernameError = false;
            $scope.passwordError = false;
            $scope.passwordMatchError = false;
            $scope.emailError = false;



            $scope.user = {};

            $scope.register = function() {

               if (validateFields()) {
                    $scope.user.firstName = $scope.firstName;
                    $scope.user.lastName = $scope.lastName;
                    $scope.user.username = $scope.username;
                    $scope.user.password = $scope.password;
                    $scope.user.email = $scope.email;

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
                var valid = true;

                if ($scope.username === null || $scope.username.length === 0) {
                    $scope.usernameErrorMessage = "* Username is required *";
                    $scope.usernameError = true;
                    valid = false;
                } else if (!$scope.checkUsername()) {
                    valid = false;
                } else { $scope.usernameError = false; }
                console.log("after checking username, valid = " + valid);
                if ($scope.password === null || $scope.password.length === 0) {
                    $scope.passwordErrorMessage = "* Password is required *";
                    $scope.passwordError = true;
                    valid = false;
                } else if (!validatePassword($scope.password)) {
                    valid = false;
                }
                else { $scope.passwordError = false; }
                if ($scope.confirmPassword === null || $scope.confirmPassword != $scope.password) {
                    $scope.passwordMatchError = true;
                    valid = false;
                }
                else { $scope.passwordMatchError = false; }
                if ($scope.email === null || $scope.email.length === 0 || !$scope.validateEmail($scope.email)) {
                    $scope.emailErrorMessage = "* Valid email is required *";
                    $scope.emailError = true;
                    valid = false;
                }
                else { $scope.emailError = false; }

                console.log("valid = " + valid);

                return valid;
            };


            $scope.checkUsername = function() {
                console.log("checking username");
                if (validateUsername($scope.username)) {
                    var valid = checkUsernameInDatabase();
                    console.log("response from checkUsernameInDatabase = " + valid);
                    return valid;
                } else {
                    console("failed validateUsername");
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
                console.log("checking the username in the database: ");;
                console.log($scope.username);
                var valid = true;
                if ($scope.username != null && $scope.username.length > 0) {
                    registerService.checkUsername($scope.username).then(function(response) {
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
            /*
                console.log("uppercase test:");
                console.log(uppercase.test(password));
                console.log("lowercase test:");
                console.log(lowercase.test(password));

                console.log("number test:");
                console.log(number.test(password));
                console.log("special test:");
                console.log(special.test(password));
            */
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
                if ($scope.password === $scope.confirmPassword || $scope.confirmPassword === null || $scope.confirmPassword === "") { $scope.passwordMatchError = false; }
                else { $scope.passwordMatchError = true; }
            };

	    }
	 ];