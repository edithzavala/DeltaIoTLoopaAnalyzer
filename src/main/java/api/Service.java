package api;

import java.util.HashMap;
import java.util.Map;

import org.loopa.comm.message.IMessage;
import org.loopa.comm.message.LoopAElementMessageCode;
import org.loopa.comm.message.Message;
import org.loopa.comm.message.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

	@PostMapping("/analyze")
	public ResponseEntity<String> createMonDataEntry(@RequestBody String motes) {
		if (motes != null) {
			Map<String, String> messageBody = new HashMap<>();
			messageBody.put("moteInfo", motes);
			IMessage motesInfoMssg = new Message("ANALYZE", Application.a.getElementId(),
					Integer.parseInt(Application.a.getElementPolicy().getPolicyContent()
							.get(LoopAElementMessageCode.MSSGINFL.toString())),
					MessageType.REQUEST.toString(), messageBody);
			Application.a.getReceiver().doOperation(motesInfoMssg);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
