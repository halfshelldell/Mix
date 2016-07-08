module.exports = function(mix) {
    mix.controller('LoginController', ['$scope', 'LoginService', '$location', function($scope, LoginService, $location) {
        // $scope.players = LoginService.getPlayerArr();
        // $scope.password = LoginService.getPassword();

        $scope.submit = function() {
            console.log("clicked login controller");
            console.log($scope.players);

        };
    }]);
};
