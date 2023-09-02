package RequestHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestBuilder {
    private String host;
    private String format;

    public void setHost(String host) {
        this.host = host;
    }

    public void setFormat(String format) {
        this.format = "&fmt="+format;
    }

    public enum types{
        ANNOTATION,
        AREA,
        ARTIST,
        CDSTUB,
        EVENT,
        INSTRUMENT,
        LABEL,
        PLACE,
        RECORDING,
        RELEASE,
        RELEASE_GROUP,
        SERIES,
        TAG,
        WORK,
        URL,
        GENRES,
        ID

    }


    public RequestBuilder() {
        host = "http://musicbrainz.org/ws/2/";
        format = "&fmt=json";

    }

    public String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .method("GET",HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    public String builder(Enum type, Enum query, String search,int offset,int... limit ) throws UnsupportedEncodingException {
        String stringQuery = host;
        if (type == types.RELEASE_GROUP){
            stringQuery = stringQuery+ type.toString().replace("_","-").toLowerCase() + "?query=" + query.toString().toLowerCase();
            stringQuery = stringQuery + URLEncoder.encode(search,"UTF-8") + format;
        } else if (query==types.ID) {
            stringQuery = stringQuery + type.toString().toLowerCase() +"/";
            stringQuery = stringQuery + URLEncoder.encode(search,"UTF-8") +"?inc=artist-credits"+ format;


        } else{
            stringQuery = stringQuery+ type.toString().toLowerCase() + "?query=" + query.toString().toLowerCase()+":";
            stringQuery = stringQuery + URLEncoder.encode(search,"UTF-8") + format;
        }

        if (limit.length > 0){
            stringQuery = stringQuery +"&limit="+ Integer.toString(limit[0]);
        }
        stringQuery = stringQuery + "&offset=" + Integer.toString(offset);
        return stringQuery;

    }




    public String getAuthor(String author, int... limit) throws UnsupportedEncodingException {

        String query = "release?query=artist:";
        String encodedAuthor = URLEncoder.encode(author,"UTF-8");
        String apiUrl = host + query + encodedAuthor + format;
        if (limit != null){
            String slimit = "&limit=" + Integer.toString(limit[0]);
            apiUrl = apiUrl + slimit;
        }
        return apiUrl;

    }

}
