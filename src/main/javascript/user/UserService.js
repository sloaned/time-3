
module.exports = ['timeclockApp.user.resource', function (userResource) {
		return {
			read: function (id) {
				return userResource.get(id);
			},
			readAll: function() {
			    return userResource.getAll();
			},
			create: function(user) {
			    return userResource.create(user);
			},
			update: function (oldId, user) {
				return userResource.update(oldId, user);
			},
			remove: function (id) {
				return userResource.remove(id);
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