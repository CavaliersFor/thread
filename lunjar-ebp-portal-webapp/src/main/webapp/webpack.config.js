var path = require('path');
var fs = require('fs');
var webpack = require('webpack');
var autoprefixer = require('autoprefixer');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

function getEntries(viewPath) {
	var dirs = fs.readdirSync(viewPath);
	var entryMap = {};
	
	dirs.forEach(function(dir) {

		// 忽略 viewPath 目录下的直接子文件，只算直接子文件夹
		if(dir.indexOf('.') === -1) {
			entryMap[dir] = [
				path.resolve(viewPath, dir + '/index')
	    ];
		}
	});

	return entryMap;
}

module.exports = {
	entry: getEntries('./assets/pages'),
	output: {
		path: path.join(__dirname, 'dist/assets'),
		filename: '[name].[chunkhash].js'
	},
	module: {
		loaders: [
			{
				test: /\.jsx?$/,
				exclude: /node_modules/,
				loader: 'babel'
			}, {
				test: /\.scss$/,
				loader: 'style!css!postcss!sass',
				loader: ExtractTextPlugin.extract('style', 'css!postcss!sass')
			},
			{
        test: /\.(eot|ttf|woff|svg|png|jpg|gif)(\?.*)?(#.*)?$/,
        loader: 'url?name=fonts/[name].[hash].[ext]'
      },
      {
      	test: /\.tpl$/,
      	loader: 'html?-attrs'
      }
		]
	},
	resolve: {
		extensions: ['', '.js', '.scss', '.css', '.tpl'],
		alias: {
			'component': '../../components'
		}
	},
	plugins: [
		new webpack.optimize.OccurenceOrderPlugin(),
		new webpack.optimize.CommonsChunkPlugin({
			name: 'common',
			// filename: 'common.js',
			minChunks: 2
		}),
		new ExtractTextPlugin('[name].[chunkhash].css'),
	],
  postcss: [autoprefixer],
  devtool: 'source-map'
};