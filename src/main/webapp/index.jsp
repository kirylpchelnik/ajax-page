<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Такси</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>
<body>
<div id="data"></div>
<button id="btnAdd">Добавить</button>


<script type="text/javascript">
    function selectTaxi(id) {
        var url = "EditTaxiServlet?id=" + id;
        $(location).attr('href', url);
    }

    function deleteTaxi(id) {
        $('#data').load('ListTaxiServlet', {'button': 'delete', 'id': id}, function () {
        });
    }

    $(document).ready(function () {
        $('#data').load('ListTaxiServlet', '', function () {
        });
        $('#btnAdd').click(function () {
            var url = "EditTaxiServlet?id=-1";
            $(location).attr('href', url);
        });
    });
</script>
</body>
</html>