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
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/simple-sidebar.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/form-elements.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/datatables/css/datatables.min.css">
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

    <jsp:include page="partfiles/sidebar.jsp"/>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <i class="fa fa-bars fa-2x sidebar-brand" id="menu-toggle"></i>
            <div class="col-sm-12 text">
                <div class="description">
                    <center><img src="resources/img/logo_made/logo_2.png" class="img-responsive" style="height:100px;width:500px"></center>
                    <div class="row">
                        <div class="col-md-8">
                            <ul class="pagination">
                                <li class="active">
                                    <a href="#">Lost & Found</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/statistics_public_contact">Public Contact</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4" style="padding-top: 15px;padding-left: 50px">
                            <%--<button type="button" class="btn" id="button_export"><i class="fa fa-file-excel-o"></i></button>--%>
                            <button type="button" class="btn-primary" id="button_save" style="height: 40px;width: 40px; border-color: white; border-width: 5px"><i class="fa fa-floppy-o"></i></button>
                            <button type="button" class="btn-primary" id="button_edit" style="height: 40px;width: 40px; border-color: white; border-width: 5px"><i class="fa fa-pencil-square-o"></i></button>
                        </div>
                    </div>
                    <hr>
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
            <br><br>
            <div class="col-sm-8">
                <center><a title="Export" href="#" id="export">Export Table to CSV</a></center>

                <table id="table1" class="display" width="100%"></table>
            </div>
            <div class="col-sm-4">
                <center><a title="Export" href="#" id="export2">Export Table to CSV</a></center>

                <table id="table2" class="display" width="100%"></table>
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
<script src="/resources/datatables/js/jquery-1.12.4.js"></script>
<script src="/resources/bootstrap/js/bootstrap.js"></script>
<script src="/resources/datatables/js/datatables.min.js"></script>
<script src="/resources/js/echarts.common.min.js"></script>
<script type="text/javascript">

	//get chart element
    var myChart = echarts.init(document.getElementById('chart1'));

	var table_title = [
		"2017", "DEC16", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" 
	];
	
	var table1_data =  [
		["Directions", "", "", "", "", "", "", "", "", "", "", "", "", ""],
		["Lost&Found", "", "", "", "", "", "", "", "", "", "", "", "", ""],
		["Payments", "", "", "", "", "", "", "", "", "", "", "", "", ""],
		["PhoneService", "", "", "", "", "", "", "", "", "", "", "", "", ""],
		["KeyService", "", "", "", "", "", "", "", "", "", "", "", "", ""],
		["Others", "", "", "", "", "", "", "", "", "", "", "", "", ""]
	];
	
	var table2_data = [
		["0", "0%", "0"],
		["0", "0%", "0"],
		["0", "0%", "0"],
		["0", "0%", "0"],
		["0", "0%", "0"],
		["0", "0%", "0"],
		["0", "", "0"],
	];
	
	var table1;
	var table2;

	$(document).ready(function() {
		
		table1 = $('#table1').DataTable({
			data: table1_data,
			columns: [
				{title: table_title[0], width: "20px"}, 
				{title: table_title[1], width: "20px"},
				{title: table_title[2], width: "20px"},
				{title: table_title[3], width: "20px"}, 
				{title: table_title[4], width: "20px"},
				{title: table_title[5], width: "20px"},
				{title: table_title[6], width: "20px"},
				{title: table_title[7], width: "20px"},
				{title: table_title[8], width: "20px"},
				{title: table_title[9], width: "20px"},
				{title: table_title[10], width: "20px"},
				{title: table_title[11], width: "20px"},
				{title: table_title[12], width: "20px"},
				{title: table_title[13], width: "20px"},
				{defaultContent: "<button class='edit-btn'  type='button' hidden='true'>edit</button>"}
			],
			autoWidth: false,
			ordering: false,
			bPaginate: false,
			bFilter: false,
			scrollX: true
		});
		
		table2 = $('#table2').DataTable({
			data: table2_data,
			columns: [
				{title: "Total", width: "15px"}, 
				{title: "%", width: "15px"},
				{title: "Average", width: "15px"}
			],
			autoWidth: false,
			ordering: false,
			bPaginate: false,
			bFilter: false,
			scrollX: true
		});
		
		$("#table1 tbody").on("click",".edit-btn",function(){
           var tds=$(this).parents("tr").children();
           $.each(tds, function(i,td){
               var tmp=$(td);
               if(i < 1 || tmp.has('button').length ){return true;}//skip first column and the invisible btn
               var text=tmp.text();
               var input=$("<input type='number' style='padding:1px 1px 1px 1px;width:100%;height:100%;'></input>");
               input.val(text);
               tmp.html(input);
           });
           $(this).toggleClass("edit-btn");
           $(this).toggleClass("save-btn");
       });
 
       $("#table1 tbody").on("click",".save-btn",function(){
           var tds=$(this).parents("tr").children();
           $.each(tds, function(i,td){
               var tmp=$(td);
               //get input to text
               if(i >= 1 || !tmp.has('button').length){
                   var text=tmp.children("input").val();
                   tmp.html(text);
                   table1.cell(tmp).data(text);//modify the data of DataTables
               }
           });
           $(this).toggleClass("edit-btn");
           $(this).toggleClass("save-btn");
       });
		
		$('#button_edit').click( function(){
			$(".edit-btn").click();
		});
		$('#button_save').click( function(){
			$(".save-btn").click();
			setTimeout(300);
			tmp_data = table1.rows().data();
			showChart(tmp_data);
		});
		
		showChart(table1_data);
	});
	
	function showChart(tmp_data){
		data_to_show = [];
		total_sum = 0;
		total_counter = 0;
		for (var i = 0; i < tmp_data.length; i++) {
			arr = [];
			sum = 0;
			counter = 0;
			for (var j = 1; j < tmp_data[i].length; j++) {
				num = 0;
				if (tmp_data[i][j] == "") {
					num = null;
				} else {
					num = parseInt(tmp_data[i][j]);
					sum+= num;
					counter++;
				}
				arr.push(num);
			}
			if (counter!=0) {
				table2_data[i][0] = sum + "";
				average = sum/counter
				average = average.toFixed(2);
				table2_data[i][2] = average + "";
			}
			total_sum += sum;
			total_counter += counter;
			data_to_show.push(arr);
		}
		for (var i = 0; i < tmp_data.length; i++) {
			if (total_sum != 0) {
				percent = table2_data[i][0]/total_sum*100;
				percent = percent.toFixed(2);
				table2_data[i][1] = percent + "%";
			}
			
		}
		table2_data[tmp_data.length][0] = total_sum + "";
		table2_data[tmp_data.length][2] = (total_sum/total_counter).toFixed(2) + "";
		table2.destroy();
		table2 = $('#table2').DataTable({
			data: table2_data,
			columns: [
				{title: "Total", width: "15px"}, 
				{title: "%", width: "15px"},
				{title: "Average", width: "15px"}
			],
			autoWidth: false,
			ordering: false,
			bPaginate: false,
			bFilter: false,
			scrollX: true
		});
		refreshChart(table_title, data_to_show);
	}
	
	function refreshChart(ctitle, cdata) {
		myChart.title = "Number of Infomation Activities in 2017 by month";
		var colors = ['#5793f3', '#d14a61', '#675bba', '#775bba', '#995bba'];
		// chart config and data
		var option = {
			color: colors,
			tooltip: {
				trigger: 'none',
				axisPointer: {
					type: 'cross'
				}
			},
			legend: {
				data:['direction', 'lost&found','payment', 'phone', 'key', 'others']
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
					data: ctitle.slice(1)
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
					data: cdata[0]
				},
				{
					name:'lost&found',
					type:'line',
					smooth: true,
					data: cdata[1]
				},
				{
					name:'payment',
					type:'line',
					smooth: true,
					data: cdata[2]
				},
				{
					name:'phone',
					type:'line',
					smooth: true,
					data: cdata[3]
				},
				{
					name:'key',
					type:'line',
					smooth: true,
					data: cdata[4]
				},
				{
					name:'others',
					type:'line',
					smooth: true,
					data: cdata[5]
				}
			]
		};
		// show chart
		myChart.setOption(option);
	}
	
    
    
</script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $(document).ready(function () {
        function exportTableToCSV($table1, filename) {
            var $headers = $table1.find('tr:has(th)')
                ,$rows = $table1.find('tr:has(td)')

                // Temporary delimiter characters unlikely to be typed by keyboard
                // This is to avoid accidentally splitting the actual contents
                ,tmpColDelim = String.fromCharCode(11) // vertical tab character
                ,tmpRowDelim = String.fromCharCode(0) // null character

                // actual delimiter characters for CSV format
                ,colDelim = '","'
                ,rowDelim = '"\r\n"';

            // Grab text from table into CSV formatted string
            var csv = '"';
            csv += formatRows($headers.map(grabRow));
            csv += rowDelim;
            csv += formatRows($rows.map(grabRow)) + '"';

            // Data URI
            var csvData = 'data:application/csv;charset=utf-8,' + encodeURIComponent(csv);

            // For IE (tested 10+)
            if (window.navigator.msSaveOrOpenBlob) {
                var blob = new Blob([decodeURIComponent(encodeURI(csv))], {
                    type: "text/csv;charset=utf-8;"
                });
                navigator.msSaveBlob(blob, filename);
            } else {
                $(this)
                    .attr({
                        'download': filename
                        ,'href': csvData
                        //,'target' : '_blank' //if you want it to open in a new window
                    });
            }

            //------------------------------------------------------------
            // Helper Functions
            //------------------------------------------------------------
            // Format the output so it has the appropriate delimiters
            function formatRows(rows){
                return rows.get().join(tmpRowDelim)
                    .split(tmpRowDelim).join(rowDelim)
                    .split(tmpColDelim).join(colDelim);
            }
            // Grab and format a row from the table
            function grabRow(i,row){

                var $row = $(row);
                //for some reason $cols = $row.find('td') || $row.find('th') won't work...
                var $cols = $row.find('td');
                if(!$cols.length) $cols = $row.find('th');

                return $cols.map(grabCol)
                    .get().join(tmpColDelim);
            }
            // Grab and format a column from the table
            function grabCol(j,col){
                var $col = $(col),
                    $text = $col.text();

                return $text.replace('"', '""'); // escape double quotes

            }
        }


        // This must be a hyperlink
        $("#export").click(function (event) {
            // var outputFile = 'export'
            var outputFile = window.prompt("What do you want to name your output file (Note: This won't have any effect on Safari)") || 'export';
            outputFile = outputFile.replace('.csv','') + '.csv'

            // CSV
            exportTableToCSV.apply(this, [$('#table1'), outputFile]);

            // IF CSV, don't do event.preventDefault() or return false
            // We actually need this to be a typical hyperlink
        });

        $("#export2").click(function (event) {
            // var outputFile = 'export'
            var outputFile = window.prompt("What do you want to name your output file (Note: This won't have any effect on Safari)") || 'export';
            outputFile = outputFile.replace('.csv','') + '.csv'

            // CSV
            exportTableToCSV.apply(this, [$('#table2'), outputFile]);

            // IF CSV, don't do event.preventDefault() or return false
            // We actually need this to be a typical hyperlink
        });
    });
</script>
</body>
</html>
