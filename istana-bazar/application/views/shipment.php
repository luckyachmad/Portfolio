<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>css/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>css/jquery.dataTables.min.css">
  <style type="text/css">
	#napkeeCanvas {
		position:absolute;
		left:0px;
		top:0px;
	}
	.napkeeComponent {
		position:absolute;
		white-space: nowrap;
	}
	.napkeeRelativeComponent {
		position:absolute;
		white-space: nowrap;
	}

	#nap8{
		left:63px;
		top:42px;
		width:457px;
		height:159px;
		z-index:0;
		background-color:#999999;
		opacity:1;
		filter:alpha(opacity=100);
		border:0px solid #000;
		-moz-border-radius:0px;
		-webkit-border-radius:0px;
	}

	#nap1{
		left:15px;
		top:38px;
		width:35.2px;
		height:21px;
		z-index:1;
		font-size: 14px;
		font-style:normal;
		font-weight:normal;
		text-decoration:none;
		text-align:left;
		color:#000000;
	}

	#nap2{
		left:62px;
		top:36px;
		width:144px;
		z-index:2;
		background-color:#FFFFFF;
		color:#000000;
		font-size: 14px;
		font-style:normal;
		font-weight:normal;
		text-decoration:none;
		text-align:center;
	}

	#nap3{
		left:261px;
		top:36px;
		width:163px;
		z-index:3;
		background-color:#FFFFFF;
		color:#000000;
		font-size: 14px;
		font-style:normal;
		font-weight:normal;
		text-decoration:none;
		text-align:center;
	}

	#nap4{
		left:227px;
		top:38px;
		width:20.900000000000002px;
		height:21px;
		z-index:4;
		font-size: 14px;
		font-style:normal;
		font-weight:normal;
		text-decoration:none;
		text-align:left;
		color:#000000;
	}

	#nap9{
		left:338px;
		top:90px;
		z-index:5;
	}

	#nap9.napkee {
		width:86px;
		height:24px;
		color:#FFFFFF;
		font-size: 14px;
		font-style:normal;
		font-weight:normal;
		text-decoration:none;
	}
        #nap10{
                left:15px;
                top:92px;
                width:46.2px;
                height:21px;
                z-index:6;
                font-size: 14px;
                font-style:normal;
                font-weight:normal;
                text-decoration:none;
                text-align:left;
                color:#000000;
        }

        #nap12{
                left:62px;
                top:91px;
                width:79px;
                z-index:7;
                background-color:#FFFFFF;
                color:#000000;
                font-size: 14px;
                font-style:normal;
                font-weight:normal;
                text-decoration:none;
                text-align:center;
        }

        #nap13{
                left:156px;
                top:92px;
                width:19.8px;
                height:21px;
                z-index:8;
                font-size: 14px;
                font-style:normal;
                font-weight:normal;
                text-decoration:none;
                text-align:left;
                color:#000000;
        }
        
        #nap15{
                left:63px;
                top:241px;
                width:656px;
                height:212px;
                z-index:9;
                font-size: 14px;
                font-style:normal;
                font-weight:normal;
                text-decoration:none;
                opacity:1.0;
                filter:alpha(opacity=100);
                background-color:#FFFFFF;
                margin:0;
                padding:0;
        }
</style>
<head>
<body>
    <div id="nap8" class="napkeeComponent napkeeCanvas">
	<span id="nap1" class="napkeeRelativeComponent napkeeLabel ">From</span>
	<input id="nap2" type="text" placeholder="Origin" class="napkeeRelativeComponent napkeeTextinput" />
        <input id="originCode" type="hidden">
	<input id="nap3" type="text" placeholder="Destination" class="napkeeRelativeComponent napkeeTextinput" />
        <input id="destinationCode" type="hidden">
	<span id="nap4" class="napkeeRelativeComponent napkeeLabel ">To</span>
	<button id="nap9" type="button" class="napkeeRelativeComponent napkeeButton btn">
            <table align="center">
                <tr>
                    <td valign="middle">
                        <img src="<?php echo base_url(); ?>icons/TruckIcon.png" width="24" height="24" />
                    </td>
                    <td valign="middle">Check</td>
		</tr>
            </table>
	</button>
        <span id="nap10" class="napkeeRelativeComponent napkeeLabel ">Weight</span>
        <input id="nap12" type="number" step="0.01" placeholder="Weight" class="napkeeRelativeComponent napkeeTextinput" />
	<span id="nap13" class="napkeeRelativeComponent napkeeLabel ">Kg</span>
    </div>
    <div id="nap15" class="napkeeComponent" hidden="true">
        <table id="resultTable" class="display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Product Service</th>
                    <th>Price</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type='text/javascript' src="<?php echo base_url(); ?>js/jquery-3.1.1.min.js"></script>
    <script type='text/javascript' src="<?php echo base_url(); ?>js/jquery-ui.js"></script>
    <script type='text/javascript' src="<?php echo base_url(); ?>js/jquery.dataTables.min.js"></script>
    <script>
    //script for trigger on origin input text
    $('#nap2').on('input', function() {
        var inputValue = $('#nap2').val().length;
        if (inputValue === 2 && inputValue < 4) {
            var origin = $('#nap2').val();
            $.ajax({
                  type:'POST',
                  url:'<?php echo base_url("index.php/shipment/checkOrigin"); ?>',
                  data:{'origin':origin},
                  success:function(data){
                    var dataResult = data.replace(/"(.*)"/,'$1').substring(data.indexOf("\""));
                    dataResult = JSON.parse(dataResult);
                    if (dataResult) {
                        $( "#nap2" ).autocomplete({
                            source: dataResult.detail,
                            select: function (event, ui) {
                                $("#originCode").val(ui.item.code);
                            }
                        });
                    } else {
                        console.log("not working");
                        console.log(dataResult);
                    }
                    
                  } //end success:function(data)
            });
        }
    });
    //script for trigger on Destination input text
    $('#nap3').on('input', function() {
        var inputValue = $('#nap3').val().length;
        if (inputValue === 2 && inputValue < 4) {
            var destination = $('#nap3').val();
            $.ajax({
                  type:'POST',
                  url:'<?php echo base_url("index.php/shipment/checkDestination"); ?>',
                  data:{'destination':destination},
                  success:function(data){
                    var dataResult = data.replace(/"(.*)"/,'$1').substring(data.indexOf("\""));
                    dataResult = JSON.parse(dataResult);
                    if (dataResult) {
                        $( "#nap3" ).autocomplete({
                            source: dataResult.detail,
                            select: function (event, ui) {
                                $("#destinationCode").val(ui.item.code);
                            }
                        });
                    } else {
                        console.log("not working");
                        console.log(dataResult);
                    }
                  } //end success:function(data)
            });
        }
    });
    //script for trigger on check fare submit input text and display as datatable
    $("#nap9").click(function(event) {
        if ($("#resultTable").DataTable()) {
            var table =  $("#resultTable").DataTable();
            $("#nap15").attr("hidden",true);
            table.destroy();
        }
        //set variable to use
        var origin = $("#nap2").val();
        var destination = $("#nap3").val();
        var originCode = $('#originCode').val();
        var destinationCode = $('#destinationCode').val();
        var weight = $('#nap12').val();
        //post to server
        $.ajax({
            type:'POST',
            url:'<?php echo base_url("index.php/shipment/checkFareResult"); ?>',
            data:{'origin':originCode, 'destination':destinationCode, 'weight':weight},
            success:function(data){
                var dataResult = data.replace(/"(.*)"/,'$1').substring(data.indexOf("\""));
                dataResult = JSON.parse(dataResult);
                if (dataResult) {
                    $("#nap15").attr("hidden",false);
                    $("#resultTable").DataTable({
                        data: dataResult.price,
                        "columns": [
                            {"data" : "origin_name"},
                            {"data" : "destination_name"},
                            {"data" : "service_display"},
                            {"data" : "price"}
                        ]
                    });
                } else {
                    console.log("not working");
                    console.log(dataResult);
                }
            } //end success:function(data)
        });
    });
    </script>
</body>
</html>
