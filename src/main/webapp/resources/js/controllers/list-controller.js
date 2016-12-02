(function() {
    'use strict';
    
    angular.module('momApp').controller('listMoMController', ['$scope', '$http', '$filter', 'MoMData', function($scope, $http, $filter, MoMData) {
        
    	var blankSearchParams = {
    			meetingName : null,
    			meetingFromDate : null,
    			meetingEndDate : null,
    			projectName:null
    	};
    	
    	$scope.datePickerSettings = {
    		meetingFromDate : false,
    		meetingEndDate : false
    	};
    	
    	$scope.searchParams = blankSearchParams;
    	
        function chunk(arr, size) {
            var newArr = [];
            var sizeOfEachSubArr = Math.floor(arr.length/size);
            var li;
            for (var i = 0; i < size; i++) {
                li = sizeOfEachSubArr*i + sizeOfEachSubArr;
                newArr.push(arr.slice(sizeOfEachSubArr*i, li));
            }
            if(arr.length - sizeOfEachSubArr*size > 0){
                newArr.push(arr.slice(li, arr.length));
            }
            return newArr;
        }
        //MoMData.search({}).success();
        
        
         $scope.clearForm = function() {
        	$scope.searchParams = blankSearchParams;
        	$scope.chunkedData = [];
        };
        
        $scope.searchForm = function(){
        	MoMData.search($scope.searchParams).success(function(data, status, headers, config) {
                //console.log(data);
        		$scope.chunkedData = chunk(data, 2);
            }).error(function(data, status, headers, config) {
                alert('error fetching mom list');
            });
        };
        
        $scope.dpOptions = {};
        $scope.openDatePicker = function(datepickerId) {
            if (datepickerId === 'meetingFromDate') {
            	if($scope.searchParams.meetingEndDate != null)
                $scope.dpOptions = {
                    maxDate: $scope.searchParams.meetingEndDate
                }
            } else if (datepickerId === 'meetingEndDate') {
                $scope.dpOptions = {
                    minDate: $scope.searchParams.meetingFromDate
                }
            }

            $scope.datePickerSettings[datepickerId] = true;
        };
        
    }]);
})(); 