<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="供应商名称"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="35">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="no" label="供应商编号" width="150" sortable>
			</el-table-column>
			<el-table-column prop="name" label="供应商名称" min-width="250" sortable>
			</el-table-column>
			<el-table-column prop="addr" label="地址" min-width="250" sortable>
			</el-table-column>
			<el-table-column prop="phone" label="联系电话" width="150" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button> -->
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
				<el-form-item label="供应商名称" prop="name">
					<el-input v-model="editForm.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="地址" prop="addr">
					<el-input v-model="editForm.addr" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="联系电话" prop="phone">
					<el-input v-model="editForm.phone" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="120px" :rules="addFormRules" ref="addForm">
				<el-form-item label="供应商编号" prop="no">
					<el-input v-model="addForm.no" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="供应商名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="地址" prop="addr">
					<el-input v-model="addForm.addr" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="联系电话" prop="phone">
					<el-input v-model="addForm.phone" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { getUserListPage, getSupplierListPage, removeSupplier, removeUser, batchRemoveUser, editSupplier, addSupplier, editUser, addUser } from '../../api/api';

	export default {
		data() {
			return {
				filters: {
					name: ''
				},
				users: [],
				total: 0,
				page: 1,
				listLoading: false,
				sels: [],//列表选中列

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					id: 0,
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				}

			}
		},
		methods: {
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			//获取供应商列表
			getUsers() {
				let para = {
					page: this.page,
					name: this.filters.name,
					size: 20
				};
				this.listLoading = true;
                //NProgress.start();
                // getUserListPage(para).then((res) => {
                  getSupplierListPage(para).then((res) => {
					console.log("getSupplierListPage res:", res);
					this.total = res.data.total;
                    this.users = res.data.res;
                    //this.users = res.data.Users;
					/*
					this.total = 2
					this.users = [{"idxx": "",
                    "name": "周平",
                    "addr":"大洞口区",
                    "no":"C001",
                    "birth":"2000-01-01",
                    "sex":"1"},
                {"idxx": "",
                    "name": "方青山",
                    "addr":"天上人间",
                    "no":"C002",
                    "birth":"2000-01-01",
                    "sex":"0"}];*/

					this.listLoading = false;
					//NProgress.done();
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { no: row.no, name:row.name, addr:row.addr };
					console.log("handleDel, para:", para);
					removeSupplier(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						console.log("handleDel, return");
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				};
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							console.log("edit Suppliers para:", para)
							editSupplier(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								let { msg, code} = res;
								if (code !== 200) {
									this.$message({
									  message: msg,
									  type: 'error'
									});
								} else {
									this.$message({
										message: '提交成功',
										type: 'success'
									});
								}
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.addForm);
							console.log("add Suppliers para:", para);
							addSupplier(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								let { msg, code} = res;
								if (code !== 200) {
									this.$message({
									  message: msg,
									  type: 'error'
									});
								} else {
									this.$message({
										message: '提交成功',
										type: 'success'
									});
								}
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
            this.getUsers();
            console.log("Supplier_table.vue: Hit")
            console.log("total:", this.total);
            console.log("users:", this.users);
            console.log("loading:", this.listLoading);
		}
	}

</script>

<style scoped>

</style>