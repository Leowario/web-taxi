<%@ page import="com.webtaxi.servlets.CustomerParser" %>
<%@ page import="com.webtaxi.users.Customer" %>
<%@ page import="static com.webtaxi.sql.SQLCustomerCommandExecutor.selectCustomerByLoginAndPassword" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your profile</title>
    <link rel="stylesheet" href="customerProfileStyle.css">
</head>
<body>
<%--TODO--%>
<%
    Cookie[] cookies = request.getCookies();
    Customer customer;
    Optional<Customer> optional = CustomerParser.getCustomer(cookies);
    if (optional.isPresent()) {
        customer = optional.get();
    } else {
        out.println("<h1>Internal error: No customer from cookie found <h1>");
        throw new IllegalArgumentException();
    }
%>
<div class="content">
    <p>First Name : <%= customer.getFirstName()%>
    </p>
    <p>Last Name : <%=customer.getLastName()%>
    </p>
    <p>Login : <%= customer.getLogin()%>
    </p>
    <p>Rating : <%=customer.getRating()%>
    </p>
</div>
</body>
</html>
