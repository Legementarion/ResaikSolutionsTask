package com.lego.resaiksolutionstask.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lego.resaiksolutionstask.R;
import com.lego.resaiksolutionstask.controller.JsonController;
import com.lego.resaiksolutionstask.utils.Settings;

/**
 * @author Lego on 30.08.2016.
 */

public class FavoriteDialog extends Dialog {

    private Context mContext;
    private JsonController mJsonController;

    private EditText mEditText;
    private TextView mText;
    private Button mButtonOk, mButtonCancel;

    private int mCurrentPage;

    public FavoriteDialog(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.context_dialog);
        mJsonController = JsonController.getInstance();
        initUi();
    }

    public void show(int g) {
        super.show();
        Log.d("WORK", "show: " + g);
        mCurrentPage = g;
    }

    private void initUi() {
        mEditText = (EditText) findViewById(R.id.dialog_comment);
        mText = (TextView) findViewById(R.id.textDialog);
        if (mJsonController.getmImages().get(mCurrentPage).isFavorite()) {
            mEditText.setVisibility(View.GONE);
            mText.setText(R.string.Remove_from_favorite);
        } else {
            mText.setText(R.string.Please_Enter_your_Comment);
            mEditText.setVisibility(View.VISIBLE);
        }
        mButtonOk = (Button) findViewById(R.id.btn_ok);
        mButtonOk.setOnClickListener(myOnClickListener);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);
        mButtonCancel.setOnClickListener(myOnClickListener);
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_ok:
                    if (mJsonController.getmImages().get(mCurrentPage).isFavorite()) {
                        mJsonController.getmImages().get(mCurrentPage).setFavorite(false);
                        mJsonController.getmImages().get(mCurrentPage).setComment("");
                    } else {
                        mJsonController.getmImages().get(mCurrentPage).setFavorite(true);
                        mJsonController.getmImages().get(mCurrentPage).setComment(mEditText.getText().toString());
                    }
                    Settings.reDraw = true;
                    dismiss();
                    break;
                case R.id.btn_cancel:
                    dismiss();
                    break;
            }
        }
    };
}
