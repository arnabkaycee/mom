angular.module('momApp', [ 'ngRoute', 'ngResource', 'monospaced.elastic',
		'ngSanitize', 'ui.select', 'ui.bootstrap', 'ngCookies', 'toastr']);

angular.module('momApp').config([ '$httpProvider', 'toastrConfig', function($httpProvider, toastrConfig) {

	$httpProvider.defaults.withCredentials = true;
	// Tough luck: the default cookie-to-header mechanism is not working for cross-origin requests!
	$httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN'; // The name of the cookie sent by the server
	$httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN'; // The default header name picked up by Spring Security
	$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    
	angular.extend(toastrConfig, {
		maxOpened : 3,
		newestOnTop : true,
		positionClass : 'toast-bottom-right',
		target : 'body'
	});
	

} ]);