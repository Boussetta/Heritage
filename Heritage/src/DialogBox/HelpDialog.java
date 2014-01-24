package DialogBox;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.heritage.android.R;

public class HelpDialog extends Dialog {

	public HelpDialog(Context context) {
		super(context);
		this.setContentView(R.layout.help);
		this.setTitle(R.string.help);		
		this.setCancelable(true);
		TextView help = (TextView)findViewById(R.id.fichierAide);
		
//		FileInputStream fIn = null;
//        InputStreamReader isr = null;
// 
//        char[] inputBuffer = new char[255];
//        String data = null;
// 
//        try{
//         fIn = context.openFileInput(context.getResources().openRawResource(R.raw.aide));
//            isr = new InputStreamReader(fIn);
//            isr.read(inputBuffer);
//            data = new String(inputBuffer);
//            }
//            catch (Exception e) {
//            }
//            /*finally {
//               try {
//                      isr.close();
//                      fIn.close();
//                      } catch (IOException e) {
//                        Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
//                      }
//            } */
//		
//		
//		String text = "";
////		try {
////			String ligne;
////			BufferedReader fichierAide = new BufferedReader(new InputStreamReader(new FileInputStream(context.getResources().openRawResource(R.raw.aide))) ;
////			do{
////				ligne = fichierAide.readLine() ;
////				if (ligne != null){
////					text = text + "\n" + ligne;
////				}
////			}while (ligne != null) ;
////			fichierAide.close () ;
////		} catch (FileNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		help.setText(text);
		help.setText("Fichier Aide\nFichier Aide\nFichier Aide\nFichier Aide\nFichier Aide\n" +
				"Fichier Aide\nFichier Aide\nFichier Aide\nFichier Aide\nFichier Aide\nFichier Aide\n" +
				"Fichier Aide");
	}

}
