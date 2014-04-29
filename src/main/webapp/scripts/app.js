'use strict';
var workDayMap = {1: 'Seg',2: 'Ter',3: 'Qua',4: 'Qui',5: 'Sex'};

angular
  .module('clubeDoPaoApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'dayOfWeekFilters'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/membros', {
        templateUrl: 'views/membro-list.html',
        controller: 'MembroListCtrl'
      })
      .when('/membros/new',{
        templateUrl: 'views/membroEdit.html',
        controller: 'MembroCreateCtrl'
      }).when('/membros/new',{
        templateUrl: 'views/membro-list-encarregados.html',
        controller: 'MembroListEncarregadosCtrl'
      })
      .when('/membros/edit/:membroId',{
    	  templateUrl: 'views/membroEdit.html',
    	  controller: 'MembroEditCtrl'
      })
      .otherwise({
        redirectTo: '/membros'
      });
  });
