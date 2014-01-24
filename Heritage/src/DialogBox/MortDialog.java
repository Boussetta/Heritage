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

import famille.D�c�d�;
import famille.Personne;

public class MortDialog extends AlertDialog.Builder{
	RadioGroup Group;
	RadioButton mort;
	RadioButton vivant;
	EditText Biens;
	EditText DateD�c�;
	public MortDialog(Context context) {
		super(context);
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.mort, null);
		this.setView(alertDialogView);
		this.setTitle(R.string.editerMembre);		
		TextView textView1 = (TextView)alertDialogView.findViewById(R.id.mortID);
        textView1.setText(Temp.D�c�d�.personne.nom + ", " + Temp.D�c�d�.personne.prenom);        
        Group = (RadioGroup)alertDialogView.findViewById(R.id.radioGroup1);
        mort = (RadioButton)alertDialogView.findViewById(R.id.personneMorte);
        mort.setOnClickListener(OptionOnClickListener);
        vivant = (RadioButton)alertDialogView.findViewById(R.id.personneVivante);
        vivant.setOnClickListener(OptionOnClickListener);
        Biens = (EditText)alertDialogView.findViewById(R.id.mortBien);
        DateD�c� = (EditText)alertDialogView.findViewById(R.id.dateDece);
        this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				if(mort.isChecked()){
					Temp.D�c�d�.personne = new D�c�d�(Temp.D�c�d�.personne);
					if(Biens.getText().length() > 0){
						((D�c�d�)Temp.D�c�d�.personne).Biens = Float.parseFloat(Biens.getText().toString());
					}					
					String s = DateD�c�.getText().toString();
					if(s.length() > 0){
						((D�c�d�)Temp.D�c�d�.personne).dateD�c� = new Date(Integer.parseInt(s.substring(0,4)),
								Integer.parseInt(s.substring(6,7)),
								Integer.parseInt(s.substring(9,10)));
					}
				}else if(vivant.isChecked()){
					Temp.D�c�d�.personne = new Personne(Temp.D�c�d�.personne);
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
				DateD�c�.setEnabled(true);
			}else if((RadioButton) v == vivant){
				Biens.setEnabled(false);
				DateD�c�.setEnabled(false);
			}
		}
	};
}