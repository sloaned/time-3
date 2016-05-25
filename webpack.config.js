var path = require('path');

module.exports = {
	context: path.join(__dirname, 'src/main/javascript'),
    entry: './timeclockApp.js',
    output: {
        path: path.join(__dirname, 'src/main/webapp/js'),
        filename: 'src.js'
    }
};