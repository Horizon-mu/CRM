<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" +
	request.getServerName() + ":" + request.getServerPort() +
	request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>"/>
	<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<%--
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
--%>
<%--分页插件--%>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js" ></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js" charset="UTF-8"></script>
<script type="text/javascript">

	$(function(){
		$("#searchBtn").click(function(){
			pageList(1,2);
		})

		//保存按钮
		$("#saveBtn").click(function(){
			$.ajax({
				url:"activity/save.do",
				data: {
					owner : $.trim($("#create-owner").val()),
					name : $.trim($("#create-name").val()),
					startDate : $.trim($("#create-startDate").val()),
					endDate : $.trim($("#create-endDate").val()),
					cost : $.trim($("#create-cost").val()),
					description : $.trim($("#create-description").val())
				},
				type: "post",
				dataType: "json",
				success:function (result){
					//alert(result);
					if (result){
						alert("添加成功");
						$("#activityAddForm")[0].reset();
						$("#createActivityModal").modal("hide");
					} else {
						alert("添加失败");
					}
				}
			})
			pageList(1,2);
		})

		//修改模态窗口的更新按钮单击事件
		$("#updateBtn").click(function(){
			if (window.confirm("确定更新~~")){
				$.ajax({
					url:"activity/editActivity.do",
					data:{
						id:$("input[name=xz]:checked").val(),
						owner : $.trim($("#edit-marketActivityOwner").val()),
						name : $.trim($("#edit-marketActivityName").val()),
						startDate : $.trim($("#edit-startTime").val()),
						endDate : $.trim($("#edit-endTime").val()),
						cost : $.trim($("#edit-cost").val()),
						description : $.trim($("#edit-describe").val())
					},
					type:"post",
					dataType:"json",
					success:function(result){
						if (result){
							alert("更新成功~");
						}
					}
				})
			}else{

			}
		})

		//点击修改按钮
		$("#editBtn").click(function(){
			//得到选中的id,经后台查询返回市场活动信息，填充到相应的地方
			var $o = $("input[name=xz]:checked");

			if ($o.length == 0){
				alert("请选择需要修改的市场活动")
			}else if($o.length >1){
				alert("最多选择一条~~~")
			}else{
				var id = $o.val();
				var html = "<option></option>";
				$.ajax({
					url:"activity/preEditActivity.do",
					data:{
						"id" : id
					},
					type:"get",
					dataType:"json",
					success:function(result){
						$.each(result.uList,function (i,n){
							if (n.name == result.activity.owner){
								html += "<option value='"+n.id+"' selected >"+n.name+"</option>";
							}else{
								html += "<option value='"+n.id+"'>"+n.name+"</option>";
							}
						})
						$("#edit-marketActivityOwner").html(html);
						//活动名称
						$("#edit-marketActivityName").val(result.activity.name);
						//开始日期
						$("#edit-startTime").val(result.activity.startDate);
						//结束日期
						$("#edit-endTime").val(result.activity.endDate);
						//成本
						$("#edit-cost").val(result.activity.cost);
						//描述
						$("#edit-describe").val(result.activity.description);
					}
				})
				$("#editActivityModal").modal("show");
			}
		})

		$("#addBtn").click(function (){
			/*加入日历*/
			$(".time").datetimepicker({
				minView : "month",
				format: "yyyy-mm-dd",
				autoclose: true,
				todayBtn: true,
				language:'zh-CN',
				pickerPosition:"bottom-left"
			});
			var html = "<option></option>"
			$.ajax({
				url : "activity/getUserList.do",
				type:"get",
				dataType:"json",
				success:function(result){
					$.each(result,function (i,n){
						html += "<option value='"+n.id+"'>"+n.name+"</option>";
					});
					$("#create-owner").html(html);
					var id = "${user.id}";
					$("#create-owner").val(id);
					//alert("123");
					/*打开模态窗口*/
					$("#createActivityModal").modal("show");
				}
			})
		})
		//页面加载完成后触发
		pageList(1,5);
		//全选操作
		$("#xzBtn").click(function(){
			$("input[name=xz]").prop("checked",this.checked);
		})
		$("#activityBody").on("click","input[name=xz]",function(){
			$("#xzBtn").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);
		})
		//删除操作
		$("#delBtn").click(function(){
			//找到复选框中所有打钩的对象，拿到他们的id
			var param="";
			var $obj = $("input[name=xz]:checked");
			if($obj.length == 0){
				alert("请选择要删除的活动！！");
			}else {
				if (window.confirm("你确定删除选中的市场活动？")){
					for(var i = 0; i<$obj.length;i++){
						param += "id=" + $($obj[i]).val();
						//判断如果不是最后一条记录，就加上&符号
						if (i < $obj.length-1){
							param += "&";
						}

				}
					$.ajax({
						url:"activity/delete.do",
						data:param,
						type:"post",
						dataType:"json",
						success:function(result){
							/*
                                {"success":false/true}
                             */
							if (result){
								//成功
								alert("删除成功！");
								pageList(1,2);
							}else {
								alert("删除失败，请重试！");
							}
						}
					})

				}

			}
		})
	})

	/*
	* 在哪些情况下需要刷新列表（调用pageList方法）
	* 	1.页面加载完成后调用
	* 	2.点击添加，修改，保存时需要调用
	* 	3.点击查询，需要调用
	* 	4.点击分页组件需要调用
	* */
	function pageList(pageNo,pageSize) {
		//将全选框的√去掉
		$("#xzBtn").checked =false;
		//alert("展现市场活动列表");
		//alert($("#search-name").val());
		$.ajax({
			url: "activity/pageList.do",
			data: {
				//页号
				"pageNo": pageNo,
				//页大小
				"pageSize": pageSize,
				"name": $.trim($("#search-name").val()),
				"owner": $.trim($("#search-owner").val()),
				"startDate": $.trim($("#search-startDate").val()),
				"endDate": $.trim($("#search-endDate").val())
			},
			type: "get",
			dataType: "json",
			success: function (result) {
				var html = "";
				$.each(result.dataList, function (i, n) {
					html += '<tr class="active">';
					html += '<td><input type="checkbox" name="xz" value="' + n.id + '"/></td>';
					html += '<td><a style="text-decoration: none; cursor: pointer;"' +
							' onclick="window.location.href=\'activity/detail.do?id='+n.id+'\';">' + n.name + '</a></td>';
					html += '<td>' + n.owner + '</td>';
					html += '<td>' + n.startDate + '</td>';
					html += '<td>' + n.endDate + '</td>';
					html += '</tr>';
				})
				$("#activityBody").html(html);
				//$("#recordView").html(result.total);
				//数据处理完成后，结合分页插件完成分页功能
				var totalPages = result.total % pageSize == 0 ? result.total % pageSize : parseInt(result.total % pageSize) + 1;

				$("#activityPage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: result.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					onChangePage: function (event, data) {
						pageList(data.currentPage, data.rowsPerPage);
					}
				});
			}
		})
	}

	
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="activityAddForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly >
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<%--引入多个class time--%>
								<input type="text" class="form-control time" id="create-endDate" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<%--data-dismiss="modal" 关闭模态窗口--%>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" />
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startTime" />
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endTime" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost"/>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">
								</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="search-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" id="searchBtn" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<%--不要写死--%>
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="delBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="xzBtn" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
						<%--<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
			</div>
		</div>
	</div>
</body>
</html>