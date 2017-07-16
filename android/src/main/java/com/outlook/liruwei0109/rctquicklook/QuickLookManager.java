package com.outlook.liruwei0109.rctquicklook;


import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.Arrays;
import java.io.File;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class QuickLookManager extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactContext;

    public QuickLookManager(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "QuickLook";
    }

    @ReactMethod
    public void openFile(String filePath) {
        File file = new File(filePath);
        if(file.exists()){
            String[] temp = filePath.split("\\.");
            String fileType = temp[temp.length-1];
            Intent intent;
            switch (fileType) {
                case "doc":
                case "docx":
                intent = this.getWordFileIntent(filePath);
                break;
                case "xls":
                case "xlsx":
                intent = this.getExcelFileIntent(filePath);
                break;
                case "ppt":
                case "pptx":
                intent = this.getExcelFileIntent(filePath);
                break;
                case "pdf":
                intent = this.getPdfFileIntent(filePath);
                break;
                default:
                intent = this.getTextFileIntent(filePath,false);
                break;
            }
            try {
                reactContext.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(reactContext, "该文件无法打开", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(reactContext,"文件不存在,打开失败",Toast.LENGTH_SHORT).show();
        }
    }

    //android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent( String param, boolean paramBoolean) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param );
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(new File(param ));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    //android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent( String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    //android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent( String param ) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    //android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent( String param ) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    //android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent( String param ) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }


    //android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent( String param ) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    //android获取一个用于发送短信的intent
    public static Intent doSendSMSTo(String phoneNumber,String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", message);
        return intent;
    }

}
