(function (angular) {
    'use strict';

    var ngModule = angular.module('NAT_XboxRating', ['NAT_XboxRating.GamesCtrl','NAT_XboxRating.gamesService']),
        cacheBustSuffix = new Date().getTime();

    // configure routing
    ngModule.config(['$routeProvider', function ($routeProvider) {
        // default route:
        $routeProvider.when('/', {templateUrl: 'angularjs/games/games.html?cache-bust=' + cacheBustSuffix, controller: 'GamesCtrl'});
        $routeProvider.otherwise({redirectTo: '/'});
    }]);

}(window.angular));
