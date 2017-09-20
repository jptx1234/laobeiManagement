<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugin/jquery-confirm/jquery-confirm.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/plugin/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath }/plugin/jquery-confirm/jquery-confirm.min.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<style>
::-webkit-scrollbar {
               width: 6px;
               height: 10px;
               background-color: rgba(0, 0, 0, 0);
}
::-webkit-scrollbar-track {
               background-color: rgba(0, 0, 0, 0.1);
}
::-webkit-scrollbar-thumb {
               border-radius: 3px;
               background-color: rgba(0, 0, 0, 0.2);
               transition: all 0.4s ease;
               -moz-transition: all 0.4s ease;
               /* Firefox 4 */
               
               -webkit-transition: all 0.4s ease;
               /* Safari 和 Chrome */
               
               -o-transition: all 0.4s ease;
               /* Opera */
}
::-webkit-scrollbar-thumb:hover {
               border-radius: 3px;
               background-color: rgba(0, 0, 0, 0.4);
               transition: all 0.4s ease;
               -moz-transition: all 0.4s ease;
               /* Firefox 4 */
               
               -webkit-transition: all 0.4s ease;
               /* Safari 和 Chrome */
               
               -o-transition: all 0.4s ease;
               /* Opera */
}
body{
	background-color: rgba(0, 0, 0, 0);
}
.well{
	background-color: transparent;
	border: none;
}
h1{
	color: #fff;
}
.mainContent{
	background-color: rgba(255, 255, 255, 0.5);
	border-radius: 6px;
	padding: 20px;
}
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
    border-top: 1px solid #fff;
}
.table>thead>tr>th {
    border-bottom: 2px solid #ffffff;
}
.add-content{
    background-color: rgba(255, 255, 255, 0.71);
    border-radius: 6px;
    padding: 10px;
    margin-bottom: 6px;
}
.has-error{
	box-shadow: 0 0 40px 4px #ea5252;
}
</style>
<script>
alert = function(msg){
	$.alert({
		title: '警告',
		content: msg,
		type: 'red'
	});
};
function jqconfirm(msg, callback){
	$.confirm({
	    title: '是否确定',
	    content: msg,
	    type: 'blue',
	    typeAnimated: true,
	    buttons: {
	        yes: {
	            text: '确定',
	            btnClass: 'btn-blue',
	            action: callback
	        },
	        no: {
	        	text: '取消'
	        }
	    }
	});
}
function changeURLParam(destiny, par, par_value) {
    var pattern = par + '=([^&]*)';
    var replaceText = par + '=' + par_value;
    if (destiny.match(pattern)) {
        var tmp = '/\\' + par + '=[^&]*/';
        tmp = destiny.replace(eval(tmp), replaceText);
        return (tmp);
    } else {
        if (destiny.match('[\?]')) {
            return destiny + '&' + replaceText;
        } else {
            return destiny + '?' + replaceText;
        }
    }
    return destiny + '\n' + par + '\n' + par_value;
}
function toPage(page){
	window.location.href=changeURLParam(window.location.href, "currPage", page);
}
</script>