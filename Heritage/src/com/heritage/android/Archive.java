package com.heritage.android;

import java.util.Iterator;
import java.util.Vector;

import DialogBox.OutilsIO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import famille.Membre;

public class Archive extends Activity{
	
	private Button retour;
	private Button charger;
	private Button effacer;
	private Spinner spinner1;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.archive);		
		OutilsIO.charger();
		spinner1 = (Spinner)this.findViewById(R.id.Sauvegardes);
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
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner1.setAdapter(adapter1);
		}		
		charger = (Button) this.findViewById(R.id.Charger);
		charger.setOnClickListener(onclicklistener);		
		effacer = (Button) this.findViewById(R.id.Effacer);
		effacer.setOnClickListener(onclicklistener);		
		retour = (Button) this.findViewById(R.id.Retour);
		retour.setOnClickListener(onclicklistener);
	}	
	private OnClickListener onclicklistener = new OnClickListener(){
		@SuppressWarnings("unchecked")
		public void onClick(View v) {
			if((Button)v == retour){
				Archive.this.finish();
			}else if((Button)v == charger){
				if(Temp.archive != null){
					if(!Temp.archive.isEmpty()){
						if(!Temp.archive.get(spinner1.getSelectedItem().toString()).isEmpty()){
							Temp.arbre.Membreslist = (Vector<Membre>) Temp.archive.get(spinner1.getSelectedItem().toString()).clone();
							Intent intent = new Intent(Archive.this,Graphe.class);
							startActivity(intent);
							Archive.this.finish();
						}
					}
				}
			}else if((Button)v == effacer){
				if(Temp.archive != null){
					if(!Temp.archive.isEmpty()){
					 AlertDialog.Builder builder = new AlertDialog.Builder(Archive.this);
						builder.setMessage(R.string.Supprimer)
						       .setCancelable(false)
						       .setPositiveButton(R.string.Oui, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   Temp.archive.remove(spinner1.getSelectedItem().toString());
						        	   spinner1 = OutilsIO.actualiser(spinner1, Archive.this);
						           }
						       })
						       .setNegativeButton(R.string.Non, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {				        	   
						        	   dialog.cancel();
						           }
						       });
						builder.show();
					}
				 }
			}
		}		
	};
}