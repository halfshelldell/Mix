module.exports = function(mix) {
    mix.controller('RatingController', ['$scope', 'RatingService', '$location', '$http', function($scope, RatingService, $location, $http) {
        $scope.recipes = RatingService.getRecipes(),

        $scope.rateRecipe = function(recipe, vote) {
            console.log("clicked rating", vote, recipe);
            $http({
                url: '/favs',
                method: 'post',
                data: {
                  recipeId: recipe.id,
                  isFav: vote,
                }
            });

        };
        $scope.delRecipe = function(){
          console.log("delete");
          $http({
            url:'/recipes',
            method: 'delete',

          });

        };
    }]);
};
