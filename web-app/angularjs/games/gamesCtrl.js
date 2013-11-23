(function () {
    'use strict';
    var ngModule = angular.module('NAT_XboxRating.GamesCtrl', ['ngCookies']);

    ngModule.controller('GamesCtrl', [
        '$scope', '$location', '$cookieStore', 'Games',
        function GamesCtrl($scope, $location, $cookieStore, Games) {
            $scope.games = [];
            $scope.newGameTitle = "";
            $scope.isDuplicateOwned = false;
            $scope.isDuplicateNotOwned = false;
            $scope.isWeekend = false;
            $scope.alreadyVoted = false;

            /**
             * Resets the page data
             */
            $scope.reset = function() {
                $scope.games = Games.query();
                $scope.newGameTitle = "";
                $scope.search = "";
                $scope.selectedTab = 1;
            };

            /**
             * Checks whether or not the new game title that was entered already exists
             */
            $scope.isDuplicateTitle = function() {
                var duplicateTitle = $scope.games.filter(function(game){
                    return game.title.toLowerCase() === $scope.newGameTitle.toLowerCase();
                });
                if(duplicateTitle.length > 0) {
                    if(duplicateTitle[0].owned) {
                        $scope.isDuplicateOwned = true;
                        $scope.isDuplicateNotOwned = false;
                    } else if(!duplicateTitle[0].owned) {
                        $scope.isDuplicateOwned = false;
                        $scope.isDuplicateNotOwned = true;
                    }
                    return true;
                } else {
                    $scope.isDuplicateOwned = false;
                    $scope.isDuplicateNotOwned = false;
                    return false;
                }
            };

            /**
             * Returns the next day at midnight as the next time the user can vote again
             */
            $scope.getNextVotingTime = function() {
                var today = new Date();
                var tomorrow = new Date();
                tomorrow.setDate(today.getDate()+1);
                tomorrow.setHours(0,0,0,0);
                return tomorrow;
            };

            /**
             * Returns true if the user can vote today
             */
            $scope.canVoteToday = function(){
                //Can't vote on weekends
                var today = new Date();
                if(today.getDay() == 0 || today.getDay() == 6) {
                    $scope.isWeekend = true;
                    return false;
                }
                //Can vote if today's date is greater than the canVoteAgainDate in the cookies
                else if($cookieStore.get('canVoteAgainDate') ?
                            new Date($cookieStore.get('canVoteAgainDate')) > today :
                            false){
                    $scope.alreadyVoted = true;
                    return false;
                } else return true;
            };

            /**
             * Functions to interact with the database using GamesService.js
             */
            $scope.addGame = function() {
                if ($scope.canVoteToday()) {
                    Games.save({title: $scope.newGameTitle}, function() {
                        $cookieStore.put('canVoteAgainDate', $scope.getNextVotingTime());
                        $location.path('/');
                        $scope.reset();
                    });
                }
            };
            $scope.addVote = function(id) {
                if ($scope.canVoteToday()) {
                    Games.addVote({id: id}, function() {
                        $cookieStore.put('canVoteAgainDate', $scope.getNextVotingTime());
                        $scope.reset();
                    });
                }
            };
            $scope.setOwned = function(id) {
                Games.setOwned({id: id}, function() {
                    $scope.reset();
                });
            };

            //Reset page data on initial load
            $scope.reset();
        }
    ]);
}());
