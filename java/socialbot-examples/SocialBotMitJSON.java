import json.*;

/**
 *  Zum Vergleich wurde hier nochmal der einfache Social Bot implementiert mit der 
 *  Verarbeitung der JSON-Daten (z.B. bei dem Projekt nur mit JSON)
 */
public class SocialBotMitJSON {
    private String username;
    private String password;
    private NetzwerkZugriff socialbotnet;

    public SocialBotMitJSON(String usernameNeu, String passwordNeu) {
        this.username = usernameNeu;
        this.password = passwordNeu;
        this.socialbotnet = new NetzwerkZugriff("https://www.socialbotnet.de");
    }

    public void posten(String nachricht) {
        // Daten müssen zuerst in POST-Anfrage geschrieben werden
        // Für /api/post ist das username, password, und message
        socialbotnet.POSTAnfrageVorbereiten("username", this.username);
        socialbotnet.POSTAnfrageVorbereiten("password", this.password);
        socialbotnet.POSTAnfrageVorbereiten("message", nachricht);
        socialbotnet.POSTAnfrageSenden("/api/post");
    }
    
    public void eigenePinnwandLiken() {
        // Alle Posts auf der eigenen Pinnwand abrufen
        String antwort = socialbotnet.GETAnfrageSenden("/api/pinnwand/"+this.username);
        // Die Antwort ist ein JSON-Array von Posts
        JSONArray posts = new JSONArray(antwort);
        // Iteration über die einzelnen Posts. 
        // Die Methode posts.length() gibt die Länge des Arrays zurück
        for (int i=0; i < posts.length(); i++) {
            // Jeder einzelne Post ist ein JSONObject
            JSONObject post = posts.getJSONObject(i);
            
            // Von dem Post wird die ID als Parameter zum Liken benötigt
            int id = post.getInt("id");
            liken(id);
        }
    }
    
    public void liken(int id) {
        // An /api/like müssen username, password und postid mitgeschickt werden
        socialbotnet.POSTAnfrageVorbereiten("username", this.username);
        socialbotnet.POSTAnfrageVorbereiten("password", this.password);
        socialbotnet.POSTAnfrageVorbereiten("postid", id);
        socialbotnet.POSTAnfrageSenden("/api/like");
    }

}
