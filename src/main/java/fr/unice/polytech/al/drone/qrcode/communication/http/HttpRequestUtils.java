package fr.unice.polytech.al.drone.qrcode.communication.http;

import org.bridj.util.Pair;
import org.json.simple.JSONObject;

/**
 * Created by cboinaud on 28/01/16.
 */
public class HttpRequestUtils {

    /**
     * Offers a generic woy to create a get request
     *
     * @param *: to be defined
     * @return
     *      Integer : response status code
     *      JSONObject : response body
     */
    public static Pair<Integer, JSONObject> getRequest(){
        return null;
    }


    /**
     * Offers a generic woy to create a post request
     *
     * @param *: to be defined
     * @return
     *      Integer : response status code
     *      JSONObject : response body
     */
    public static Pair<Integer, JSONObject> postRequest(){
        return null;
    }


    /**
     * Offers a generic woy to create a post request
     * using multipart data.
     *
     * @param *: to be defined
     * @return
     *      Integer : response status code
     *      JSONObject : response body
     */
    public static Pair<Integer, JSONObject> postMultipartRequest(){
        return null;
    }

}
