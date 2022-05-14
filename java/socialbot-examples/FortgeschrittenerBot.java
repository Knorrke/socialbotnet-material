public class FortgeschrittenerBot
{
    private String username;
    private String password;
    private NetzwerkZugriff socialbotnet;
    
    private String[] nachrichten;
    private String[] nachrichtenAnUser;
    public FortgeschrittenerBot(String usernameNeu, String passwordNeu) {
        this.username = usernameNeu;
        this.password = passwordNeu;
        this.socialbotnet = new NetzwerkZugriff("https://www.socialbotnet.de");
        this.nachrichten = new String[] {
            "Mir ist langweilig",
            "Hat jemand eine gute Filmempfehlung?",
            "Das Wetter ist heute ja nicht so toll...",
            "Unglaublich..."
        };

        this.nachrichtenAnUser = new String[] {
            "Hey %user%, hast du einen Instagram Account?",
            "Hallo %user%, ich sehe dein Hobby ist %hobbies%. Lust das mal zusammen zu machen?",
            "Was machst du heute, %user%?"
        };
    }

    public void starten() {
        int optionen = 3;
        // 20 Posts, aber man könnte auch while(true) machen
        for(int i=0; i<20; i++) {
            int rand = zufallszahl(optionen);
            switch(rand) {
                case 0: anZufaelligenUserPosten(nachrichtenAnUser[zufallszahl(nachrichtenAnUser.length)]);
                break;
                case 1: posten(nachrichten[zufallszahl(nachrichten.length)]);
                break;
                case 2: zufaelligenBeitragKopieren();
                break;
            }

            try{
                // zwischen 20 und 40 Sekunden warten
                Thread.sleep(20000 + zufallszahl(20000));
            } catch(Exception ignore){}
        }
    }

    public int zufallszahl(int grenze) {
        return (int) (Math.random()*grenze);
    }
    
    public void posten(String nachricht) {
        socialbotnet.POSTAnfrageVorbereiten("username",username);
        socialbotnet.POSTAnfrageVorbereiten("password",password);
        socialbotnet.POSTAnfrageVorbereiten("message",nachricht);
        socialbotnet.POSTAnfrageSenden("/api/post");
    }

    public void anUserPosten(String andererUser, String nachricht) {
        socialbotnet.POSTAnfrageVorbereiten("username",username);
        socialbotnet.POSTAnfrageVorbereiten("password",password);
        socialbotnet.POSTAnfrageVorbereiten("message",nachricht);
        socialbotnet.POSTAnfrageSenden("/api/post/" + andererUser);
    }
    
    public void anZufaelligenUserPosten(String nachricht) {
        String antwort = socialbotnet.GETAnfrageSenden("/api/users");
        User[] users = AntwortParser.zuUserArray(antwort);
        User zufaelligerUser = users[zufallszahl(users.length)];
        
        String username = zufaelligerUser.getUsername();
        String hobbies = zufaelligerUser.getHobbies();
        String about = zufaelligerUser.getAbout();

        if((nachricht.contains("%hobbies%") && hobbies.equals(""))
        || (nachricht.contains("%about%") && about.equals(""))) {
            return;
        }
        
        nachricht = nachricht.replaceAll("%user%", username);
        nachricht = nachricht.replaceAll("%hobbies%", hobbies);
        nachricht = nachricht.replaceAll("%about%", about);

        anUserPosten(nachricht, username);
    }
    
    public void zufaelligenBeitragKopieren() {
        String antwort = socialbotnet.GETAnfrageSenden("/api/posts");
        Post[] posts = AntwortParser.zuPostArray(antwort);
        Post zufallsPost = posts[zufallszahl(posts.length)];
        
        String nachricht = zufallsPost.getMessage();
        posten(nachricht);
    }
}
