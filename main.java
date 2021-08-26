/**
 * Cette classe represente un programme permettant a la compagnie XXX de fabriquer
 * des codes numeriques de ses employes a partir de leurs informations personnelles (la
 * dates de naissance et le sexe).
 *
 * @author XRF
 * @version 2020-10-01
 */

public class GestionEmploye {

    //Declaration de constantes
    public static final String MSG_PRESENTATION = "Ce programme genere des codes numeriques pour les employes de la compagnie PERSO INC.\n";
    public static final String MSG_ANNEE_NAISSANCE = "Entrez l'annee de naissance (Entre 1950 et 2002 inclusivement): ";
    public static final String MSG_MOIS_NAISSANCE = "Entrez le mois de naissance (Celui-ci doit etre entre 1 et 12 inclusivement): ";
    public static final String MSG_JOUR_NAISSANCE = "Entrez le jour de naissance (Entre 1 et %s inclusivement): %n";
    public static final String MSG_SEXE = "Veuillez indiquer votre sexe ('f' ou ‘F’ pour «femme» et 'h' ou ‘H’ pour «homme»): ";
    public static final String MSG_CODE_BISSEXTILE = "Le nombre de codes generes a partir d'une annee bissextile: ";
    public static final String MSG_ERREUR = "Erreur. Entree invalide, veuillez recommencer!";
    public static final String MSG_FIN = "FIN NORMALE DU PROGRAMME";

    public static void main(String[] args) {

        //Declaration de variables
        int nbrJourMax = 0;                 //Le nombre maximal de jours dans un mois
        int compteurAnneeBissextile = 0;    //Le nombre de codes generes a partir d’une annee bissextile
        int compteurFemme = 0;              //Le nombre total d’employes de sexe feminin
        int compteurHomme = 0;              //Le nombre total d’employes de sexe masculin
        boolean veutContinuer = true;       //L’intention de l'utilisateur a poursuivre le programme
        boolean estEntreeCorrecte = false;  //La validite de valeur saisie

        //Presenter le logiciel
        System.out.println(MSG_PRESENTATION);

        while (veutContinuer) {

            //Declaration de variables
            int anneeNaissance = 0;
            int moisNaissance = 0;
            int jourNaissance = 0;
            String sexe = "";

            //Demander et lire l’annee de naissance
            estEntreeCorrecte = false;

            while (veutContinuer && !estEntreeCorrecte) {
                System.out.println(MSG_ANNEE_NAISSANCE);
                anneeNaissance = Clavier.lireInt();

                if (anneeNaissance == 0) {   //Si 0, fin du programme
                    veutContinuer = false;

                } else if (anneeNaissance >= 1950 && anneeNaissance <= 2002) {
                    estEntreeCorrecte = true;
                    if (anneeNaissance % 400 == 0) {
                        compteurAnneeBissextile++;
                    }

                } else {
                    System.out.println(MSG_ERREUR);
                }
            }

            //Demander et lire le mois de naissance
            estEntreeCorrecte = false;

            while (veutContinuer && !estEntreeCorrecte) {
                System.out.println(MSG_MOIS_NAISSANCE);
                moisNaissance = Clavier.lireInt();

                if (moisNaissance == 0) {   //Si 0, fin du programme
                    veutContinuer = false;

                } else if (moisNaissance >= 1 && moisNaissance <= 12) {
                    estEntreeCorrecte = true;

                    //Determiner le nombre maximal de jours dans un mois
                    if (moisNaissance == 1 || moisNaissance == 3 || moisNaissance == 5 || moisNaissance == 7
                            || moisNaissance == 8 || moisNaissance == 10 || moisNaissance == 12) {
                        nbrJourMax = 31;

                    } else if (moisNaissance == 2) {
                        if (anneeNaissance % 400 == 0) {
                            nbrJourMax = 29;

                        } else {
                            nbrJourMax = 28;
                        }

                    } else {
                        nbrJourMax = 30;
                    }

                } else {
                    System.out.println(MSG_ERREUR);
                }
            }

            //Demander et lire le jour de naissance
            estEntreeCorrecte = false;

            while (veutContinuer && !estEntreeCorrecte) {

                System.out.printf(MSG_JOUR_NAISSANCE, nbrJourMax);
                jourNaissance = Clavier.lireInt();

                if (jourNaissance == 0) {   //Si 0, fin du programme
                    veutContinuer = false;

                } else if (jourNaissance >= 1 && jourNaissance <= nbrJourMax) {
                    estEntreeCorrecte = true;

                } else {
                    System.out.println(MSG_ERREUR);
                }
            }

            //Demander et lire le sexe
            estEntreeCorrecte = false;

            while (veutContinuer && !estEntreeCorrecte) {
                System.out.println(MSG_SEXE);
                sexe = Clavier.lireString();

                if (sexe.equals("0")) {   //Si 0, fin du programme
                    veutContinuer = false;

                } else if (sexe.equalsIgnoreCase("f")) {
                    compteurFemme++;
                    estEntreeCorrecte = true;

                } else if (sexe.equalsIgnoreCase("h")) {
                    compteurHomme++;
                    estEntreeCorrecte = true;

                } else {
                    System.out.println(MSG_ERREUR);
                }
            }

            //Calculer les 9 chiffres du code numerique
            if (anneeNaissance > 0 && moisNaissance > 0 && jourNaissance > 0 && veutContinuer) {

                //Declaration de variables
                int c1;     //Le 1er chiffre du code numerique
                int c2;     //Le 2e chiffre du code numerique
                int c3;     //Le 3e chiffre du code numerique
                int c4;     //Le 4e chiffre du code numerique
                int c5;     //Le 5e chiffre du code numerique
                int c6;     //Le 6e chiffre du code numerique
                int c7;     //Le 7e chiffre du code numerique
                int c8;     //Le 8e chiffre du code numerique
                int c9;     //Le 9e chiffre du code numerique

                //Calculer c1
                c1 = anneeNaissance / 10 % 10;   //Avant dernier chiffre de l'annee de naissance

                //Calculer c2
                c2 = anneeNaissance % 10;   //Dernier chiffre de l'annee de naissance

                //Calculer c3
                if (sexe.equalsIgnoreCase("f")) {
                    c3 = (moisNaissance + 70) / 10;   //1er chiffre du mois + 7 s'il s'agit d'une femme

                } else if (moisNaissance < 10) {   //0 s'il ne s'agit pas d'une femme et le mois saisi ...
                    c3 = 0;                        //se situe entre janvier et septembre (inclusivement)

                } else {   //1er chiffre du mois s'il s'agit d'un homme
                    c3 = 1;
                }

                //Calculer c4
                c4 = moisNaissance % 10;   //2e chiffre du mois

                //Calculer c5
                if (jourNaissance < 10) {   //1re chiffre du jour
                    c5 = 0;

                } else {
                    c5 = jourNaissance / 10;
                }

                //Calculer c6
                c6 = jourNaissance % 10;   //2e chiffre du jour

                //Calculer c7
                c7 = (c1 + 7 * c3 + 5 * c5) % 10;   //Ce qui reste apres division par 10 de c1 + 7 * c3 + 5 * c5

                //Calculer c8
                c8 = 9 * (c2 + c4 + 2 * c6) % 10;   //Ce qui reste apres division par 10 de 9 * ( c2 + c4 + 2 * c6 )

                //Declaration de variable
                int somme;   //Somme des 8 premiers chiffres du code numerique

                somme = c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8;

                //Calculer c9
                c9 = (10 - (somme % 10)) % 10;  //Chiffre entre 0 et 9 tel que la somme des 9 chiffres ...
                                                //est un multiple de 10

                //Afficher le code numerique genere
                System.out.println("\nVoici votre code: " + c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9);
            }
        }

        //Statistiques

        //Declaration de variable
        int compteurEmploye = compteurFemme + compteurHomme;   //Le nombre de codes generes

        if (compteurEmploye > 0) {

            //Nombre de codes generes
            System.out.println("\nLe nombre de codes generes: " + compteurEmploye);

            //Nombre de codes generes a partir d'une annee bissextile
            System.out.println(MSG_CODE_BISSEXTILE + compteurAnneeBissextile);

            //Pourcentage de femmes
            float pourcentageFemme = (float) compteurFemme / (float) compteurEmploye * 100;
            System.out.println("Le pourcentage de femmes: "
                    + String.format("%.2f", pourcentageFemme) + "%");   //Garder deux chiffres apres la virgule

            //Pourcentage d'hommes
            float pourcentageHomme = (float) compteurHomme / (float) compteurEmploye * 100;
            System.out.println("Le pourcentage d'hommes: "
                    + String.format("%.2f", pourcentageHomme) + "%");   //Garder deux chiffres apres la virgule

            //Declarer la fin du programme
            System.out.println("\n" + MSG_FIN);
        }
    }
}
