'use strict';

angular.module('clubeDoPaoApp')
.controller('MembroListCtrl',
		function($scope, $http) {
			$http.get('api/membro/all').success(function(data) {
				$scope.membros = data;
			});

})
.controller('MembroEditCtrl', function($scope,$location, $routeParams, $http) {

	$http.get('api/membro/' + $routeParams.membroId).success(function(data) {
		$scope.membro = data;
		$scope.weekDays = workDayMap;
	});

	$scope.save = function(){
		$http.post('api/membro',$scope.membro).then(function(data){
			$location.path('/');
		});
	};
	$scope.destroy = function(){
		$http.delete('api/membro/' + $scope.membro.id).then(function(data){

			$location.path('/');
		});
	};
}).controller('MembroCreateCtrl', function($scope,$location, $routeParams, $http) {

		$scope.membro = {disponibilidade:[]};
		$scope.weekDays = workDayMap;

	$scope.save = function(){
		$http.post('api/membro',$scope.membro).then(function(data){

			$location.path('/');
		});
	};


})
.controller('MembroListEncarregadosCtrl',
		function($scope, $http) {
			$http.get('api/membro/all').success(function(data) {
				$scope.membros = data;
			});

})
.directive("checkboxGroup", function() {
	return {
		transclude : false,
		restrict : "A",
		link : function(scope, elem, attrs) {
			var input = elem.find('input');
			var intDay = parseInt(scope.day);
			if (scope.membro.disponibilidade.indexOf(intDay) !== -1) {
				$(elem[0]).button('toggle', false);
			}

			elem.bind('change', function() {
				var index = scope.membro.disponibilidade.indexOf(intDay);
				if (input[0].checked) {
					if (index === -1)
						scope.membro.disponibilidade.push(intDay);
				}
				else {
					if (index !== -1)
						scope.membro.disponibilidade.splice(index, 1);
				}
				scope.$apply(scope.membro.disponibilidade.sort(function(a, b) {
					return a - b;
				}));
			});
		}
	};
});
