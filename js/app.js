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
