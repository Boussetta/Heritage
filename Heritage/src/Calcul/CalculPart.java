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
	
	public static void exécuter(Context context,final Membre Décédé){
		mProgressDialog = ProgressDialog.show(context, "Please wait",
	            "Calcul des parts d\'héritage...", true);
		
		new Thread((new Runnable() {
			public void run() {
				Message msg = null;
				status = calcul(Décédé);
				if (ErrorStatus.NO_ERROR == status) {
					msg = mHandler.obtainMessage(MSG_CNF,
                            "Parsing and computing ended successfully !");
                    // sends the message to our handler
                    mHandler.sendMessage(msg);
				}
			}			
		})).start();
	}
	
	protected static ErrorStatus calcul(Membre Décédé){
		try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	    }
		CalculPart.Biens = ((famille.Décédé)Décédé.personne).Biens;
		CalculPart.Reste = ((famille.Décédé)Décédé.personne).Biens;
		if(Existe.fils(Décédé)){
			Log.e("cas", "fils existe");
			FilsExiste.exécuterTraitement(Décédé);
		}else if(Existe.fils_de_fils(Décédé)){
			Log.e("cas", "fils fils existe");
			FilsFilsExiste.exécuterTraitement(Décédé);
		}else if(Existe.père(Décédé)){
			Log.e("cas", "père existe");
			PèreExiste.exécuterTraitement(Décédé);
		}else if(Existe.grandPère(Décédé)){
			Log.e("cas", "grand père existe");
			grandPèreExiste.exécuterTraitement(Décédé);
		}else if(Existe.frère(Décédé)){
			Log.e("cas", "frère existe");
			frèreExiste.exécuterTraitement(Décédé);
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
