<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <html>

    <head>
        <% //把account 的值傳過來了
        Object name=session.getAttribute("name");
            String username = "";
                if(name!=null){
                        username = name.toString();
                        }
                        %>
            <title>馬尚飽</title>
            <!-- Required meta tags -->
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

            <!-- Optional JavaScript -->
            <!-- jQuery first, then Popper.js, then Bootstrap JS -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

            <!-- ajax -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

            <script src="js/hostMenu.js" type="text/javascript"></script>
            <style>
                .affix {
                    top: 0;
                    width: 100%;
                    -webkit-transition: all .5s ease-in-out;
                    transition: all .5s ease-in-out;
                }
                
                .affix-top {
                    position: static;
                    top: -35px;
                }
                
                .affix+.container-fluid {
                    padding-top: 70px;
                }
                
                nav {
                    background-color: #C2D3DA;
                }
                
                body {
                    background-color: #F1F3F2;
                    font-family: "微軟正黑體";
                    margin-top: 100px;
                    padding-bottom: 70px;
                    padding-right: 0px !important;
                }
                
                *.modal-open {
                    overflow-y: scroll;
                    padding-right: 0 !important;
                }
				#title{
					width:500px;
					background-color:#81A3A7;
					font-size:48px;
					color:white;
					font-family:Comic Sans MS;
				}
				@media (max-width:480px){
					.container-fluid{
						padding-top: 100px;
					}
					#title{
						font-size:36px;
						width:100%;
					}
				}
            </style>
             <script>
                                        var var1 = "";
                                        var1 = "<%=username%>";

                                        if (var1 == "") {
                                            console.log("!2345fdws");
                                            $(document).ready(function() {
                                                window.location.href = "https://fisystem.herokuapp.com/HostLogin.jsp";
                                            });
                                        }
                                    </script>
    </head>

    <body>
        <nav class="navbar navbar-expand-sm navbar-fixed-top">
            <ul class="navbar-nav mr-auto">
                <img src="picture/title.jpg" alt="馬尚飽" style="width:68px;float:left;">
                    <p id="title">Full Immediately</p>
                
            </ul>
            <form style="text-align:right">
                <a href="HostMenu.jsp" style="color:#666666"><span class="glyphicon glyphicon-cutlery" /><strong>編輯菜單 </strong></a>
                <a href="ListOrder.jsp" style="color:#666666"><span class="glyphicon glyphicon-shopping-cart" /><strong>歷史訂單 </strong></a>
                <a href="ManageOrder.jsp" style="color:#666666"><span class="glyphicon glyphicon-list" /><strong>管理訂單 </strong></a>
                <a href="HostLogin.jsp" style="color:#666666"><span class="glyphicon glyphicon-off" /><strong>登出</strong></a>
            </form>
        </nav>
        <div class="container-fluid">
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" style="background-color:#585A56;color:white">Menu</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" id="one-tab" data-toggle="tab" href="#alacartePanel" role="tab" aria-controls="alacartePanel" style="color:#272424"><strong>單點</strong></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="two-tab" data-toggle="tab" href="#setMenuPanel" role="tab" aria-controls="setMenuPanel" style="color:#272424"><strong>套餐</strong></a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active in" id="alacartePanel" role="tabpanel" aria-labelledby="one-tab">
                    <button type="button" style="margin-top:8px;background-color:#B5C1B4;color:white;" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal">
                新增餐點種類
            </button>
                    <ul class="list-group" id="list">
                    </ul>
                </div>
                <div class="tab-pane fade" id="setMenuPanel" role="tabpanel" aria-labelledby="two-tab">
                    <button type="button" style="margin-top:8px;background-color:#B5C1B4;color:white;" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal2">
                新增套餐
            </button>
                    <div class="row" id="setRow"></div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">新增餐點種類</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <label class="col-form-label">請輸入種類名稱：</label>
                            <br>
                            <input type="text" id="className" />
                            <input type="file" id="myFile" />
                            <input type="button" value="提交" onclick="makeImgurUrl('class')" />
                            <br>
                            <label id="cnoti" class="col-form-label"> </label>
                            <br>
                            <label id="curlLabel"> </label>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn bg-primary" id="uploadClass">新增</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">新增套餐</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <label class="col-form-label">請輸入要新增的套餐名稱：</label>
                            <br>
                            <input type="text" id="newSet" />
                            <br>
                            <label class="col-form-label">請輸入要新增的套餐價錢：</label>
                            <br>
                            <input type="text" id="newSetPrice" onkeyup="value=value.replace(/[^\d]/g,'') " />
                            <br>
                            <label class="col-form-label">請輸入要新增的餐點介紹：</label>
                            <br>
                            <input type="text" id="setDes" />
                            <br>
                            <input type="file" id="newSetFile" />
                            <input type="button" value="提交" onclick="makeImgurUrl('set')" />
                            <br>
                            <label id="snoti" class="col-form-label"> </label>
                            <br>
                            <label id="surlLabel"> </label>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn bg-primary" id="uploadSet">新增</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- -->
            <div class="modal fade" id="exampleModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="addMenuTitle">新增餐點</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <!-- 這個input看不到 拿來放classname -->
                            <input type="hidden" id="addMenuClassName" value="隱藏欄位值">

                            <label class="col-form-label">請輸入要新增的餐點名稱：</label>
                            <br>
                            <input type="text" id="menuName" />
                            <br>
                            <label class="col-form-label">請輸入要新增的餐點價錢：</label>
                            <br>
                            <input type="text" id="myPrice" onkeyup="value=value.replace(/[^\d]/g,'') " />
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn bg-primary" id="uploadMenu">新增</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal4" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">刪除餐點種類</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <label for="recipient-name" class="col-form-label">請選擇要刪除的餐點種類：</label>
                            <select id='selectname2'></select>
                            <br>
                            <input data-dismiss="modal" id="deleteNew" type="submit" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <!-- <button type="submit" class="btn bg-primary" id="upload">新增</button> -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal5" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">修改餐點</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <!-- 這個input看不到 拿來放classname -->
                            <input type="hidden" id="newMenuClass" value="隱藏欄位值">
                            <input type="hidden" id="oldMenu" value="隱藏欄位值">
                            <label class="col-form-label">請輸入修改後的餐點名稱：</label>
                            <br>
                            <input type="text" id="newmenuName" />
                            <br>
                            <label class="col-form-label">請輸入修改後的餐點價錢：</label>
                            <br>
                            <input type="text" id="newmyPrice" onkeyup="value=value.replace(/[^\d]/g,'') " />
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn bg-primary" id="UpdateMenu">儲存</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal6" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">刪除餐點類別</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        </div>
                        <div class="modal-body">
                            <!-- 這個input看不到 拿來放classname -->
                            <input type="hidden" id="deleteMenuClass" value="隱藏欄位值">
                            <h1>確定要刪除嗎?</h1>
                            <br>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn bg-primary" id="DeleteMenu">確定</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav style="background-color:#585A56;color:white;" class="navbar navbar-default navbar-fixed-bottom">
            <p style="position:absolute;right:20px;width:120px;">Made By FI</p>
        </nav>
    </body>

    </html>