(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports = function(mix) {
    mix.controller('CreateController', ['$scope', 'CreateService', '$location', function($scope, CreateService, $location) {

        $scope.createRecipe = function() {
            console.log("clicked create");

        };


    }]);
};

},{}],2:[function(require,module,exports){
module.exports = function(mix) {
    mix.controller('LoginController', ['$scope', 'LoginService', '$location', '$http', function($scope, LoginService, $location, $http) {
        $scope.username = "",
        $scope.userpassword = "",

        $scope.login = function() {
            console.log("clicked login");
            $http({
                  url: '/login',
                  method: 'post',
                  data: {
                      username: $scope.username,
                      password: $scope.userpassword,
                  },
              }).then(function () {
                  $location.path('/mixmatch');
              }).catch(function () {
                  console.error('INTRUDER');
                  $location.path('/shit')
              });

        };

    }]);
};

// usera
// passworda

},{}],3:[function(require,module,exports){
let mix = angular.module('mixApp', ['ngRoute']);
//
// // Controllers
require('./controllers/logincontroller')(mix);
require('./controllers/createcontroller')(mix);
//
// // Services
require('./services/loginservice')(mix);
require('./services/createservice')(mix);


mix.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/login',
        })
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'templates/login.html',
        })
        .when('/mixmatch', {
            // controller: '',
            templateUrl: 'templates/mixmatch.html',
        })
        .when('/rating', {
            controller: '',
            templateUrl: 'templates/rating.html',
        })
        .when('/create', {
            controller: 'CreateController',
            templateUrl: 'templates/create.html',
        })
        .otherwise({
            templateUrl: 'templates/shit.html',
        });
}]);

},{"./controllers/createcontroller":1,"./controllers/logincontroller":2,"./services/createservice":4,"./services/loginservice":5}],4:[function(require,module,exports){
module.exports = function (mix) {
    mix.factory('CreateService', ['$http', function ($http) {
        let newrecipe = [];

        return {
          // $http({
                // url: '/recipe????',
          //       method: 'post',
                // data: {
                //     id:
                //     recipeName:
                //     instructions:
                //     ingredients:
                //     skill:
                //     fileName:
                //     user:
                // },
                // dataType: 'json',
                //     contentType: "application/json"
          //   });
          
                // return user;
            // },
        };
    }]);
};




// Recipe Object…. int id, String recipeName, int time, String instructions, String ingredients, String skill, String fileName, User user

},{}],5:[function(require,module,exports){
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

},{}]},{},[3])