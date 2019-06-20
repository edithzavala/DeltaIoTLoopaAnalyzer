package model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class MotesInfo {
	private int run;
	private Map<String, AnalyzerMote> motes;

	public int getRun() {
		return run;
	}

	public void setRun(int run) {
		this.run = run;
	}

	public Map<String, AnalyzerMote> getMotes() {
		return motes;
	}

	public void setMotes(Map<String, AnalyzerMote> motes) {
		this.motes = motes;
	}

}
