package album.chid.work.team.com.album;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hanwe on 2015/4/19.
 */
public class DownloadFileAsync extends AsyncTask<String, String, String>
{

    private Context m_Context;
    private ProgressBar m_ProgressBar;

    DownloadFileAsync(Context context, ProgressBar pro_download)
    {
        m_Context = context;
        m_ProgressBar = pro_download;
    }

    @Override
    protected String doInBackground(String... params)
    {
        InputStream input = null;
        FileOutputStream output = null;
        HttpURLConnection connection = null;
        try
        {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();

            File fopen = new File(m_Context.getFilesDir(), "File.zip");
            fopen.createNewFile();
//            output = new FileOutputStream("File.zip", m_Context.MODE_PRIVATE);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1)
            {
                // allow canceling with back button
                if (isCancelled())
                {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
//                    publishProgress((int) (total * 100 / fileLength));
                    m_ProgressBar.setProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        }
        catch (Exception e)
        {
            return e.toString();
        }
        finally
        {
            try
            {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            }
            catch (IOException ignored)
            {
            }

            if (connection != null)
                connection.disconnect();
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(String... values)
    {
        super.onProgressUpdate(values);
    }
}
