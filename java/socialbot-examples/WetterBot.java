import json.*;
/**
 * Dieser Bot kommuniziert mit der API von openweathermap.org um passend zu dem aktuellen Wetter in M�nchen zu posten.
 * Der API Schl�ssel (Siehe Paramater appid) kann auf api.openweathermap.org kostenlos beantragt werden.
 */
public class WetterBot {
    private String username;
    private String password;
    private NetzwerkZugriff socialbotnet;

    // erh�ltlich bei api.openweathermap.org (kostenlos)
    private String appid = ""; 
    
    private String[] nachrichtenSehrKalt = {
            "Heute hats doch bestimmt Minusgrade",
            "Bei dem Wetter geh ich nicht nach drausen",
        };
    private String[] nachrichtenKalt = {
            "-.-",
            "Ich sp�re eine Kaltwetterfront auf uns zukommen",
            "Wann wird es endlich wieder w�rmer",
        };
    private String[] nachrichtenWarm = {
            "Ich glaube, heute ist es w�rmer :-)",  
            "Heute ist es sch�n warm!",
        };
    private String[] nachrichtenHeiss = {
            "VIEEEEL zu hei� heute! Ich schwitz schon im Stehen.",
            "Heute w�re eine Abk�hlung gut... Vielleicht gehe ich sp�ter noch ins Schwimmbad"
        };

    public WetterBot(String usernameNeu, String passwordNeu) {
        this.username = usernameNeu;
        this.password = passwordNeu;
        this.socialbotnet = new NetzwerkZugriff("https://www.socialbotnet.de");
    }

    public void posten(String nachricht) {
        socialbotnet.POSTAnfrageVorbereiten("username", this.username);
        socialbotnet.POSTAnfrageVorbereiten("password", this.password);
        socialbotnet.POSTAnfrageVorbereiten("message", nachricht);
        socialbotnet.POSTAnfrageSenden("/api/post");
    }

    public double temperaturAbrufen() {
        NetzwerkZugriff openWeather = new NetzwerkZugriff(
            "https://api.openweathermap.org/data/2.5"
        );
        String antwort = openWeather.GETAnfrageSenden(
            "/weather?q=munich&units=metric&appid=" + appid
        );
        JSONObject antwortJSON = new JSONObject(antwort);
        JSONObject main = antwortJSON.getJSONObject("main");
        double temperatur = main.getDouble("temp");
        return temperatur;
    }

    public void wetterKommentieren() {
        double temperatur = temperaturAbrufen();
        String[] moeglicheNachrichten;
        if (temperatur < 5) {
            moeglicheNachrichten = nachrichtenSehrKalt;
        } else if (temperatur < 15) {
            moeglicheNachrichten = nachrichtenKalt;
        } else if (temperatur < 30) {
            moeglicheNachrichten = nachrichtenWarm;
        } else {
            moeglicheNachrichten = nachrichtenHeiss;
        }

        int zufall = zufallsZahl(0, moeglicheNachrichten.length);
        posten(moeglicheNachrichten[zufall]);
    }

    private int zufallsZahl(int untereGrenze, int obereGrenze) {
        int zufall = (int) (Math.random()*(obereGrenze - untereGrenze));
        return zufall + untereGrenze;
    }
}
