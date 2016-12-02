(function(){
angular.module('momApp').factory('userData',function(){

	var data = {
	        alertLogout : false,
	        alertFailure : false,
	        initial : true
	    };

	    return {
	        getAlertLogout: function () {
	            return data.alertLogout;
	        },
	        setAlertLogout: function (alertLogout) {
	            data.alertLogout = alertLogout;
	        },
	        getAlertFailure: function () {
	            return data.alertFailure;
	        },
	        setAlertFailure: function (alertFailure) {
	            data.alertFailure = alertFailure;
	        },
	        getInitial: function () {
	            return data.initial;
	        },
	        setInitial: function (initial) {
	            data.initial = initial;
	        }
	    };

});
})();