module.exports = function (mix) {
    mix.factory('RatingService', ['$http', function ($http) {
        let recipes = [];

        return {
            getRecipes: function () {
                $http({
                    url: '/recipes',
                    method: 'get'
                }).then(function (results) {
                  console.table(results.data);
                    angular.copy(results.data, recipes)
                });

                return recipes;
            },
        };
    }]);
};
