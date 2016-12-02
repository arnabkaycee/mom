(function(){
'use strict';
angular.module('momApp').controller('MainController', function ($cookies, $http, $location, $q, $resource, $scope, Login, $window) {

	$scope.logout = function() {
		Login.logout(function (data, status, headers, config) {
			// Success handler
			if(data.status == "true"){
				delete $cookies['JSESSIONID'];
				$window.location.href = "/mom/login.html?q=logout";
			}else if(data.status == "false"){
				//$location.path( "/" );
			}
			console.info('The user has been successfully logged out!');
		}, function(data, status, headers, config) {
			// Failure handler
			console.error('Something went wrong while trying to logout!');
		});
	};
	
});
})();