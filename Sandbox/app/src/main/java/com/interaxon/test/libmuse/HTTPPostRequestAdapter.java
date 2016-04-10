package com.interaxon.test.libmuse;

import android.content.ContentValues;
import android.os.AsyncTask;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by john on 4/9/16.
 */

public class HTTPPostRequestAdapter extends AsyncTask<Integer, Integer, Long>
{
    final String URLaddress = "https://api.particle.io/v1/devices/55ff6b065075555331281787/led?access_token=df13bec2b12d231295a08e827619cdbd9d5e534f";

    @Override
    protected Long doInBackground(Integer... params)
    {

        HttpURLConnection con = null;
        String urlParameters = "args=on";
        if(params[0] == 0)
            urlParameters = "args=off";

        try
        {
            URL url = new URL(URLaddress);

            con = (HttpsURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

        }
        catch(ProtocolException prot)
        {
            prot.printStackTrace();
        }
        catch(MalformedInputException mal)
        {
            mal.printStackTrace();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
        finally
        {
            if(con != null)
                con.disconnect();
        }

        return new Long(1);

    }
}
