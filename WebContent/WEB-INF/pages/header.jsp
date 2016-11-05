<header>
	<div class="container">
	<%
		Integer idUser = (Integer)session.getAttribute("idUser");
    	if (idUser != null)
    	{
     %>
     		<a href="/JWeb/logged" title="JWeb Project - Home">JWeb Project</a>
        	<div class="logged">
        <%
        	Boolean admin = ((Boolean)session.getAttribute("admin")).booleanValue();
	    	if (admin == true) { %>
	    		<a href="/JWeb/news">Manage news</a>
	    		<a href="/JWeb/user">Manage users</a>	
	    <% } 
	    	else { %>
	    		<a href="/JWeb/product">Product</a>
	    <% } %>
        	<a href="logout">Logout</a>
        	</div>
        	<%
    	}
    	else
    	{
        	%>
        	<a href="/JWeb/" title="JWeb Project - Home">JWeb Project</a>
        	<form class="login" method="POST" action="connexion">
        		<input type="email" name="login" placeholder="Email" required />
				<input type="password" name="password" placeholder="Password" required />
				<input class="submit" type="Submit" value="Sign in">
			</form>
        	<%
    	}
	%>
	<div class="clear"></div>
	</div>
</header>