

module.exports = ['timeclockApp.login.resource', function (loginResource) {
       		return {
       			read: function (id) {
       				return loginResource.get({id:id});
       			},
       			login: function (credentials) {
       			    return loginResource.login(credentials);
       			}
       		};
       	}];