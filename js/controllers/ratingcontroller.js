module.exports = function(mix) {
    mix.controller('RatingController', ['$scope', 'RatingService', 'LoginService', '$location', '$http', function($scope, RatingService, LoginService, $location, $http) {
        $scope.recipes = RatingService.getRecipes(),
        $scope.user = LoginService.getUser(),

        $scope.rateRecipe = function(recipe, vote) {
            console.log("clicked rating", vote, recipe);
            $http({
                url: '/favs',
                method: 'post',
                data: {
                    id: recipe.id,
                    fav: vote,
                }
            });

        };
    }]);
};
