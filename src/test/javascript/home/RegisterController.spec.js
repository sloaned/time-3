
describe('RegisterController', function() {
	beforeEach(module('timeclockApp.register'));

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
		controller = $controller('timeclockApp.register.register-controller', { $scope: $scope });
	}));
    /*
	it('Has value set to $scope.clearButtonText', function() {
		expect($scope.clearButtonText).toEqual('Clear');
	});  */

});