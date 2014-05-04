'use strict';
var workDayMap = {2: 'Seg',3: 'Ter',4: 'Qua',5: 'Qui',6: 'Sex'};

angular
  .module('clubeDoPaoApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'dayOfWeekFilters',
    'encarregadoFilters'
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
      }).when('/membros/encarregados',{
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
