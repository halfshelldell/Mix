module.exports = function(mix) {
    mix.controller('LoginController', ['$scope', 'LoginService', '$location', function($scope, LoginService, $location) {
        $scope.username = "",
        $scope.userpassword = "",

        $scope.login = function() {
            console.log("clicked login");

        };
        // $http({
        //       // url: '/users',
        //       method: 'post',
        //       data: {
        //           name: $scope.username,
        //           password: $scope.userpassword,
        //       },
        //   }).then(function () {
        //       $location.path('/mixmatch');
        //   }).catch(function () {
        //       console.error('INTRUDER');
        //       $location.path('/shit')
        //   });

    }]);
};
