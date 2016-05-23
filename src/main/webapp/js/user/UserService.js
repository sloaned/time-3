
angular.module('timeclockApp.user')
	.factory('timeclockApp.user.service', ['timeclockApp.user.resource', function (userResource) {
		return {
			read: function (id) {
				return userResource.get({id:id});
			},
			readAll: userResource.query,
			create: userResource.save,
			update: function (oldId, user) {
				return userResource.update(_.merge({oldId:oldId}, user));
			},
			remove: function (id) {
				return userResource.remove({id:id});
			}
		};
	}]);