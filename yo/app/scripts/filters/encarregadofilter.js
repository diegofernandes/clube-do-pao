'use strict';

angular.module('encarregadoFilters',[])
  .filter('encarregado', function () {
    return function (input) {
      return input? input: "Sem pão";
    };
  });
