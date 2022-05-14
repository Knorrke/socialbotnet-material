# /usr/bin/python

from Netzwerkzugriff import *


class Socialbot:
    def __init__(self, username, password):
        """ initialisiere Bot-Objekt mit vorhandenem Usernamen und Passwort """
        self._username = username
        self._password = password
        self._socialbotnet = Netzwerkzugriff("https://www.socialbotnet.de")

    def posten(self, nachricht):
        '''
        Ergänze hier den Code, damit der Bot im Netzwerk posten kann
        '''

    '''
    Schreibe weitere Funktionen für den Bot
    '''
