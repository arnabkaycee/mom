(function() {
    'use strict';
    var sortingOrder = 'name';

    var allStatus = [{
        displayName: 'Open',
        val: 0
    }, {
        displayName: 'Closed',
        val: 1
    }, {
        displayName: 'Deferred',
        val: 2
    }];

    var errMsg = null;
    /*var allEmails = ["sachin@gmail.com", "arnab@gmail.com", "sachin@gmail.com", "arnabc@gmail.com", "gmail@arnab.com"];*/



    angular.module('momApp').controller('editActionItems', ['$scope', '$http', '$filter', 'MoMData', function($scope, $http, $filter, MoMData) {
        //definition of default variables
        //$scope.format = 'M/d/yy h:mm:ss a';
        /*$scope.errMsg = errMsg;*/
        $scope.allStatus = allStatus;
        /*$scope.data = $routeScope.data;*/
        $scope.validEmail = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

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

        /*$scope.availableColors = ['abc@gmail.com', 'pqr@gmail.com'];*/

        $scope.validateEmail = function(emailArr, i) {
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


        /********************************************* Data table [Start] ***************************************/
        $scope.sortingOrder = sortingOrder;
        $scope.pageSizes = [5, 10, 25, 50];
        $scope.reverse = false;
        $scope.filteredItems = [];
        $scope.groupedItems = [];
        $scope.itemsPerPage = 3;
        $scope.pagedItems = [];
        $scope.currentPage = 0;
        $scope.items = $scope.data;

        var searchMatch = function(haystack, needle) {
            if (!needle) {
                return true;
            }
            return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
        };

        // init the filtered items
        /*$scope.search = function() {
            $scope.filteredItems = $filter('filter')($scope.items, function(item) {
                for (var attr in item) {
                    if (searchMatch(item[attr], $scope.query))
                        return true;
                }
                return false;
            });
            // take care of the sorting order
            if ($scope.sortingOrder !== '') {
                $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
            }
            $scope.currentPage = 0;
            // now group by pages
            $scope.groupToPages();
        };*/

        // show items per page
        $scope.perPage = function() {
            $scope.groupToPages();
        };

        // calculate page in place
        /*$scope.groupToPages = function() {
            $scope.pagedItems = [];

            for (var i = 0; i < $scope.items.length; i++) {
                if (i % $scope.itemsPerPage === 0) {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.filteredItems[i]];
                } else {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
                }
            }
        };*/

        $scope.groupToPages = function() {
            $scope.pagedItems = [];

            for (var i = 0; i < $scope.data.actionItems.length; i++) {
                if (i % $scope.itemsPerPage === 0) {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.data.actionItems[i]];
                } else {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.data.actionItems[i]);
                }
                setFormSettings($scope.data.actionItems[i]);
            }
        };


        var setFormSettings = function(item) {
            item['targetDateDPSts'] = false;
            item['rvTargetDateDPSts'] = false;
            item['actualEndDateDPSts'] = false;
        };

        $scope.deleteItem = function(idx) {
            var itemToDelete = $scope.pagedItems[$scope.currentPage][idx];
            var idxInItems = $scope.data.actionItems.indexOf(itemToDelete);
            $scope.data.actionItems.splice(idxInItems, 1);
            //$scope.search();
            $scope.groupToPages();
            return false;
        };

        $scope.range = function(start, end) {
            var ret = [];
            if (!end) {
                end = start;
                start = 0;
            }
            for (var i = start; i < end; i++) {
                ret.push(i);
            }
            return ret;
        };

        $scope.prevPage = function() {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.nextPage = function() {
            if ($scope.currentPage < $scope.pagedItems.length - 1) {
                $scope.currentPage++;
            }
        };

        $scope.setPage = function() {
            $scope.currentPage = this.n;
        };


        // change sorting order
        $scope.sort_by = function(newSortingOrder) {
            if ($scope.sortingOrder == newSortingOrder)
                $scope.reverse = !$scope.reverse;

            $scope.sortingOrder = newSortingOrder;
        };

        /********************************************* Data table [Start] ***************************************/

        $scope.removeRow = function(index) {
            var currPage = $scope.currentPage;
            var indexToRemove = $scope.currentPage * $scope.itemsPerPage + (index);
            $scope.deleteItem(indexToRemove);
            if ($scope.data.actionItems.length == 0) {
                addNewActionItem();
                $scope.groupToPages();
                $scope.currentPage = 0;
            } else {
                if ($scope.pagedItems.length - 1 < currPage)
                    $scope.currentPage = currPage - 1;
                else {
                    $scope.currentPage = currPage;
                }
            }
        };


        $scope.addActionItemRow = function() {
            addNewActionItem();
            //$scope.search();
            $scope.groupToPages();
            $scope.currentPage = (Math.ceil($scope.data.actionItems.length / $scope.itemsPerPage) - 1);
        };

        var addNewActionItem = function() {
            $scope.data.actionItems.push({
                "actionItem": "",
                "responsibility": [],
                "targetDate": moment().toDate(),
                "revisedTargetDate": null,
                "actualEndDate": null,
                "status": {
                    val: 0
                },
                "remarks": ""
            });
        };

        $scope.dpOptions = {};
        $scope.openActionItemDatePicker = function(actionItem, datepickerId) {
            if (datepickerId === 'targetDateDPSts') {
                $scope.dpOptions = {
                    minDate: $scope.data.meetingDate
                }
            } else if (datepickerId === 'rvTargetDateDPSts') {
                $scope.dpOptions = {
                    minDate: actionItem.targetDate
                }
            } else if (datepickerId === 'actualEndDateDPSts') {
                $scope.dpOptions = {
                    minDate: (actionItem.revisedTargetDate ? actionItem.revisedTargetDate : $scope.meetingDate)
                }
            }

            actionItem[datepickerId] = true;

            /*$scope.items[i]['targetDateDPSts'] = false;
            $scope.items[i]['rvTargetDateDPSts'] = false;
            $scope.items[i]['actualEndDateDPSts'] = false;*/
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
        
        $scope.$watch('data', function(){
        	$scope.groupToPages();
        });
        
        
    }]);
})();