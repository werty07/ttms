<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
function checkpower1(n){
	if(UrlParm.parm("EmpPower")=="经理")
	{
		switch(n){
		case 1:window.location="../studio/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 2:window.location="../play/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 3:window.location="../schedule/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 4:window.location="../ticket/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		}
		
	}
	else
	{
		alert("抱歉，您没有此管理权限!");
		href="#";
	}
}
function checkpower2(){
	if(UrlParm.parm("EmpPower")=="经理")
	{
		window.location="../employee/eindex.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));
	}
	else
	{
		alert("抱歉，您没有此管理权限!");
		href="#";
	}
}
function checkpower3(n){
	if(UrlParm.parm("EmpPower")=="售票员"||UrlParm.parm("EmpPower")=="经理")
	{
		switch(n){
		case 1:window.location="../ticket/index_2.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 2:window.location="../ticket/index_1.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		}    		
	}
	else
	{
		alert("抱歉，您没有此管理权限!");
		href="#";
	}
}
function checkpower4(n){
	if(UrlParm.parm("EmpPower")=="会计"||UrlParm.parm("EmpPower")=="经理")
	{
		switch(n){
		case 1:window.location="../finance/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 2:window.location="../finance/index_1.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		case 3:window.location="../finance/index_2.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));break;
		}
	}
	else
	{
		alert("抱歉，您没有此管理权限!");
		href="#";
	}
}
function self(){
	window.location="../employee/index.html?&EmpId="+encodeURI(UrlParm.parm("EmpId"))
   		  +"&EmpName="+encodeURI(UrlParm.parm("EmpName"))
		  +"&EmpGender="+encodeURI(UrlParm.parm("EmpGender"))
		  +"&EmpTelnum="+encodeURI(UrlParm.parm("EmpTelnum"))
		  +"&EmpEmail="+encodeURI(UrlParm.parm("EmpEmail"))
		  +"&EmpPwd="+encodeURI(UrlParm.parm("EmpPwd"))
	      +"&EmpPower="+encodeURI(UrlParm.parm("EmpPower"));
}
</script>
</head>
<body>
</body>
</html>