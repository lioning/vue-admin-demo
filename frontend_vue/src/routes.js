import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import store_table from './views/stores/store_table.vue'
import supplier_table from './views/supplier/supplier_table.vue'
import users_table from './views/users/users_table.vue'
import product_table from './views/products/products_table.vue'
import statistics_charts from './views/statistics/echarts.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '门店管理',
        iconCls: 'fa fa-home',//图标样式class
        children: [
            { path: '/store_main', component: Main, name: '主页', hidden: true },
            { path: '/store_table', component: store_table, name: '门店列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '供应商管理',
        // iconCls: 'el-icon-message',//图标样式class
        iconCls: 'fa fa-android',
        children: [
            { path: '/supplier_main', component: Main, name: '主页', hidden: true },
            { path: '/supplier_table', component: supplier_table, name: '供应商列表' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'fa fa-id-card-o',//图标样式class
        children: [
            { path: '/user_main', component: Main, name: '主页', hidden: true },
            { path: '/user_table', component: users_table, name: '用户列表' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '商品管理',
        iconCls: 'fa fa-shopping-bag',//图标样式class
        children: [
            { path: '/product_main', component: Main, name: '主页', hidden: true },
            { path: '/product_table', component: product_table, name: '商品列表' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '销售报表',
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/statistics', component: statistics_charts, name: '月度销售报表' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;