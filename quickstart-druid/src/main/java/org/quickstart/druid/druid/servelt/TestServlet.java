package org.quickstart.druid.druid.servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quickstart.druid.druid.model.TableOperator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TestServlet extends HttpServlet {
	private TableOperator operator;
	

	@Override
	public void init() throws ServletException {
		
		super.init();
		 ServletContext servletContext = this.getServletContext();   
         
         WebApplicationContext ctx
         	= WebApplicationContextUtils.getWebApplicationContext(servletContext);
         operator = (TableOperator)ctx.getBean("SpringTableOperatorBean");
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getSession().setAttribute("username", "myname");
		boolean createResult = false;
		boolean insertResult = false;
		boolean dropResult = false;
		
		try {
			operator.createTable();
			createResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (createResult) {
			try {
				operator.insert();
				insertResult = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				operator.tearDown();
				dropResult = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		out.println("{'createResult':"+createResult+",'insertResult':"
				+insertResult+",'dropResult':"+dropResult+"}");
		
		out.flush();
		out.close();
	}

}
