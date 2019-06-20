package logic;

import org.loopa.comm.message.IMessage;
import org.loopa.element.sender.messagesender.AMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.PlannerClient;

public class AnalyzerMessageSender extends AMessageSender {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	private PlannerClient pcli = new PlannerClient();

	@Override
	public void processMessage(IMessage mssg) {
		if (mssg.getMessageBody().get("type").equals("PLAN_Planner")) {
			pcli.requestPlanning((String) this.getComponent().getComponentRecipient("Planner").getRecipient(), mssg);
		}
	}
}
