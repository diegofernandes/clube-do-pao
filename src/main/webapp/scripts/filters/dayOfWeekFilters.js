
angular.module('dayOfWeekFilters', []).filter('dayOfWeek', function() {
  return function(input) {
    return input ? workDayMap[input]: null;
  };
});
