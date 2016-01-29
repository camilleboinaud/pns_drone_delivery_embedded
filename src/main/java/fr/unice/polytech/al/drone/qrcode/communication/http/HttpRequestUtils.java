package fr.unice.polytech.al.drone.qrcode.communication.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.bridj.util.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Created by cboinaud on 28/01/16.
 */
public class HttpRequestUtils {

    /**
     * Offers a generic woy to create a get request
     *
     * @param *: to be defined
     * @return
     *      JSONObject : response body
     */
    public static JSONObject getRequest(String url, Pair<String, String> ... parameters) throws RuntimeException {
        try {

            // Query parameters construction
            String queryParameters = "?";
            for(Pair<String, String> parameter : parameters){
                queryParameters += parameter.getKey() + "=" + parameter.getValue() + "&";
            }

            // HTTP/GET request construction
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url + ((queryParameters.length() > 1)
                    ? queryParameters.substring(0, queryParameters.length() - 2) : ""));
            HttpResponse response = client.execute(get);

            // Checks response status code
            if(response.getStatusLine().getStatusCode() != 200){
                throw new RuntimeException("HTTP/GET request failed with error code "
                        + response.getStatusLine().getStatusCode());
            }

            // Read response body and transform it into String
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent())
            );

            String result = "", line;
            while ((line = reader.readLine()) != null){
                result += line;
            }

            // Shutting down connection
            client.getConnectionManager().shutdown();

            // Returns response content
            return ((JSONObject) new JSONParser().parse(result));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * Offers a generic woy to create a post request
     *
     * @param *: to be defined
     * @return
     *      JSONObject : response body
     */
    public static JSONObject postRequest(String url, JSONObject requestBody) throws RuntimeException {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            StringEntity content = new StringEntity(requestBody.toJSONString());

            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json");
            post.setEntity(content);

            HttpResponse response = client.execute(post);

            // Checks response status code
            if(response.getStatusLine().getStatusCode() != 200){
                throw new RuntimeException("HTTP/GET request failed with error code "
                        + response.getStatusLine().getStatusCode());
            }

            // Read response body and transform it into String
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent())
            );

            String result = "", line;
            while ((line = reader.readLine()) != null){
                result += line;
            }

            // Shutting down connection
            client.getConnectionManager().shutdown();

            // Returns response content
            return ((JSONObject) new JSONParser().parse(result));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Offers a generic woy to create a post request
     * using multipart data.
     *
     * @param *: to be defined
     * @return
     *      JSONObject : response body
     */
    public static JSONObject postJsonImageMultipartRequest(String url, JSONObject jsonObject, File image)
        throws RuntimeException {

        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(url);

            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", image)
                    .addTextBody("name", "remy")
                    .build();

            httppost.setEntity(entity);
            System.out.println("executing request " + httppost.getRequestLine());
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            System.out.println(response.getStatusLine());

            if (resEntity != null) {
                System.out.println(EntityUtils.toString(resEntity));
            }
            if (resEntity != null) {
                resEntity.consumeContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws ParseException {
        /*
        //Testing get json flight plan OK
        System.out.println(getRequest("http://localhost:4000/api/flightplan").toJSONString());

        //Testing post json mail auth OK
        JSONObject obj = (JSONObject)(new JSONParser()).parse("{ \"myjson\" : { \"param1\": \"value1\", \"param2\": \"0\"}}");
        System.out.println(postRequest("http://localhost:4000/api/mailauth", obj).toJSONString());

        System.out.println(postJsonImageMultipartRequest("http://localhost:4000/api/deliveryack", obj, new File("img-gen/itheproof.png")).toJSONString());
        */
    }

}
