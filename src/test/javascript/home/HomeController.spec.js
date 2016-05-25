
describe('HomeController', function() {
	beforeEach(module('timeclockApp.home'));
	
	var $controller;
	var $scope = {};
    var controller;
	
	beforeEach(inject(function(_$controller_) {
		// The injector unwraps the underscores (_) from around the parameter
		// names when matching
		$controller = _$controller_;
		
		// Reset scope before each test.
		$scope = {};
		
		// create controller before each test
		controller = $controller('timeclockApp.home.controller', { $scope: $scope });
	}));
	
	it('Has value set to $scope.prop', function() {
		expect($scope.prop).toEqual('Here is a value!');
	});
	
	
});