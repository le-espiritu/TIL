# Tab UI 실습



## Tab UI를 만들기 위한 HTML과 CSS 구조 전략



### 자주 볼수 있는 Tab UI의 형태

<img width="752" alt="3-6-1_Tab_UI_image" src="https://user-images.githubusercontent.com/88477839/160245921-323c2fe5-7302-4fa7-833b-493a06b3386e.png">



### 실습

~~~css
/*  tabui.css */

h2{text-align: center;}
h2,h4{
  margin:0px;
}
.tab{
  width:600px;
  margin:0px auto;
}
.tabmenu{
  background-color: bisque;
}
.tabmenu > div{
  display: inline-block;
  width: 146px;
  text-align : center;
  height : 50px;
  line-height: 50px;
  cursor: pointer;
}
.content{
  padding:5%;
  background-color: antiquewhite;
}
~~~

~~~html
<html>
  <head>
    <link rel="stylesheet" href="tabui.css"
  </head>
  <body>
    <h2>TAB UI TEST</h2>
    
    <div class="tab">
      <div class="tabmenu"> <!-- div대신 nav 쓸 수 있음 또는 ul li 사용 -->
        <div>crong</div>
        <div>jk</div>
        <div>pobi</div>
        <div>honux</div>
      </div>
      <section class="content">
      	<h4>hello jk</h4>
        <p>golf, facebook</p>
      </section>
    </div>
    
    <script src="tabuiJS.js"></script>
    
    <script id="tabcontent" type="my-template">
    	<h4>hello {name}</h4>
    	<p>{faborites}</p>
    </script>
    
  </body>
</html>
~~~



## Tab UI에 생명 불어넣기

~~~javascript
//tabuiJs.js

function makeTemplate(data, clickedName){
  var html = document.getElementById("tabcontent").innerHTML;
  var resultHTML = "";
  
  for(var i =0, len=data.length; i<len; i++){
		if(data[i].name === clickedName){
      resultHTML = html.replace("{name}",data[i].name)
    									 .replace("{favorites}",data[i].favorites.join(" ")); 
      break;
    }
  }
  document.querySelector(".content").innerHTML = resultHTML;
}

function sendAjax(url, clickedName){
  var oReq = XMLHttpRequest();
  oReq.addEventListener("load",function(){
    var data = JSON.parse(oReq.responseText);
    makeTemplate(data, clickedName);
  });
  oReq.open("GET", url);
  oReq.send();
}

var tabmenu = document.querySelector(".tabmenu");
tabmenu.addEventListener("click", function(evt){
  sendAjax("./json.txt", evt.target.innertext);
});
~~~

~~~
<jostn.txt>

[
	{
	"name":"crong",
	"favorites" : ["golf","facebook"]
	},
	{
	"name":"jk",
	"favorites" : ["soccer","apple"]
	},
	{
	"name":"honux",
	"favorites" : ["game","orange"]
	},
	{
	"name":"pobi",
	"favorites" : ["book","youtube"]
	}
]
~~~



