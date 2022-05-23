package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DecodeToken {
    
    public String sub;
    public List<String>  roles =new ArrayList<>();

    public static DecodeToken getDecoded(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(b64payload), "UTF-8");

        return new Gson().fromJson(jsonString, DecodeToken.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
    
}