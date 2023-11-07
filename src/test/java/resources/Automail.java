package resources;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.io.Files;


public class Automail {
	
	  private static WebDriver driver = null;

	  private boolean remote=false;
	  // private String proxy="http://localhost:3128";
	  private String proxy="";

	public void Gen_Reports() throws InterruptedException, IOException, org.json.simple.parser.ParseException, AddressException, MessagingException {


		DateFormat dateFormat1 = new SimpleDateFormat("d-MMM-yy");
		Date date1 = new Date();
		System.out.println(dateFormat1.format(date1));

		
		
	      ChromeOptions options = new ChromeOptions();
		//FirefoxOptions options = new FirefoxOptions();
	    // Set options for ChromeDriver
		    options.addArguments("--lang=en");
		    options.addArguments("--headless");
		    options.addArguments("--remote-allow-origins=*");
	    // System Property for Chrome Driver
	    if(remote) {
	      String nodeURL = "http://hub:4444/wd/hub/";

	      options.addArguments("--whitelisted-ips=''");
	      options.addArguments("--no-sandbox");
	      options.addArguments("--disable-dev-shm-usage");
	      options.addArguments("--ignore-certificate-errors");
	      if(proxy != null && proxy.length() != 0) {
	        options.addArguments("--proxy-server=" + proxy);
	      }
	  
	      // Launch on remote (selenium chrome container launched by CI) the chrome driver 
	      driver = new RemoteWebDriver(new URL(nodeURL), options ); 
	    }else{

	    	//System.setProperty("webdriver.gecko.driver", "src/test/resources/Drivers/geckodriver.exe");   
	    	System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
	      // Launch on local the chrome driver 
	      //driver = new FirefoxDriver(options);
	      driver = new ChromeDriver(options);
	    }
		
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();

	    driver.get("file:///" + System.getProperty("user.dir") + "/test-reports " + dateFormat1.format(date1) + "/test-output/htmlreports/EDHAPIReport.html");
		driver.navigate().refresh();
		Thread.sleep(5000);

		File screen2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("table screenshot done");

		String currentDir1 = System.getProperty("user.dir");
		Files.copy(screen2, new File(currentDir1 + "/ReportImage/" + "piechart" + ".png"));

		System.out.println("copied");
		Thread.sleep(2000);
		System.out.println("Start mail");
		
		String to ="pooja.gulia@orange.com, vaishnavi.agarkathe@orange.com";
		//String to = "gaurav2.kumar@orange.com,sakshi2.sharma@orange.com,rajeev.rai@orange.com,mukesh.gupta.ext@orange.com,imane.laghmam.ext@orange.com,imane.mamouni@orange.com,rajendra.kumar@orange.com";

		String from = "pooja.gulia@orange.com";
		final String username = "GTCG6683@orange.com";
		final String password = "Sunshine@23";
//		String host = "outlook.office365.com";
		 String host = "exchange-eme6.itn.ftgroup";
//		String host = "smtp1.smtpft.francetelecom.fr";

//#####################################################################

		String subject ="EDH Catalog Automated API Report";
		StringBuffer body = new StringBuffer("<html><body><p><font color=\"blue\">Hello  All,<br><br>");

		body.append("Please find below Automated Test results for EDH Application : <br><br>");
		// body.append("Federated Inventory Application URL used : <font color=\"orange\"> FEI </font> <font color=\"#ccad00;\"> http://feigui.fei-int1.caas-cnp-apps-v2-ka.com.intraorange/guardian-agent/home </font> <br><br>");
//		body.append("URL used : <font color=\"#ccad00;\"> http://feigui.fei-int1.caas-cnp-apps-v2-ka.com.intraorange/guardian-agent/home </font> <br><br>");
//		body.append("The detailed artifacts are available at : "+"https://gitlab.tech.orange/finv/finv-automation/-/jobs"+"<br><br>");
		body.append("Logs are available in PDF & HTML format. </font></p>");
		body.append("<img style=\"border:10px solid black\" src=\"cid:image\" width=\"90%\" height=\"60%\"/><br>");

		String name = null;
		String status = null;
		Long duration = null;
		String nameofsce = null;
		String error_message=null;
		NumberFormat formatter = new DecimalFormat("#0.00");
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(System.getProperty("user.dir") + "/target/" + "cucumber" + ".json");

		Object object = jsonParser.parse(reader);
		JSONArray jsonArr = (JSONArray) object;
		
		for (int l = 0; l < jsonArr.size(); l++) {
			
			JSONObject element = (JSONObject) jsonArr.get(l);
			
			JSONArray jsPath = (JSONArray) element.get("elements");
			String feature = (String) element.get("name");
			
			JSONArray tag = (JSONArray) element.get("tags");

		
		String arr[] = new String[jsPath.size()];

		/*body.append("<table>");
		body.append("<tr>");
		body.append("<td><p><font color=\"orange\">" + feature+ "</font></p></td>");
		body.append("</tr>");
		body.append("</table><br>");*/
		String feture = (String) element.get("name");

		body.append("<p><font color=\"blue\"> Feature Name : " +feture+ "</font></p>");
		
		for (int i = 0; i < jsPath.size(); i++) {
			int sl=1;
			JSONObject steps = (JSONObject) jsPath.get(i);
			JSONArray array = (JSONArray) steps.get("steps");
			String n = (String) steps.get("name");
			body.append("<p><font color=\"blue\"> Scenario Name : " +n+ "</font></p><br>");
			System.out.println(n);
			body.append("<table>");
			body.append("<style>");
			body.append("table,th,td{border:1px dotted black;border-collapse:collapse;padding:5px;font-size:15px}");
			body.append("th{background:purple}");
			body.append("</style>");

			body.append("<tr>");
			body.append("<th>Sl No.</th>");
			body.append("<th>TestCaseExecuted</th>");
			body.append("<th>Status</th>");
			// body.append("<th>TimeStamp</th>");
	// body.append("<th>TestSuiteName</th>");

			body.append("<th>ResponseTime</th>");
			// body.append("<th>Jira Bug ID</th>");
			body.append("</tr>");
			for (int j = 0; j < array.size(); j++) {
				JSONObject names = (JSONObject) array.get(j);
				name = (String) names.get("name");

				JSONObject res = (JSONObject) names.get("result");
				double Sec = 0.001;
				for (int k = 0; k < res.size(); k++) {
					
					error_message = (String) res.get("error_message");
					if(error_message!=null) {
						error_message = (String) res.get("error_message");
					}
					
					
					status = (String) res.get("status");
					if ((Long) res.get("duration") != null) {
						duration = (Long) res.get("duration");
						Sec = ((duration) / 1000451578.37);
					}

				}
			
				body.append("<tr>");
				if (status.equals("passed")) {
					body.append("<p><font color=\"green\">");
					body.append("<td align=\"center\">" + (sl++) + "</td>");
					body.append("<td>" + name + "</td>");
					body.append("<td align=\"center\">" + status + "</td>");
					body.append("<td align=\"center\">" + formatter.format(Sec) + " Sec" + "</td>");
					// body.append("<td></td>");
					// body.append("<td></td>");

					body.append("</p></font>");
				} else if (status.equals("failed")) {
					body.append("<p><font color=\"red\">");

					// body.append("<td>" + TestTimeTest + "</td>");
					// body.append("<td>"+TestName+"</td>");
					body.append("<td>" + (sl++) + "</td>");
					body.append("<td><font color=\"red\">" + name + "<br/><br/>"+error_message+"</p></font></td>");
					body.append("<td>" + status + "</td>");
					body.append("<td>" + formatter.format(Sec) + " Sec" + "</td>");

					body.append("</p></font>");
				}

				else if (status.equals("skipped")) {
					body.append("<p><font color=\"blue\">");
					body.append("<td>" + (sl++) + "</td>");
					body.append("<td>" + name + "</td>");
					body.append("<td>" + status + "</td>");
					body.append("<td>" + formatter.format(Sec) + " Sec" + "</td>");
					body.append("</p></font>");
				}
				
			}
			body.append("</tr>");

			body.append("</table><br>");
//			if(i==0) {
//				body.append("<img style=\"border:10px solid black\" src=\"cid:image1\" width=\"65%\" height=\"14%\"/><br>" );
//
//			}
//			else if(i==1)
//			{
//				body.append("<img style=\"border:10px solid black\" src=\"cid:image2\" width=\"65%\" height=\"14%\"/><br>" );
//
//			}
		}
		}

		body.append("<p><font color=\"blue\">This is automatic report.</font></p><br><br><br>");
		body.append("</body></html>");

//################################################################################################

		// inline images
		Map<String, String> inlineImages = new HashMap<String, String>();
		// inlineImages.put("image1",
		// "C:\\Scripts\\CCR_AutomationSuite\\screenshot\\config.jpg");
		// inlineImages.put("image2", "C:\\Scripts\\CCR_AutomationSuite\\screenshot\\cases.jpg");
	    inlineImages.put("image", System.getProperty("user.dir")+ "/ReportImage/" + "piechart" + ".png");
	    
	    inlineImages.put("files", System.getProperty("user.dir") + "/test-reports " + dateFormat1.format(date1) + "/test-output/htmlreports/" + "EDHAPIReport.html");
	    
	    inlineImages.put("files", System.getProperty("user.dir") + "/test-reports " + dateFormat1.format(date1) + "/test-output/PdfReport/" + "Report.pdf");
	    
		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "false");
//		props.put("mail.smtp.host", host);
//		props.put("mail.debug", "true");
//		props.put("mail.smtp.port", 25);
//		props.put("mail.smtp.ssl.trust", host);
//		props.put("mail.user", username);
//		props.put("mail.password", password);
//		props.put("mail.smtp.starttls.required", "false");
//		// props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		// props.setProperty("java.net.preferIPv4Stack", "true");
//		props.setProperty("mail.smtp.starttls.enable", "false");
//		 props.put("mail.smtp.ssl.enable", "true");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", host);
		 props.put("mail.debug", "true");
		 props.put("mail.smtp.port", 25);
		 props.put("mail.smtp.ssl.trust", host);
		 props.put("mail.user", username);
		 props.put("mail.password", password);
		 props.put("mail.smtp.starttls.required", "true");
		 props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 // props.setProperty("java.net.preferIPv4Stack", "true");
		 props.setProperty("mail.smtp.starttls.enable", "true");

//creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		Session session = Session.getInstance(props, auth);
//creates a new e-mail message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject(subject);
		msg.setSentDate(new Date());

//creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(body.toString(), "text/html");
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

//adds inline image attachments

		// MimeBodyPart attachmentBodyPart= new MimeBodyPart();
		// DataSource source = new FileDataSource("C:\\Project\\Contract\\Report.zip");
		// // ex : "C:\\test.pdf"
		// attachmentBodyPart.setDataHandler(new DataHandler(source));
		// attachmentBodyPart.setFileName("Report.zip"); // ex : "test.pdf"

		// multipart.addBodyPart(attachmentBodyPart); // add the attachement part

		/*
		 * if (inlineImages != null && inlineImages.size() > 0) { Set<String> setImageID
		 * = inlineImages.keySet();
		 * 
		 * for (String contentId : setImageID) { MimeBodyPart imagePart = new
		 * MimeBodyPart(); imagePart.setHeader("Content-ID", "<" + contentId + ">");
		 * imagePart.setDisposition(MimeBodyPart.INLINE);
		 * 
		 * String imageFilePath = inlineImages.get(contentId); try {
		 * imagePart.attachFile(imageFilePath); } catch (IOException ex) {
		 * ex.printStackTrace(); }
		 * 
		 * multipart.addBodyPart(imagePart); } }
		 */

		if (inlineImages != null && inlineImages.size() > 0) {
			Set<String> setImageID = inlineImages.keySet();

			for (String contentId : setImageID) {
				MimeBodyPart imagePart = new MimeBodyPart();
				imagePart.setHeader("Content-ID", "<" + contentId + ">");
				imagePart.setDisposition(MimeBodyPart.INLINE);

				String imageFilePath = inlineImages.get(contentId);
				try {
					imagePart.attachFile(imageFilePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(imagePart);
			}
		}

		msg.setContent(multipart);
		Transport.send(msg);

		if(driver!=null)
		{
			driver.quit();
		}


	}

	
	  
	
	public static void main(String arg[]) throws AddressException, ParseException, InterruptedException, IOException,
			MessagingException, org.json.simple.parser.ParseException, java.text.ParseException {
		Automail automail = new Automail();
		automail.Gen_Reports();
	}
	 
	 

}
