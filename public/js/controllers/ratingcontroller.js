module.exports = function(mix) {
    mix.controller('RatingController', ['$scope', 'RatingService', '$location', '$http', function($scope, RatingService, $location, $http) {
        $scope.recipes = RatingService.getRecipes()

        $scope.rateRecipe = function(recipe, vote) {
            console.log("clicked rating", vote, recipe.id);
            $http({
                url: '/favs',
                method: 'post',
                data: {
                    id: recipe.id,
                    votes: vote,
                }
            });

        };
    }]);
};
