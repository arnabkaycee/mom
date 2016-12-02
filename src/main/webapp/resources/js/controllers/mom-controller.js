(function() {
    angular.module('momApp').controller('MomController', ['$scope', 'MoMData', '$routeParams', 'toastr', function($scope, MoMData, $routeParams, toastr) {
        //definition of default variables
        //$scope.format = 'M/d/yy h:mm:ss a';
    	
    	var id = $routeParams.id;
    	$scope.formSubmitted = false;
    	$scope.editMode = false;
		$scope.data = MoMData.getData();
		/*$routeScope.data = MoMData.getData();*/
    	if(id){
    		//in edit mode
    		$scope.editMode = true;
    		MoMData.get(id)
        	.success(function(data, status, headers, config){
        		//$scope.data = data;
        		processDateInJson(data);
        		$scope.data = data;
        		/*$routeScope.data = data;*/
        	})
        	.error(function(data, status, headers, config){
        		alert('error fetching mom');
        	});
    	}

    	/* Form Validation [Start] */
    	var isValidActionItemForm = function(){
        	var ai = $scope.data.actionItems;
        	var flag = true;
            if(!$scope.editMode){
                for(var idx in ai){
                    if(ai[idx].actionItem && (!ai[idx].responsibility.length || !ai[idx].targetDate)){
                        flag = false;
                        break;
                    }
                }
            }else{
                for(var idx in ai){
                    if(ai[idx].actionItem && (!ai[idx].responsibility.length || !ai[idx].targetDate)){
                        flag = false;
                        break;
                    }else if(ai[idx].status === 2 && !ai[idx].actualEndDate){
                        flag = false;
                        break;
                    }
                }
            }
            return flag;
        };
        
        $scope.isValidForm = function(){
            var actionItemFlag = isValidActionItemForm();
            return ($scope.basicInformationForm.$valid && $scope.discussionPoints.$valid) && actionItemFlag;
        };
        
    	/* Form Validation [End] */
    	
    	
        
        /* To control tab handling [Start] */
    	var initFlag = [true, false, false];
    	
    	var addVisitedTab = function(tabId){
    		if(!checkVisitedTab(tabId)){
    			//$scope.visitedTabs.push(tabId);
    			initFlag[tabId-1] = true;
    		}
    	};
    	var checkVisitedTab = function(tabId){
    		/*if(jQuery.inArray(tabId,$scope.visitedTabs)>=0){
    			return true;
    		}
    		return false;*/
    		return initFlag[tabId-1];
    	};
    	
    	
    	
        $scope.selectedTab = 1;
        $scope.visitedTabs = [$scope.selectedTab];
        
        $scope.selectTab = function(selectedTab) {
            $scope.selectedTab = selectedTab;
            addVisitedTab(selectedTab);
            /*if(initFlag[selectedTab-1]){
            	initFlag[selectedTab-1] = false;
            }*/
        };

        $scope.isSelected = function(tab) {
            return $scope.selectedTab === tab;
        };
        
        /*$scope.$watch('selectedTab', function(oldValue, newValue){
        	
        });*/
        
        $scope.hasTabError = function(tabId){
        	//if tabId = 1, i.e BI Tab
        	if(checkVisitedTab(tabId) && !$scope.isSelected(tabId)){
        		switch(tabId){
        			case 1:
        				return $scope.basicInformationForm.$invalid;
        			case 2:
        				return $scope.discussionPoints.$invalid;
        			case 3:
        				return isValidActionItemForm();
        		}
        	}
        };


        /** Calendar Config - [Start] */
        /** Start and End Time Configuration [Start]**/
        
        var now = moment().utc();
        var nowTime = moment(0).utc().minutes(now.minutes()).hours(now.hours()).toDate();
        
        $scope.$watch('data.meetingDate', function(oldValue, newValue){
        	var isMeetingDateToday = moment(moment()).isSame(moment($scope.data.meetingDate), 'day');
        	$scope.getStartMaxTime = isMeetingDateToday?nowTime:$scope.data.endTime;
        });
        /** Start and End Time Configuration [End]**/
        
        $scope.calendarOpenFlags = {
            "meetingDate": false
        };
        
        $scope.meetingDateOptions = {
            maxDate: moment().toDate(),
            minDate: moment().subtract(10, 'd').toDate()
        };
        $scope.openCalendar = function(e, flag) {
            $scope.calendarOpenFlags[flag] = true;
        }

        /** Calendar Config - [End] */


        /** Decision / Discussion Point Functions [Start] **/
        $scope.addNewDIPt = function() {
            $scope.data.discussionPoints.push({
                "name": ""
            });
        };
        $scope.addNewDSPt = function() {
            $scope.data.decisionPoints.push({
                "name": ""
            });
        };
        $scope.removeDIPt = function(index) {
            if ($scope.data.discussionPoints.length > 1) {
                $scope.data.discussionPoints.splice(index, 1);
            } else {
                $scope.data.discussionPoints[0].point = "";
            }
        };
        $scope.removeDSPt = function(index) {
            if ($scope.data.decisionPoints.length > 1) {
                $scope.data.decisionPoints.splice(index, 1);
            } else {
                $scope.data.decisionPoints[0].point = "";
            }
        };
        /** Decision / Discussion Point Functions [End] **/


        $scope.validEmail = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        $scope.availableColors = ['abc@gmail.com', 'pqr@gmail.com'];
        $scope.validateEmail = function(emailArr) {
            var idx = 0;
            var c, flag;
            for (c in emailArr) {
                if (!$scope.validEmail.test(emailArr[c])) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                $scope.data.actionItems[i]['responsibility'].splice(c, 1);
                /*$scope.errMsg = 'Invalid Email';*/
            }
        };
        $scope.submitForm = function() {
        	$scope.formSubmitted = true;
            MoMData.add($scope.data)
            .success(function(data, status, headers, config){
            	toastr.success('MOM created successfully.', 'Success');
            	$scope.clearForm();
            })
            .error(function(data, status, headers, config){
            	toastr.error('Error in creating MOM. Please try again', 'Error');
            });
            
        };

        $scope.copyEmails = function() {
            var index;
            for (index in $scope.data.participants) {
                if(jQuery.inArray($scope.data.participants[index], $scope.data.recipients) == -1){
                    $scope.data.recipients.push($scope.data.participants[index]);
                }
            }
        };
        $scope.allEmails = [];
        $scope.refreshEmail = function(email) {
            if (email && email.length > 3) {
                return MoMData.getEmail(email).success(function(data, status, headers, config) {
                    $scope.allEmails = data;
                }).error(function(data, status, headers, config) {
                    alert('error fetching email data');
                });
            }
        };

        $scope.allLocations = [];
        $scope.refreshLocations = function(location) {
            if (location && location.length > 3) {
                return MoMData.getLocation(location).success(function(data, status, headers, config) {
                    $scope.allLocations = data;
                }).error(function(data, status, headers, config) {
                    alert('error fetching location data');
                });
            }
        };

        $scope.allProjects = [];
        $scope.refreshProjects = function(project) {
            if (project && project.length > 3) {
                return MoMData.getProject(project).success(function(data, status, headers, config) {
                    $scope.allProjects = data;
                }).error(function(data, status, headers, config) {
                    alert('error fetching project data');
                });
            }
        };
        $scope.tagTransform = function(newTag) {
            var item = {
                name: newTag,
                id:null
            };
            return item;
        };
        
        var processDateInJson = function(jsonData){
        	jsonData.meetingDate = new Date(jsonData.meetingDate);
        	jsonData.startTime = new Date(jsonData.startTime);
        	jsonData.endTime = new Date(jsonData.endTime);
        	var items = jsonData.actionItems;
        	var fieldsToConvert = ['targetDate', 'actualEndDate', 'revisedTargetDate'];
        	
        	for (var i = 0; i < jsonData.actionItems.length; i++) {
        		for(var fieldIdx in fieldsToConvert){
        			if(jsonData.actionItems[i][fieldsToConvert[fieldIdx]]){
        				jsonData.actionItems[i][fieldsToConvert[fieldIdx]] = new Date(jsonData.actionItems[i][fieldsToConvert[fieldIdx]]);
        			}
        		}
        	}
        };
        
        $scope.clearForm = function(){
        	$scope.data = MoMData.getData();
        	$scope.formSubmitted = false;
        };
        
        
        $scope.checkEmptyMS = function(value){
        	return jQuery.isEmptyObject(value);
        };
        
    }]);
})();
