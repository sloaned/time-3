var _ = require('lodash');

module.exports = ['timeclockApp.login.resource', function (loginResource) {
		return {
			read: function (id) {
				return loginResource.get({id:id});
			},
			readAll: loginResource.query,
			create: loginResource.save,
			update: function (oldId, user) {
				return loginResource.update(_.merge({oldId:oldId}, user));
			},
			remove: function (id) {
				return loginResource.remove({id:id});
			},
			login: function (credentials) {
			    return loginResource.login(credentials);
			}
		};
	}];