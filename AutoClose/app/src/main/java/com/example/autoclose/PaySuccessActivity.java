package com.example.autoclose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 支付成功页面
 * @author Administrator
 *
 */
public class PaySuccessActivity extends Activity {
	private ImageView im_back;
	private TextView tv_title;
	private TextView tv_paysuccess_time;//开始是3秒
	private ImageView im_paysuccess;//对号
	private Boolean abc=false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paysuccess);
		im_back= (ImageView) findViewById(R.id.im_back);
		im_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});

		tv_paysuccess_time=(TextView) findViewById(R.id.paysuccess_time);
		im_paysuccess= (ImageView) findViewById(R.id.paysuccess_im);
		im_paysuccess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//点击成功后跳转到当前订单的界面
				Intent intent = new Intent(PaySuccessActivity.this, AfterPayActivity.class);

				startActivity(intent);
				abc=true;
			}
		});
		CountDownTextViewHelper helper_pay=new CountDownTextViewHelper(tv_paysuccess_time, "0", 5, 1);
		helper_pay.setOnFinishListener(new CountDownTextViewHelper.OnFinishListener() {

			@Override
			public void finish() {
				// TODO Auto-generated method stub
				if (abc==false) {

					Intent intent2 = new Intent(PaySuccessActivity.this, AfterPayActivity.class);

					startActivity(intent2);

				}
			}
		});

		helper_pay.start();

	}

}
