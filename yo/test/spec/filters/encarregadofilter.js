'use strict';

describe('Filter: encarregadoFilter', function () {

  // load the filter's module
  beforeEach(module('angularTestApp'));

  // initialize a new instance of the filter before each test
  var encarregadoFilter;
  beforeEach(inject(function ($filter) {
    encarregadoFilter = $filter('encarregadoFilter');
  }));

  it('should return the input prefixed with "encarregadoFilter filter:"', function () {
    var text = 'angularjs';
    expect(encarregadoFilter(text)).toBe('encarregadoFilter filter: ' + text);
  });

});
