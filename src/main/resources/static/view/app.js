var app = angular.module('app', ['ngMaterial']);

app.controller('AccountCtrl', ['$scope','AccountService','$mdDialog',
  function ($scope,AccountService,$mdDialog) {

      $scope.getAccount = function () {
          var id = $scope.account.id;
          AccountService.getAccount($scope.account.id)
            .then(function success(response) {
                $scope.account = response.data;
                $scope.account.id = id;
                $scope.message='';
                $scope.errorMessage = '';
            },
            function error (response) {
                $scope.message = '';
                if (response.status === 404){
                    $scope.errorMessage = 'Account not found!';
                }
                else {
                    $scope.errorMessage = "Error getting account!";
                }
            });
      };

      $scope.getAllAccounts = function () {
          AccountService.getAllAccounts()
            .then(function success(response) {
                $scope.accounts = response.data.accounts;
                $scope.message='';
                $scope.errorMessage = '';
            },
            function error (response) {
                $scope.message='';
                $scope.errorMessage = 'Error getting accounts!';
            });
      };

    $scope.showAdvanced = function(ev) {
        $mdDialog.show({
          controller: DialogController,
          templateUrl: 'dialog1.tmpl.html',
          parent: angular.element(document.body),
          targetEvent: ev,
          windowClass: 'large-Modal',
          clickOutsideToClose:true,
          fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
        .then(function(answer) {
          $scope.status = answer;
          $scope.getAllAccounts();
        }, function() {
          $scope.status = 'You cancelled the dialog.';
        });
      };

function DialogController($scope, $mdDialog, AccountService) {
    $scope.addAccount = function () {
              if ($scope.account != null && $scope.account.id) {
                  AccountService.addAccount($scope.account.id)
                    .then (function success(response){
                    $mdDialog.hide('Account added!');
                    },
                    function error(response){
                        $mdDialog.hide('Error adding account!');
                  });
              }
              else {
                  $scope.errorMessage = 'Please enter an id!';
              }
          };

    $scope.hide = function() {
          $mdDialog.hide();
        };

        $scope.cancel = function() {
          $mdDialog.cancel();
        };

        $scope.answer = function(answer) {
          $mdDialog.hide(answer);
        };

  }

}]);

app.service('AccountService', [ '$http', function($http) {

    this.getAccount = function getAccount(accountId) {
        return $http({
            method : 'GET',
            url : 'account/' + accountId
        });
    };

    this.addAccount = function addAccount(accountId) {
        return $http({
            method : 'POST',
            url : 'account',
            data : {
                accountId : accountId
            }
        });
    };

    this.getAllAccounts = function getAllAccounts() {
        return $http({
            method : 'GET',
            url : 'accounts'
        });
    };

} ]);


/*
this.updateAccount = function updateAccount(accountId, name) {
    return $http({
        method : 'PUT',
        url : 'user/' + accountId,
        data : {
            name : name
        }
    });
}

this.deleteAccount = function deleteAccount(id) {
    return $http({
        method : 'DELETE',
        url : 'account/' + id
    })
}
*/

app.config(function($mdThemingProvider) {

    // Configure a dark theme with primary foreground yellow
    $mdThemingProvider.theme('docs-dark', 'default')
      .primaryPalette('yellow')
      .dark();

  });