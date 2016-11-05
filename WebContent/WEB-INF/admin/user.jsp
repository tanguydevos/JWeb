<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>JWeb - Manage users</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="../pages/header.jsp"%>
	<div id="page">
		<div class="success">
			<div class="container">
				<p>
					<% 
						String success = (String)request.getAttribute("success");
						if (success != null)
							out.print(success);
					%>
				</p>
			</div>
		</div>
		<div class="error">
			<div class="container">
				<p>
					<% 
						String error = (String)request.getAttribute("error");
						if (error != null)
							out.print(error);
					%>
				</p>
			</div>
		</div>
		<div class="container">
				<div class="area">
					<h1>List of users</h1>
					<form method="POST" action="updateUser">
					<table>
						<thead>
							<tr>
								<td>Id</td>
								<td>Status</td>
								<td>Firstname</td>
								<td>Lastname</td>
								<td>Email</td>
								<td>Admin Privileges</td>
								<td>Newsletter</td>
								<td>Delete</td>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<User> list = User.getUser();
								Integer i = 0;
								if (list != null)
									for (User user : list) {
										if (i % 2 != 0)
											out.println("<tr class='trprime'><td>" + user.getId() + "</td>");
										else
											out.println("<tr class='trsecond'><td>" + user.getId() + "</td>");
										if (user.getAdmin() == true)
											out.println("<td>Admin</td>");
										else
											out.println("<td>User</td>");

										out.println("<td>"+ user.getFirstname() + "</td>" + "<td>"
													+ user.getLastname() + "</td>" + "<td>"
													+ user.getEmail() + "</td>");
										
										if (user.getAdmin() == true)
											out.println("<td><input type='checkbox' name='admin' value='" + user.getId() + "' checked /></td>");
										else
											out.println("<td><input type='checkbox' name='admin' value='" + user.getId() + "' /></td>");
										
										if (user.getNewsletter() == true)
											out.println("<td><input type='checkbox' name='newsletter' value='" + user.getId() + "' checked /></td>");
										else
											out.println("<td><input type='checkbox' name='newsletter' value='" + user.getId() + "' /></td>");
										
										out.println("<td><input type='checkbox' name='delete' value='" + user.getId() + "' /></td></tr>");
										i++;
									}
							%>
						</tbody>
					</table>
					<input type="submit" class="submit" value="Save">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../pages/footer.jsp" %>
</body>
</html>