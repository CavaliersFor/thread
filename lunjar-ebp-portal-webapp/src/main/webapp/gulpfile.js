/**
 * @haw
 */

var path = require('path');
var fs = require('fs');
var gulp = require('gulp');

// 加载第三方任务插件
var browserSync = require('browser-sync');
var del = require('del');
var inject = require('gulp-inject');

// 
var options = {
	basePath: './',
	devPath: 'assets',
	buildPath: './dist'
}

function assetsTransform(filepath, file, index, length, targetFile) {
	filepath = filepath.substring(1, filepath.length);

	if (filepath.indexOf('.css') !== -1) {
		return '<link rel="stylesheet" href="#getWebresourcesUrl()' + filepath + '">';
	} else if (filepath.indexOf('.js') !== -1) {
		return '<script src="#getWebresourcesUrl()' + filepath + '"></script>';
	}
}

function getHtmlPlugins(viewPath) {
	var pages = fs.readdirSync(viewPath);
	var destDir = viewPath;

	// header.vm
	gulp.src(path.join(viewPath, 'commons/header.vm'))
   	.pipe(inject(gulp.src(options.buildPath + '/assets/common*.css'), {
	  	ignorePath: 'dist',
	  	transform: assetsTransform
	  }))
    .pipe(gulp.dest(path.join(destDir, 'commons')));

  // other pages
	pages.forEach(function(page) {
		var htmlPath = path.join(viewPath, page);
		var name = page.split('.')[0];
		console.log(htmlPath, name);

		// 忽略 viewPath 目录下的直接子文件夹，只算直接子文件
		if(page.indexOf('.') !== -1) {

	 		// other page
	 		gulp.src(htmlPath)
	   	.pipe(inject(gulp.src([options.buildPath + '/assets/' + name + '*.css',
	   		options.buildPath + '/assets/common*.js',
	   		options.buildPath + '/assets/' + name + '*.js']), {
		  	ignorePath: 'dist',
		  	transform: assetsTransform
		  }))
	    .pipe(gulp.dest(destDir));
		}
	});
}

// 构建web开发服务器
gulp.task('browser', function() {
	browserSync({
		proxy: 'http://localhost:8080/',
		// port: 80,
		port: 2122,
		reloadDelay: 200,
		files: [
			'**/*.html',
			'**/*.jsp',
			"**/*.vm"
		],
		open: false
	});
});

// 监控文件的变化，并执行相应的任务
gulp.task('watch', function() {
	var watcher = gulp.watch([
		'**/*.{jsp,html,vm}'
	]);
	watcher.on('change', function(event) {
		// var root = '/Users/haw/taobao/TAE_Cloud_SDK/tae-dev/webapps/ROOT';
    // var root = '/usr/local/opt/tomcat/libexec/webapps/ROOT';
    var root = 'E:\\DevTools\\apache-tomcat-8.5.4\\webapps\\ROOT';
	// var path = event.path.split('src/main/webapp')[1].replace(/\\/g, '/');
	var path = event.path.split('src\\main\\webapp')[1].replace(/\\/g, '/');
	if(event.type === 'deleted') {
		del([root + path], {
			force: true
		});
	} else {
		path = path.substring(0, path.lastIndexOf('/'));
		gulp.src(event.path)
			.pipe(gulp.dest(root + path));
	}
	});
});


gulp.task('inject', function() {
	getHtmlPlugins(path.join(options.buildPath, './WEB-INF/views/pages'));
});

// 开发过程执行的任务
gulp.task('dev', ['watch', 'browser']);
