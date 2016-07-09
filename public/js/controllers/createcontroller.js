module.exports = function(mix) {
    mix.controller('CreateController', ['$scope', 'CreateService', '$location', function($scope, CreateService, $location) {

        $scope.createRecipe = function() {
            console.log("clicked create");

        };


    }]);
};
