package escrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap; 
import java.util.Map;

import com.google.gson.Gson;


public class API {
	public static void main(String[] args) throws IOException {

		//API.GETRequest();

		//API.POSTRequest();
		Map parentMap=new LinkedHashMap();
		
		ArrayList parties = new ArrayList();
		Map partiesMap1=new LinkedHashMap();
		Map partiesMap2=new LinkedHashMap();
		partiesMap1.put("role", "buyer");
		partiesMap1.put("customer", "me");

		partiesMap2.put("role", "seller");
		partiesMap2.put("customer", "keanu.reeves@test.escrow.com");
		
		parties.add(partiesMap1);
		parties.add(partiesMap2);
		parentMap.put("parties", parties);
		
		
		parentMap.put("currency", "usd");  
		parentMap.put("description", "1962 Fender Stratocaster"); 
		
		ArrayList items = new ArrayList();
		Map itemmap=new LinkedHashMap();
 
		itemmap.put("title", "1962 Fender Stratocaster");
		itemmap.put("description", "Like new condition, includes original hard case.");
		itemmap.put("type", "general_merchandise");
		itemmap.put("inspection_period", 259200);
		itemmap.put("quantity", 1);
		items.add(itemmap);
		
		
		ArrayList scheduleList = new ArrayList();
		
		Map scheduleMap=new LinkedHashMap();
		scheduleMap.put("amount", 2111);
		scheduleMap.put("payer_customer", "me");
		scheduleMap.put("beneficiary_customer", "keanu.reeves@test.escrow.com");
		scheduleList.add(scheduleMap);
		itemmap.put("schedule",scheduleList);
		
		ArrayList itemArr = new ArrayList();
		itemArr.add(itemmap);
		parentMap.put("items", itemArr);
		
		
		Gson gson = new Gson();
		String json = "{\"parties\":[{\"role\":\"buyer\",\"customer\":\"me\"},{\"role\":\"seller\",\"customer\":\"keanu.reeves@test.escrow.com\"}],\"currency\":\"usd\",\"description\":\"1962 Fender Stratocaster\",\"items\":[{\"title\":\"1962 Fender Stratocaster\",\"description\":\"Like new condition, includes original hard case.\",\"type\":\"general_merchandise\",\"inspection_period\":259200,\"quantity\":1,\"schedule\":[{\"amount\":95000.0,\"payer_customer\":\"me\",\"beneficiary_customer\":\"keanu.reeves@test.escrow.com\"}]}]}";
		
		System.out.println(json);
		
		
		try {
            URL url = new URL ("https://api.escrow.com/2017-09-01/transaction");
            String encoding = Base64.getEncoder().encodeToString(("aaaamate@gmail.com:amanMate12@").getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            OutputStream os = connection.getOutputStream();
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length); 

    	    os.flush();

    	    os.close();

    	    int responseCode = connection.getResponseCode();

    	    System.out.println("POST Response Code :  " + responseCode);

    	    System.out.println("POST Response Message : " + connection.getResponseMessage());
            
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

	}
	
	
	
	public static void GETRequest() throws IOException {

	    URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/1");

	    String readLine = null;

	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

	    conection.setRequestMethod("GET");

	    conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here

	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {

	        BufferedReader in = new BufferedReader(

	            new InputStreamReader(conection.getInputStream()));

	        StringBuffer response = new StringBuffer();

	        while ((readLine = in .readLine()) != null) {

	            response.append(readLine);

	        } in .close();

	        // print result

	        System.out.println("JSON String Result " + response.toString());

	        //GetAndPost.POSTRequest(response.toString());

	    } else {

	        System.out.println("GET NOT WORKED");

	    }

	}
	
	
	public static void POSTRequest() throws IOException {

	    final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +

	        "    \"id\": 101,\r\n" +

	        "    \"title\": \"Test Title\",\r\n" +

	        "    \"body\": \"Test Body\"" + "\n}";

	    System.out.println(POST_PARAMS);

	    
	    URL obj = new URL("https://api.escrow.com/2017-09-01/transaction");

	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

	    postConnection.setRequestMethod("POST");

	    postConnection.setRequestProperty("userId", "a1bcdefgh");

	    postConnection.setRequestProperty("Content-Type", "application/json");

	    postConnection.setDoOutput(true);

	    OutputStream os = postConnection.getOutputStream();

	    os.write(POST_PARAMS.getBytes());

	    os.flush();

	    os.close();

	    int responseCode = postConnection.getResponseCode();

	    System.out.println("POST Response Code :  " + responseCode);

	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success

	        BufferedReader in = new BufferedReader(new InputStreamReader(

	            postConnection.getInputStream()));

	        String inputLine;

	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {

	            response.append(inputLine);

	        } in .close();

	        // print result

	        System.out.println(response.toString());

	    } else {

	        System.out.println("POST NOT WORKED");

	    }

	}
}
