

module.exports = ['$http', function ($http) {
		return {
		    register: function(user) {
		        return $http.post('/api/user', user);
		    },
		    checkUsername: function(username) {
		        return $http.get('/api/username/' + username);
		    }
		};
	}];