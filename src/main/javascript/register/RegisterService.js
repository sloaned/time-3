

module.exports = ['timeclockApp.register.resource', function (registerResource) {
       		return {
       			read: function (id) {
       				return registerResource.get({id:id});
       			},
       			register: function (user) {
       			    return registerResource.register(user);
       			},
       			checkUsername: function (username) {
       			    return  registerResource.checkUsername(username);
       			}
       		};
       	}];