var app = angular.module('app', []);
app.controller('ListController', function ($scope, $http) {
    const apiUrl = 'http://localhost/api';
    $scope.page = null;
    $scope.delId = -1;
    $scope.editId = -1;
    $scope.editName = '';
    $scope.getAll = function() {
        $http({
            method: 'GET',
            url: apiUrl + '/'
        })
        .then(function (response) {
            console.log(response.data);
            $scope.page = response.data;
        });
    };
    $scope.getById = function(id) {
        $http({
            method: 'GET',
            url: apiUrl + '/' + id
        })
        .then(function (response) {
            console.log(response.data);
            $scope.editItem = response.data;
        });
    };
    $scope.deleteById = function(id) {
        $http({
            method: 'GET',
            url: apiUrl + '/delete/' + id
        })
        .then(function (response) {
//            return response.data;
        });
    };
    $scope.saveItem = function() {
        $http({
            method: 'POST',
            url: apiUrl + '/',
            data: $scope.editItem
        });
    };
    $scope.doDelete = function(id) {
        if (($scope.delId == -1) && ($scope.editId == -1)) {
            $scope.delId = id;
        }
    };
    $scope.doDeleteOk = function() {
        $scope.deleteById($scope.delId);
        $scope.delId = -1;
        $scope.getAll()
    };
    $scope.doDeleteCancel = function() {
        $scope.delId = -1;
    };
    $scope.doEdit = function(id) {
        if (($scope.editId == -1) && ($scope.delId == -1)) {
            $scope.editId = id;
            $scope.getById($scope.editId);
        }
    };
    $scope.doEditOk = function() {
        $scope.saveItem();
        $scope.editId = -1;
        $scope.getAll();
        window.location.reload();
    };
    $scope.doEditCancel = function() {
        $scope.editId = -1;
    };
    $scope.getAll();
});