var path = require('path');

module.exports = function(config) {
  config.set({
    basePath: path.join(__dirname, 'src/test/javascript'),
    frameworks: ['jasmine'],
    browsers: ['PhantomJS'],
    autoWatch: false,
    logLevel: config.LOG_INFO,
    singleRun: true,
    client: {
    	captureConsole: true
    },
    files: [
        //Source Files that have been web-packed
        path.join(__dirname, 'src/main/webapp/js/**/*.js'), 
        
        // Angular mock files
        path.join(__dirname, 'node_modules/angular-mocks/angular-mocks.js'),
        
        // Test files, starts from the basePath directory
        '**/*.spec.js'
    ]
  });
};