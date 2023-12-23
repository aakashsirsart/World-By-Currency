<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List" %>
<%@ page import="com.as.wbc.model.ImageModel" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>


body {
	font-family: Arial;
}

* {
	box-sizing: border-box;
}

form.example input[type=text] {
	padding: 10px;
	font-size: 17px;
	border: 1px solid grey;
	float: left;
	width: 80%;
	background: #f1f1f1;
}

form.example button {
	float: left;
	width: 20%;
	padding: 10px;
	background: #2196F3;
	color: white;
	font-size: 17px;
	border: 1px solid grey;
	border-left: none;
	cursor: pointer;
}

form.example button:hover {
	background: #0b7dda;
}

form.example::after {
	content: "";
	clear: both;
	display: table;
}
</style>
</head>
<body>
	</br>
	</br>
	</br>
	<form class="example" action="search" method="post"
		style="margin: auto; max-width: 800px">
		<input type="text" placeholder="Search.." name="currencyName">
		<button type="submit">
			Search <i class="fa fa-search"></i>
		</button>
		</br> </br> </br>
		<h2>Country Information</h2>
    <table border="1" style="width:100%">
        <thead>
            <tr>
                <th>Country</th>
                <th>Capital</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
                <% List<ImageModel> countries = (List<ImageModel>) request.getAttribute("countries");
                if (countries != null && !countries.isEmpty()) {
                    for (com.as.wbc.model.ImageModel country : countries) { %>
                        <tr>
                            <td><%= country.getCountryName() %></td>
                            <td><%= country.getCountryCapital() %></td>
                            <td><img src="<%= country.getCountryImage() %>" alt="<%= country.getCountryName() %>"></td>
                        </tr>
                    <% }
                } else { %>
                    <tr>
                        <td colspan="3">No countries found</td>
                    </tr>
                <% } %>
            </tbody>
    </table>
	</form>
</body>
</html>
