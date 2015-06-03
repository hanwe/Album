package album.chid.work.team.com.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import album.chid.work.team.com.album.QRScan;
import album.chid.work.team.com.album.R;

/**
 * Created by hanwe on 2015/4/30.
 */
public class ChoseFunction extends AlertDialog
{
    private Button bu_scan, bu_login;
    private Context m_context;

    public ChoseFunction(Context context, int them)
    {
        super(context, them);

        m_context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_chose_dialog);

        bu_scan = (Button) findViewById(R.id.album_bu_scan);
        bu_scan.setOnClickListener(mOnClickListener);

        bu_login= (Button) findViewById(R.id.album_bu_login);
        bu_login.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.album_bu_scan:
                {
                    Intent intent = new Intent();
                    intent.setClass(m_context,  QRScan.class );
                    m_context.startActivity(intent);
                    break;
                }
            }
        }
    };
}
