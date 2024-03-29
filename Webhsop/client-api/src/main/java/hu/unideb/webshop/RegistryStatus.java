package hu.unideb.webshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistryStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	public static List<String> statuses;
	/*
    NEW ("NEW"),
    INPROGRESS ("INPROGRESS"),
    NEEDMATERIAL("NEEDMATERIAL"),
    READYFORCREATE("READYFORCREATE"),
    READY("READY"),
    TRANSPORT("TRANSPORT");
    */
	static{
		statuses = new ArrayList<String>();
		statuses.add("NEW");
		statuses.add("READY");
		statuses.add("NEEDPRODUCT");
		statuses.add("TRANSPORT");
		statuses.add("DURINGTRANSPORT");
	}
	public static List<String> getStatuses() {
		return statuses;
	}
	public static void setStatuses(List<String> statuses) {
		RegistryStatus.statuses = statuses;
	}



    
}
