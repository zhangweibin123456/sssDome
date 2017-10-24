$package('XHY.fileDirectory');
XHY.fileDirectory = function() {
	var _box = null;
	// Grid 工具类
	var Utils = {
		getCheckedRows : function() {
			return $("#data-list").datagrid('getChecked');
		},
		checkSelect : function(rows) {// 检查grid是否有勾选的行, 有返回 true,没有返回true
			var records = rows;
			if (records && records.length > 0) {
				return true;
			}
			XHY.alert('警告', '未选中记录.', 'warning');
			return false;

		},
		checkSelectOne : function(rows) {// 检查grid是否只勾选了一行,是返回 true,否返回true
			var records = rows;
			if (!Utils.checkSelect(records)) {
				return false;
			}
			if (records.length == 1) {
				return true;
			}
			XHY.alert('警告', '只能选择一行记录.', 'warning');
			return false;
		}
	};

	var _this = {
		dirtree : $('#dirtree'),
		parentId : $('#parentId'),
		depth : $('#depth'),
		addDirDataAction : 'add.do',
		addDirDataWin : $('#edit-win'),
		addDirDataForm : $('#editForm'),
		addDirTypeAction : 'save.do',
		addDirTypeForm : function() {
			return $("#addDirTypeForm");
		},
		addDirTypeWin : function() {
			return $("#add-dirtype-win");
		},
		saveDirType : function() {
			if (_this.addDirTypeForm().form('validate')) {
				XHY.progress('Please waiting', 'Save ing...');// 缓冲条
				_this.addDirTypeForm().attr('action', _this.addDirTypeAction);
				XHY.saveForm(_this.addDirTypeForm(), function(data) {
					XHY.closeProgress();// 关闭缓冲条
					if (data.success) {
						_this.addDirTypeWin().dialog('close');
						XHY.alert("提示", data.msg);
					} else {
						XHY.alert("提示", data.msg);
					}
					$('#dirtree').tree('reload');
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload', param);
				});
			}
		},
		initForm : function() {
			// 添加文件目录
			_this.addDirTypeWin().find("#btn-dirtype-submit").click(function() {
				_this.saveDirType();
			});
			_this.addDirTypeWin().find("#btn-pwd-close").click(function() {
				$.messager.confirm('确认', '你确定要退出吗?', function(r) {
					if (r) {
						_this.addDirTypeWin().dialog('close');
					}
				});
			});
		},
		config : {
			event : {
				add : function(callback) {
					var node = $('#searchForm #dataTypeId')
							.combobox('getValue');
					_this.addDirDataWin.dialog('open');
					_this.addDirDataForm.resetForm();
					if (node != '') {
						_selDirType.combobox('setValue', node);
					}
				},
				// 刷新Grid 数据
				refresh : function() {
					var param = $("#searchForm").serializeObject();
					$('#data-list').datagrid('reload', param);
					var newValue = $('#dataTypeId').combobox('getValue');
					$("#selDirTypeId").combobox('setValue', newValue);
				},
				// 删除记录
				remove : function() {
					var records = Utils.getCheckedRows();
					if (Utils.checkSelect(records)) {
						$.messager.confirm('确认', '你确定要删除记录?', function(r) {
							if (r) {
								XHY.progress();
								var arr = [], idKey = 'dataId'; // 主键名称
								$.each(records, function(i, record) {
									arr.push('id=' + record[idKey]);
								});
								var data = arr.join("&");
								XHY.deleteForm('delete.do', data, function(
										result) {
									XHY.closeProgress();
									_this.config.event.refresh();
									// 回调函数
									if (result) {
										XHY.alert("提示", result.msg);
									}
								});
							}
						});
					}
				}
			},
			dataGrid : {
				title : '文件目录分类列表',
				url : 'dataList.do',
				idField : 'id',
				columns : [ [ {
					field : 'id',
					checkbox : true
				}, {
					field : 'dirName',
					title : '目录名称',
					width : 250,
					sortable : false
				}, {
					field : 'creator',
					title : '创建人',
					width : 105,
					sortable : false
				}, {
					field : 'createDate',
					title : '创建时间',
					width : 150,
					sortable : false
				}, {
					field : 'note',
					title : '备注',
					width : 300,
					sortable : false
				} ] ],
				toolbar : [ null ]
			}
		},

		initcombobox : function(project) {
			//			
			// $("#projectName").combobox({
			// url:'getProjectList.do',
			// valueField:'projectId',
			// textField:'projectName'
			// });

			$("#projectId").combobox(
							{
								url : 'getProjectList.do?project='+project,
								valueField : 'projectId',
								textField : 'projectName',

								multiple : false,
								formatter : function(row) {
									var s = "<option class='selectId' style='vertical-align: middle'  id='selectId_"
											+ row.projectId
											+ "' value='"
											+ row.projectId
											+ "'>"
											+ row.projectName + "</option>";
									return s;
								}
							});

		},

		init : function() {

			_this.initForm();
			_box = new YDataGrid(_this.config);
			_box.init();
			_this.dirtree.tree({
				url : 'getDirTree.do',
				checkbox : false,
				onLoadSuccess : function() {
					// _this.dirtree.tree("collapseAll");//折叠
					_this.dirtree.tree("expandAll"); // 展开
				}
			});
		}
	};
	return _this;
}();

$(function() {
	XHY.fileDirectory.init();
	// 点击树查询
	$("#dirtree").click(function() {
		var node = $('#dirtree').tree('getSelected'); // 获取选中树节点
		$('#data-list').datagrid('reload', {
			id : node.id
		});
	});

	// 添加目录树
	$("#btnadddir").click(function() {
		var node = $('#dirtree').tree('getSelected'); // 获取选中树节点
		if (node == null || node == '') {
			XHY.alert("提示", "请先选择节点，再添文件目录！");
		} else {

			// 新加
			var data = {
				'id' : node.id
			};
			XHY.getById('getById.do', data, function(result) {
 
				var depth = result.data.depth;
				if (depth >= 2) {
					XHY.alert("提示", "此节点已为最小节点,无法继续添加子节点！");
					return;
				} else if (depth == 0) {

					$("#div2_dirName").hide();
					$("#div1_dirName").show();
				} else {
					XHY.fileDirectory.initcombobox(result.data.projectId);

					$("#div1_dirName").hide();
					$("#div2_dirName").show();
				}
				$("#add-dirtype-win").dialog('open');
				$("#addDirTypeForm").resetForm();
				$("#parentId").val(node.id);

			});

		}
	});
	// 修改目录树
	$("#btneditdir").click(
			function() {
				var node = $('#dirtree').tree('getSelected'); // 获取选中树节点
				if (node == null || node == '') {
					XHY.alert("提示", "请选择要修改的节点！");
				} else {
					var nodef = $('#dirtree').tree('getParent', node.target); // 当前节点父节点
					if (nodef == null || nodef == '') {
						XHY.alert("提示", "当前节点是根节点，不能修改！");
					} else {
						$("#add-dirtype-win").dialog('open');
						$("#addDirTypeForm").resetForm();
						var data = {
							'id' : node.id
						};
						XHY.getById('getById.do', data, function(result) {

							// ****************************
							var depth = result.data.depth;

							if (depth == 1) {

								$("#div2_dirName").hide();
								$("#div1_dirName").show();
							} else {
								//XHY.fileDirectory.initcombobox();
								$("#div1_dirName").hide();
								$("#div2_dirName").show();
								XHY.fileDirectory.initcombobox(result.data.projectId);
								
								$('#projectId').combobox('select',result.data.projectId);
								
                                 
							}

							$("#dirName").val(result.data.dirName);
							$("#dirId").val(result.data.id);
							$("#note").val(result.data.note);
							$("#depth").val(result.data.depth);
						});
					}
				}
			});
	// 删除目录树
	$("#btnremovedir").click(function() {
		var node = $('#dirtree').tree('getSelected'); // 获取选中树节点
		if (node == null || node == '') {
			XHY.alert("提示", "请选择要删除的节点！");
		} else {
			var nodef = $('#dirtree').tree('getParent', node.target); // 当前节点父节点
			if (nodef == null || nodef == '') {
				XHY.alert("提示", "当前节点是根节点，不能删除！");
			} else {
				var nodez = $('#dirtree').tree('getChildren', node.target); // 当前节点子节点
				if (nodez != null && nodez != '') {
					XHY.alert("提示", "当前节点有子节点，不能删除！");
				} else {
					$.messager.confirm('确认', '您确定要删除这条记录?', function(r) {
						if (r) {
							XHY.progress();
							var data = {
								'id' : node.id
							};
							XHY.deleteForm('delete.do', data, function(result) {
								XHY.alert("提示", result.msg, "", "");
								$('#dirtree').tree('reload');
								var param = $("#searchForm").serializeObject();
								$('#data-list').datagrid('reload', param);
								XHY.closeProgress();
								// 回调函数
								if (jQuery.isFunction(callback)) {
									callback(result);
								}
							});
						}
					});
				}
			}
		}
	});
});