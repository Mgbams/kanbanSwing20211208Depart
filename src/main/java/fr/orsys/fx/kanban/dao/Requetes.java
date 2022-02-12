package fr.orsys.fx.kanban.dao;

public class Requetes {

    public static final String AJOUT_COLONNE = "INSERT INTO colonne (nom) VALUES (?)";
    public static final String RECUPERATION_COLONNES = "SELECT id, nom FROM colonne";
    public static final String RECUPERATION_COLONNE_PAR_ID = "SELECT id, nom FROM colonne WHERE id=?";

    public static final String AJOUT_VILLE = "INSERT INTO ville (code_postal, nom, code_insee, complement, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String RECUPERATION_VILLES = "SELECT id, code_postal, nom, code_insee, complement, latitude, longitude FROM ville";
    public static final String RECUPERATION_VILLE_PAR_ID = "SELECT id, code_postal, nom, code_insee, complement, latitude, longitude FROM ville WHERE id=?";
    public static final String RECUPERATION_COUNT_VILLE = "SELECT COUNT(*) FROM ville";
    public static final String RECUPERATION_VILLE_COMMENCANT_PAR_NOM = "SELECT nom FROM ville WHERE nom LIKE '%nom%'";
}   
