<!DOCTYPE html>
<html>

<head>
    <title>To Do Application</title>
    <link href="resources/css/jquery-ui.css" rel="stylesheet">
    <link href="resources/css/jquery-ui.theme.css" rel="stylesheet">
    <link href="resources/css/jquery-ui.structure.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/ui.jqgrid.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>

<body>
    <script>
        $(document).ready(function(){
          $('input[name=pendingDate]').datepicker();         
         });
        function addTask()
    	{
        	var grid = $("#list");
    		var selectedDescription = $('#description').val();

			if(selectedDescription.length == 0)
			{
				alert ('Description field is empty..');
				return false;
			} 
    		grid.jqGrid('setGridParam',
   						{ 		url :"saveData",
      							postData :  
      							{
 						 	description : $('#description').val(),
 						 	pendingDate : $('#pendingDate').val()
      							},
      							loadtext : 'Updating Status. Please wait..!',
      						
  						}).trigger('reloadGrid'); 
    		$('#description').val('');
    		$('#pendingDate').val('');
    		
    	}
        function deleteTask()
    	{
        	var grid = $("#list");
    		var selRowIds = grid.jqGrid('getGridParam', 'selarrrow');    		

			if(selRowIds.length == 0)
			{
				alert ('Select at least element..');
				return false;
			}
			var selRowArr = jQuery("#list").getGridParam('selarrrow');
			var selectedAppIds = [];
        	for(var i=0;i<selRowArr.length;i++){
        		var celValue =  $('#list').jqGrid('getCell', selRowArr[i], 'taskId');
        		selectedAppIds.push(celValue);
        	}
    		grid.jqGrid('setGridParam',
   						{ 		url :"deleteTask",
      							postData :  
      							{
 						 	taskIdList : selectedAppIds.toString(),
      							},
      							loadtext : 'Updating Status. Please wait..!',
      						
  						}).trigger('reloadGrid'); 
    	}
        function markCompleted()
    	{
        	var grid = $("#list");
    		var selRowIds = grid.jqGrid('getGridParam', 'selarrrow');

			if(selRowIds.length == 0)
			{
				alert ('Select at least element..');
				return false;
			}

			var selRowArr = jQuery("#list").getGridParam('selarrrow');
        	var selectedAppIds = [];
        	for(var i=0;i<selRowArr.length;i++){
        		var celValue =  $('#list').jqGrid('getCell', selRowArr[i], 'taskId');
        		selectedAppIds.push(celValue);
        	}
    		grid.jqGrid('setGridParam',
   						{ 		url :"changeStatus",
      							postData :  
      							{
      								taskIdList :selectedAppIds.toString()
      							},
      							loadtext : 'Updating Status. Please wait..!',
      						
  						}).trigger('reloadGrid'); 
    	}
    </script>
    <div class="headingLogin">
        <h2>Welcome</h2>
        <p>To Your <strong>To Do</strong>, Task Manager</p>
    </div>
    <fieldset>
        <table>
            <tr>
                <td class="lbl">
                    Add Task
                </td>
            </tr>
            <tr>
                <td class="lbl">
                    Description
                </td>
                <td>
                    <textarea rows="2" cols="15" name="description" id="description" class="inputTextAreaLong"></textarea>
                </td>
            </tr>
            <tr>
                <td class="lbl">
                    Panding Date
                </td>
                <td>
                    <div class="fl"> From <input type="text" name="pendingDate" value="" id="pendingDate"> &nbsp;
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                <div style="margin-top:10px;">
                    <input type="button" name="buttonAction" id="buttonAction" value="Add" class="smallButton" onclick="addTask()">
                </div>
                </td>
            </tr>
        </table>
    </fieldset>
    <div><br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <table id="list">
            <tr>
                <td />
            </tr>
        </table>
        <div id="pager"></div>
        <div style="margin-top:10px;">
                    <input type="button" name="markButton" id="markButton" value="Mark Completed" class="smallButton" onclick="markCompleted()">
                    <input type="button" name="deleteButton" id="deleteButton" value="Delete Task" class="smallButton" onclick="deleteTask()">
        </div>
    </div>
    <script src="resources/js/jquery-1.11.1.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
    <script src="resources/js/i18n/grid.locale-en.js"></script>
    <script src="resources/js/jquery.jqGrid.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
         	$("#list").jqGrid({
                        url : "loadData",
                        datatype : "json",
                        mtype : 'POST',
                        colNames : [ 'Task Id','Pednding Date','Description','Status'],
                        colModel : [ {
                                name : 'taskId',
                                index : 'taskId',
                                search :false,
                                width : 150
                        }, {
                                name : 'pendingDate',
                                index : 'pendingDate',
                                width : 150,
                                search :false,
                                editable : false
                        }, {
                                name : 'taskDescription',
                                index : 'taskDescription',
                                width : 550,
                                search :false,
                                editable : false
                        },{
                            name : 'taskStatus',
                            index : 'taskStatus',
                            width : 550,
                            stype: 'select',
                            searchoptions: {value:":--;0:PENDING;1:COMPLETED",clearSearch : false},
                            editable : false
                    }],
                        pager : '#pager',
                        rowNum : 10,
                        id : 'taskId',
                        height: 'auto',
                        rowList : [ 10 ],
                        sortname : 'invid',
                        sortorder : 'desc',
                        viewrecords : true,
                        gridview : true,
                        multiselect: true,
                  		multiboxonly: false,
                        caption : 'To Do Schedule',
                        jsonReader : {
                                repeatitems : false,
                        }
                });
                jQuery("#list").jqGrid('navGrid', '#pager', {
                        edit : false,
                        add : false,
                        del : false,
                        search : false
                });
                
                jQuery("#list").jqGrid('filterToolbar', { stringResult: true, searchOnEnter: false, defaultSearch: "cn" });
                   
                $('#showSelected').on('click',function(){
                	
                	var selRowArr = jQuery("#list").getGridParam('selarrrow');
                	var selectedAppIds = [];
                	for(var i=0;i<selRowArr.length;i++){
                		var celValue =  $('#list').jqGrid('getCell', selRowArr[i], 'alias');
                		selectedAppIds.push(celValue);
                	}
                	alert(selectedAppIds);
                	$('#list').trigger( 'reloadGrid' );               	
         		
                });                	
         });       
    </script>
</body>

</html>