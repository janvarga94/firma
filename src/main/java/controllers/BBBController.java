package controllers;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SoapClientConfig;

import client.BankOrderClient;
import io.spring.guides.gs_producing_web_service.ImportNalogZaPlacanjeRequest;
import io.spring.guides.gs_producing_web_service.ImportNalogZaPlacanjeResponse;

@RestController
public class BBBController {

	
	private String MyAccount = "moj racun";
	private String MyModel = "43";
	private String MyNumber = "asdfasdf";
	
	private String SendingMoneyToWho = "zarko";
	
	
	public BBBController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value = "/api/hi", 
			method = RequestMethod.GET)
	public ResponseEntity getAllBankAccounts() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
	    BankOrderClient wsclient = /*new BankOrderClient();*/ context.getBean(BankOrderClient.class);
	    wsclient.setDefaultUri("http://localhost:10011/ws");
	    ImportNalogZaPlacanjeRequest request = new ImportNalogZaPlacanjeRequest();
	    
	    
	    
	    request.setAmount(2);
	    Date date = new Date();
	    try {
	    	XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			request.setBankOrderDate(now);
			request.setCurrensyDate(now);
			request.setOrderDate(now);
	    } catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    request.setDebtor(MyAccount);
	    request.setDirection("A");
	    request.setFirstAccount(SendingMoneyToWho);
	    request.setFirstModel("as");
	    request.setFirstNumber("+12312321");
	    request.setPurposeOfPayment("just because");
	    request.setRecipient("to who");
	    request.setSecondAccount("2nd acc");
	    request.setSecondModel("12");
	    request.setSecondNumber("4322434");
	    request.setUrgently(false);
	//    request
	    
	    

	   
	    	ImportNalogZaPlacanjeResponse resp = wsclient.getBeer(request);
	    	System.out.println(resp.getCreatedBankOrderId());
	    
	    
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
