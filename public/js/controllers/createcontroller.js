module.exports = function(mix) {
    mix.controller('CreateController', ['$scope', 'LoginService', '$location', function($scope, LoginService, $location) {
        $scope.username = "",

        $scope.login = function() {
            console.log("clicked login");

        };
        // $http({
              // url: '/users',
        //       method: 'post',
        //       data: {
        //           username: $scope.username,
        //           password: $scope.password,
        //       },
        //   }).then(function () {
        //       $location.path('/mixmatch');
        //   }).catch(function () {
        //       console.error('INTRUDER');
        //       $location.path('/shit')
        //   });

    }]);
};
