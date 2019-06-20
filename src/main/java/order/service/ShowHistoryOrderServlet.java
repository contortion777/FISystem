package order.service;

import order.java.MyOrder;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ShowHistoryOrderServlet")
public class ShowHistoryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ShowHistoryOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		////////////////////////////////////////// 上面這裡要填老闆端給的參數 決定要拿的菜單狀態 或是 拿到客人ID
		//String MyStatus ="已完成";

		MyOrder connection = new MyOrder();

		//JSONArray jsonArray = connection.getJson();
		try {
			connection.getDateOrder(year,month,day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(connection.getJson());
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
