/**
 * Dies ist ein einfacher Social Bot, der posten und alle Beiträge auf seiner eigene Pinnwand Liken kann.
 */
public class EinfacherSocialBot {
    private String username;
    private String password;
    private NetzwerkZugriff socialbotnet;

    public EinfacherSocialBot(String usernameNeu, String passwordNeu) {
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
        // Mit dem vorgefertigten JSON-Parser zu gewohnten Objekten umwandeln.
        Post[] posts = AntwortParser.zuPostArray(antwort);
        // Iteration über die einzelnen Posts
        for (int i=0; i<posts.length; i++) {
            Post post = posts[i];
            
            // Von dem Post wird die ID als Parameter zum Liken benötigt
            int id = post.getId();
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
