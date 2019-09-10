package Constant;

public enum Request {

	LOGIN("0"),
	RESPONSE("1"),
	GETCONNECTIONSCHAT("2"),
	FRIENDSONLINE("3"),
	PROFILE("4"),
	SEARCHUSER("5"),
	FRIENDADD("6"),
	MESSAGE("7"),
	CONTACT("8"),
	FRIENDS("9"),
	FILE("10"),
	POLLCREATE("11"),
	NOTIFICATION("12"),
	USERID("13"),
	REGISTER("14"),
	CALLDETAILS("15"),
	SEARCHFRIENDS("16"),
	SEARCHUSERS("17"),
	FRIENDREQUEST("18"),
	GETUSER("19"),
	ACCEPTREQUEST("20"),
	ONLINE("21"),
	SETUSER("22"),
	GETCHATS("23");

	Request(String s){
		s.toString();
	}

}
