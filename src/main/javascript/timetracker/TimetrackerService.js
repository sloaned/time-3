

module.exports = ['$http', function ($http) {
		return {
		    loggedIn: function() {
                var userId = sessionStorage.userId;
                var token = sessionStorage.loginToken;
                console.log(userId);
                console.log(token);
                return $http({
                    method: 'GET',
                    url: '/api/user/login',
                    headers: {
                        'userId': userId,
                        'token' : token
                    }
                });
            },
            logout: function() {
                sessionStorage.userId = "";
                sessionStorage.loginToken = "";
                sessionStorage.userRole = "";
            }
		};
	}];