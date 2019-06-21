$(document).ready(function() {
	$.ajax({
		//抓菜單種類
		type: "GET",
		url: "https://fisystem.herokuapp.com/ShowMenuClass",
		dataType: "json",
		success: function(data) {
			let foodClassnameArray = [];
			for (let i = 0; i < data.length; i++) {
				// food
				let foodClass = data[i];
				// food class name
				let className = foodClass.ClassName;
				foodClassnameArray.push(className);
			}
			// filt same class name
			let foodClassnames = foodClassnameArray.filter(function(element, index, arr) {
				return arr.indexOf(element) === index;
			});

			for (let q = 0; q < foodClassnames.length; q++) {

				let foodClassname = foodClassnames[q];

				// menu panel add class
				$("#list").append(
					'<li class="list-group-item">' +
					'<div class="row">' +
					'<button type="button" class="close" data-toggle="modal" data-target="#exampleModal6" onclick="deleteMenuClass(\'' + foodClassname + '\')" aria-label="Close" data-dismiss="modal">' +
					'<span aria-hidden="true">x</span>' + '</button>' +
					'<div class="col-sm-3">' +
					'<img src="'+data[q].url+'" class="img-responsive">' +
					'</div>' +
					'<div class="col-sm-9">' +
					'<div class="product_text right">' +
					'<h1 class="sub_title">' +
					'<strong style="color:#585A56">' + foodClassname + '</strong>' +
					'<button type="button" style="background-color:#B5C1B4;color:white;" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal3" onclick="addMenu(\'' + foodClassname + '\')">新增餐點</button>' +
					'</h1>' +
					'<div id="' + foodClassname + 'li" class="qty mt-5"style="float:left;width:100%"></div>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</li>'
				);

				// set food of each class

			}
		},
		error: function() {
			console.log("can not GET")
		}
	});
});

$(document).ready(function() {
	$.ajax({
		//抓菜單
		type: "GET",
		url: "https://fisystem.herokuapp.com/ShowAllMenu",
		dataType: "json",
		success: function(data) {

			for (let q = 0; q < data.length; q++) {
				let classname = data[q].ClassName;
				let name = data[q].Name;
				let price = data[q].Price;
				let div = "#" + classname + "li";
				$(div).append(
					'<div class="well well-sm" style="float:left;width:33%">' +
					'<strong style="font-size:16px"><span style="float:left">' + name + '</span><span style="float:right">' + '$' + price +
					'<button type="button" style="margin-left:8px;background-color:#81A3A7;color:white;" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal5" onclick="updateMenu(\'' + name + '\',\''+ classname + '\')">修改</button>' +
					'<button type="button" style="margin-left:8px" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal4" onclick="deleteMenu(\'' + name + '\')">刪除</button></span></strong>' +
					'</div>'
				);
			}
		},
		error: function() {
			console.log("can not GET")
		}
	});
});

$(document).ready(function() {
	$.ajax({
		//抓套餐
		type: "GET",
		url: "https://fisystem.herokuapp.com/ShowSetMenu",
		dataType: "json",
		success: function(data) {

			for (let q = 0; q < data.length; q++) {
				let name = data[q].Name;
				let price = data[q].Price;
				let des = data[q].Description;
				let pic = data[q].url;
				$("#setRow").append(
					'<div class="col-sm-6 col-md-3">' +
					'<div class="thumbnail">' +
					'<img src="' + pic + '" alt="' + name + '">' +
					'<div class="caption">' +
					'<h5><strong>' + name + '</strong><strong style="float:right">$' + price + '</strong></h5>' +
					'<p>' + des + '</p>' +
					'<button type="button" style="margin-left:8px" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal7" onclick="deleteSetMenu(\'' + name + '\')">刪除</button>' +
					'</div>' +
					'</div>' +
					'</div>'
				);
			}
		},
		error: function() {
			console.log("can not GET")
		}
	});
});

$(document).ready(function() {
	//新增餐點
	$('#uploadMenu').click(function(e) {
		let text_data2 = $('#menuName').val();
		let price_data2 = $('#myPrice').val();
		let class_data2 = $("#addMenuClassName").val();
		let order = {
			"ClassName" : class_data2,
			"Name": text_data2,
			"Price" : price_data2
		};
		if(text_data2==""||price_data2==""){
			alert("請輸入名稱或價錢");

		}
		else{
			let jsonData = JSON.stringify(order);

			$.ajax({
				url: "https://fisystem.herokuapp.com/AddMenu",
				data: jsonData, //data只能指定單一物件
				dataType: "text",
				type: "Post",
				success: function() {
					alert("新增成功！");
					window.location.reload()
				},
				error: function() {
					alert("失敗");
				}

			});
		}
	});

	//修改餐點
	$('#UpdateMenu').click(function() {
		let old_name = $('#oldMenu').val();
		let text_data2 = $('#newmenuName').val();
		let price_data2 = $('#newmyPrice').val();
		let class_data2 = $("#newMenuClass").val();
		let order = {
			"ClassName" : class_data2,
			"Name": text_data2,
			"Price" : price_data2
		};
		if(text_data2==""||price_data2==""){
			alert("請輸入名稱或價錢");

		}else{
			let jsonData = JSON.stringify(order);
			let ulr = "https://fisystem.herokuapp.com/UpdateMenu?name=" + old_name;
			console.log(ulr);
			console.log(jsonData);
			$.ajax({
				url: ulr,
				data: jsonData, //data只能指定單一物件
				dataType: "text",
				type: "Post",
				success: function() {
					alert("修改成功！");
					window.location.reload()
				},
				error: function() {
					alert("失敗");
				}

			});}
	});

	//刪除餐點
	$('#deletemenubtn').click(function() {
		let dname = $('#deleteMenu').val();
		let fd = new FormData();
		fd.append("Name", dname);
		console.log(dname);
		$.ajax({
			url: "https://fisystem.herokuapp.com/RemoveMenu",
			//url: "http://localhost:8080/FullImmediately/RemoveMenu",
			cache: false,
			contentType: false,
			processData: false,
			data: fd, //data只能指定單一物件
			type: "Post",
			success: function() {
				alert("刪除成功！");
				window.location.reload()
			},
			error: function() {
				alert("失敗");
			}
		});
	});

	//刪除餐點種類
	$('#DeleteClass').click(function(e) {
		let dclassname = $('#deleteMenuClass').val();
		let fd = new FormData();
		fd.append("ClassName", dclassname);
		console.log(dclassname);
		$.ajax({
			url: "https://fisystem.herokuapp.com/RemoveMenuClass",
			//url: "http://localhost:8080/FullImmediately/RemoveMenuClass",
			cache: false,
			contentType: false,
			processData: false,
			data: fd, //data只能指定單一物件
			type: "Post",
			success: function() {
				alert("刪除成功！");
				window.location.reload()
			},
			error: function() {
				alert("失敗");
			}
		});
	});

	//新增餐點種類
	$('#uploadClass').click(function(e) {
		let name = $('#className').val();
		let url = $("#curlLabel").html();
		let classObj = {
			"ClassName" : name,
			"url" : url
		};
		if(name==""||url==""){
			alert("請輸入名稱或加入檔案");

		}else{
			let jsonData = JSON.stringify(classObj);

			$.ajax({
				url: "https://fisystem.herokuapp.com/AddMenuClass",
				data: jsonData, //data只能指定單一物件
				dataType: "text",
				type: "Post",
				success: function() {
					alert("新增成功！");
					window.location.reload()
				},
				error: function() {
					alert("失敗");
				}
			});}
	});

	//新增套餐
	$('#uploadSet').click(function(e) {
		let name = $('#newSet').val();
		let price = $("#newSetPrice").val();
		let des = $("#setDes").val()
		let url = $("#surlLabel").html();
		let classObj = {
			"Name" : name,
			"Price" : price,
			"Description" : des,
			"url" : url
		};
		if(name==""||price==""||des==""||url==""){
			alert("請輸入完整資訊");

		} else {
			let jsonData = JSON.stringify(classObj);

			$.ajax({
				url: "https://fisystem.herokuapp.com/AddSet",
				data: jsonData, //data只能指定單一物件
				dataType: "text",
				type: "Post",
				success: function() {
					alert("新增成功！");
					window.location.reload()
				},
				error: function() {
					alert("失敗");
				}
			});
		}
	});

	//刪除套餐
	$('#deletesetbtn').click(function(e) {
		let dsetname = $('#deleteSet').val();
		let fd = new FormData();
		fd.append("Name", dsetname);
		console.log(dsetname);
		$.ajax({
			url: "https://fisystem.herokuapp.com/RemoveSet",
			//url: "http://localhost:8080/FullImmediately/RemoveSet",
			cache: false,
			contentType: false,
			processData: false,
			data: fd, //data只能指定單一物件
			type: "Post",
			success: function() {
				alert("刪除成功！");
				window.location.reload()
			},
			error: function() {
				alert("失敗");
			}
		});
	});
});

function addMenu(className) {
	$("#addMenuTitle").html("新增" + className);
	$("#addMenuClassName").val(className);
}

function updateMenu(nnn,ccc) {
	$("#newMenuClass").val(ccc);
	$("#oldMenu").val(nnn);
}

function deleteMenu(nnn) {
	$("#deleteMenu").val(nnn);
}

function deleteMenuClass(ddd) {
	$("#deleteMenuClass").val(ddd);
}

function deleteSetMenu(nnn) {
	$("#deleteSet").val(nnn);
}

function makeImgurUrl(para) {
	const id = 'cd39c2181d056c8'; // 填入 App 的 Client ID
	const token = 'd31ada64e270338e2c9508b655df8306bba3c403'; // 填入 token
	const album = 'aOCHDUx'; // 若要指定傳到某個相簿，就填入相簿的 ID

	let file;
	let notiLabel;
	let urlLabel;

	if (para == "class") {
		file = $('#myFile').prop('files')[0];
		notiLabel = $("#cnoti");
		urlLabel = $("#curlLabel");
	} else {	//pare = set
		file = $('#newSetFile').prop('files')[0];
		notiLabel = $("#snoti");
		urlLabel = $("#surlLabel");
	}

	let settings = {
		async: false,
		crossDomain: true,
		processData: false,
		contentType: false,
		type: 'POST',
		url: 'https://api.imgur.com/3/image',
		headers: {
			Authorization: 'Bearer ' + token
		},
		mimeType: 'multipart/form-data'
	};
	let form = new FormData();
	form.append('image', file);
	form.append('album', album); // 有要指定的相簿就加這行

	settings.data = form;

	$.ajax(settings).done(function(res) {
		notiLabel.html("成功上傳摟～");
		urlLabel.html(JSON.parse(res).data.link);
	});
}
