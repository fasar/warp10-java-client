package fr.avalonlab.warp10.lowlevel;

import fr.avalonlab.warp10.WarpConst;
import fr.avalonlab.warp10.exceptions.W10ServerException;
import fr.avalonlab.warp10.lowlevel.model.GTSFullText;
import fr.avalonlab.warp10.model.WSelector;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warp10SyncApacheHttpClient implements Warp10Sync {
    private String warpUrl;
    private String readToken;
    private String writeToken;
    private Duration globalTimeout;

    public Warp10SyncApacheHttpClient(String warpUrl, String readToken, String writeToken, Duration globalTimeout) {
        this.warpUrl = warpUrl;
        if (this.warpUrl.endsWith("/")) {
            this.warpUrl = this.warpUrl.substring(0, this.warpUrl.length() - 2);
        }
        if (!this.warpUrl.startsWith("http")) {
            this.warpUrl = "http://" + warpUrl;
        }
        this.readToken = readToken;
        this.writeToken = writeToken;
        this.globalTimeout = globalTimeout;
    }

    @Override
    public void ingress(List<String> gtsInputs) throws W10ServerException {
        String collect = gtsInputs.stream().collect(Collectors.joining("\n"));
        ingress(collect);
    }

    @Override
    public void ingress(String gtsInput) throws W10ServerException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpPost httppost = new HttpPost(warpUrl + WarpConst.HTTP_ENDPOINT_UPDATE);
            httppost.addHeader(WarpConst.HTTP_WARP_10_TOKEN_HEADER, writeToken);
            final StringEntity reqEntity = new StringEntity(gtsInput, StandardCharsets.UTF_8);
            httppost.setEntity(reqEntity);

            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                // Get hold of the response entity
                final HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            throw new W10ServerException(e.getMessage(), e);
        }
    }

    @Override
    public String exec(String warpScript) throws W10ServerException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpPost httppost = new HttpPost(warpUrl + WarpConst.HTTP_ENDPOINT_EXEC);
            httppost.addHeader(WarpConst.HTTP_WARPSCRIPT_CONTENT_HEADER, WarpConst.HTTP_WARPSCRIPT_CONTENT_TEXT_HEADER);
            final StringEntity reqEntity = new StringEntity(warpScript, StandardCharsets.UTF_8);
            httppost.setEntity(reqEntity);

            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                // Get hold of the response entity
                final HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                String res = EntityUtils.toString(entity);
                return res;
            }
        } catch (IOException e) {
            throw new W10ServerException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(WSelector selector, Instant start, Instant end) throws W10ServerException {
        throw new W10ServerException("Not Implemented");
    }

    @Override
    public void deleteAll(WSelector selector) throws W10ServerException {
        throw new W10ServerException("Not Implemented");
    }

    @Override
    public Stream<GTSFullText> fetchData(WSelector selector, boolean dedup, Instant start, Instant end) throws W10ServerException {
        throw new W10ServerException("Not Implemented");
    }
}
