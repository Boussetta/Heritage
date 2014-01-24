package com.heritage.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import famille.ArbreGN;
import famille.Membre;
import famille.Personne;
import famille.Personne.sexe;
import geometrie.FormesGéométriques.Ligne;
import geometrie.FormesGéométriques.Point;

public class Temp {

	public static Hashtable<String, Vector<Membre>> archive = new Hashtable<String, Vector<Membre>>();
	
	public static Bitmap grille;
	public static Bitmap Trash;
	public static Bitmap male;
	public static Bitmap femelle;
	public static Bitmap tampon;

	public static void initJPG(Context context){
		InputStream inputStream = null;
		try {
            AssetManager assetManager = context.getAssets();   
            inputStream = assetManager.open("trash_icone.png");
            Temp.Trash = BitmapFactory.decodeStream(inputStream);
            inputStream = assetManager.open("male.png");
            Temp.male = BitmapFactory.decodeStream(inputStream);
            inputStream = assetManager.open("femelle.png");
            Temp.femelle = BitmapFactory.decodeStream(inputStream);
            inputStream = assetManager.open("grille.jpg");
            Temp.grille = BitmapFactory.decodeStream(inputStream);
		}catch (IOException e) {
        	e.printStackTrace();
        }finally {
        	try {
        		if (inputStream != null)
        			inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	public static ArbreGN arbre = new ArbreGN();
		
	public static sexe SelectedSexe = null;	
	public static sexe tampoSexe = null;	
	
	public static Membre Membre = null;
	public static boolean Décédé_éditable = false;	
		
	public static boolean Translation;
	public static float TranslationX = 0;
	public static float TranslationY = 0;
	public static float TrX = 0;
	public static float TrY = 0;	
	public static float InitX = 0;
	public static float InitY = 0;
	
	public static float X;
	public static float Y;
	
	public static Ligne ligne = null;
	public static Point départ = null;
	public static Ligne tampoLigne = null;
	
	public static Membre M1 = null;
	public static Membre M2 = null;
	public static Membre Décédé = null;
	public static Personne personne = null;

	public static String Role1 = null;
	public static String Role2 = null;
	
	public static void viderMémoire() {
		Temp.arbre = new ArbreGN();
		Temp.SelectedSexe = null;
		Temp.tampoSexe = null;
		Temp.Membre = null;
		Temp.Décédé_éditable = false;
		Temp.TranslationX = 0;
		Temp.TranslationY = 0;
		Temp.TrX = 0;
		Temp.TrY = 0;
		Temp.InitX = 0;
		Temp.InitY = 0;
		Temp.ligne = null;
		Temp.départ = null;
		Temp.tampoLigne = null;
		Temp.M1 = null;
		Temp.M2 = null;
		Temp.Décédé = null;
		Temp.personne = null;
	}
	
}
