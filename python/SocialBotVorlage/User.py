#!/usr/bin/python

class User:
    def __init__(self, id: int, username: str, hobbies: str, about: str):
        """ erzeugt ein User-Objekt, das über Methoden die Informationen zurückgibt

        Bsp.:
        user = User(1, 'max', 'Muster malen', 'Ich bin Durchschnitt.')
        print(user.hobbies) -> Muster malen """
        self._id = id
        self._username = username
        self._hobbies = hobbies
        self._about = about

    @property
    def username(self) -> str:
        return self._username

    @property
    def id(self) -> int:
        return self._id

    @property
    def hobbies(self) -> str:
        return self._hobbies

    @property
    def about(self) -> str:
        return self._about
