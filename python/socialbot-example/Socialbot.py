# /usr/bin/python

from Netzwerkzugriff import *


class Socialbot:
    def __init__(self, username, password):
        """ initialisiere Bot-Objekt mit vorhandenem Usernamen und Passwort """
        self._username = username
        self._password = password
        self._socialbotnet = Netzwerkzugriff("https://www.socialbotnet.de")
        print("Bot angemeldet")

    def posten(self, nachricht):
        self._socialbotnet.POSTAnfrageSenden("/api/post", username=self._username, password=self._password,
                                             message=nachricht)
        '''
        Ergänze hier den Code, damit der Bot im Netzwerk posten kann
        '''

    def likePost(self, id):
        self._socialbotnet.POSTAnfrageSenden("/api/like", username=self._username, password=self._password,
                                             postid=str(id))

    def postPokemonTrivia(self, poke_id):
        pokemon = Netzwerkzugriff(f'https://pokeapi.co/api/v2')
        species_response = pokemon.GETAnfrageSenden(f'/pokemon-species/{poke_id}')
        stats_response = pokemon.GETAnfrageSenden(f'/pokemon/{poke_id}')
        species = json.loads(species_response)
        stats = json.loads(stats_response)
        for lang in species["names"]:
            if lang["language"]["name"] == "de":
                name = lang["name"]
        height = stats["height"] / 10
        weight = stats["weight"] / 10
        self.posten(f'Wusstest Du, dass {name} {height} m groß ist und {weight} kg wiegt?')

    def spamPokemonTrivia(self):
        for i in range(1, 251):
            self.postPokemonTrivia(i)

    '''
    Schreibe weitere Funktionen für den Bot
    '''
