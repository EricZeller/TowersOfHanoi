import java.io.*;

public class tuermehanoi {
  // Hauptprogramm main  
  
  public static void main (String ars[])throws IOException{
    InputStreamReader Input = new InputStreamReader(System.in);
    BufferedReader DataIn = new BufferedReader(Input);
    
    System.out.println("Bitte Anzahl der Scheiben eingeben:");
    
    int n = 0;
    n = eingabe(DataIn, n);
    
    int gerade = 0;
    gerade = abfrage(n);
    
    verschiebeArt(gerade, n);
  }
  
  //Unterprogramm Abfrage zum prüfen, ob n gerade oder ungerade ist
  public static int abfrage (int pn){
    int pgerade = 0;
    if (pn%2 == 1) {
      pgerade = 0;                                                                                                                                                  
    } else {
      pgerade = 1;       
    } // end of if-else// end of if
    return pgerade;
  }
  
  //Unterprogramm zum festlegen welcher Startstab
  public static void verschiebeArt (int pgerade, int pn){
    if (pgerade == 1) {
      cba(pn);
    } else {
      bca(pn);
    } // end of if-else
  }
       
  public static int obersteScheibe (int i, int x, int pn){
    if (x == 0 && i < pn) {
      i++;
    } // end of if
    return i;
  } 

  public static int vergleiche_abc1 (int x1, int x2, int pn){
    int a = 0;
    
    if (x1 == 0 || x2 == 0) {
      if (x1 < x2) {
        a = x2;
      } // end of if-else
    } else {
      if (x1 > x2) {
        a = x2;
      } // end of if-else
    } // end of if-else 
    return a;
  }
  
  public static int vergleiche_abc2 (int x1, int x2, int pn){
    int b = 0;
    
    if (x1 == 0 || x2 == 0) {
      if (x1 > x2) {
        b = x1;
      }
    } else {
      if (x1 < x2) {
        b = x1;
      }
    } // end of if-else 
    return b;
  }
  
  public static void cba (int pn){
    int [] sA = new int [pn*2];
    int [] sB = new int [pn*2];
    int [] sC = new int [pn*2];
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    
    //Initialisierung
    if (l == 0){
      for (i = 0; i < pn; i++) {       
        sA [i] = i+1;
      }
      
      for (int z = 0;z < pn ;z++ ) {
        ausgabe(pn,sA[z]);
        ausgabe(pn,sB[z]);
        ausgabe(pn,sC[z]);
        System.out.println(" ");
      } // end of for
      System.out.println(" ");
      l++;
    } // end of if
    do {
      if (sA[pn-1] > 0 || sB[pn-1] > 0) {
        i = 0;
        j = 0;
        for (int z = 0;z < pn ;z++) {
          i = obersteScheibe(i, sA[z], pn);
          j = obersteScheibe(j, sB[z], pn);
        } // end of for
        
        if (i > 0) {
          sA[i-1] = vergleiche_abc1 (sA[i], sB[j], pn);
        } // end of if
        
        if (j > 0) {
          sB[j-1] = vergleiche_abc2 (sA[i], sB[j], pn);
        } // end of if
        
        if (i > 0) {
          if (sA[i-1] != 0) {
            sB[j] = 0;
          } else {
            sA[i] = 0;
          } // end of if-else
        } else {
          sA[i] = 0;
        } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
      
      if (sA[pn-1] > 0 || sB[pn-1] > 0) {
        i = 0;
        k = 0;
        for (int z = 0;z < pn ;z++) {
          i = obersteScheibe(i, sA[z], pn);
          k = obersteScheibe(k, sC[z], pn);
        } // end of for
        
        if (i > 0) {
          sA[i-1] = vergleiche_abc1 (sA[i], sC[k], pn);
        } // end of if
        
        if (k > 0) {
          sC[k-1] = vergleiche_abc2 (sA[i], sC[k], pn);
        } // end of if
        
        if (sA[i-1] != 0) {
          sC[k] = 0;
        } else {
          sA[i] = 0;
        } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
      
      if (sA[pn-1] > 0 || sB[pn-1] > 0) {  
        j = 0;
        k = 0;
        for (int z = 0;z < pn ;z++) {
          j = obersteScheibe(j, sB[z], pn);
          k = obersteScheibe(k, sC[z], pn);
        } // end of for
        
        if (j > 0) {
          sB[j-1] = vergleiche_abc1 (sB[j], sC[k], pn);
        } // end of if
        
        if (k > 0) {
          sC[k-1] = vergleiche_abc2 (sB[j], sC[k], pn);
        } // end of if
        
        if (sB[j-1] != 0) {
          sC[k] = 0;
        } else {
          sB[j] = 0;
        } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
    } while (sA[pn-1] > 0 || sB[pn-1] > 0); // end of do-while
  }
  
  public static void bca (int pn){
    int [] sA = new int [pn*2];
    int [] sB = new int [pn*2];
    int [] sC = new int [pn*2];
    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    
    //Initialisierung
    if (l == 0){
      for (i = 0; i < pn; i++) {       
        sA [i] = i+1;
      }
      
      for (int z = 0;z < pn ;z++ ) {
        ausgabe(pn,sA[z]);
        ausgabe(pn,sB[z]);
        ausgabe(pn,sC[z]);
        System.out.println(" ");
      } // end of for
      System.out.println(" ");
      l++;
    } // end of if
    do {
      if (sA[pn-1] > 0 || sB[pn-1] > 0) {
        i = 0;
        k = 0;
        for (int z = 0;z < pn ;z++) {
          i = obersteScheibe(i, sA[z], pn);
          k = obersteScheibe(k, sC[z], pn);
        } // end of for
        
        if (i > 0) {
          sA[i-1] = vergleiche_abc1 (sA[i], sC[k], pn);
        } // end of if
        
        if (k > 0) {
          sC[k-1] = vergleiche_abc2 (sA[i], sC[k], pn);
        } // end of if
        
        if (i>0) {
          if (sA[i-1] != 0) {
          sC[k] = 0;
        } else {
          sA[i] = 0;
        } // end of if-else
        } else {
          sA[i] = 0;
        } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
      
            if (sA[pn-1] > 0 || sB[pn-1] > 0) {
        i = 0;
        j = 0;
        for (int z = 0;z < pn ;z++) {
          i = obersteScheibe(i, sA[z], pn);
          j = obersteScheibe(j, sB[z], pn);
        } // end of for
        
        if (i > 0) {
          sA[i-1] = vergleiche_abc1 (sA[i], sB[j], pn);
        } // end of if
        
        if (j > 0) {
          sB[j-1] = vergleiche_abc2 (sA[i], sB[j], pn);
        } // end of if
        
          if (sA[i-1] != 0) {
            sB[j] = 0;
          } else {
            sA[i] = 0;
          } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
      
      if (sA[pn-1] > 0 || sB[pn-1] > 0) {  
        j = 0;
        k = 0;
        for (int z = 0;z < pn ;z++) {
          j = obersteScheibe(j, sB[z], pn);
          k = obersteScheibe(k, sC[z], pn);
        } // end of for
        
        if (j > 0) {
          sB[j-1] = vergleiche_abc1 (sB[j], sC[k], pn);
        } // end of if
        
        if (k > 0) {
          sC[k-1] = vergleiche_abc2 (sB[j], sC[k], pn);
        } // end of if
        
        if (sB[j-1] != 0) {
          sC[k] = 0;
        } else {
          sB[j] = 0;
        } // end of if-else
        
        for (int z = 0;z < pn ;z++) {
          ausgabe(pn,sA[z]);
          ausgabe(pn,sB[z]);
          ausgabe(pn,sC[z]);
          System.out.println(" ");
        } // end of for
        System.out.println(" ");
      } // end of if
    } while (sA[pn-1] > 0 || sB[pn-1] > 0); // end of do-while
  }
    
   //Unterprogramm Einlesen                                                                                     
  public static int eingabe(BufferedReader DataIn, int pn)throws IOException{                               
    int eingabeLocal;                                                                                       
    
    eingabeLocal = Integer.parseInt(DataIn.readLine());                                                  
    return eingabeLocal;
  }
  
  public static void ausgabe (int pn, int stabwert) {
    if (stabwert == 0) {
      for (int i = pn; i > 0; i--) {
        System.out.print(" ");
      }  
      System.out.print("|");
      for (int i = pn; i > 0; i--) {
        System.out.print(" ");
      }
    } // end of if
    if (stabwert > 0) {
      for (int i = pn - stabwert; i > 0; i--) {
        System.out.print(" ");
      } 
      System.out.print("|");
      for (int i = stabwert * 2 - 1; i > 0; i--) {
        System.out.print("_");
      }
      System.out.print("|");
      for (int i = pn - stabwert; i > 0; i--) {
        System.out.print(" ");
      } 
    } // end of if
  }
}
