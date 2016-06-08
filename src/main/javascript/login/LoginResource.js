
module.exports = ['$http', function ($http) {
		return {
		    login: function(credentials) {
		        return $http.post('/api/user/login', credentials);
		    }
		};
	}];


