package com.example.ex9_1;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText name;
    EditText number;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.phone_number);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(
                        AddActivity.this, Manifest.permission.WRITE_CONTACTS)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddActivity.this,new String[]
                            {Manifest.permission.WRITE_CONTACTS},16);
                }else {
                    addContacts();
                }
            }
        });
    }

    private void addContacts() {
        String nameString=name.getText().toString();
        String numberString=number.getText().toString();
        if(TextUtils.isEmpty(nameString)||TextUtils.isEmpty(numberString)){
            return;
        }else{
            ContentResolver resolver=getContentResolver();
            //1.在RawContacts表中增加一行数据，并获取该行数据的id
            ContentValues values=new ContentValues();//将
            values.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,nameString);
            Uri uri=resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
            long rawContactsId=ContentUris.parseId(uri);//将URI解析
            //2.在data表中添加联系人姓名
            //清空values
            //设置rawContactsId
            //设置mimeTypeId,内容类别
            //设置联系人姓名数据
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactsId);
            values.put(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,nameString);
            resolver.insert(ContactsContract.Data.CONTENT_URI,values);
            //2.在data表中添加联系人号码
            //清空values
            //设置rawContactsId
            //设置mimeTypeId,内容类别
            //设置联系人姓名数据
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactsId);
            values.put(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,numberString);
            resolver.insert(ContactsContract.Data.CONTENT_URI,values);
            setResult(RESULT_OK);
            finish();
        }

    }
    //请求授权结果处理
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 16:if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                addContacts();
            }else {
                Toast.makeText(this,"不同意授权",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
