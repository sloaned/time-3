
angular.module('timeclockApp', [
      'ui.router',
      'timeclockApp.routes',
      'timeclockApp.home',
      'timeclockApp.user'
    ])
	.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
	    $rootScope.$state = $state;
	    $rootScope.$stateParams = $stateParams;
	    $rootScope.error = null;
	}])
	.config(['$httpProvider', function($httpProvider) {

		$httpProvider.interceptors.push(['$q', '$rootScope', function($q, $rootScope) {
			return {
				'request' : function(config) {
					$rootScope.error = null;
					return config;
				},
				'response' : function(response) {
					$rootScope.error = null;
					return $q.when(response);
				},
				'responseError' : function(rejection) {
					$rootScope.error = rejection;
					return $q.reject(rejection);
				}
			};
		}]);
	} ]);