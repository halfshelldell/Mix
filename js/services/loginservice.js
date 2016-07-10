module.exports = function(mix) {
    mix.factory('LoginService', ['$http', function($http) {
        let user = [];

        return {
            getUser: function() {
                $http({
                    url: '/login',
                    method: 'get'
                }).then(function(results) {
                    console.table(results.data);
                    angular.copy(results.data, user)
                });

                return user;
            },
        };
    }]);
};
