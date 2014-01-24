package DialogBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.heritage.android.Archive;
import com.heritage.android.Temp;

import famille.Membre;

public class OutilsIO {
	@SuppressWarnings("unchecked")
	public static void charger() {
		try {
			ObjectInputStream backUpFile = new ObjectInputStream(new FileInputStream("Heritage.sav"));
			Temp.archive = (Hashtable<String, Vector<Membre>>)(backUpFile.readObject());
			backUpFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Spinner actualiser(Spinner spinner1, Archive archive) {
		OutilsIO.enregistrer();
		OutilsIO.charger();
		
		if(!Temp.archive.isEmpty()){
			Iterator<String> it;
			it=Temp.archive.keySet().iterator();
			int i = (Temp.archive).size();
			String[] array = new String[i];
			int count = 0;
			while(it.hasNext()){
				array[count] = it.next();
				count ++;
			}						
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(archive,android.R.layout.simple_spinner_item, array);
			adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner1.setAdapter(adapter1);
		}		
		return spinner1;
	}

	public static void enregistrer() {
		try {
			ObjectOutputStream saveFile = new ObjectOutputStream(new FileOutputStream("Heritage.sav"));
			saveFile.writeObject(Temp.archive);
			saveFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
