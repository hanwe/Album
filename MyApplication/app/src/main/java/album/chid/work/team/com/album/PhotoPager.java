package album.chid.work.team.com.album;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by hanwe on 2015/4/19.
 */
public class PhotoPager extends Activity
{
    private ProgressBar pro_download;
    private Intent intent;
    private String strUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_main_fist_body);

        pro_download = (ProgressBar) findViewById(R.id.album_firstin_probar);

        intent = this.getIntent();
        strUrl = intent.getStringExtra("ScanInfo");



        setDownload();

    }

    private void setDownload()
    {
        DownloadFileAsync m_DownloadFileAsync = new DownloadFileAsync(this, pro_download);

        if(!strUrl.equals(""))
        {
            m_DownloadFileAsync.execute(strUrl);
        }

    }
}
