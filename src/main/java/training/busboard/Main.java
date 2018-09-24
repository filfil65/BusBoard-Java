package training.busboard;

public class Main {
    public static void main(String args[]) {
        // Your code here!
    	// Sam's API ID is: c3b16dc7
    	// Sam's API key is : 5293d7854df1ddc814d674a109060164
    	
    	System.out.println("Please enter an ATCO code for a bus stop:");
		String input = readline.nextLine().toLowerCase(); 
    	
    	Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    	String name = client.target("https://transportapi.com/v3/uk/bus/stop/" + string +"/live.json?app_id=c3b16dc7&app_key=5293d7854df1ddc814d674a109060164&group=route&nextbuses=no")
    	        .request(MediaType.TEXT_PLAIN)
    	        .get(String.class);
    	
    }
}	
