
module.exports = ['$http', function ($http) {
		return {
		    login: function(credentials) {
		        return $http.post('/api/user/login', credentials);
		    }


		};
	}];


		/*$resource('/api/user/login', null, {
			login: {
				method: 'GET',
				params: {
					username: '@username',
					password: '@password'
				}
			},

		});
	}];




	angular.module('SpacedOut').factory('customerFactory', ['$http',
       function($http) {
        return {
        	getAllCustomers: function(){
        		return $http.get('/customer');
        	},
        	getCustomerById: function(id){
        		return $http.get('/customer/' + id);
        	},
        	addCustomer: function(customer){
        		return $http.post('/customer', customer);
        	},
        	updateCustomer: function(customer){
        		return $http.put('/customer', customer);
        	}

        };
    }]);*/