(function() {
'use strict';
angular.module('momApp').factory('Login',function($http, $resource, $location) {
    	
    	return {
    		/**
    		 * Service function that logs in the user with the specified username and password.
    		 * To handle the returned promise we use a successHandler/errorHandler approach because we want to have
    		 * access to the additional information received when the failure handler is invoked (status, etc.).
    		 */
    		login: function(username, password, successHandler, errorHandler) {

    				// Prepare the headers
    				var headers = {
    					'Content-Type': 'application/x-www-form-urlencoded'
    				};

    				// Post the credentials for logging in
    				$http.post('http://localhost:8080/mom/j_spring_security_check', 'j_username=' + username + '&j_password=' + password, {
    					headers: headers
    				})
    					.success(successHandler)
    					.error(errorHandler);
    		},

    		logout: function(successHandler, errorHandler) {

    				// Prepare the headers
    				var headers = {
    					'Content-Type': 'application/x-www-form-urlencoded'
    				};

    				// Post the credentials for logging out
    				$http.post('http://localhost:8080/mom/j_spring_security_logout', '', {
    					headers: headers
    				})
    					.success(successHandler)
    					.error(errorHandler);
    		}
    	};
    	
    });
})();