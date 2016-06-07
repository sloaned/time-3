

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