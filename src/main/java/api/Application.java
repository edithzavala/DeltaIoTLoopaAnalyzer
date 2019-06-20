package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.loopa.analyzer.Analyzer;
import org.loopa.analyzer.IAnalyzer;
import org.loopa.comm.message.IMessage;
import org.loopa.comm.message.LoopAElementMessageCode;
import org.loopa.comm.message.Message;
import org.loopa.comm.message.MessageType;
import org.loopa.comm.message.PolicyConfigMessageBody;
import org.loopa.element.functionallogic.enactor.IFunctionalLogicEnactor;
import org.loopa.element.functionallogic.enactor.analyzer.AnalyzerFunctionalLogicEnactor;
import org.loopa.element.sender.messagesender.IMessageSender;
import org.loopa.policy.IPolicy;
import org.loopa.policy.Policy;
import org.loopa.recipient.Recipient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import logic.AnalyzerMessageSender;
import logic.FunctionalLogicEnactorManager;
import model.DeltaIoTAnalyzerSenderPolicy;

@SpringBootApplication
public class Application {
	public static String ANALYZER_ID;
	public static IAnalyzer a;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ANALYZER_ID = args[0];
		String policyFilePath = "/tmp/config/" + args[1];

		/** Init policy (MANDATORY) **/
		Map<String, String> initPolicy = new HashMap<>();
		initPolicy.put(LoopAElementMessageCode.MSSGINFL.toString(), "1");
		initPolicy.put(LoopAElementMessageCode.MSSGINAL.toString(), "2");
		initPolicy.put(LoopAElementMessageCode.MSSGADAPT.toString(), "3");
		initPolicy.put(LoopAElementMessageCode.MSSGOUTFL.toString(), "4");
		initPolicy.put(LoopAElementMessageCode.MSSGOUTAL.toString(), "5");

		/****** Create analyzer ***/
		// System.out.println(policyContent.toString());
		IMessageSender sMS = new AnalyzerMessageSender();
		IFunctionalLogicEnactor flE = new AnalyzerFunctionalLogicEnactor(new FunctionalLogicEnactorManager());
		IPolicy ap = new Policy(ANALYZER_ID, initPolicy);
		a = new Analyzer(ANALYZER_ID, ap, flE, sMS);
		a.construct();

		/***** Add logic policies ****/
		String policyString = "";
		Map<String, String> policyContent = new HashMap<>();
		try {
			policyString = new String(Files.readAllBytes(Paths.get(policyFilePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		policyContent.put("config", policyString);
		PolicyConfigMessageBody messageContentFL = new PolicyConfigMessageBody(a.getFunctionalLogic().getComponentId(),
				policyContent);
		IMessage mssgAdaptFL = new Message(ANALYZER_ID, a.getReceiver().getComponentId(), 2,
				MessageType.REQUEST.toString(), messageContentFL.getMessageBody());
		a.getReceiver().doOperation(mssgAdaptFL);

		/*** Add recipients and corresponding policies ****/
		DeltaIoTAnalyzerSenderPolicy analyzerRecepients;
		ObjectMapper mapper = new ObjectMapper();
		try {
			analyzerRecepients = mapper.readValue(policyString, DeltaIoTAnalyzerSenderPolicy.class);
			analyzerRecepients.getRecipients().forEach(recepient -> {
				a.addElementRecipient(
						new Recipient(recepient.getId(), recepient.getTypeOfData(), recepient.getRecipient()));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
