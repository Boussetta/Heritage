package DialogBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.heritage.android.R;
import com.heritage.android.Temp;

import famille.Membre;

public class SaveDialog extends AlertDialog.Builder{

	AutoCompleteTextView NomSauvegarde;
	public SaveDialog(Context context) {
		super(context);
		
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.sauvegarder, null);
		this.setView(alertDialogView);
		this.setTitle(R.string.enregistrerArbre);
		NomSauvegarde = (AutoCompleteTextView)alertDialogView.findViewById(R.id.Sauvegarde);
		OutilsIO.charger();
		if(!Temp.archive.isEmpty()){
			Iterator<String> it;
			it=Temp.archive.keySet().iterator();
			int i = (Temp.archive).size();
			String[] array = new String[i];
			int count = 0;
			while(it.hasNext()){
				array[count] = (String) it.next();
				count ++;
			}
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, array);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			NomSauvegarde.setAdapter(adapter1);
		}
		this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			@SuppressWarnings("unchecked")
			public void onClick(DialogInterface dialog, int which) {
				
				if(!Temp.archive.containsKey(NomSauvegarde.getText().toString())){
					Temp.archive.put(NomSauvegarde.getText().toString(), (Vector<Membre>) Temp.arbre.Membreslist.clone());
					OutilsIO.enregistrer();
				}else{
					Temp.archive.remove(NomSauvegarde.getText().toString());
					Temp.archive.put(NomSauvegarde.getText().toString(), (Vector<Membre>) Temp.arbre.Membreslist.clone());
					try {
						ObjectOutputStream saveFile = new ObjectOutputStream(new FileOutputStream("Heritage.sav"));
						saveFile.writeObject(Temp.archive);
						saveFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}        	
        });
        this.setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}        	
        });
	}
}
