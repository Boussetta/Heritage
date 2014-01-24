package DialogBox;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.heritage.android.Graphe;
import com.heritage.android.R;
import com.heritage.android.Temp;

import famille.Membre;
import famille.Personne;

public class MembreDialog extends AlertDialog.Builder{
	EditText nom;
	EditText prenom;
	public MembreDialog(Context context) {
		super(context);
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.personne, null);
		this.setView(alertDialogView);
		this.setTitle(R.string.editerMembre);
		this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				nom = (EditText)alertDialogView.findViewById(R.id.nom);
            	prenom = (EditText)alertDialogView.findViewById(R.id.prenom);	        		            	
            	Temp.personne = new Personne(Temp.tampoSexe,nom.getText().toString(),prenom.getText().toString());
            	Temp.arbre.Membreslist.add(new Membre(Temp.personne,Temp.X + 10 - Temp.TranslationX - Temp.TrX,
            															Temp.Y + 10 - Temp.TranslationY - Temp.TrY));
            	Temp.tampoSexe = null;
            	Temp.Membre = null;
            	Graphe.rndr_view.invalidate();
			}        	
        });
        this.setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Temp.tampoSexe = null;
				Graphe.rndr_view.invalidate();
			}        	
        });
	}

}
