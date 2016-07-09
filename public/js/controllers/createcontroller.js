module.exports = function(mix) {
    mix.controller('CreateController', ['$scope', 'CreateService', '$location', '$http', function($scope, CreateService, $location, $http) {
            $scope.recipeName = "",
            $scope.time = 0,
            $scope.instructions = "",
            $scope.ingredients = "",
            $scope.skill = "",
            $scope.file = "",

            $scope.createRecipe = function() {
                console.log("clicked create");
                $http({
                    url: '/create-recipe',
                    method: 'post',
                    data: {
                        recipeName: $scope.recipeName,
                        time: $scope.time,
                        instructions: $scope.instructions,
                        ingredients: $scope.ingredients,
                        skill: $scope.skill,
                        file: $scope.file,

                    },

                });

            };
    }]);
};
