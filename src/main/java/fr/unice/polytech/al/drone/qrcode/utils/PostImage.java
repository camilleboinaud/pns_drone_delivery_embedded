package fr.unice.polytech.al.drone.qrcode.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class PostImage {
    public static void main(String[] args) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            String path = "/home/remy/Bureau/5026-paint-colors-WallFizz.jpg";
            HttpPost httppost = new HttpPost("http://localhost:4000/upload/picture");
            File file = new File(path);


            FileBody fileBody = new FileBody(file);
            String[] extension = path.split("\\.");

            HttpEntity entity = MultipartEntityBuilder.create()
                    .addPart("file", fileBody)
                    .addTextBody("extension", extension[extension.length-1])
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
    }

}