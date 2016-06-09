
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
    		    getAll: function() {
    		        return $http.get('/api/user');
    		    },
    		    get: function(userId) {
    		        return $http.get('/api/user/' + userId);
    		    },
    		    create: function(user) {
    		        return $http.post('/api/user', user);
    		    },
    		    update: function(userId, user) {
    		        return $http.put('/api/user/' + userId, user);
    		    },
    		    remove: function(userId) {
    		        return $http.delete('/api/user/' + userId);
    		    }
    		};
    	}];