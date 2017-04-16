function getAjaxProxy(p){
		return new Ext.data.proxy.Ajax(Ext.merge({
		         type: 'ajax'
			     ,url: ''
			     ,reader: {
			    	    type: 'json',
		                root: 'data',
		                totalProperty: 'totalCount'
		       } 
		       ,tiemout: 20000
		       ,simpleSortMode: false
		       ,extraParams: {}
		       ,filterParam: 'query'
		       ,listeners: {
			      	 exception: function($this, response, operation, eOpts){
			      		   showResult(false,response);
			      	 }
		       }
	},p));
}
/**
 * 显示ajax处理结果
 * */
function showResult(success, res){
	   var result;
	   if(res['timedout'] == true){ //超时
		   Ext.Msg.alert("系统提示","超时"+"<br/><font color='red'>("+"请重新登录"+")</font>");
		   return false;
	   }
	   var result;
	   try{
		   result = Ext.decode(res['responseText']);
	   }catch(e){
		   Ext.Msg.alert("系统提示","登录超时",function(){
			   //window.location.replace("auth!logOffUser.action");
		   });
		   return false;
	   }
	   if(!result || success == false){
		   Ext.Msg.alert("系统提示","错误"+"<br/><font color='red'>("+"数据有误"+")</font>");
		   return false;
	   }
	   if(result['code'] != 0){
		   Ext.Msg.alert("提示", result['msg']);
		   return false;
	   }
	   return result;
}
Ext.onReady(function() {
	var gridPanel;
	var userStore,userProxy;
	userProxy =  getAjaxProxy({url: 'user_getAllUser.action'});
	userStore = Ext.create('Ext.data.Store', {
		fields : [ 'id', 'name', 'password', 'type'],
		buffered : false,
		pageSize : 10,
		leadingBufferZone : 50,
		proxy : userProxy,
		autoLoad : true,
		listeners : {
			load : function($this) {
				Ext.getBody().unmask();
			}
		}
	});
	gridPanel = Ext.create('Ext.panel.Panel', {
		layout : 'fit',
		border : false,
		bodyStyle : 'background: white',
		id : 'treePanelBig',
		rootVisible : false,
		frame : false,
		items : [ {
			border : true,
			layout : 'fit',
			id : 'rightPanel',
			store : userStore,
			xtype : 'grid',
			//selModel : sm,
			//selType : 'checkboxmodel',
			columns : {
				items : [ {
					text : "序号",
					width : 60,
					xtype : 'rownumberer',
					align : 'center'
				}, {
					text : "名称",
					dataIndex : 'name',
					minWidth : 64,
					//renderer : renderHeader
				}, {
					text : "密码",
					dataIndex : 'password',
					minWidth : 70,
					flex : 1
				}/*, ctrColumn */],
				defaults : {
					menuDisabled : true,
					sortable : false
				}
			},
			bbar : [ '->', {
				id : 'padingBar',
				xtype : 'pagingtoolbar',
				store : userStore,
				border : false,
				displayInfo : true
			} ],
			margin : '3 1 1 -1',
			viewConfig : {
				trackOver : false,
				disableSelection : false,
				emptyText : "呜呜呜"
			}
		} ]
	});
});