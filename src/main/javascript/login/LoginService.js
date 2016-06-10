
module.exports = ['timeclockApp.login.resource', function (loginResource) {
       		return {
       			login: function (credentials) {
       			    return loginResource.login(credentials);
       			}
       		};
       	}];