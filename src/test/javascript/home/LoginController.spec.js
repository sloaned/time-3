
describe('LoginController', function() {
	beforeEach(module('timeclockApp.login'));

	var $controller;
	var $scope;
    var controller;

	beforeEach(inject(function(_$controller_) {
		// The injector unwraps the underscores (_) from around the parameter
		// names when matching
		$controller = _$controller_;

		// Reset scope before each test.
		$scope = {};

		// create controller before each test
		controller = $controller('timeclockApp.login.login-controller', { $scope: $scope });
	}));

	it('Has value set to $scope.registerButtonText', function() {
		expect($scope.registerButtonText).toEqual('Register an account');
	});

    /*

	it('should display an error message on login failure', inject(function($q, $rootScope) {
	    var deferred = $q.defer();
	    var promise = deferred.promise;

        $scope.username = "admin";
        $scope.password = "badpassword";

        var promise = $scope.login();
        promise.then(function() {
            expect($scope.loginFailMessage).toEqual("* Invalid credentials *")
            expect($scope.loginFailed).toEqual(true);
        });

    }));  */

});