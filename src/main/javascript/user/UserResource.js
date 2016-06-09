
module.exports = ['$resource', function ($resource) {
		return $resource('/api/user/:id', null, {
			update: {
				method: 'PUT',
				params: {
					id: '@oldId'
				}
			}
		});
	}];

	module.exports = ['$http', function ($http) {
    		return {
    		    register: function(user) {
    		        return $http.post('/api/user', user);
    		    },
    		    checkUsername: function(username) {
    		        return $http.get('/api/username/' + username);
    		    },
    		    loggedIn: function(userId, token) {
    		        return $http({
    		            method: 'GET',
    		            url: '/api/user/login',
    		            headers: {
    		                'userId': userId,
    		                'token' : token
    		            }
    		        });
    		    },
    		    readAll: function() {
    		        return $http.get('/api/user');
    		    }
    		};
    	}];