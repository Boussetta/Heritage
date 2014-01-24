package DialogBox;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.heritage.android.Graphe;
import com.heritage.android.R;
import com.heritage.android.Temp;

import famille.Décédé;
import famille.Personne;

public class MortDialog extends AlertDialog.Builder{
	RadioGroup Group;
	RadioButton mort;
	RadioButton vivant;
	EditText Biens;
	EditText DateDécé;
	public MortDialog(Context context) {
		super(context);
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.mort, null);
		this.setView(alertDialogView);
		this.setTitle(R.string.editerMembre);		
		TextView textView1 = (TextView)alertDialogView.findViewById(R.id.mortID);
        textView1.setText(Temp.Décédé.personne.nom + ", " + Temp.Décédé.personne.prenom);        
        Group = (RadioGroup)alertDialogView.findViewById(R.id.radioGroup1);
        mort = (RadioButton)alertDialogView.findViewById(R.id.personneMorte);
        mort.setOnClickListener(OptionOnClickListener);
        vivant = (RadioButton)alertDialogView.findViewById(R.id.personneVivante);
        vivant.setOnClickListener(OptionOnClickListener);
        Biens = (EditText)alertDialogView.findViewById(R.id.mortBien);
        DateDécé = (EditText)alertDialogView.findViewById(R.id.dateDece);
        this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				if(mort.isChecked()){
					Temp.Décédé.personne = new Décédé(Temp.Décédé.personne);
					if(Biens.getText().length() > 0){
						((Décédé)Temp.Décédé.personne).Biens = Float.parseFloat(Biens.getText().toString());
					}					
					String s = DateDécé.getText().toString();
					if(s.length() > 0){
						((Décédé)Temp.Décédé.personne).dateDécé = new Date(Integer.parseInt(s.substring(0,4)),
								Integer.parseInt(s.substring(6,7)),
								Integer.parseInt(s.substring(9,10)));
					}
				}else if(vivant.isChecked()){
					Temp.Décédé.personne = new Personne(Temp.Décédé.personne);
				}
				Graphe.rndr_view.invalidate();
			}        	
        });
        this.setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}        	
        });
	}
	RadioButton.OnClickListener OptionOnClickListener = new RadioButton.OnClickListener(){
		public void onClick(View v) {
			if((RadioButton) v == mort){
				Biens.setEnabled(true);
				DateDécé.setEnabled(true);
			}else if((RadioButton) v == vivant){
				Biens.setEnabled(false);
				DateDécé.setEnabled(false);
			}
		}
	};
}