var execAndDraw = function execAndDraw(formID, tableID){
	var request = new XMLHttpRequest();
	request.open('POST', "/sql2", true);
	request.setRequestHeader('Content-Type', 'text/html; charset=utf-8');
	request.responseType =	"json";
	request.addEventListener("readystatechange", () => {
		{
			if (request.readyState === 4 && request.status === 200) {
			  var result = request.response;
			  drawTableObj(tableID, result);
			}
		}
	});
	var query = document.getElementById(formID).value;
	request.send(query);
}

var drawTableObj = function drawTableObj(tableID, tableObj){
  var tableRef = document.getElementById(tableID);
  var headers = tableObj.headers;
  var headerRow = tableRef.insertRow(-1);
  for(var j=0; j<headers.length; j++){
	var headerCell = headerRow.insertCell(-1);
	headerCell.classList.add('col');
	var newText = document.createTextNode(headers[j]);
	headerCell.appendChild(newText);
  }
  var rows = tableObj.dataRows;
  for(var i=0; i<rows.length; i++){
	  var arrCell = tableObj.dataRows[i];
	  var newRow = tableRef.insertRow(-1);
	  for(var j=0; j<arrCell.length; j++){
		var newCell = newRow.insertCell(-1);
		newCell.classList.add('col');
		var newText = document.createTextNode(arrCell[j]);
		newCell.appendChild(newText);
	  }
  }
}
