	(function(){
	'use strict';
	angular.module('momApp').controller('LoginController', function ($cookies, $http, $location, $q, $resource, $scope, Login, $window, $rootScope, Common, toastr) {
		
		$scope.alerts = [];
		
		$scope.alertLogout = false;
		$scope.alertFailure = false;
		$scope.initial = true;
		
		$rootScope.authenticated = false;
		
		var result = Common.getQueryString(window.location.search.substring(1));
		
		if(result == 'logout'){
			$scope.alertLogout = true;
			$scope.alertFailure = false;
			$scope.initial = false;
		}
		
		$scope.credentials = {
			username: '',
			password: ''
		};
		
		if($scope.alertLogout){
			$scope.alerts.push({msg: "You've successfully logged out", type: 'success'});
			var myEl = angular.element( document.querySelector( '#loginbox' ) );
		    myEl.css('margin-top','10px');
		}
		
		$scope.login = function () {
			Login.login($scope.credentials.username, $scope.credentials.password, function (data, status, headers, config) {
				// Success handler
				if(data.status == "true"){
					$window.location.href = '/mom/index.html';
				}else if(data.status == "false"){
					$scope.alerts = [{msg: "Invalid credentials. Please try again", type: 'danger'}];
					var myEl = angular.element( document.querySelector( '#loginbox' ) );
				    myEl.css('margin-top','10px');
				}
				console.info('The user has been successfully logged in!');
			}, function(data, status, headers, config) {
				// Failure handler
				console.error('Something went wrong while trying to login!');
			});
		};
		
		$scope.closeAlert = function(index) {
		    $scope.alerts.splice(index, 1);
		    var myEl = angular.element( document.querySelector( '#loginbox' ) );
		    myEl.css('margin-top','70px');
		};

		});
	
	})();