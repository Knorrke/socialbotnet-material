#!/usr/bin/python

import requests
import urllib.request

class Netzwerkzugriff:
    def __init__(self, domain):
        '''
        @param domain: Domänenname mit http(s)://
        '''
        self._domain = domain

    def POSTAnfrageSenden(self, url, **pairs):
        '''
        Sendet POST-Anfrage an die angegebene Adresse an den den verbundenen Server.
        @param url: Die relative Adresse auf dem Server, an die die Anfrage geschickt werden soll.
        @param **pairs: beliebige Anzahl Parameter (Schlüssel) mit ihrem Wert
        '''
        try:
            r = requests.post(self._domain + url, data=pairs, proxies=urllib.request.getproxies())
            urlParameter = []
            for key, value in pairs.items():
                urlParameter.append(key + "=" + value)
            print("Sende 'POST'-Anfrage an URL:", url)
            print("POST-Parameter: ", "&".join(urlParameter))
            self._antwortLesen(r)
        except requests.exceptions.MissingSchema:
            print("Fehlerhafte URL " + self._domain + url)
            print("Vielleicht hast du http:// oder https:// vergessen")
        except requests.exceptions.ConnectionError:
            print("Fehlerhafte Domain " + self._domain)
            print("Überprüfe, ob es diese Seite überhaupt gibt.")
        except Exception as e:
            print("Bei der Verbindung ist etwas schief gelaufen.")
            print(e)

    def GETAnfrageSenden(self, url):
        '''
        Sendet die GET-Anfrage an die angegebene Adresse an den verbundenen Server.
        Parameter können mit ? angehängt werden. Bei mehreren Parametern müssen diese durch & verbunden werden.
        Beispiel: /api/posts?sortby=likes&limit=5
        @param url: Die relative Adresse auf dem Server, an die die Anfrage geschickt werden soll.
        '''
        try:
            r = requests.get(self._domain + url, proxies=urllib.request.getproxies())
            print("Sende 'GET'-Anfrage an URL:", url)
            antwort = self._antwortLesen(r)
            return antwort
        except requests.exceptions.MissingSchema:
            print("Fehlerhafte URL " + self._domain + url)
            print("Vielleicht hast du http:// oder https:// vergessen")
        except requests.exceptions.ConnectionError:
            print("Fehlerhafte Domain " + self._domain)
            print("Überprüfe, ob es diese Seite überhaupt gibt.")
        except Exception as e:
            print("Bei der Verbindung ist etwas schief gelaufen.")
            print(e)

    def _antwortLesen(self, req):
        try:
            responseCode = req.status_code
            print("Die Antwort hat den Status:", responseCode)
            req.encoding = req.apparent_encoding
            print("Antwort:", req.text)
            return req.text
        except Exception as e:
            print("Bei der Verbindung ist etwas schief gelaufen.")
            print(e)
            return None
