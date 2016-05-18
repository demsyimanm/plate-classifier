var lwip = require('lwip');
var async = require('async');
var files = [
	{'dir':'0', 'num':10}, 	{'dir':'1', 'num':13}, 	{'dir':'2', 'num':11}, {'dir':'3', 'num':11},	{'dir':'4', 'num':10},
	{'dir':'5', 'num':9},	{'dir':'6', 'num':5}, {'dir':'7', 'num':10},
	{'dir':'8', 'num':11}, {'dir':'9', 'num':9}, {'dir':'A', 'num':8}, {'dir':'B', 'num':11}, {'dir':'C', 'num':2},
	{'dir':'D', 'num':3},	{'dir':'E', 'num':7}, {'dir':'F', 'num':4}, {'dir':'G', 'num':4}, {'dir':'H', 'num':7},
	{'dir':'I', 'num':6}, {'dir':'J', 'num':4}, {'dir':'K', 'num':5}, {'dir':'L', 'num':9}, {'dir':'M', 'num':1},
	{'dir':'N', 'num':2}, {'dir':'O', 'num':1}, {'dir':'P', 'num':9}, {'dir':'Q', 'num':0}, {'dir':'R', 'num':5},
	{'dir':'S', 'num':6}, {'dir':'T', 'num':7}, {'dir':'U', 'num':5},	{'dir':'V', 'num':6}, {'dir':'W', 'num':3},
	{'dir':'X', 'num':5}, {'dir':'Y', 'num':8}, {'dir':'Z', 'num':4}
];

var datas = [];

function readDatas(cb) {
	var fileNames = [];

	for (var i = 0; i<files.length; i++) {
		for (var j = 1; j<=files[i].num; j++) {
			fileNames.push("dataset/"+files[i].dir+"/"+j+".png");
		}
	}

	async.each(fileNames, function (fileName, callback) {
		lwip.open(fileName, function(err, image) {
			if (!err) {
				image.resize(50,50, function (err, image) {
					var obj = {};
					var pixel = [];
					for (var j = 0; j<50; j++) {
						for (var k = 0; k<50; k++) {
							if (image.getPixel(j,k).r > 0) pixel[j*50 + k ] = 1;
							else pixel[j*50 + k ] = 0;
						}
					}
					obj.pixel = pixel;
					obj.class = fileName[8];
					datas.push(obj);
					callback();
				});
			} else {
				console.log("error open an image");
				callback();
			}
		});
	}, function (err) {
		if (err) {
			console.log(err);
		} else {
			console.log(datas.length);
			// open testdata

			// do training

		}
	});
}

readDatas();

