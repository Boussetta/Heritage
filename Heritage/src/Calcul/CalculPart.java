package Calcul;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import famille.Membre;

public class CalculPart {
	protected static ProgressDialog mProgressDialog;
	private static ErrorStatus status;
	public static final int MSG_ERR = 0;
	public static final int MSG_CNF = 1;
	public static final int MSG_IND = 2;
	enum ErrorStatus {
	    NO_ERROR, ERROR_1, ERROR_2
	};	
	public static float Biens = 0;
	public static float Reste = 0;
	
	public static void ex�cuter(Context context,final Membre D�c�d�){
		mProgressDialog = ProgressDialog.show(context, "Please wait",
	            "Calcul des parts d\'h�ritage...", true);
		
		new Thread((new Runnable() {
			public void run() {
				Message msg = null;
				status = calcul(D�c�d�);
				if (ErrorStatus.NO_ERROR == status) {
					msg = mHandler.obtainMessage(MSG_CNF,
                            "Parsing and computing ended successfully !");
                    // sends the message to our handler
                    mHandler.sendMessage(msg);
				}
			}			
		})).start();
	}
	
	protected static ErrorStatus calcul(Membre D�c�d�){
		try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	    }
		CalculPart.Biens = ((famille.D�c�d�)D�c�d�.personne).Biens;
		CalculPart.Reste = ((famille.D�c�d�)D�c�d�.personne).Biens;
		if(Existe.fils(D�c�d�)){
			Log.e("cas", "fils existe");
			FilsExiste.ex�cuterTraitement(D�c�d�);
		}else if(Existe.fils_de_fils(D�c�d�)){
			Log.e("cas", "fils fils existe");
			FilsFilsExiste.ex�cuterTraitement(D�c�d�);
		}else if(Existe.p�re(D�c�d�)){
			Log.e("cas", "p�re existe");
			P�reExiste.ex�cuterTraitement(D�c�d�);
		}else if(Existe.grandP�re(D�c�d�)){
			Log.e("cas", "grand p�re existe");
			grandP�reExiste.ex�cuterTraitement(D�c�d�);
		}else if(Existe.fr�re(D�c�d�)){
			Log.e("cas", "fr�re existe");
			fr�reExiste.ex�cuterTraitement(D�c�d�);
		}
		return ErrorStatus.NO_ERROR;
	}	
	final static Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
	        switch (msg.what) {
	        case MSG_IND:
	            if (mProgressDialog.isShowing()) {
	                mProgressDialog.setMessage(((String) msg.obj));
	            }
	            break;
	        case MSG_ERR:
	            if (mProgressDialog.isShowing()) {
	                mProgressDialog.dismiss();
	            }
	            break;
	        case MSG_CNF:
	            if (mProgressDialog.isShowing()) {
	                mProgressDialog.dismiss();
	            }
	            break;
	        default: // should never happen
	            break;
	        }
		}
	};
}
