package album.chid.work.team.com.album;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import album.chid.work.team.com.Define.UIDefine;
import album.chid.work.team.com.Dialog.ChoseFunction;


public class MainActivity extends ActionBarActivity implements Animation.AnimationListener
{

    private ImageButton imgbu_add;
    private Animation toLargeAnimation;
    private Animation toSmallAnimation;
    private UIDefine m_UIDefine;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        m_UIDefine = UIDefine.getInstance(this);

        setContentView(R.layout.activity_main);

        imgbu_add = (ImageButton) findViewById(R.id.album_imgbu_add);
        imgbu_add.getLayoutParams().height = m_UIDefine.getLayoutHeight(10);
        imgbu_add.getLayoutParams().width = m_UIDefine.getLayoutHeight(10);

        toLargeAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_large);
        toSmallAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_small);

        toLargeAnimation.setAnimationListener(MainActivity.this);
        toSmallAnimation.setAnimationListener(MainActivity.this);
        imgbu_add.startAnimation(toSmallAnimation);

        addListenerOnButton();
    }

    public void addListenerOnButton()
    {

        imgbu_add.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.album_imgbu_add:
                    {
                        ChoseFunction m_ChoseFunction = new ChoseFunction(MainActivity.this, R.style.add_dialog);
                        m_ChoseFunction.show();

                        Window dialogWindow = m_ChoseFunction.getWindow();
                        android.view.WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.width = m_UIDefine.getLayoutWidth(50);
                        lp.height = m_UIDefine.getLayoutHeight(20);
                        dialogWindow.setBackgroundDrawable(new BitmapDrawable());
                        dialogWindow.setAttributes(lp);
                        //                        Intent intent = new Intent();
                        //                        intent.setClass(MainActivity.this, PhotoPager.class);
                        //                        startActivity(intent);
                        //                        finish();
                        break;
                    }
                }

            }

        });

    }


    @Override
    public void onAnimationStart(Animation animation)
    {

    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        if (animation.hashCode() == toLargeAnimation.hashCode())
        {
            imgbu_add.startAnimation(toSmallAnimation);
        }
        else
        {
            imgbu_add.startAnimation(toLargeAnimation);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }
}
