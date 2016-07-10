(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);throw new Error("Cannot find module '"+o+"'")}var f=n[o]={exports:{}};t[o][0].call(f.exports,function(e){var n=t[o][1][e];return s(n?n:e)},f,f.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports = function(mix) {
    mix.controller('CreateController', ['$scope', 'CreateService', '$location', '$http', function($scope, CreateService, $location, $http) {
            // $scope.recipeName = "",
            // $scope.time = 0,
            // $scope.instructions = "",
            // $scope.ingredients = "",
            // $scope.skill = "easy",
            // $scope.file = "",

            $scope.createRecipe = function() {
                console.log("clicked create");
                // $http({
                //     url: '/create-recipe',
                //     method: 'post',
                //     data: {
                //         recipeName: $scope.recipeName,
                //         time: $scope.time,
                //         instructions: $scope.instructions,
                //         ingredients: $scope.ingredients,
                //         skill: $scope.skill,
                //         file: $scope.file,
                //
                //     },
                //
                // });

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
module.exports = function(mix) {
    mix.controller('RatingController', ['$scope', 'RatingService', 'LoginService', '$location', '$http', function($scope, RatingService, LoginService, $location, $http) {
        $scope.recipes = RatingService.getRecipes(),
        $scope.user = LoginService.getUser(),

        $scope.rateRecipe = function(recipe, vote) {
            console.log("clicked rating", vote, recipe);
            $http({
                url: '/favs',
                method: 'post',
                data: {
                    id: recipe.id,
                    votes: vote,
                }
            });

        };
    }]);
};

},{}],4:[function(require,module,exports){
let mix = angular.module('mixApp', ['ngRoute']);
//
// // Controllers
require('./controllers/logincontroller')(mix);
require('./controllers/createcontroller')(mix);
require('./controllers/ratingcontroller')(mix);
//
// // Services
require('./services/loginservice')(mix);
require('./services/createservice')(mix);
require('./services/ratingservice')(mix);


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
            controller: 'RatingController',
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

},{"./controllers/createcontroller":1,"./controllers/logincontroller":2,"./controllers/ratingcontroller":3,"./services/createservice":5,"./services/loginservice":6,"./services/ratingservice":7}],5:[function(require,module,exports){
module.exports = function (mix) {
    mix.factory('CreateService', ['$http', function ($http) {
        let newrecipe = [];

        return {
          // $http({
                // url: '/create-recipe',
          //       method: 'post',
                // data: {
                //
                //     recipeName:
                      // time:
                //     instructions:
                //     ingredients:
                //     skill:
                //     file:

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

},{}],6:[function(require,module,exports){
module.exports = function(mix) {
    mix.factory('LoginService', ['$http', function($http) {
        let user = [];

        return {
            getUser: function() {
                $http({
                    url: '/login',
                    method: 'get'
                }).then(function(results) {
                    console.table(results.data);
                    angular.copy(results.data, user)
                });

                return user;
            },
        };
    }]);
};

},{}],7:[function(require,module,exports){
module.exports = function (mix) {
    mix.factory('RatingService', ['$http', function ($http) {
        let recipes = [];

        return {
            getRecipes: function () {
                $http({
                    url: '/recipes',
                    method: 'get'
                }).then(function (results) {
                  console.table(results.data);
                    angular.copy(results.data, recipes)
                });

                return recipes;
            },
        };
    }]);
};

},{}]},{},[4])