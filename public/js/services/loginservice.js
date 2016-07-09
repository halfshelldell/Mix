module.exports = function (mix) {
    mix.factory('LoginService', ['$http', function ($http) {
        let user = [];

        return {
          postUser: function() {
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
            // });
          }
                // return postUser;
            // },
        };
    }]);
};
