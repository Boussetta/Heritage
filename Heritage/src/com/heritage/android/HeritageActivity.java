package com.heritage.android;

import DialogBox.AboutDialog;
import DialogBox.HelpDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class HeritageActivity extends Activity {
	ImageButton	quit;
	ImageButton	help;
	ImageButton	about;
	ImageButton	nouveau;
	ImageButton	archive;
	public static final int ID_NOTIFICATION = 1988;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        createNotify();
        setContentView(R.layout.main);
        quit = (ImageButton) this.findViewById(R.id.quitter);
        quit.setOnClickListener(onclicklistener);
        help = (ImageButton) this.findViewById(R.id.aide);
        help.setOnClickListener(onclicklistener);
        about = (ImageButton) this.findViewById(R.id.info);
        about.setOnClickListener(onclicklistener);
        nouveau = (ImageButton) this.findViewById(R.id.nouveaArbre);
        nouveau.setOnClickListener(onclicklistener);
        archive = (ImageButton) this.findViewById(R.id.archive);
        archive.setOnClickListener(onclicklistener);
    }

    private OnClickListener onclicklistener = new OnClickListener(){
  		public void onClick(View v) {
  			if((ImageButton)v == quit){
  				AlertDialog.Builder builder = new AlertDialog.Builder(HeritageActivity.this);
  				builder.setMessage(R.string.Quitter)
  				       .setCancelable(false)
  				       .setPositiveButton(R.string.Oui, new DialogInterface.OnClickListener() {
  				           public void onClick(DialogInterface dialog, int id) {
  				        	   Temp.viderMémoire();
  				        	   cancelNotify();
  				        	   HeritageActivity.this.finish();
  				           }
  				       })
  				       .setNegativeButton(R.string.Non, new DialogInterface.OnClickListener() {
  				           public void onClick(DialogInterface dialog, int id) {
  				        	   
  				        	   dialog.cancel();
  				           }
  				       });
  				builder.show();
  			}
  			if((ImageButton)v == help){
  				HelpDialog Dialog = new HelpDialog(HeritageActivity.this);
				Dialog.show();
  			}
  			if((ImageButton)v == about){
  				AboutDialog Dialog = new AboutDialog(HeritageActivity.this);
				Dialog.show();
  			}
  			if((ImageButton)v == nouveau){
  				Intent intent = new Intent(HeritageActivity.this,Graphe.class);
  				startActivity(intent);
  			}
  			if((ImageButton)v == archive){
  				Intent intent = new Intent(HeritageActivity.this,Archive.class);
  				startActivity(intent);
  			}
  		}
  	};
  	private void createNotify(){
  		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
  		Notification notification = new Notification(R.drawable.ic_launcher,String.valueOf(R.string.notification), System.currentTimeMillis());
  		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, HeritageActivity.class), 0);
  		notification.setLatestEventInfo(this, "Héritage Islamique", "", pendingIntent);
  		notificationManager.notify(ID_NOTIFICATION, notification);
  	}
  	private void cancelNotify(){
    	NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    	notificationManager.cancel(ID_NOTIFICATION);
    }
}