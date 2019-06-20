package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.loopa.comm.message.IMessage;
import org.loopa.comm.message.LoopAElementMessageBody;
import org.loopa.comm.message.LoopAElementMessageCode;
import org.loopa.comm.message.Message;
import org.loopa.comm.message.MessageType;
import org.loopa.element.functionallogic.enactor.analyzer.IAnalyzerFleManager;
import org.loopa.generic.element.component.ILoopAElementComponent;
import org.loopa.policy.IPolicy;
import org.loopa.policy.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.AnalyzerLink;
import model.AnalyzerMote;
import model.DeltaIoTAnalyzerFLPolicy;
import model.LogicMessage;
import model.MotesInfo;

public class FunctionalLogicEnactorManager implements IAnalyzerFleManager {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	private IPolicy allPolicy = new Policy(this.getClass().getName(), new HashMap<String, String>());
	private Map<Integer, Map<String, AnalyzerMote>> motesInfoPerRun = new HashMap<>();
	private DeltaIoTAnalyzerFLPolicy policy;
	private ILoopAElementComponent owner;

	@Override
	public void setConfiguration(Map<String, String> config) {
		LOGGER.info("Component (re)configured");
		if (config.containsKey("config")) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				this.policy = mapper.readValue(config.get("config"), DeltaIoTAnalyzerFLPolicy.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.allPolicy.update(new Policy(this.allPolicy.getPolicyOwner(), config));
	}

	@Override
	public void processLogicData(Map<String, String> data) {
		LOGGER.info("Data received ");
		if (data.keySet().contains("moteInfo")) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				LogicMessage logicMessage = mapper.readValue(data.get("moteInfo"), LogicMessage.class);
				MotesInfo mInfo = mapper.readValue(logicMessage.getContent(), MotesInfo.class);
				//LOGGER.info("Run " + mInfo.getRun());
				if (motesInfoPerRun.get(mInfo.getRun()) == null) {
					motesInfoPerRun.put(mInfo.getRun(), mInfo.getMotes());
				} else {
					motesInfoPerRun.get(mInfo.getRun()).putAll(mInfo.getMotes());
				}
//				LOGGER.info("Knowledge " + motesInfoPerRun.toString());
				if (motesInfoPerRun.get(mInfo.getRun()).keySet().containsAll(this.policy.getMotes())) {
					if (analyzeLinkSettings(new ArrayList<>(motesInfoPerRun.get(mInfo.getRun()).values()))) {
						MotesInfo outInfo = new MotesInfo();
						outInfo.setRun(mInfo.getRun());
						outInfo.setMotes(motesInfoPerRun.get(mInfo.getRun()));
						sendDataToPlanner(mapper.writeValueAsString(outInfo));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean analyzeLinkSettings(List<AnalyzerMote> motes) {
		for (AnalyzerMote mote : motes) {
			for (AnalyzerLink link : mote.getLinks()) {
				if (link.getSNR() > 0 && link.getPower() > 0 || link.getSNR() < 0 && link.getPower() < 15) {
					return true;
				}
			}
			if (mote.getLinks().size() == 2) {
				if (mote.getLinks().get(0).getPower() != mote.getLinks().get(1).getPower())
					return true;
			}
		}
		return false;
	}

	private void sendDataToPlanner(String moteInfo) {
		LoopAElementMessageBody messageContent = new LoopAElementMessageBody("PLAN", moteInfo);
		messageContent.getMessageBody().put("contentType", "analysisData");
		String code = this.getComponent().getElement().getElementPolicy().getPolicyContent()
				.get(LoopAElementMessageCode.MSSGOUTFL.toString());
		IMessage mssg = new Message(this.owner.getComponentId(), this.allPolicy.getPolicyContent().get(code),
				Integer.parseInt(code), MessageType.REQUEST.toString(), messageContent.getMessageBody());
		((ILoopAElementComponent) this.owner.getComponentRecipient(mssg.getMessageTo()).getRecipient())
				.doOperation(mssg);
	}

	@Override
	public void setComponent(ILoopAElementComponent c) {
		this.owner = c;
	}

	@Override
	public ILoopAElementComponent getComponent() {
		return this.owner;
	}

}
