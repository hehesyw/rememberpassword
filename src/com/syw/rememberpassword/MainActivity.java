package com.syw.rememberpassword;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/*
 * 本程序需要实现一个记住密码的功能,主要是利用SharedPreferences对象将程序中的密码存储起来
 * 启动的时候通过判断是勾选复选框，来判断是否加载密码
 */
public class MainActivity extends Activity implements OnClickListener{
	private EditText account_edit;
	private EditText password_edit;
	private CheckBox checkBox;
	private Button login_button;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref=getSharedPreferences("saveInfo",MODE_PRIVATE);
        account_edit=(EditText)findViewById(R.id.accountEdit);
        password_edit=(EditText)findViewById(R.id.passwordEdit);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        login_button=(Button)findViewById(R.id.buttonId);
        
        Boolean b=pref.getBoolean("isRemember",false);
        Log.d("TAG","bb");
        if(b){
        	String account=pref.getString("account","");
        	String password=pref.getString("password","");
        	Log.d("TAG","bb");
        	account_edit.setText(account);
        	password_edit.setText(password);
        	checkBox.setChecked(true);
        }
        login_button.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		String account=account_edit.getText().toString();
		String password=password_edit.getText().toString();
		if(account.equals("admin")&&password.equals("123456")){
			if(checkBox.isChecked()){//判断复选框是否被选
				editor=pref.edit();
				editor.putString("account",account);
				editor.putString("password",password);
				editor.putBoolean("isRemember",true);
				editor.commit();
			}
		}
	}

}
