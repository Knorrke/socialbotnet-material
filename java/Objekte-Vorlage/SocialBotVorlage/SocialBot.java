public class SocialBot {
    private String username;
    private String password;
    private NetzwerkZugriff socialbotnet;

    public SocialBot(String usernameNeu, String passwordNeu) {
        this.username = usernameNeu;
        this.password = passwordNeu;
        this.socialbotnet = new NetzwerkZugriff("https://www.socialbotnet.de");
    }

    public void posten(String nachricht) {
        /**
         * Ergänze hier den Code, damit der Bot im Netzwerk posten kann. 
         */
    }
    
    /**
     * Schreibe weitere Funktionen für den Bot
     */
}
