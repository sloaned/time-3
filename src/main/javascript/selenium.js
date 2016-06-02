
var angular = require('angular'),
	req = require('angular-debounce'),
	_ = require('lodash');

module.exports = angular.module('camp.selenium', [
     'rt.debounce'
   ])
   .run(['$rootScope', '$window', 'debounce', function ($rootScope, $window, debounce) {

	   function executeAll(callbacks) {
		   _.remove(callbacks, function (callback){
			   if (_.isFunction(callback)){
				   callback();
			   }
			   return true;
		   });
	   }
	   
	   $window.selenium = $window.selenium || {};
	   
	   if (_.isEmpty($window.selenium) || !_.has($window.selenium, '$watch')) {
		   $window.selenium = _.merge($rootScope.$new(true), {waits:[], ready:false}, $window.selenium);
	   }
	   
	   $window.selenium.$watchCollection('waits', function (newWaits, oldWaits) {
		   if ($window.selenium.ready && newWaits.length > oldWaits.length) {
			   executeAll(newWaits);
		   }
	   });
	   
	   $rootScope.$on('$viewContentLoaded', debounce(100, function () {
		   $window.selenium.ready = true;
		   executeAll($window.selenium.waits);
	   }));
	    
	   $rootScope.$on('$stateChangeStart', debounce(100, function () {
		   $window.selenium.ready = false;
	   }));
	}]);