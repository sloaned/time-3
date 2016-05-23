
angular.module('timeclockApp.user')
	.factory('timeclockApp.user.resource', ['$resource', function ($resource) {
		return $resource('/api/user/:id', null, {
			update: {
				method: 'PUT',
				params: {
					id: '@oldId'
				}
			}	
		});
	}]);