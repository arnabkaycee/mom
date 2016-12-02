
(function() {
    'use strict';
    angular.module('momApp').config(function($routeProvider) {
        
    	 $routeProvider
    	 .when('/add', {
                templateUrl: "resources/templates/mom/addMom.html",
                controller: "MomController",
                controllerAs: "amc"
         })
         
         .when('/', {
                templateUrl: "resources/templates/home/home.html"
          })
          
          .when('/list', {
            templateUrl : "resources/templates/mom/listMom.html",
            controller:"listMoMController"
          })
          
          .when('/edit/:id', {
                templateUrl: "resources/templates/mom/addMom.html",
                controller: "MomController",
                controllerAs: "amc"
         })

        .otherwise({
            redirectTo: '/'
        });
    });
}());