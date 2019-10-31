
var execAndDraw = function execAndDraw(tableID, query){
	var request = new XMLHttpRequest();
	request.open('POST', "/sql2", true);
	request.setRequestHeader('Content-Type', 'text/html; charset=utf-8');//'text/plain'); //'application/json');
	request.responseType =	"json";
	request.addEventListener("readystatechange", () => {
		{
			if (request.readyState === 4 && request.status === 200) {
			  var result = request.response;
			  drawTableObj(tableID, result);
			}
		}
	});
	console.log("query = " + query);
	//var query2 = "select * from DOCUMENT where ROWNUM <=3"
	var query2 = document.getElementById('form_firstTT').value;
	//var data = new FormData();
	//data.append('sqlQuery', query2);
	//request.send(data);
	request.send(query2);
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


var execAndDraw0 = function execAndDraw0(tableID, query){
	var request = new XMLHttpRequest();
	request.open('GET', "/sqlexec/");
	request.setRequestHeader('Content-Type', 'application/json');
	request.responseType =	"json";
	request.addEventListener("readystatechange", () => {
		{
			if (request.readyState === 4 && request.status === 200) {
			  var result = request.response;
			  drawTableObj(tableID, result);
			}
		}
	});
	request.send();
}