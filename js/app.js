let mix = angular.module('MixApp', ['ngRoute']);
//
// // Controllers
// require('./js/controllers/logincontroller')(mix);
// require('./js/controllers/questioncontroller')((mix;
//
// // Services
// require('./js/services/questionservice')(mix);
// require('./js/services/loginservice')(mix);


mix.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/login',
        })
        .when('/login', {
            // controller: 'LoginController',
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
