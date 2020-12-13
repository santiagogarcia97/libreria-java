anychart.onDocumentReady(function() {
  var cats = document.getElementById('cats').value;
  var array = cats.split(", ");
  array.pop();
  var data = [];
  var dic = {};

  for(var i = 0; i < array.length; i++){
		if(array[i] in dic){
			dic[array[i]]++;
		}
		else{
			dic[array[i]] = 1;
		}
  }

  for(key in dic){
	data.push({x: key, value: dic[key]});
  }
  var chart = anychart.pie();
  chart.title("Cantidad de libros por categoria");
  chart.data(data);
  chart.sort("desc");
  chart.legend().position("right");
  chart.legend().itemsLayout("vertical");	
  chart.container('container');
  chart.draw();

});