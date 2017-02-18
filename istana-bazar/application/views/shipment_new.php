<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>css/jquery-ui.css">
        <style>
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
        /*real height is 240 px, if result come*/
        #nap8{
            left:122px;
            top:40px;
            width:509px;
            height:150px;
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

        #nap3{
            left:306px;
            top:36px;
            width:175px;
            z-index:2;
            background-color:#FFFFFF;
            color:#000000;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:center;
        }

        #nap4{
            left:278px;
            top:38px;
            width:20.900000000000002px;
            height:21px;
            z-index:3;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:left;
            color:#000000;
        }

        #nap9{
            left:403px;
            top:90px;
            z-index:4;
        }

        #nap9.napkee {
            width:78px;
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
            z-index:5;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:left;
            color:#000000;
        }

        #nap12{
            left:76px;    
            top:91px;    
            width:79px;    
            z-index:6;    
            background-color:#FFFFFF;    
            color:#000000;    
            font-size: 14px;    
            font-style:normal;    
            font-weight:normal;    
            text-decoration:none;
            text-align:center;        
        }

        #nap13{
            left:180px;
            top:92px;
            width:19.8px;
            height:21px;
            z-index:7;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:left;
            color:#000000;
        }

        #nap19{
            left:15px;
            top:145px;
            width:50.6px;
            height:21px;
            z-index:8;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:left;
            color:#000000;
        }

        #nap20{
            left:76px;
            top:143px;
            width:151px;
            z-index:9;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
        }

        #nap21{
            left:76px;
            top:36px;
            width:175px;
            z-index:10;
            background-color:#FFFFFF;
            color:#000000;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:center;
        }

        #nap22{
            left:15px;
            top:189px;
            width:426px;
            height:36px;
            z-index:11;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
            text-align:left;
            color:#000000;
            white-space: normal;
        }

        #nap23{
            left:306px;
            top:90px;
            z-index:12;
        }

        #nap23.napkee {
            width:76px;
            height:24px;
            color:#FFFFFF;
            font-size: 14px;
            font-style:normal;
            font-weight:normal;
            text-decoration:none;
        }
        </style>
    </head>
    <body>
        <input id="originCode" type="hidden">
        <input id="destinationCode" type="hidden">
        <div id="nap8" class="napkeeComponent napkeeCanvas">
            <span id="nap1" class="napkeeRelativeComponent napkeeLabel ">From</span>
            <input id="nap3" type="text" placeholder="Destination" class="napkeeRelativeComponent napkeeTextinput" />
            <span id="nap4" class="napkeeRelativeComponent napkeeLabel ">To</span>
            <button id="nap9" type="button" class="napkeeRelativeComponent napkeeButton btn">
                <table align="center">
                    <tr>
                        <td valign="middle">
                            <img src="<?php echo base_url(); ?>icons/TruckIcon.png" width="16" height="16" />
                        </td>
                        <td valign="middle">Check</td>
                    </tr>
                </table>
            </button>
            <span id="nap10" class="napkeeRelativeComponent napkeeLabel ">Weight</span>
            <input id="nap12" 
                   type="number" 
                   step="0.1" 
                   placeholder="0"
                   class="napkeeRelativeComponent napkeeTextinput" />
            <span id="nap13" 
                  class="napkeeRelativeComponent napkeeLabel ">Kg</span>
            <span id="nap19" 
                  class="napkeeRelativeComponent napkeeLabel " hidden="true">Service</span>
            <select id="nap20" class="napkeeRelativeComponent napkeeCombobox" hidden="true" name="service">
                <option value="0" label="Select Service"></option>
            </select>
            <input id="nap21" 
                   type="text" 
                   placeholder="Origin" class="napkeeRelativeComponent napkeeTextinput" />
            <div id="nap22" 
                 class="napkeeRelativeComponent napkeeParagraph" 
                 cellspacing="0" cellpadding="0" hidden="true">
            </div>
            <button id="nap23" type="button" class="napkeeRelativeComponent napkeeButton btn">
                <table align="center">
                    <tr>
                        <td valign="middle">
                            <img src="<?php echo base_url(); ?>icons/UndoIcon.png" width="16" height="16" />
                        </td>
                        <td valign="middle">Reset</td>
                    </tr>
                </table>
            </button>
        </div>
        <script type='text/javascript' src="<?php echo base_url(); ?>js/jquery-3.1.1.min.js"></script>
        <script type='text/javascript' src="<?php echo base_url(); ?>js/jquery-ui.js"></script>
        <script>
            //autocomplete for origin
            $('#nap21').on('input', function() {
                var inputValue = $('#nap21').val().length;
                if (inputValue === 2 && inputValue < 4) {
                    var origin = $('#nap21').val();
                    $.ajax({
                          type:'POST',
                          url:'<?php echo base_url("index.php/shipment/checkOrigin"); ?>',
                          data:{'origin':origin},
                          success:function(data){
                            var dataResult = data.replace(/"(.*)"/,'$1').substring(data.indexOf("\""));
                            dataResult = JSON.parse(dataResult);
                            if (dataResult) {
                                $( "#nap21" ).autocomplete({
                                    source: dataResult.detail,
                                    select: function (event, ui) {
                                        $("#originCode").val(ui.item.code);
                                    }
                                });
                            } else {
                                window.alert("Please Check Your Connection");
                            }

                          } //end success:function(data)
                    });
                }
            });
            //autocomplete for destination
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
                                window.alert("Please Check Your Connection");
                            }
                          } //end success:function(data)
                    });
                }
            });
            //Reset Button
            $('#nap23').click(function(){
                $('#nap21, #nap3, #nap12, #originCode, #destinationCode').val('');
                //back to default combobox
                $('#nap20').find('option').remove().end();
                $('<option />', {value: '0', text: 'Select Service'}).appendTo('#nap20');
                //make element to hidden
                $('#nap19 ,#nap20, #nap22').attr('hidden', true);
                $('#nap8').css('height', '150px');
            });
            //check button
            $('#nap9').click(function(){
                //set variable to use
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
                            //make element to come out
                            $('#nap19 ,#nap20').attr('hidden', false);
                            //get view for background height
                            $('#nap8').css('height', '190px');
                            for (var i = 0; i < dataResult.price.length; i++) {
                                $('<option />', {value: dataResult.price[i].price, text: dataResult.price[i].service_display}).appendTo('#nap20');
                            }
                        } else {
                            window.alert("Please Check Your Connection");
                        }
                    } //end success:function(data)
                });
            });
            //selection trigger
            $( "#nap20" ).change(function() {
                var farePrice = $( "#nap20 option:selected" ).val();
                farePrice = parseInt(farePrice);
                //get view for background height
                $('#nap8').css('height', '240px');
                $('#nap22').attr('hidden', false);
                $('#nap22').html("<p>Shipping Cost Rp." + farePrice.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            });
        </script>
    </body>
</html>