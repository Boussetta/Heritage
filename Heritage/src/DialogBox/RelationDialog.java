package DialogBox;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.heritage.android.Graphe;
import com.heritage.android.R;
import com.heritage.android.Temp;

import famille.Personne.sexe;
public class RelationDialog extends AlertDialog.Builder{
	
	Spinner spinner1;
	Spinner spinner2;
	
	public RelationDialog(Context context) {
		super(context);
		/***************** I - Chargement de la Boite de dialogue *****************/
		LayoutInflater factory = LayoutInflater.from(context);
		final View alertDialogView = factory.inflate(R.layout.relation, null);
		this.setView(alertDialogView);
		this.setTitle("Valider Relation");
		/***************** I - 1 - Remplissage des Spinner *****************/
		final String[] items1 = {"p�re","�poux", "fr�re", "fils"};
		final String[] items2 = {"m�re", "�pouse","soeur", "fille"};
		
		TextView textView1 = (TextView)alertDialogView.findViewById(R.id.textView1);
        textView1.setText("R�le de: " + Temp.M1.personne.prenom);
        
        spinner1 = (Spinner)alertDialogView.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = null;
        if(Temp.M1.personne.sxe == sexe.male){
        	adapter1 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, items1);
        }else{
        	adapter1 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, items2);
        }
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String Selected = arg0.getSelectedItem().toString();
				if(Selected.equals("p�re") || Selected.equals("m�re")){
					spinner2.setSelection(3, true);
				}else if(Selected.equals("�poux") || Selected.equals("�pouse")){
					spinner2.setSelection(1, true);
				}else if(Selected.equals("fr�re") || Selected.equals("soeur")){
					spinner2.setSelection(2, true);
				}else if(Selected.equals("fils") || Selected.equals("fille")){
					spinner2.setSelection(0, true);
				}				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        TextView textView2 = (TextView)alertDialogView.findViewById(R.id.textView2);
        textView2.setText("R�le de: " + Temp.M2.personne.prenom);
        spinner2 = (Spinner)alertDialogView.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = null;
        if(Temp.M2.personne.sxe == sexe.male){
        	adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, items1);
        }else{
        	adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, items2);
        }
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String Selected = arg0.getSelectedItem().toString();
				if(Selected.equals("p�re") || Selected.equals("m�re")){
					spinner1.setSelection(3, true);
				}else if(Selected.equals("�poux") || Selected.equals("�pouse")){
					spinner1.setSelection(1, true);
				}else if(Selected.equals("fr�re") || Selected.equals("soeur")){
					spinner1.setSelection(2, true);
				}else if(Selected.equals("fils") || Selected.equals("fille")){
					spinner1.setSelection(0, true);
				}				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
		this.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which) {
				Temp.Role1 = spinner1.getSelectedItem().toString();
				Temp.Role2 = spinner2.getSelectedItem().toString();
				if(Temp.Role1.equals("p�re")){					
					Temp.M2.p�re = Temp.M1;
					if(Temp.M1.conjoint != null){
						Temp.M2.m�re = Temp.M1.conjoint;
					}
					Temp.M1.fils.add(Temp.M2);					
				}else if(Temp.Role1.equals("m�re")){
					Temp.M2.m�re = Temp.M1;
					if(Temp.M1.conjoint != null){
						Temp.M2.p�re = Temp.M1.conjoint;
						Temp.M1.conjoint.fils.add(Temp.M2);
					}else{
						if(Temp.M2.p�re != null){
							Temp.M1.conjoint = Temp.M2.p�re;
							Temp.M2.p�re.conjoint = Temp.M1;
						}else{
							Temp.M1.fils.add(Temp.M2);
						}
					}
				}else if(Temp.Role1.equals("�poux") || Temp.Role1.equals("�pouse")){
					Temp.M2.conjoint = Temp.M1;
					Temp.M1.conjoint = Temp.M2;					
				}
				Temp.arbre.Membreslist.remove(Temp.M2);	
				Temp.M1 = null;
				Temp.M2 = null;
				Temp.Membre = null;
				Graphe.rndr_view.invalidate();
			}
		});
		this.setNegativeButton(R.string.Annuler, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Temp.tampoLigne = null;
				Graphe.rndr_view.invalidate();
			}			
		});
	}
}