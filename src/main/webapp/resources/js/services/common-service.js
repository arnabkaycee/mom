(function() {
'use strict';
angular.module('momApp').factory('Common',function($http, $resource, $location) {
    	
    	return {
    		
    		getQueryString : function(query){
    			 var query = query;
    			 var vars = query.split("&");
    			 var result;
    			  for (var i=0;i<vars.length;i++) {
    			    var pair = vars[i].split("=");
    			        // If first entry with this name
    			    if (typeof query_string[pair[0]] === "undefined") {
    			      result = ""
    			        // If second entry with this name
    			    } else if (typeof query_string[pair[0]] === "string") {
    			      result = decodeURIComponent(pair[1]);
    			      query_string[pair[0]] = arr;
    			        // If third or later entry with this name
    			    } else {
    			      result = decodeURIComponent(pair[1]);
    			    }
    			  }
    			  return result;
    		}
    		
    	};
    	
    });
})();




var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
        // If first entry with this name
    if (typeof query_string[pair[0]] === "undefined") {
      query_string[pair[0]] = decodeURIComponent(pair[1]);
        // If second entry with this name
    } else if (typeof query_string[pair[0]] === "string") {
      var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
      query_string[pair[0]] = arr;
        // If third or later entry with this name
    } else {
      query_string[pair[0]].push(decodeURIComponent(pair[1]));
    }
  } 