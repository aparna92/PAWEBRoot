<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Search | INFORMATION RETRIEVAL SYSTEMS</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="" />
    <link href="css/home.css" rel="stylesheet" type="text/css" />
     <link href="css/dom.css" rel="stylesheet" type="text/css" />
      <link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
       <script type="text/javascript" src="./js/jquery-1.8.3.js" ></script>
         <script type="text/javascript" src="./js/jquery-ui.js" ></script>
       
     <link rel="stylesheet" type="text/css" href="css/jquery.jqChart.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqRangeSlider.css" />
    <script src="js/jquery.mousewheel.js" type="text/javascript"></script>
    <script src="js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
 
</head>
<style>

 
</style>
<script>
 
</script>
<body>
 <div id="page">
    <jsp:include page="header.jsp"></jsp:include>
                
        <div id="main">	
            <div id="sidebar">
   
     <jsp:include page="leftContent.jsp"></jsp:include>
     
            </div><!-- sidebar -->    	              
            <div id="content">
        <div id="contheader">
      
    
                    <h2>Reports</h2>
                      <div id="content2">	
                      <script> 
                      
                      function myProfile(){
                    	  
                    	  alert("Please relogin and try again");
                    	  
                      }
                      
                      function changePWd(){
                    	  
                     	  alert("Please relogin and try again");
                      }
 $(document).ready(function () {

	 
	 
	 var ad = ${res};
	 
	 
            var background = {
                type: 'linearGradient',
                x0: 0,
                y0: 0,
                x1: 0,
                y1: 1,
                colorStops: [{ offset: 0, color: '#d2e6c9' },
                             { offset: 1, color: 'white' }]
            };

            $('#jqChart').jqChart({
                title: { text: 'Pie Chart' },
                legend: { title: 'Authors' },
                border: { strokeStyle: '#6ba851' },
                background: background,
                animation: { duration: 1 },
                shadows: {
                    enabled: true
                },
                series: [
                    {
                        type: 'pie',
                        fillStyles: ['#418CF0', '#FCB441', '#E0400A', '#056492', '#BFBFBF', '#1A3B69', '#FFE382'],
                        labels: {
                            stringFormat: '%.1f%%',
                            valueType: 'percentage',
                            font: '15px sans-serif',
                            fillStyle: 'white'
                        },
                        explodedRadius: 10,
                        explodedSlices: [5],
                        data:  ad
                    }
                ]
            });

            $('#jqChart').bind('tooltipFormat', function (e, data) {
                var percentage = data.series.getPercentage(data.value);
                percentage = data.chart.stringFormat(percentage, '%.2f%%');

                return '<b>' + data.dataItem[0] + '</b><br />' +
                       data.value + ' (' + percentage + ')';
            });
        });
    </script>

 
 
    <div>
        <div id="jqChart" style="width: 500px; height: 300px;">
        </div>
    </div>
 


</div>
</div>


               </div><!-- content -->  
                                 
            <div class="clearing">&nbsp;</div> 
            
            <div id="footer">
                <jsp:include page="footer.jsp"></jsp:include>
               
            </div>
        </div><!-- main -->
    </div><!-- page -->



 



</body>

<script>
 

</script>
</html>