(function () {
    'use strict';
    var ngmodule = angular.module('NAT_XboxRating.gamesService', ['ngResource']);

    ngmodule.factory('Games', function ($resource) {
        return $resource('/NAT_XboxRating/game/:task', {}, {
            query: {method: 'GET', params:{task:'query'}, isArray: true},
            save: {method: 'POST', params:{task:'addGame'}},
            addVote: {method: 'POST', params:{task:'addVote'}},
            setOwned: {method: 'PUT', params:{task:'setOwned'}}
        });
    });

}());