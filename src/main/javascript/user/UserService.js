var _ = require('lodash');

module.exports = ['timeclockApp.user.resource', function (userResource) {
		return {
			read: function (id) {
				return userResource.get({id:id});
			},
			readAll: function() {
			    return userResource.readAll();
			},
			create: userResource.save,
			update: function (oldId, user) {
				return userResource.update(_.merge({oldId:oldId}, user));
			},
			remove: function (id) {
				return userResource.remove({id:id});
			},
			loggedIn: function () {
			    var userId = sessionStorage.userId;
			    var token = sessionStorage.loginToken;
			    console.log(userId);
			    console.log(token);
			    return userResource.loggedIn(userId, token);
			}
		};
	}];