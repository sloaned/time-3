

module.exports = ['$http', function ($http) {
		return {
		    register: function(user) {
		        return $http.post('/api/user', user);
		    }
		};
	}];