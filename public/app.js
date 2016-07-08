(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
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

},{}],2:[function(require,module,exports){
let mix = angular.module('mixApp', ['ngRoute']);
//
// // Controllers
require('./controllers/logincontroller')(mix);
// require('./js/controllers/questioncontroller')((mix;
//
// // Services
require('./services/loginservice')(mix);


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
            // controller: '',
            templateUrl: 'templates/create.html',
        })
        .otherwise({
            templateUrl: 'templates/shit.html',
        });
}]);

},{"./controllers/logincontroller":1,"./services/loginservice":3}],3:[function(require,module,exports){
module.exports = function(mix) {
    mix.factory('LoginService', function() {
      let userArr = [];
      console.log("test login service");

      return {
        // addToPlayerArr: function(player) {
        //   playerArr.push(player);
        // },
        // // getPlayerArr: function() {
        // //   return playerArr;
        // // },
        // // getPassword: function(){
        // //   return loginPassword;
        // // }

      };
    });

  };

},{}]},{},[2])