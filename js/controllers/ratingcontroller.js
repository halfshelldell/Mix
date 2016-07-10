module.exports = function(mix) {
    mix.controller('RatingController', ['$scope', 'RatingService', '$location', '$http', function($scope, RatingService, $location, $http) {
        $scope.recipes = RatingService.getRecipes(),

        $scope.rateRecipe = function(recipe, vote) {
            console.log("clicked rating", vote, recipe);
            $http({
                url: '/favs',
                method: 'post',
                data: {
                    d: recipe.id,
                    fav: vote,
                }
            });

        };
        $scope.delRecipe = function(){
          $http({
            url:'/recipes',
            method: 'delete',

          });

        };
    }]);
};
