package DialogBox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.heritage.android.R;

public class AboutDialog extends AlertDialog.Builder{

	ImageButton share;
	
	public AboutDialog(final Context context) {
		super(context);
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.about, null);
		this.setView(alertDialogView);
		this.setTitle(R.string.about);
		this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}        	
        });
		share = (ImageButton)alertDialogView.findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				final Intent MessIntent = new Intent(Intent.ACTION_SEND);
	        	MessIntent.setType("text/plain");
	        	MessIntent.putExtra(Intent.EXTRA_TEXT, R.string.heritage);
	        	context.startActivity(Intent.createChooser(MessIntent, "Partager avec..."));				
			}
			
		});
	}

}
