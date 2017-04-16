Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items: [{
            region: 'north',
            html: '<h1 class="x-panel-header" style="background:grey;">Logo</h1>',
            border: false,
            height: 50,
            margins: '0 0 0 0'
        }, {
            region: 'west',
            collapsible: true,
            split: true,
            id: 'MainMenu',
            title: '系统导航',
            width: 150,
            layout: 'accordion',
            items: [
                {
                    title: '系统菜单',
                    layout: 'fit',
                    items: [
                        {
                            xtype: 'treepanel',
                            border: 0,
                            rootVisible: false,
                            root: {
                                expanded: true,
                                children: [
                                    { id: "01", text: "用户管理", leaf: true, href: 'back/user/user.jsp' },
                                    { id: "02", text: "新闻类型", leaf: true, href: 'back/type/type.jsp' },
                                    { id: "03", text: "新闻", leaf: true, href: 'back/news/news.jsp' }
                                ]
                            }
                        }
                    ]
                },
            ]
            // could use a TreePanel or AccordionLayout for navigational items
        },{
            region: 'center',
            xtype: 'tabpanel', 
            id: 'MainTabPanel',
            activeTab: 0,  
            items: {
                title: '首页',
                html: '<h3>欢迎使用</h3><input type="button" value="添加新标签" onclick="CreateIframeTab(\'MainTabPanel\',\'01\', \'系统管理\', \'http://www.baidu.com\');" />'
            }
        }]
    });

    bindNavToTab("MainMenu", "MainTabPanel");
});

function bindNavToTab(accordionId, tabId) {
    var accordionPanel = Ext.getCmp(accordionId);
    if (!accordionPanel) return;

    var treeItems = accordionPanel.queryBy(function (cmp) {
        if (cmp && cmp.getXType() === 'treepanel') return true;
        return false;
    });
    if (!treeItems || treeItems.length == 0) return;

    for (var i = 0; i < treeItems.length; i++) {
        var tree = treeItems[i];

        tree.on('itemclick', function (view, record, htmlElement, index, event, opts) {
            if (record.isLeaf()) {
                // 阻止事件传播
                event.stopEvent();

                var href = record.data.href;

                if (!href) return;
                // 修改地址栏
                window.location.hash = '#' + href;
                // 新增Tab节点
                CreateIframeTab(tabId, record.data.id, record.data.text, href);
            }
        });
    }
}

function CreateIframeTab(tabpanelId, tabId, tabTitle, iframeSrc) {
    var tabpanel = Ext.getCmp(tabpanelId);
    if (!tabpanel) return;  //未找到tabpanel，返回

    //寻找id相同的tab
    var tab = Ext.getCmp(tabId);
    if (tab) { tabpanel.setActiveTab(tab); return; }

    //新建一个tab，并将其添加到tabpanel中
    //tab = Ext.create('Ext.tab.Tab', );
    tab = tabpanel.add({
        id: tabId,
        title: tabTitle,
        closable: true,
        html: '<iframe style="overflow:auto;width:100%; height:100%;" src="' + iframeSrc + '" frameborder="0"></iframe>'
    });
    tabpanel.setActiveTab(tab);
}