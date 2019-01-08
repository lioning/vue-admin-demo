<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="24">
                <!-- <div style="width:100%; height:10px;"> -->
                <div>
                    <span>年份：</span>
                    <el-select v-model="pickYear" placeholder="选择年份" @change="yearSelected">
                        <!-- <el-option label="是" value="Y"> </el-option> -->
                        <el-option v-for="(item,index) in allYears" :key="index" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                    <span>门店名称：</span>
                    <el-select v-model="store" placeholder="选择门店" @change="storeSelected">
                        <!-- <el-option label="是" value="Y"> </el-option> -->
                        <el-option v-for="(item,index) in allStore" :key="index" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24">
                <div id="chartLine" style="width:100%; height:400px;"></div>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import { getStatisticsMonth, getStoreList} from '../../api/api';
    import echarts from 'echarts'

    export default {
        data() {
            return {
                store:"",
                allStore:[],
                pickYear:"",
                allYears:[],
                chartColumn: null,
                chartBar: null,
                chartLine: null,
                chartPie: null,
                sales:[10,20,30,40,50,60,20,50],
                title:"销售额统计",
            }
        },

        methods: {
            drawLineChart(storedata) {
                this.chartLine = echarts.init(document.getElementById('chartLine'));
                this.chartLine.setOption({
                    title: {
                        left: 'center',
                        text: this.title
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        //data: ['邮件营销', '联盟广告', '搜索引擎']
                    },
                    grid: {
                        left: '3%',
                        right: '10%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        name: '月份',
                        boundaryGap: false,
                        // data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                        data: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12']
                    },
                    yAxis: {
                        type: 'value',
                        name: '销售额（万元）',
                    },
                    series: [
                        {
                            name: '',
                            type: 'line',
                            stack: '总量',
                            // data: [120, 132, 101, 134, 90, 230, 210]
                            data: this.seriesdata
                        },
                        // {
                        //     name: '联盟广告',
                        //     type: 'line',
                        //     stack: '总量',
                        //     data: [220, 182, 191, 234, 290, 330, 310]
                        // },
                        // {
                        //     name: '搜索引擎',
                        //     type: 'line',
                        //     stack: '总量',
                        //     data: [820, 932, 901, 934, 1290, 1330, 1320]
                        // }
                    ]
                });
            },
            drawCharts() {

                this.drawLineChart()

            },
            storeSelected(){
                console.log("storeSelected success")
                if (this.pickYear != "" && this.store != ""){
                    this.getDataAndDraw()
                }
            },
            yearSelected() {
                console.log("yearSelected success")
                if (this.pickYear != "" && this.store != ""){
                    this.getDataAndDraw()
                }
            },
            getDataAndDraw() {
                let para = { year: this.pickYear, store:this.store };
                console.log("getDataAndDraw sucees, para", para)
                getStatisticsMonth(para).then((res) => {
					console.log("getStatistics res:", res);
                    this.seriesdata = res;
                    this.drawCharts(res)
				});
            },
			//获取供应商列表
			getStores() {
				let para = {
				};
                // this.allStore = [{
                //     "label": "供应商1",
                //     "value": "1"
                // },{
                //     "label": "供应商2",
                //     "value": "2"
                // },{
                //     "label": "供应商3",
                //     "value": "3"
                // }],
                getStoreList(para).then((res) => {
					console.log("getStoreList res:", res);
                    this.allStore = res.data.res;
				});
			},
            //生成年份数据
			getYears() {
                var myDate = new Date();
                console.log("getYears running:", myDate.getFullYear());
                for (var i=myDate.getFullYear();i>1980;i--)
                {
                    var option=new Object();
                    option.label = i
                    option.value = i
                    this.allYears.push(option)

                }
			},
        },

        mounted: function () {
            this.getStores()
            this.getYears()
            this.drawCharts()
        },
        updated: function () {
            this.drawCharts()
        }
    }
</script>

<style scoped>
    .chart-container {
        width: 100%;
        float: left;
    }
    /*.chart div {
        height: 400px;
        float: left;
    }*/

    .el-col {
        padding: 30px 20px;
    }
</style>
