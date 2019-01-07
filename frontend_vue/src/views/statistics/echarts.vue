<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="12">
                <div id="chartLine" style="width:100%; height:400px;"></div>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import echarts from 'echarts'

    export default {
        data() {
            return {
                chartColumn: null,
                chartBar: null,
                chartLine: null,
                chartPie: null
            }
        },

        methods: {
            drawLineChart() {
                this.chartLine = echarts.init(document.getElementById('chartLine'));
                this.chartLine.setOption({
                    title: {
                        left: 'center',
                        text: '销售额统计'
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
                        name: '销售额（万）',
                    },
                    series: [
                        {
                            name: '邮件营销',
                            type: 'line',
                            stack: '总量',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: '联盟广告',
                            type: 'line',
                            stack: '总量',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            name: '搜索引擎',
                            type: 'line',
                            stack: '总量',
                            data: [820, 932, 901, 934, 1290, 1330, 1320]
                        }
                    ]
                });
            },
            drawCharts() {

                this.drawLineChart()

            },
        },

        mounted: function () {
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
