import axios from 'axios';
import qs from 'qs';
//axios.defaults.baseURL = 'http://localhost:8080';
//let base = 'StoreSystem/'; // StoreSystem 映射到 servlet 所在的 http://localhost:8080 
let base = '';
// 分隔符 '/' 必须放在 base 变量内，否则发布时将 base 置为空，请求路径将是绝对路径（根路径），而不是相对 appname 的相对路径
//export const requestLogin = params => { return axios.post(`${base}login`, params).then(res => res.data); };
export const requestLogin = params => { return axios.post(`${base}do_login.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}user_listpage.do`, { params: params }); };

export const addUser = params => { return axios.post(`${base}user_add.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const removeUser = params => { return axios.post(`${base}user_delete.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }

export const batchRemoveUser = params => { return axios.get(`${base}user/batchremove`, { params: params }); };

export const editUser = params => { return axios.post(`${base}user_edit.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
//export const addUser = params => { return axios.get(`${base}user/add`, { params: params }); };


export const getStoreList = params => { return axios.get(`${base}store/list`, { params: params }); };
export const getStoreListPage = params => { return axios.get(`${base}store_listpage.do`, { params: params }); };
export const editStore = params => { return axios.post(`${base}store_edit.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const addStore = params => { return axios.post(`${base}store_add.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const removeStore = params => { return axios.post(`${base}store_delete.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }


export const getSupplierList = params => { return axios.get(`${base}supplier/list`, { params: params }); };
export const getSupplierListPage = params => { return axios.get(`${base}supplier_listpage.do`, { params: params }); };
export const editSupplier = params => { return axios.post(`${base}supplier_edit.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const addSupplier = params => { return axios.post(`${base}supplier_add.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const removeSupplier = params => { return axios.post(`${base}supplier_delete.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }


export const getProductList = params => { return axios.get(`${base}product/list`, { params: params }); };
export const getProductListPage = params => { return axios.get(`${base}product_listpage.do`, { params: params }); };
export const editProduct = params => { return axios.post(`${base}product_edit.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const addProduct = params => { return axios.post(`${base}product_add.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }
export const removeProduct = params => { return axios.post(`${base}product_delete.do`, qs.stringify(params), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(res => res.data); }