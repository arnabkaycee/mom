angular.module('momApp').factory('MoMData', function MoMFactory($http, $q) {
     var self = this;
     this.data = {
        "id": null,
        "meetingName": "",
        "project": [],
        "location": [],
        "meetingDate": moment().toDate(),
        "startTime": null,
        "endTime": null,
        "participants": [],
        "recipients": [],
        "discussionPoints": [{
            "id" : null,
            "name": ""
        }],
        "decisionPoints": [{
            "id" : null,
            "name": ""
        }],
        "actionItems": [{
            "actionItem": "",
            "responsibility": [],
            "targetDate": moment().toDate(),
            "revisedTargetDate": null,
            "actualEndDate": null,
            "status": {
                val: 0
            },
            "remarks": ""
        }]
    };
    
	
    return {
        add: function(data, successCallback, errorCallback) {
            return $http.post('/mom/addmom', data);
        },
        get: function(momId) {
            return $http({
                url: '/mom/getmom', 
                method: "POST",
                params: {'momId': momId}
             });
        },
        search: function(params) {
            return $http.post('/mom/getmoms',params);
        },
        getEmail : function(emailPattern){
            return $http({
                url: '/mom/email', 
                method: "POST",
                params: {'email': emailPattern}
             });
        },
        getLocation : function(location){
            return $http({
                url: '/mom/location', 
                method: "POST",
                params: {'location': location}
             });
        },getProject : function(project){
            return $http({
                url: '/mom/project', 
                method: "POST",
                params: {'project': project}
             });
        },
        getData : function(){
            return angular.copy(self.data);
        },
        setData : function(param){
        	self.data = param;
        },
        
    };
});