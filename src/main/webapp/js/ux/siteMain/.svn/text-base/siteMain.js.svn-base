$package('XHY.siteMain');
XHY.siteMain = function(){
	var _box = null;
	var _this = {
		config:{
			event:{
				add:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
  			dataGrid:{
  				title:'Site List',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'Name',width:120,sortable: false},
					{field:'domain',title:'Domain',width:220,sortable: false,sorter:function(a,b){return (a>b?1:-1);}},
					{field:'state',title:'State',width:80,align:'center',sortable: false,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							if(value == 0){
								return "可用";
							}
							if(value == 1){
								return "禁用";
							}
						}},
					{field:'rank',title:'Rank',align:'right',width:80,sortable: false},
					{field:'types',title:'Types',width:250,sortable: false},
					{field:'pic',title:'Pic',width:250,sortable: false}
				]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	XHY.siteMain.init();
});		