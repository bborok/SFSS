<%--
  Created by IntelliJ IDEA.
  User: PrivateAcc
  Date: 2017-09-29
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SFU</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/form-elements.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>

<style>
    #side-container{
    }

    #side-contact{
        position: absolute;
        bottom: 0;
        color: #ffffff;
    }

</style>

<body>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <p>SFU SFEP</p>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/schedule">Schedule</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <li class="active">
                <a href="${pageContext.request.contextPath}/statistics">Statistics</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/payroll">Payroll</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log">Log</a>
            </li>
        </ul>
        <div id="side-container">
            <div id="side-contact" style="text-align:center; margin-bottom:10px">

                <p style="text-align:center; font-size:20px">
                    <a class="fa fa-sign-out fa-x" href="${pageContext.request.contextPath}/">Sign Out</a>
                </p>
                <br>
                <p style="font-weight: bold; text-decoration: underline;">Contact: </p>
                <p class="fa fa-phone "> (604)-666-6666</p>

                <p class="fa fa-envelope-o"> admin_sfep@sfu.ca</p>
            </div>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <div class="description">
                    <center><img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px"></center><hr>
                </div>
            </div>

            <center>
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="option1" autocomplete="off"> Burnaby
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="option2" autocomplete="off"> Surrey
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="option3" autocomplete="off"> Vancouver
                    </label>
                </div>
            </center>

            <div align="right" class="row">
                <button type="button" class="btn"><i class="fa fa-file-excel-o"></i></button>
                <button type="button" class="btn"><i class="fa fa-floppy-o"></i></button>
                <button type="button" class="btn"><i class="fa fa-pencil-square-o"></i></button>
            </div>
            <br><br>
            <div class="col-sm-12">
                <table id="table1" class="table table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>2017</th>
                        <th>DEC'16</th>
                        <th>JAN</th>
                        <th>FEB</th>
                        <th>MAR</th>
                        <th>APR</th>
                        <th>MAY</th>
                        <th>JUN</th>
                        <th>JUL</th>
                        <th>AUG</th>
                        <th>SEP</th>
                        <th>OCT</th>
                        <th>NOV</th>
                        <th>DEV</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Directions</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Lost&Found</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Payments</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>PhoneService</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>KeyService</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    <tr>
                        <td>Others</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                        <td>56</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <hr>
            <div class="col-sm-3">
                <table id="table2" class="table table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Total</th>
                        <th>%</th>
                        <th>Num</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    <tr>
                        <th>333</th>
                        <th>3%</th>
                        <th>11</th>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="col-sm-12">
                <div id="chart1" style="width:100%;height:400px;"></div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="resources/js/jquery-1.12.4.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/js/echarts.common.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart1'));
    myChart.title = "Number of Infomation Activities in 2017 by month";
    var colors = ['#5793f3', '#d14a61', '#675bba', '#775bba', '#995bba'];
    // 指定图表的配置项和数据
    var option = {
        color: colors,
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['direction', 'payment', 'phone', 'key', 'others']
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return 'number  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: ["DEC14", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name:'direction',
                type:'line',
                smooth: true,
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3, 11]
            },
            {
                name:'payment',
                type:'line',
                smooth: true,
                data: [70.7, 175.6, 2.6, 5.9, 9.0, 26.4, 28.7,  182.2, 48.7, 18.8, 6.0, 2.3, 11]
            },
            {
                name:'phone',
                type:'line',
                smooth: true,
                data: [182.2, 48.7, 18.8, 6.0, 2.3, 112.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 45]
            },
            {
                name:'key',
                type:'line',
                smooth: true,
                data: [5.9, 66, 26.4, 28.7, 70.7, 155, 89, 48.7, 18.8, 6.0, 2.3, 11, 23]
            },
            {
                name:'others',
                type:'line',
                smooth: true,
                data: [18.8, 6.0, 2.3, 11, 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>
