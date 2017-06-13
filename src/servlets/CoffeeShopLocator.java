package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CoffeeShopLocator")
public class CoffeeShopLocator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoffeeShopLocator() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

		super.init(config);

		List<StarbucksCoffee> entries = new ArrayList<StarbucksCoffee>();

		int counter = 0;
		int c = 0;
		ServletContext context = getServletContext();
		String fileName = context.getRealPath("/WEB-INF/starbucks.csv");

		String line = null;
		String temp[] = new String[10];

		try {

			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(",");
				System.out.println(temp[0]);
				System.out.println(temp[1]);
				System.out.println(temp[2]);
				System.out.println(temp[3]);
				System.out.println(temp[4]);
				c++;
				System.out.println(line);
				if (temp.length == 5) {
					String[] total = temp[2].split(":");

					if (!temp[temp.length - 1].contains(";")) {

						String[] zip = temp[4].trim().split("\\s+");

						if (zip.length == 1) {

							entries.add(new StarbucksCoffee(Double
									.parseDouble(temp[0]), Double
									.parseDouble(temp[1]), total[1], temp[3],
									zip[0]));
						}

						else {
							if (zip[1].length() == 5 || zip[1].length() == 10) {
								entries.add(new StarbucksCoffee(Double
										.parseDouble(temp[0]), Double
										.parseDouble(temp[1]), total[1],
										temp[3], zip[1]));
							}

						}

					} else {
						String[] zipp = temp[4].split(";");
						String[] zip = zipp[0].trim().split("\\s+");

						if (zip.length == 1) {

							entries.add(new StarbucksCoffee(Double
									.parseDouble(temp[0]), Double
									.parseDouble(temp[1]), total[1], temp[3],
									zip[0], zipp[1]));
						}

						else {
							if (zip[1].length() == 5 || zip[1].length() == 10) {
								entries.add(new StarbucksCoffee(Double
										.parseDouble(temp[0]), Double
										.parseDouble(temp[1]), total[1],
										temp[3], zip[1], zipp[1]));
							}

						}
					}
					counter++;
				} else {
					System.out.println("invalid input");
				}

			}

			bufferedReader.close();
			System.out.println(c);
			System.out.println(counter);
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}

		getServletContext().setAttribute("entries", entries);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>");
		out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css'>");
		out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
		out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>");
		out.println("<title>");
		out.println("CoffeeShopLocator");
		out.println("</title>");
		out.println("</head>");
		out.println("<body style='font-family:verdana' height: 100% margin:0 padding: 0 width: 100% text-align: center vertical-align: middle>");
		out.println("<center><h2>STARBUCKS COFFEE SEARCH</h2>");
		out.println("<form action='CoffeeShopLocator' method='post'>");
		out.println("<select id='type' name='type'>");
		out.println("<option>Select</option>");
		out.println("<option value='location'>Location</option>");
		out.println("<option value='city'>City</option>");
		out.println("<option value='zipcode'>Zipcode</option>");
		out.println("</select>");
		out.println("Search");
		out.println("<input type='text' name='query'>");
		out.println("<input type='submit'class='btn btn-primary btn-info' name='submit' value='Search'>");
		out.println("</center>");
		out.println("</form>");
		out.println("</body></html>");

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<StarbucksCoffee> entries1 = (List<StarbucksCoffee>) getServletContext()
				.getAttribute("entries");

		String value = request.getParameter("type");
		String search = request.getParameter("query");

		PrintWriter out = response.getWriter();

		out.println("<html><head>");
		out.println(" <meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>");
		out.println(" <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>");
		out.println(" <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>");
		out.println("<title>CoffeeShopLocator</title>");
		out.println("</head><body style='background-color:lightred;font-family:verdana;'>");
		out.println("<h1>SEARCH RESULTS</h1>");
		out.println("<table id='results' style='width:100%' border='5'>");
		out.println("<tr><th>CITY</th><th>LOCATION</th><th>ZIPCODE</th><th>PHONE NUMBER</th></tr>");

		for (StarbucksCoffee sc : entries1) {
			System.out.println(sc.toString());
		}
		if (value.equals("location")) {

			for (StarbucksCoffee sc : entries1) {

				String[] data = search.split(",");

				double total = Math
						.acos((Math.cos(Double.parseDouble(data[0])) * Math
								.cos(sc.getLatitude()))
								+ (Math.sin(Double.parseDouble(data[0])) * Math
										.sin(sc.getLatitude()))
								* (Math.cos(Double.parseDouble(data[1])
										- sc.getLongitude())));
				total = total * 3959;

				System.out.println(total);

				if (total <= 10) {
					out.println("<tr>");
					out.println("<td>" + sc.getCity() + "</td>");
					out.println("<td>" + sc.getLocation() + "</td>");
					out.println("<td>" + sc.getZip() + "</td>");
					out.println("<td>" + sc.getPhone() + "</td>");
					out.println("</tr>");
				}
			}
		} else if (value.equals("city")) {

			for (StarbucksCoffee sc : entries1) {

				if (sc.getCity().trim().toLowerCase()
						.contains(request.getParameter("query").toLowerCase())) {
					out.println("<tr>");
					out.println("<td>" + sc.getCity() + "</td>");
					out.println("<td>" + sc.getLocation() + "</td>");
					out.println("<td>" + sc.getZip() + "</td>");
					out.println("<td>" + sc.getPhone() + "</td>");
					out.println("</tr>");

				}

			}
		}

		else if (value.equals("zipcode")
				&& (request.getParameter("query").length() == 5 || request
						.getParameter("query").length() == 10)) {

			for (StarbucksCoffee sc : entries1) {

				if (sc.getZip().equals(request.getParameter("query"))) {

					out.println("<tr><td>" + sc.getCity() + "</td>");
					out.println("<td>" + sc.getLocation() + "</td>");
					out.println("<td>" + sc.getZip() + "</td>");
					out.println("<td>" + sc.getPhone() + "</td></tr>");
				}

			}
		}

		out.println("</table>");
		out.println("</ br>");
		out.println("<a href='CoffeeShopLocator'> <input type= 'submit' value='Search Again' /></a>");
		out.println("</body></html>");
	}

}